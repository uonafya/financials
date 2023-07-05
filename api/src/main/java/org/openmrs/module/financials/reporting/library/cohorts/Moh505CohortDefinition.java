package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Moh505CohortDefinition {
	
	public CohortDefinition getSampleNumbers() {
		SqlCohortDefinition sql = new SqlCohortDefinition();
		sql.setName("Sample Patients");
		sql.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sql.addParameter(new Parameter("endDate", "End Date", Date.class));
		sql.setQuery("SELECT patient_id FROM patient");
		return sql;
	}
}
