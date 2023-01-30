package org.openmrs.module.financials.reports;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.diagnosis.lists.DiagnosisLists;
import org.openmrs.module.financials.reporting.converter.DiagnosisDataConverter;
import org.openmrs.module.financials.reporting.converter.ObjectCounterConverter;
import org.openmrs.module.financials.reporting.converter.VisitTypeConverter;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.hiv.BMIAtLastVisitCalculation;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.DateConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.converter.ObsValueConverter;
import org.openmrs.module.reporting.data.encounter.definition.EncounterDatetimeDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.SqlPatientDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.EncounterDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.query.encounter.definition.EncounterQuery;
import org.openmrs.module.reporting.query.encounter.definition.SqlEncounterQuery;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.financials.reports.SetupMalariaReportRegister.DATE_FORMAT;

@Component
@Builds({ "financials.common.report.222" })
public class SetupMoh222Register extends AbstractHybridReportBuilder {
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor hybridReportDescriptor,
	        PatientDataSetDefinition patientDataSetDefinition) {
		return null;
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		return Arrays.asList(ReportUtils.map(dailyRegister(), "startDate=${startDate},endDate=${endDate+23h}"));
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	private DataSetDefinition dailyRegister() {
		EncounterDataSetDefinition dsd = new EncounterDataSetDefinition();
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.setName("daily");
		PatientIdentifierType upn = MetadataUtils.existing(PatientIdentifierType.class,
		    CommonMetadata._PatientIdentifierType.OPENMRS_ID);
		DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
		DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(
		        upn.getName(), upn), identifierFormatter);
		dsd.addRowFilter(ReportUtils.map(allDailyPatientsCohort(), "startDate=${startDate},endDate=${endDate+23h}"));
		
		DataConverter formatter = new ObjectFormatter("{familyName}, {givenName} {middleName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), formatter);
		dsd.addColumn("count", new PersonIdDataDefinition(), "", new ObjectCounterConverter());
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Identifier", identifierDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("Age", new AgeDataDefinition(), "", null);
		dsd.addColumn("EncounterDate", new EncounterDatetimeDataDefinition(), "", new DateConverter(DATE_FORMAT));
		dsd.addColumn(
		    "visit",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "81f2e941-d724-4794-98bf-8764b593c838")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new VisitTypeConverter());
		dsd.addColumn(
		    "weight",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "5089AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "height",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "5090AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn("bmi", new CalculationDataDefinition("bmi", new BMIAtLastVisitCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn(
		    "wc",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "163080AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "systolic",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "5085AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "diastolic",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "5086AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "htn",
		    FinancialsUtils.getObservation(
		        Context.getConceptService().getConceptByUuid("160250AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        DiagnosisLists.getHypertensionConceptList()), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "dm",
		    FinancialsUtils.getObservation(
		        Context.getConceptService().getConceptByUuid("160250AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        DiagnosisLists.getDiabetesListConcepts()), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "rbs",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "d0d107f7-9452-4129-a209-b9a4d1b46d4a")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "fbs",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "160912AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "hba1c",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "159644AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn("DiagnosisType", getFinalOrProvisionalDiagnosis(), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new DiagnosisDataConverter());
		return dsd;
	}
	
	private EncounterQuery allDailyPatientsCohort() {
		List<Integer> allDiagnosis = new ArrayList<Integer>(DiagnosisLists.getDiabetesList());
		allDiagnosis.addAll(DiagnosisLists.getHypertensionList());
		String listToString = StringUtils.join(allDiagnosis, ",");
		SqlEncounterQuery cd = new SqlEncounterQuery();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Active screened for DM and HTN Patients from MOPC");
		cd.setQuery("SELECT e.encounter_id FROM encounter e  INNER JOIN patient p ON p.patient_id=e.patient_id inner join obs o ON e.encounter_id=o.encounter_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate "
		        + " AND e.voided = 0 AND o.voided = 0  AND o.concept_id IN("
		        + 160250
		        + ","
		        + 160249
		        + ")"
		        + " AND o.value_coded IN(" + listToString + ")");
		return cd;
	}
	
	private DataDefinition getFinalOrProvisionalDiagnosis() {
		SqlPatientDataDefinition definition = new SqlPatientDataDefinition();
		definition.setName("Final and Provisional diagnosis");
		definition.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		definition.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		definition
		        .setSql("SELECT p.patient_id,o.concept_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id INNER JOIN obs o ON e.encounter_id=o.encounter_id "
		                + " WHERE p.voided= 0 AND e.voided= 0 AND o.voided=0 AND e.encounter_datetime BETWEEN :onOrAfter AND :onOrBefore AND o.concept_id IN("
		                + 160250 + "," + 160249 + ")");
		
		return definition;
	}
	
}
