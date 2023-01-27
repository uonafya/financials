package org.openmrs.module.financials.reports;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.reporting.calculation.*;
import org.openmrs.module.financials.reporting.converter.DrugListConverter;
import org.openmrs.module.financials.reporting.converter.EncounterDateConverter;
import org.openmrs.module.financials.reporting.converter.OutcomeConverter;
import org.openmrs.module.financials.reporting.converter.ReferralFromConverter;
import org.openmrs.module.financials.reporting.converter.ReferralToConverter;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.TelephoneNumberCalculation;
import org.openmrs.module.kenyaemr.calculation.library.hiv.BMIAtLastVisitCalculation;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.kenyaemr.reporting.data.converter.IdentifierConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.converter.ObsValueConverter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.EncountersForPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ObsForPersonDataDefinition;
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
@Builds({ "ehraddons.common.report.204A" })
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
		dsd.addColumn("Identifier", new CalculationDataDefinition("Identifier", new PatientIdentifierCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("Date", getEncounterDate(), "onOrAfter=${startDate},onOrBefore=${endDate}", new BirthdateConverter(
		        DATE_FORMAT));
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("age", new AgeDataDefinition(), "");
		dsd.addColumn("village", new CalculationDataDefinition("village", new VillageAndLandmarkCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("telephone", new CalculationDataDefinition("telephone", new TelephoneNumberCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("weight",
		    getObservation(Context.getConceptService().getConceptByUuid("5089AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")),
		    "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn("height",
		    getObservation(Context.getConceptService().getConceptByUuid("5090AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")),
		    "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn("BMI", getBMI(), "endDate=${endDate}", new CalculationResultConverter());
		dsd.addColumn("RVT", getRevisit(), "endDate=${endDate}", new CalculationResultConverter());
		dsd.addColumn("FV", getFevers(), "endDate=${endDate}", new CalculationResultConverter());
		dsd.addColumn("DIAGF",
		    getObservation(EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS)),
		    "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn("DIAGP",
		    getObservation(EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROVISIONAL_DIAGNOSIS)),
		    "onOrAfter=${startDate},onOrBefore=${endDate}", new ObsValueConverter());
		dsd.addColumn("DR", getDrugs(), "endDate=${endDate}", new DrugListConverter());
		dsd.addColumn("OUT",
		    getObservation(Context.getConceptService().getConceptByUuid("160433AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")),
		    "onOrAfter=${startDate},onOrBefore=${endDate}", new OutcomeConverter());
		dsd.addColumn("RFF",
		    getObservation(Context.getConceptService().getConceptByUuid("160481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")),
		    "onOrAfter=${startDate},onOrBefore=${endDate}", new ReferralFromConverter());
		dsd.addColumn("RFT",
		    getObservation(Context.getConceptService().getConceptByUuid("477a7484-0f99-4026-b37c-261be587a70b")),
		    "onOrAfter=${startDate},onOrBefore=${endDate}", new ReferralToConverter());
		
		return dsd;
		
	}
	
	private DataDefinition getBMI() {
		CalculationDataDefinition cd = new CalculationDataDefinition("BMI", new BMIAtLastVisitCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		return cd;
		
	}
	
	private DataDefinition getObservation(Concept question) {
		ObsForPersonDataDefinition obs = new ObsForPersonDataDefinition();
		obs.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		obs.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		obs.setWhich(TimeQualifier.LAST);
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
		EncountersForPatientDataDefinition dsd = new EncountersForPatientDataDefinition();
		dsd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		dsd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		dsd.setWhich(TimeQualifier.LAST);
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
	
	private DataDefinition getRevisit() {
		CalculationDataDefinition cd = new CalculationDataDefinition("RVT", new RevisitPatientCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		return cd;
		
	}
}
