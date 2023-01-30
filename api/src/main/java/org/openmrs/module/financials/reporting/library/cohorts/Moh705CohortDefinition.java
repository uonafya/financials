package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.reporting.library.common.EhrAddonCommons;
import org.openmrs.module.financials.reporting.library.queries.Moh705Queries;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.openmrs.module.financials.EhrAddonsConstants.getConcept;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh705CohortDefinition {
	
	private final EhrAddonCommons ehrAddonCommons;
	
	@Autowired
	public Moh705CohortDefinition(EhrAddonCommons ehrAddonCommons) {
		this.ehrAddonCommons = ehrAddonCommons;
	}
	
	/**
	 * Get adult patients who have given diagnosis - MOH705A
	 * 
	 * @return @{@link org.openmrs.module.reporting.cohort.definition.CohortDefinition}
	 */
	public CohortDefinition getPatientsWhoHaveDiagnosis705(List<Integer> list) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get children and adult patients who have diagnosis based on list of concepts");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setQuery(Moh705Queries.getPatientsWhoMatchDiagnosisBasedOnConcepts(
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROBLEM_ADDED).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROVISIONAL_DIAGNOSIS).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS).getConceptId(),
		    EhrAddonsConstants.getConcept("17b33cd3-1af9-4a1b-a65b-b5e30540b189").getConceptId(), list));
		return cd;
	}
	
	/**
	 * Get adult patients who have given diagnosis - overral
	 * 
	 * @return @{@link org.openmrs.module.reporting.cohort.definition.CohortDefinition}
	 */
	public CohortDefinition getPatientsWhoHaveDiagnosisOverral() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get children and adult patients who have diagnosis based on list of concepts overall");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setQuery(Moh705Queries.getPatientsWhoMatchAtLeastDiagnosisBasedOnConcepts(
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROBLEM_ADDED).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROVISIONAL_DIAGNOSIS).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS).getConceptId(),
		    EhrAddonsConstants.getConcept("17b33cd3-1af9-4a1b-a65b-b5e30540b189").getConceptId()));
		return cd;
	}
	
	private CohortDefinition getPatientsWhoHaveOtherDiagnosis705(List<Integer> list) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get children patients who have other diagnosis based on list of concepts");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setQuery(Moh705Queries.getPatientsWhoMatchOtherDiagnosisBasedOnConcepts(
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROBLEM_ADDED).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROVISIONAL_DIAGNOSIS).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS).getConceptId(),
		    EhrAddonsConstants.getConcept("17b33cd3-1af9-4a1b-a65b-b5e30540b189").getConceptId(), list));
		return cd;
	}
	
	public CohortDefinition getPatientsWhoHaveDiagnosis705AWithAge(List<Integer> list) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get children with diagnosis");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705A",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("CHILD", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(0, 4), "effectiveDate=${endDate}"));
		cd.setCompositionString("MOH705A AND CHILD");
		return cd;
	}
	
	public CohortDefinition getPatientsWhoHaveOtherDiagnosis705AWithAge(List<Integer> list) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get children with other diagnosis");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705A",
		    ReportUtils.map(getPatientsWhoHaveOtherDiagnosis705(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("CHILD", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(0, 4), "effectiveDate=${endDate}"));
		cd.setCompositionString("MOH705A AND CHILD");
		return cd;
	}
	
	public CohortDefinition getExactOtherDiagnosisForPatientsMOH705A(List<Integer> list) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get exact other diagnosis for the children patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("OTHER",
		    ReportUtils.map(getPatientsWhoHaveOtherDiagnosis705AWithAge(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("PASS",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705AWithAge(list), "startDate=${startDate},endDate=${endDate}"));
		cd.setCompositionString("OTHER AND NOT PASS");
		return cd;
	}
	
	public CohortDefinition getPatientsWhoHaveDiagnosis705BWithAge(List<Integer> list) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get adults with diagnosis");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705B",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("ADULT", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(5, 200), "effectiveDate=${endDate}"));
		cd.setCompositionString("MOH705B AND ADULT");
		return cd;
	}
	
	public CohortDefinition getPatientsWhoHaveOtherDiagnosis705BWithAge(List<Integer> list) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get adults with other diagnosis");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705B",
		    ReportUtils.map(getPatientsWhoHaveOtherDiagnosis705(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("ADULT", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(5, 200), "effectiveDate=${endDate}"));
		cd.setCompositionString("MOH705B AND ADULT");
		return cd;
	}
	
	public CohortDefinition getExactOtherDiagnosisForPatientsMOH705B(List<Integer> list) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get exact other diagnosis for the adults patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("OTHER",
		    ReportUtils.map(getPatientsWhoHaveOtherDiagnosis705BWithAge(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("PASS",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705BWithAge(list), "startDate=${startDate},endDate=${endDate}"));
		cd.setCompositionString("OTHER AND NOT PASS");
		return cd;
	}
	
	public CohortDefinition getMalariaStatus(List<Integer> answers) {
		String ans = (String.valueOf(answers).replaceAll("\\[", "")).replaceAll("]", "");
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get the status of malaria either suspect or confirmed");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id"
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE e.encounter_datetime BETWEEN :startDate AND DATE_ADD(DATE_ADD(:endDate, INTERVAL 23 HOUR), INTERVAL 59 MINUTE) "
		        + " AND o.concept_id IN(32,1643) AND o.value_coded IN(" + ans + ")");
		return cd;
	}
	
	public CohortDefinition getMalariaDiagnosis705A(List<Integer> list, List<Integer> ansList) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Suspected and confirmed Malaria Diagnosis for 705A");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705A",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705AWithAge(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("status", ReportUtils.map(getMalariaStatus(ansList), "startDate=${startDate},endDate=${endDate}"));
		cd.setCompositionString("MOH705A AND status");
		return cd;
	}
	
	public CohortDefinition getMalariaDiagnosis705B(List<Integer> list, List<Integer> ansList) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Suspected and confirmed Malaria Diagnosis for 705 B");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705B",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705BWithAge(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("status", ReportUtils.map(getMalariaStatus(ansList), "startDate=${startDate},endDate=${endDate}"));
		cd.setCompositionString("MOH705B AND status");
		return cd;
	}
	
	public CohortDefinition getNewChildrenPatients() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Children visiting for the first time");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("NEW", ReportUtils.map(getNewPatients(), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("CHILD", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(0, 4), "effectiveDate=${endDate}"));
		cd.setCompositionString("NEW AND CHILD");
		return cd;
	}
	
	public CohortDefinition getNewAdultsPatients() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Adults visiting for the first time - new patients in the facility");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("NEW", ReportUtils.map(getNewPatients(), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("ADULT", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(5, 200), "effectiveDate=${endDate}"));
		cd.setCompositionString("NEW AND ADULT");
		return cd;
	}
	
	public CohortDefinition getRevisitsChildrenPatients() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Children with revisits");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("RVT", ReportUtils.map(getRevisitPatients(), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("CHILD", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(0, 4), "effectiveDate=${endDate}"));
		cd.setCompositionString("RVT AND CHILD");
		return cd;
	}
	
	public CohortDefinition getRevisitAdultsPatients() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Adults with revisit - those showing many visits within the year");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("RVT", ReportUtils.map(getRevisitPatients(), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("ADULT", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(5, 200), "effectiveDate=${endDate}"));
		cd.setCompositionString("RVT AND ADULT");
		return cd;
	}
	
	public CohortDefinition getAllChildrenPatientsReferrals(int qn, int ans) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Children with referrals to this facility");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("REF", ReportUtils.map(getReferrals(qn, ans), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("CHILD", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(0, 4), "effectiveDate=${endDate}"));
		cd.setCompositionString("REF AND CHILD");
		return cd;
	}
	
	public CohortDefinition getAllAdultsPatientsReferrals(int qn, int ans) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Adults with referrals to this facility");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("REF", ReportUtils.map(getReferrals(qn, ans), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("ADULT", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(5, 200), "effectiveDate=${endDate}"));
		cd.setCompositionString("REF AND ADULT");
		return cd;
	}
	
	private CohortDefinition getReferrals(int qn, int ans) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get referrals for patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setQuery(Moh705Queries.getPatientsWhoAreReferred(qn, ans));
		return cd;
	}
	
	/**
	 * Get new patients
	 * 
	 * @return
	 */
	private CohortDefinition getNewPatients() {
		EncounterType registrationInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "8efa1534-f28f-11ea-b25f-af56118cf21b");
		EncounterType revisitInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "98d42234-f28f-11ea-b609-bbd062a0383b");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get new patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch(
		    "NEW",
		    map(ehrAddonCommons.getPatientStates(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.NEW_PATIENT).getConceptId(),
		        registrationInitial.getEncounterTypeId(), revisitInitial.getEncounterTypeId()),
		        "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("VISIT",
		    map(ehrAddonCommons.getPatientWithNewVisitsBasedOnVisits(), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("REVISIT", map(getRevisitPatients(), "endDate=${endDate}"));
		cd.addSearch("WITHIN", map(ehrAddonCommons.hasEncounter(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
		
		cd.setCompositionString("(WITHIN AND (NEW OR VISIT)) AND NOT REVISIT");
		return cd;
		
	}
	
	public CohortDefinition getAllPatientsWithDiagnosis() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("All patients who have at least diagnosis recorded");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("DIAGNOSIS", map(getPatientsWhoHaveDiagnosisOverral(), "startDate=${startDate},endDate=${endDate}"));
		cd.setCompositionString("DIAGNOSIS");
		return cd;
	}
	
	private CohortDefinition getRevisitPatients() {
		EncounterType registrationInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "8efa1534-f28f-11ea-b25f-af56118cf21b");
		EncounterType revisitInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "98d42234-f28f-11ea-b609-bbd062a0383b");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get revisit patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch(
		    "RVT",
		    map(ehrAddonCommons.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.REVISIT_PATIENT)
		            .getConceptId(), registrationInitial.getEncounterTypeId(), revisitInitial.getEncounterTypeId()),
		        "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("VISIT", map(ehrAddonCommons.getPatientRevisitsBasedOnVisits(), "endDate=${endDate}"));
		cd.addSearch("WITHIN", map(ehrAddonCommons.hasEncounter(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
		cd.setCompositionString("WITHIN AND (RVT OR VISIT)");
		return cd;
		
	}
	
}
