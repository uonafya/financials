package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.springframework.stereotype.Component;

@Component
public class Moh706CohortDefinition {
	
	public CohortDefinition getAllPatients() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("All patients");
		sql.setQuery("SELECT patient_id FROM patient");
		return sql;
	}
}
