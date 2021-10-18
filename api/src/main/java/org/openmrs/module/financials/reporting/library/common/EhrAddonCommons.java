package org.openmrs.module.financials.reporting.library.common;

import org.openmrs.module.financials.reporting.calculation.EncountersBasedOnDaySuppliedCalculation;
import org.openmrs.module.kenyacore.report.cohort.definition.CalculationCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.DurationUnit;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EhrAddonCommons {
	
	/**
	 * Patients who have encounters on date
	 * 
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getPatientsHavingEncountersOnDate(int day) {
		CalculationCohortDefinition cd = new CalculationCohortDefinition("Encounters per day",
		        new EncountersBasedOnDaySuppliedCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addCalculationParameter("day", day);
		return cd;
	}
	
	/**
	 * Get patients dimesion age
	 * 
	 * @param minAge
	 * @param maxAge
	 * @return
	 */
	public CohortDefinition createXtoYAgeCohort(Integer minAge, Integer maxAge) {
		AgeCohortDefinition xToYCohort = new AgeCohortDefinition();
		xToYCohort.setName("age");
		if (minAge != null) {
			xToYCohort.setMinAge(minAge);
		}
		if (maxAge != null) {
			xToYCohort.setMaxAge(maxAge);
		}
		xToYCohort.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
		return xToYCohort;
	}
	
	/**
	 * Patients who are female
	 * 
	 * @return the cohort definition
	 */
	public CohortDefinition femaleCohort() {
		GenderCohortDefinition cohort = new GenderCohortDefinition();
		cohort.setName("femaleCohort");
		cohort.setFemaleIncluded(true);
		cohort.setMaleIncluded(false);
		return cohort;
	}
	
	/**
	 * Patients who are male
	 * 
	 * @return the cohort definition
	 */
	public CohortDefinition maleCohort() {
		GenderCohortDefinition cohort = new GenderCohortDefinition();
		cohort.setName("maleCohort");
		cohort.setMaleIncluded(true);
		cohort.setFemaleIncluded(false);
		return cohort;
	}
	
	/**
	 * Get patients who are new to the facility
	 * 
	 * @return @CohortDefinition
	 */
	public CohortDefinition getPatientStates(int answer, int enc1, int enc2) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("has obs between dates");
		cd.addParameter(new Parameter("endDate", "Before Date", Date.class));
		cd.addParameter(new Parameter("startDate", "After Date", Date.class));
		String sql = " SELECT patient_id FROM( "
		        + " SELECT max_enc.patient_id, max_enc.encounter_date FROM ( "
		        + " SELECT p.patient_id AS patient_id, MAX(e.encounter_datetime) AS encounter_date FROM patient p "
		        + " INNER JOIN encounter e "
		        + " ON p.patient_id=e.patient_id "
		        + " WHERE "
		        + " p.voided=0 "
		        + " AND e.voided=0"
		        + " AND e.encounter_datetime BETWEEN :startDate AND DATE_ADD(DATE_ADD(:endDate, INTERVAL 23 HOUR), INTERVAL 59 MINUTE) "
		        + " AND e.encounter_type IN("
		        + enc1
		        + ","
		        + enc2
		        + ")"
		        + " GROUP BY p.patient_id) max_enc "
		        
		        + " INNER JOIN "
		        + " ( "
		        + " SELECT pp.patient_id AS patient_id, ee.encounter_datetime AS encounter_date FROM patient pp "
		        + "  INNER JOIN encounter ee ON pp.patient_id=ee.patient_id "
		        + " INNER JOIN obs o ON ee.encounter_id=o.encounter_id "
		        + " WHERE ee.encounter_type IN ("
		        + enc1
		        + ","
		        + enc2
		        + ")"
		        + " AND ee.voided= 0  "
		        + " AND pp.voided = 0 "
		        + " AND o.voided=0 "
		        + " AND ee.encounter_datetime BETWEEN :startDate AND DATE_ADD(DATE_ADD(:endDate, INTERVAL 23 HOUR), INTERVAL 59 MINUTE) "
		        + " AND o.value_coded =" + answer + " )max_obs ON max_enc.patient_id=max_obs.patient_id "
		        
		        + " WHERE max_enc.encounter_date = max_obs.encounter_date) out_table ";
		cd.setQuery(sql);
		return cd;
	}
	
	public CohortDefinition getPatientRevisitsBasedOnVisits() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Has visits within a period of time REVISTS");
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.setQuery("SELECT tbl.patient_id FROM( "
		        + " SELECT p.patient_id, COUNT(visit_id) FROM patient p INNER JOIN visit v ON p.patient_id=v.patient_id "
		        + " WHERE v.date_started BETWEEN DATE_ADD(:endDate, INTERVAL -12 MONTH) AND DATE_ADD(DATE_ADD(:endDate, INTERVAL 23 HOUR), INTERVAL 59 MINUTE) "
		        + " GROUP BY p.patient_id HAVING COUNT(visit_id) > 1) tbl ");
		
		return cd;
	}
	
	public CohortDefinition getPatientWithNewVisitsBasedOnVisits() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Has visits within a period of time NEW VISITS");
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.setQuery("SELECT tbl.patient_id FROM( "
		        + " SELECT p.patient_id, COUNT(visit_id) FROM patient p INNER JOIN visit v ON p.patient_id=v.patient_id "
		        + " WHERE v.date_started BETWEEN :startDate AND DATE_ADD(DATE_ADD(:endDate, INTERVAL 23 HOUR), INTERVAL 59 MINUTE) "
		        + " GROUP BY p.patient_id HAVING COUNT(visit_id) <= 1) tbl ");
		
		return cd;
	}
	
	/**
	 * Patients who at most maxAge years old on ${effectiveDate}
	 * 
	 * @return the cohort definition
	 */
	public CohortDefinition agedAtMost(int maxAge) {
		AgeCohortDefinition cd = new AgeCohortDefinition();
		cd.setName("aged at most " + maxAge);
		cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
		cd.setMaxAge(maxAge);
		return cd;
	}
	
	/**
	 * Patients who are at least minAge years old on ${effectiveDate}
	 * 
	 * @return the cohort definition
	 */
	public CohortDefinition agedAtLeast(int minAge) {
		AgeCohortDefinition cd = new AgeCohortDefinition();
		cd.setName("aged at least " + minAge);
		cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
		cd.setMinAge(minAge);
		return cd;
	}
	
	/**
	 * patients who are at least minAge years old and at most years old on ${effectiveDate}
	 * 
	 * @return CohortDefinition
	 */
	public CohortDefinition agedAtLeastAgedAtMost(int minAge, int maxAge) {
		AgeCohortDefinition cd = new AgeCohortDefinition();
		cd.setName("aged between " + minAge + " and " + maxAge + " years");
		cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
		cd.setMinAge(minAge);
		cd.setMaxAge(maxAge);
		return cd;
	}
	
	/**
	 * patients who are at least minAge months old and at most months old on ${effectiveDate}
	 * 
	 * @return CohortDefinition
	 */
	public CohortDefinition agedAtLeastAgedAtMostInMonths(int minAge, int maxAge) {
		AgeCohortDefinition cd = new AgeCohortDefinition();
		cd.setName("aged between " + minAge + " and " + maxAge + " years");
		cd.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
		cd.setMinAge(minAge);
		cd.setMaxAge(maxAge);
		cd.setMinAgeUnit(DurationUnit.MONTHS);
		cd.setMaxAgeUnit(DurationUnit.MONTHS);
		return cd;
	}
}
