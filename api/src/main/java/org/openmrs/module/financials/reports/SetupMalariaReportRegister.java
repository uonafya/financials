package org.openmrs.module.financials.reports;

import org.openmrs.EncounterType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.reporting.calculation.PatientIdentifierCalculation;
import org.openmrs.module.financials.reporting.converter.ObsCommentsConverter;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.hiv.CountyAddressCalculation;
import org.openmrs.module.kenyaemr.calculation.library.hiv.SubCountyAddressCalculation;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.DateConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.converter.ObsValueConverter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.SqlPatientDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ObsForPersonDataDefinition;
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

import static org.openmrs.module.financials.reports.SetupANCRegisterReport.ENC_DATE_FORMAT;

@Component
@Builds({ "financials.common.report.malaria" })
public class SetupMalariaReportRegister extends AbstractHybridReportBuilder {
	
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	private final CommonDatasetDefinition commonDatasetDefinition;
	
	@Autowired
	public SetupMalariaReportRegister(CommonDatasetDefinition commonDatasetDefinition) {
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
		
		report.setBaseCohortDefinition(ReportUtils.map(allMalariaPatientsCohort(),
		    "startDate=${startDate},endDate=${endDate+23h}"));
		return Arrays.asList(ReportUtils.map(allPatients(), "startDate=${startDate},endDate=${endDate+23h}"));
	}
	
	protected DataSetDefinition allPatients() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("mal");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addSortCriteria("vDate", SortCriteria.SortDirection.ASC);
		
		DataConverter formatter = new ObjectFormatter("{familyName}, {middleName} {givenName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), formatter);
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("vDate", getEncounterDate(), "startDate=${startDate},endDate=${endDate}");
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Identifier", new CalculationDataDefinition("Identifier", new PatientIdentifierCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("Age", new AgeDataDefinition(), "", null);
		dsd.addColumn("SubCounty", new CalculationDataDefinition("SubCounty", new SubCountyAddressCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("County", new CalculationDataDefinition("County", new CountyAddressCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("Results", new ObsForPersonDataDefinition("Results", TimeQualifier.LAST, Context.getConceptService()
		        .getConceptByUuid("32AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), null, null), "", new ObsValueConverter());
		dsd.addColumn("Comments", new ObsForPersonDataDefinition("Comments", TimeQualifier.LAST, Context.getConceptService()
		        .getConceptByUuid("32AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), null, null), "", new ObsCommentsConverter());
		dsd.addColumn("Onset Date", new ObsForPersonDataDefinition("Onset Date", TimeQualifier.LAST, Context
		        .getConceptService().getConceptByUuid("164428AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), null, null), "",
		    new ObsCommentsConverter());
		return dsd;
	}
	
	protected CohortDefinition allMalariaPatientsCohort() {
		EncounterType labEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "11d3f37a-f282-11ea-a825-1b5b1ff1b854");
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Active Patients");
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN encounter_type et ON et.encounter_type_id=e.encounter_type "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id "
		        + " WHERE e.encounter_datetime BETWEEN :startDate AND :endDate AND o.concept_id IN(32,1643) "
		        + " AND o.value_coded IS NOT NULL " + " AND et.encounter_type_id=" + labEncounterType.getEncounterTypeId());
		return cd;
	}
	
	private DataDefinition getEncounterDate() {
		SqlPatientDataDefinition dsd = new SqlPatientDataDefinition();
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End  Date", Date.class));
		dsd.setName("Encounter date");
		dsd.setQuery("SELECT p.patient_id, MIN(e.encounter_datetime) FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " WHERE e.encounter_datetime BETWEEN :startDate AND :endDate AND p.voided=0 AND e.voided=0 GROUP BY p.patient_id");
		return dsd;
	}
}
