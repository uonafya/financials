package org.openmrs.module.financials.reporting.library.cohorts;

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

@Component
public class Moh705CohortDefinition {
	
	private EhrAddonCommons ehrAddonCommons;
	
	private Moh717CohortDefinition moh717CohortDefinition;
	
	@Autowired
	public Moh705CohortDefinition(EhrAddonCommons ehrAddonCommons, Moh717CohortDefinition moh717CohortDefinition) {
		this.ehrAddonCommons = ehrAddonCommons;
		this.moh717CohortDefinition = moh717CohortDefinition;
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
		cd.setName("Get children with diagnosis");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705B",
		    ReportUtils.map(getPatientsWhoHaveOtherDiagnosis705(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("ADULT", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(5, 200), "effectiveDate=${endDate}"));
		cd.setCompositionString("MOH705B AND ADULT");
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
		cd.addSearch("NEW",
		    ReportUtils.map(moh717CohortDefinition.getNewPatients(), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("CHILD", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(0, 4), "effectiveDate=${endDate}"));
		cd.setCompositionString("NEW AND CHILD");
		return cd;
	}
	
	public CohortDefinition getNewAdultsrenPatients() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Adults visiting for the first time");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("NEW",
		    ReportUtils.map(moh717CohortDefinition.getNewPatients(), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("ADULT", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(5, 200), "effectiveDate=${endDate}"));
		cd.setCompositionString("NEW AND ADULT");
		return cd;
	}
	
	public CohortDefinition getRevisitsChildrenPatients() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Children with revisits");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("RVT",
		    ReportUtils.map(moh717CohortDefinition.getRevisitPatients(), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("CHILD", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(0, 4), "effectiveDate=${endDate}"));
		cd.setCompositionString("RVT AND CHILD");
		return cd;
	}
	
	public CohortDefinition getRevisitAdultsrenPatients() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Adults with revisit");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("RVT",
		    ReportUtils.map(moh717CohortDefinition.getRevisitPatients(), "startDate=${startDate},endDate=${endDate}"));
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
	
}
