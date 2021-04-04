package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.metadata.EhrAddonsMetadata;
import org.openmrs.module.financials.reporting.library.queries.Moh717Queries;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Moh717CohortDefinition {
	
	public CohortDefinition getAllPatients() {
		SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition();
		sqlCohortDefinition.setName("All patients for 717 report");
		sqlCohortDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sqlCohortDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		sqlCohortDefinition
		        .setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate");
		return sqlCohortDefinition;
	}
	
	/**
	 * Get special clinic patients
	 * 
	 * @param answer
	 * @return
	 */
	public CohortDefinition getSpecialClinicPatients(int answer) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Special Clinic Patients");
		cd.addParameter(new Parameter("startDate", "startDate", Date.class));
		cd.addParameter(new Parameter("endDate", "endDate", Date.class));
		cd.setQuery(Moh717Queries.getSpecialClinicPatients(EhrAddonsMetadata.getAdultsInitialEncounterType()
		        .getEncounterTypeId(), EhrAddonsMetadata.getAdultReturnEncounterType().getEncounterTypeId(),
		    EhrAddonsMetadata.getPedsInitialEncounterType().getEncounterTypeId(), EhrAddonsMetadata
		            .getPedsReturnEncounterType().getEncounterTypeId(), EhrAddonsMetadata.getCheckInEncounterType()
		            .getEncounterTypeId(), EhrAddonsMetadata.getAncEncounterType().getEncounterTypeId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.SPECIAL_CLINIC).getConceptId(), answer));
		
		return cd;
	}
}
