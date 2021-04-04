package org.openmrs.module.financials.reporting.library.common;

import org.openmrs.module.financials.reporting.calculation.EncountersBasedOnDaySuppliedCalculation;
import org.openmrs.module.kenyacore.report.cohort.definition.CalculationCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
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
	public CohortDefinition getNewPatients() {
		SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition();
		sqlCohortDefinition.setName("New patients");
		sqlCohortDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sqlCohortDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		sqlCohortDefinition
		        .setQuery("SELECT p.patient_id FROM patient p INNER JOIN  "
		                + "(SELECT COUNT(p.patient_id) AS patient_count, p.patient_id AS patient_id FROM patient p INNER JOIN visit v ON p.patient_id=v.patient_id "
		                + " WHERE v.date_started <=:endDate group by p.patient_id ) visits ON p.patient_id=visits.patient_id WHERE visits.patient_count <= 1 ");
		return sqlCohortDefinition;
	}
	
	public CohortDefinition getRevisitPatients() {
		SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition();
		sqlCohortDefinition.setName("Revisit patients");
		sqlCohortDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sqlCohortDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		sqlCohortDefinition
		        .setQuery("SELECT p.patient_id FROM patient p INNER JOIN  "
		                + "(SELECT COUNT(p.patient_id) AS patient_count, p.patient_id AS patient_id FROM patient p INNER JOIN visit v ON p.patient_id=v.patient_id "
		                + " WHERE v.date_started <=:endDate group by p.patient_id ) visits ON p.patient_id=visits.patient_id WHERE visits.patient_count > 1 ");
		return sqlCohortDefinition;
	}
}
