package org.openmrs.module.financials.reports;

import org.openmrs.PatientIdentifierType;
import org.openmrs.module.financials.reporting.calculation.VillageAndLandmarkCalculation;
import org.openmrs.module.financials.reporting.converter.EncounterDateConverter;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.TelephoneNumberCalculation;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.kenyaemr.reporting.data.converter.IdentifierConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
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

import static org.openmrs.module.financials.reports.SetupMalariaReport.DATE_FORMAT;

@Component
@Builds({ "ehraddons.common.report.512" })
public class SetupFpRegisterReport extends AbstractHybridReportBuilder {
	
	private CommonDatasetDefinition commonDatasetDefinition;
	
	@Autowired
	public SetupFpRegisterReport(CommonDatasetDefinition commonDatasetDefinition) {
		this.commonDatasetDefinition = commonDatasetDefinition;
	}
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor hybridReportDescriptor,
	        PatientDataSetDefinition patientDataSetDefinition) {
		return null;
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		PatientDataSetDefinition dsd = getFamilyPlanningRegister();
		dsd.setName("fpr");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		//report.setBaseCohortDefinition(ReportUtils.map(getRadiologyEncounter(), "startDate=${startDate},endDate=${endDate}"));
		
		return Arrays.asList(ReportUtils.map((DataSetDefinition) dsd, "startDate=${startDate},endDate=${endDate}"));
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	private PatientDataSetDefinition getFamilyPlanningRegister() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.setName("fpr");
		//dsd.addRowFilter(getRadiologyEncounter(), "startDate=${startDate},endDate=${endDate+23h}");
		DataConverter nameFormatter = new ObjectFormatter("{familyName}, {givenName}, {middleName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);
		PatientIdentifierType opdNumber = MetadataUtils.existing(PatientIdentifierType.class,
		    CommonMetadata._PatientIdentifierType.PATIENT_CLINIC_NUMBER);
		DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(
		        opdNumber.getName(), opdNumber), new IdentifierConverter());
		
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("identifier", identifierDef, "");
		//dsd.addColumn("Date", getEncounterDate(), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		//new EncounterDateConverter());
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("age", new AgeDataDefinition(), "");
		dsd.addColumn("village", new CalculationDataDefinition("village", new VillageAndLandmarkCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("telephone", new CalculationDataDefinition("telephone", new TelephoneNumberCalculation()), "",
		    new CalculationResultConverter());
		return dsd;
		
	}
}
