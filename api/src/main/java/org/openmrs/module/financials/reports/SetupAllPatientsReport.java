package org.openmrs.module.financials.reports;

import org.openmrs.PatientIdentifierType;
import org.openmrs.module.financials.reporting.calculation.PatientIdentifierCalculation;
import org.openmrs.module.financials.reporting.calculation.VillageAndLandmarkCalculation;
import org.openmrs.module.financials.reporting.converter.EncounterDateConverter;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.DateConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.EncountersForPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.data.visit.definition.SqlVisitDataDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Builds({ "ehraddons.common.report.allPatients" })
public class SetupAllPatientsReport extends AbstractHybridReportBuilder {
	
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	private CommonDatasetDefinition commonDatasetDefinition;
	
	@Autowired
	public SetupAllPatientsReport(CommonDatasetDefinition commonDatasetDefinition) {
		this.commonDatasetDefinition = commonDatasetDefinition;
	}
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor hybridReportDescriptor,
	        PatientDataSetDefinition patientDataSetDefinition) {
		return null;
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	@Override
	protected void addColumns(HybridReportDescriptor report, PatientDataSetDefinition dsd) {
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		
		PatientDataSetDefinition allVisits = allPatients();
		allVisits.addParameter(new Parameter("startDate", "Start Date", Date.class));
		allVisits.addParameter(new Parameter("endDate", "End Date Date", Date.class));
		report.setBaseCohortDefinition(ReportUtils.map(allPatientsCohort(), "startDate=${startDate},endDate=${endDate+23h}"));
		
		return Arrays.asList(
		    ReportUtils.map((DataSetDefinition) allVisits, "startDate=${startDate},endDate=${endDate+23h}"),
		    ReportUtils.map(commonDatasetDefinition.getFacilityMetadata(), ""));
	}
	
	protected PatientDataSetDefinition allPatients() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("all");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		PatientIdentifierType upn = MetadataUtils.existing(PatientIdentifierType.class,
		    CommonMetadata._PatientIdentifierType.PATIENT_CLINIC_NUMBER);
		DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
		DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(
		        upn.getName(), upn), identifierFormatter);
		
		DataConverter formatter = new ObjectFormatter("{familyName}, {givenName} {middleName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), formatter);
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Identifier", new CalculationDataDefinition("Identifier", new PatientIdentifierCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("Age", new AgeDataDefinition(), "", null);
		dsd.addColumn("vDate", getEncounterDate(), "onOrAfter=${startDate},onOrBefore=${endDate}",
		    new EncounterDateConverter());
		return dsd;
	}
	
	protected CohortDefinition allPatientsCohort() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Active Patients");
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate AND p.voided=0 AND e.voided=0");
		return cd;
	}
	
	private DataDefinition getEncounterDate() {
		EncountersForPatientDataDefinition dsd = new EncountersForPatientDataDefinition();
		dsd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		dsd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		dsd.setWhich(TimeQualifier.FIRST);
		return dsd;
	}
}
