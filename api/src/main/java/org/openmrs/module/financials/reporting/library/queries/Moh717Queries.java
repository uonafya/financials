package org.openmrs.module.financials.reporting.library.queries;

public class Moh717Queries {
	
	/**
	 * Special clinic query
	 * 
	 * @return String
	 */
	public static String getSpecialClinicPatients(int conceptsQsn) {
		String sql = " SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id INNER JOIN obs o "
		        + " ON e.encounter_id=o.encounter_id WHERE "
		        + " o.concept_id IN(%d) AND e.encounter_datetime BETWEEN :startDate AND :endDate ";
		return String.format(sql, conceptsQsn);
	}
	
}
