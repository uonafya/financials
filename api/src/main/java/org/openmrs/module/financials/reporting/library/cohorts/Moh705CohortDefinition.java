package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.Concept;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.metadata.EhrAddonsMetadata;
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
	
	@Autowired
	private EhrAddonCommons ehrAddonCommons;
	
	/**
	 * Get adult patients who have given diagnosis - MOH705A
	 * 
	 * @return @{@link org.openmrs.module.reporting.cohort.definition.CohortDefinition}
	 */
	private CohortDefinition getPatientsWhoHaveDiagnosis705A(List<Integer> list) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get children patients who have diagnosis based on list of concepts");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setQuery(Moh705Queries.getPatientsWhoMatchDiagnosisBasedOnConcepts(
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROBLEM_ADDED).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROVISIONAL_DIAGNOSIS).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS).getConceptId(), list));
		return cd;
	}
	
	public CohortDefinition getPatientsWhoHaveDiagnosis705AWithAge(List<Integer> list) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get children with diagnosis");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705A",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705A(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("CHILD", ReportUtils.map(ehrAddonCommons.createXtoYAgeCohort(0, 5), "effectiveDate=${endDate}"));
		cd.setCompositionString("MOH705A AND CHILD");
		return cd;
	}
	
	private CohortDefinition getPatientsWhoHaveDiagnosis705B(List<Integer> list) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get adults patients who have diagnosis based on list of concepts");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setQuery(Moh705Queries.getPatientsWhoMatchDiagnosisBasedOnConcepts(
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROBLEM_ADDED).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PROVISIONAL_DIAGNOSIS).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS).getConceptId(), list));
		return cd;
	}
	
	public CohortDefinition getPatientsWhoHaveDiagnosis705BWithAge(List<Integer> list) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get children with diagnosis");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705B",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705A(list), "startDate=${startDate},endDate=${endDate}"));
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
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate "
		        + " AND o.concept_id IN(32,1643) AND o.value_coded IN(" + ans + ")");
		return cd;
	}
	
	public CohortDefinition getMalariaDiagnosis705A(List<Integer> list, List<Integer> ansList) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Suspected and confirmed Malaria Diagnosis for 705A");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705A",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705A(list), "startDate=${startDate},endDate=${endDate"));
		cd.addSearch("status", ReportUtils.map(getMalariaStatus(ansList), "startDate=${startDate},endDate=${endDate"));
		cd.setCompositionString("MOH705A AND status");
		return cd;
	}
	
	public CohortDefinition getMalariaDiagnosis705B(List<Integer> list, List<Integer> ansList) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Suspected and confirmed Malaria Diagnosis for 705 B");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("MOH705B",
		    ReportUtils.map(getPatientsWhoHaveDiagnosis705B(list), "startDate=${startDate},endDate=${endDate"));
		cd.addSearch("status", ReportUtils.map(getMalariaStatus(ansList), "startDate=${startDate},endDate=${endDate"));
		cd.setCompositionString("MOH705B AND status");
		return cd;
	}
	
}
