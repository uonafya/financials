package org.openmrs.module.financials.reports;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.calculation.DmHtnDiagnosisCalculation;
import org.openmrs.module.financials.reporting.converter.HtnDmDataConverter;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.financials.reports.SetupMalariaReport.DATE_FORMAT;

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
		dsd.addRowFilter(ReportUtils.map(
		    allDailyPatientsCohort(Context.getConceptService().getConceptByUuid("160250AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
		            .getConceptId(), Arrays.asList(119481, 117399)), "startDate=${startDate},endDate=${endDate}"));
		
		DataConverter formatter = new ObjectFormatter("{familyName}, {givenName}");
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
		        "164181AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
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
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "74eb8e8d-d078-4fa3-8973-2d710d8f46df")), "onOrAfter=${startDate},onOrBefore=${endDate+23h+59m}",
		    new HtnDmDataConverter(1));
		dsd.addColumn(
		    "dm",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "74eb8e8d-d078-4fa3-8973-2d710d8f46df")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new HtnDmDataConverter(2));
		dsd.addColumn(
		    "rbs",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "887AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "fbs",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "160912AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn(
		    "hba1c",
		    FinancialsUtils.getObservation(Context.getConceptService().getConceptByUuid(
		        "160912AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new ObsValueConverter());
		dsd.addColumn("diagnosis", new CalculationDataDefinition("diagnosis", new DmHtnDiagnosisCalculation()), "",
		    new CalculationResultConverter());
		return dsd;
	}
	
	private EncounterQuery allDailyPatientsCohort(int questionConceptId, List<Integer> answers) {
		String codedValues = StringUtils.join(answers, ',');
		SqlEncounterQuery cd = new SqlEncounterQuery();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Active screened for DM and HTN Patients from MOPC");
		cd.setQuery("SELECT e.encounter_id FROM encounter e  INNER JOIN patient p ON p.patient_id=e.patient_id inner join obs o ON e.encounter_id=o.encounter_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate "
		        + " AND e.voided = 0 AND o.voided = 0  AND o.concept_id IN("
		        + questionConceptId
		        + ") AND o.value_coded IN("
		        + codedValues + ")");
		return cd;
	}
	
}
