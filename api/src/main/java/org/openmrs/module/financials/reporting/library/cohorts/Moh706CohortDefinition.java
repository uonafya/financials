package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

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
}
