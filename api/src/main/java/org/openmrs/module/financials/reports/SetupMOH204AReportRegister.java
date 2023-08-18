package org.openmrs.module.financials.reports;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.reporting.calculation.*;
import org.openmrs.module.financials.reporting.converter.*;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.TelephoneNumberCalculation;
import org.openmrs.module.kenyaemr.calculation.library.hiv.BMIAtLastVisitCalculation;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.converter.ObsValueConverter;
import org.openmrs.module.reporting.data.patient.definition.SqlPatientDataDefinition;
import org.openmrs.module.reporting.data.person.definition.*;
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

import static org.openmrs.module.financials.reports.SetupMalariaReportRegister.DATE_FORMAT;

@Component
@Builds({ "financials.common.report.204A" })
public class SetupMOH204AReportRegister extends AbstractHybridReportBuilder {
	
	private CommonDatasetDefinition commonDatasetDefinition;
	
	@Autowired
	public SetupMOH204AReportRegister(CommonDatasetDefinition commonDatasetDefinition) {
		this.commonDatasetDefinition = commonDatasetDefinition;
	}
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor hybridReportDescriptor,
	        PatientDataSetDefinition patientDataSetDefinition) {
		return null;
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		EncounterType opd = Context.getEncounterService().getEncounterTypeByUuid("ba45c278-f290-11ea-9666-1b3e6e848887");
		PatientDataSetDefinition dsd = ipdList();
		dsd.setName("opru");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		report.setBaseCohortDefinition(ReportUtils.map(allChildrenDiagnosisPatientsCohort(opd.getEncounterTypeId()),
		    "startDate=${startDate},endDate=${endDate+23h}"));
		
		return Arrays.asList(ReportUtils.map((DataSetDefinition) dsd, "startDate=${startDate},endDate=${endDate+23h}"),
		    ReportUtils.map(commonDatasetDefinition.getFacilityMetadata(), ""));
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	private PatientDataSetDefinition ipdList() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("opru");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addSortCriteria("Date", SortCriteria.SortDirection.ASC);
		
		DataConverter nameFormatter = new ObjectFormatter("{familyName}, {givenName}, {middleName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);
		
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("identifier", getRevisit("ID"), "endDate=${endDate+23h}", new CalculationResultConverter());
		dsd.addColumn("STS", getRevisit("ST"), "endDate=${endDate+23h}", new CalculationResultConverter());
		dsd.addColumn("Date", getEncounterDate(), "startDate=${startDate},endDate=${endDate}");
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("age", new AgeDataDefinition(), "");
		dsd.addColumn("village", new CalculationDataDefinition("village", new VillageAndLandmarkCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("telephone", new CalculationDataDefinition("telephone", new TelephoneNumberCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn(
		    "weight",
		    getObservation(Context.getConceptService().getConceptByUuid("5089AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn(
		    "height",
		    getObservation(Context.getConceptService().getConceptByUuid("5090AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn(
		    "temp",
		    getObservation(Context.getConceptService().getConceptByUuid("5088AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn(
		    "muac",
		    getObservation(Context.getConceptService().getConceptByUuid("1343AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn(
		    "bps",
		    getObservation(Context.getConceptService().getConceptByUuid("5085AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn(
		    "bpd",
		    getObservation(Context.getConceptService().getConceptByUuid("5086AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn(
		    "rr",
		    getObservation(Context.getConceptService().getConceptByUuid("5242AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn(
		    "pr",
		    getObservation(Context.getConceptService().getConceptByUuid("5087AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn(
		    "os",
		    getObservation(Context.getConceptService().getConceptByUuid("5092AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn("BMI", getBMI(), "endDate=${endDate}", new CalculationResultConverter());
		dsd.addColumn("NEW", getNewOrRevisit("NEW"), "endDate=${endDate}", new CalculationResultConverter());
		dsd.addColumn("RVT", getNewOrRevisit("RVT"), "endDate=${endDate}", new CalculationResultConverter());
		dsd.addColumn("FV", getFevers(), "endDate=${endDate}", new CalculationResultConverter());
		dsd.addColumn(
		    "DIAGF",
		    getObservation(EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS),
		        TimeQualifier.ANY), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObservationListAnswersConverter());
		dsd.addColumn(
		    "DIAGP",
		    getObservation(EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROVISIONAL_DIAGNOSIS),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn("DR", getDrugs(), "endDate=${endDate}", new DrugListConverter());
		dsd.addColumn("OUT", getDeadPeople(), "", new OutcomeConverter());
		dsd.addColumn(
		    "RFF",
		    getObservation(Context.getConceptService().getConceptByUuid("160481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ReferralFromConverter());
		dsd.addColumn(
		    "RFT",
		    getObservation(Context.getConceptService().getConceptByUuid("477a7484-0f99-4026-b37c-261be587a70b"),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate}", new ReferralToConverter());
		
		return dsd;
		
	}
	
	private DataDefinition getBMI() {
		CalculationDataDefinition cd = new CalculationDataDefinition("BMI", new BMIAtLastVisitCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		return cd;
		
	}
	
	private DataDefinition getObservation(Concept question, TimeQualifier timeQualifier) {
		ObsForPersonDataDefinition obs = new ObsForPersonDataDefinition();
		obs.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		obs.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		obs.setWhich(timeQualifier);
		obs.setQuestion(question);
		return obs;
		
	}
	
	private DataDefinition getDrugs() {
		CalculationDataDefinition cd = new CalculationDataDefinition("DR", new CurrentDrugsCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		return cd;
		
	}
	
	private DataDefinition getFevers() {
		CalculationDataDefinition cd = new CalculationDataDefinition("FV", new FeversForPatientCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
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
	
	private CohortDefinition allChildrenDiagnosisPatientsCohort(int encounterType) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Active Child Patients");
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id INNER JOIN obs o ON e.encounter_id=o.encounter_id "
		        + " INNER JOIN concept_name cn ON cn.concept_id = o.value_coded "
		        + " INNER JOIN person pe ON p.patient_id=pe.person_id "
		        + " INNER JOIN concept c ON c.concept_id = cn.concept_id "
		        + " WHERE e.encounter_datetime BETWEEN :startDate AND :endDate "
		        + " AND e.encounter_type="
		        + encounterType
		        + " AND o.value_coded IS NOT NULL "
		        + " AND cn.locale = 'en' AND cn.locale_preferred = 1 "
		        + " AND TIMESTAMPDIFF(YEAR, pe.birthdate, :endDate) < 5 " + " AND c.class_id IN(4)");
		return cd;
	}
	
	private DataDefinition getNewOrRevisit(String flag) {
		CalculationDataDefinition cd = new CalculationDataDefinition("RVT", new RevisitPatientCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addCalculationParameter("flag", flag);
		return cd;
		
	}
	
	private DataDefinition getDeadPeople() {
		SqlPatientDataDefinition dsd = new SqlPatientDataDefinition();
		dsd.setName("Get dead people");
		dsd.setQuery("SELECT p.patient_id,pe.death_date FROM patient p INNER JOIN person pe ON p.patient_id=pe.person_id "
		        + " WHERE pe.death_date IS NOT NULL");
		return dsd;
	}
	
	private DataDefinition getRevisit(String flag) {
		CalculationDataDefinition cd = new CalculationDataDefinition("RVT", new RevisitPatientCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addCalculationParameter("flag", flag);
		return cd;
		
	}
}
