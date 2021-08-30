package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.module.financials.reporting.library.common.EhrAddonCommons;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MohOpthlamicCohortDefinition {
	
	@Autowired
	private EhrAddonCommons ehrAddonCommons;
	
	public CohortDefinition getAllPatients() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setQuery("SELECT patient_id FROM patient");
		return cd;
	}
}
