package org.openmrs.module.financials.reporting.library.cohorts;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Moh706CohortDefinition {
	
	public CohortDefinition getAllUrineAnalysisGlucoseTestsPositives() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get urine analysis patients - glucose");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id in (159734) AND o.value_coded IN (703,1874,1362,1363,1364,1365) "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		        + " UNION "
		        + " SELECT p.patient_id FROM patient p INNER JOIN encounter e on p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id in (159733) AND o.value_numeric IS NOT NULL "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getAllUrineAnalysisKetonesTestsPositives() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get urine analysis patients - Ketones");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id in (161442) AND o.value_coded IN (1874,1362,1363,1364,1365) "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getAllUrineAnalysisProteinsTestsPositives() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get urine analysis patients - Proteins");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN (1875) AND o.value_coded IN (703,1874,1362,1363,1364,1365) "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getAllMalariaTests() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with malaria tests");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN (32) "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getAllRapidMalariaTests() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with rapid malaria tests");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN (1643) "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getAllMalariaTestsPositiveCases() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with malaria tests positive cases");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN (32,1643) AND o.value_coded IN (703,161246,161247,161248) "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getAllRapidMalariaTestsPositiveCases() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with rapid malaria tests positive cases");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN (1643) AND o.value_coded IN (703,161246,161247,161248) "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getResponsesBasedOnAnswer(int question, List<Integer> ans) {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with obs recorded based on value coded");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id="
		        + question
		        + " AND o.value_coded IN ("
		        + StringUtils.join(ans, ',')
		        + ") "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getPatientsWithObs(int question) {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with obs recorded");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id="
		        + question
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(List<Integer> question, List<Integer> ans) {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with obs recorded based on value coded list and question list and list of answers");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN ("
		        + StringUtils.join(question, ',')
		        + ") "
		        + " AND o.value_coded IN ("
		        + StringUtils.join(ans, ',')
		        + ") "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getResponsesBasedOnAlistOfQuestions(List<Integer> question) {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with obs recorded based on value coded list and question list");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN ("
		        + StringUtils.join(question, ',') + ") " + " AND e.encounter_datetime BETWEEN :startDate AND :endDate "
		
		);
		return sql;
	}
	
	public CohortDefinition getResponsesBasedOnValueNumericQuestion(int question) {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with obs recorded based on value numeric concept id");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN ("
		        + question
		        + ") "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate AND o.value_numeric IS NOT NULL "
		
		);
		return sql;
	}
	
	public CohortDefinition getResponsesBasedOnValueNumericQuestionBetweenLimits(int question, double lower, double upper) {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Get patients with obs recorded based on value numeric concept id within limits");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id = e.patient_id INNER JOIN obs o ON e.encounter_id = o.encounter_id "
		        + " WHERE p.voided=0 AND e.voided = 0 AND o.voided = 0 AND o.concept_id IN ("
		        + question
		        + ") "
		        + " AND e.encounter_datetime BETWEEN :startDate AND :endDate AND o.value_numeric IS NOT NULL AND o.value_numeric BETWEEN "
		        + lower + " AND " + upper
		
		);
		return sql;
	}
}
