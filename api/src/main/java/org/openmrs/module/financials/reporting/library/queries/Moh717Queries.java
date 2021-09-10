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
		        + " o.concept_id IN(%d) AND e.encounter_datetime BETWEEN :startDate AND :endDate AND p.voided = 0 AND e.voided=0 AND o.voided=0 ";
		return String.format(sql, conceptsQsn);
	}
	
	/**
	 * Special clinics query that are NOT listed
	 * 
	 * @return String
	 */
	public static String getSpecialClinicPatientsOutsideRange(int conceptsQsn, int d1, int d2, int d3, int d4, int d5,
	        int d6, int d7, int d8, int d9, int d10, int d11, int d12, int d13, int d14, int d15, int d16) {
		String sql = " SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id INNER JOIN obs o "
		        + " ON e.encounter_id=o.encounter_id WHERE "
		        + " o.concept_id IN(%d) AND e.encounter_datetime BETWEEN :startDate AND :endDate AND p.voided = 0 AND e.voided=0 AND o.voided=0 "
		        + " AND o.value_coded NOT IN(%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d)";
		return String.format(sql, conceptsQsn, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16);
	}
	
	public static String getMopcClinicQuery(int triageMopc, int opdMopc) {
		String sql = "SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id INNER JOIN obs o "
		        + " ON e.encounter_id=o.encounter_id WHERE e.encounter_datetime BETWEEN :onOrAfter AND :onOrBefore AND p.voided = 0 AND e.voided=0 AND o.voided=0 "
		        + " AND o.value_coded IN(%d, %d)";
		return String.format(sql, triageMopc, opdMopc);
		
	}
	
}
