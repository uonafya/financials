package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh711CohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh711IndicatorDefinition {
	
	private Moh711CohortDefinition moh711CohortDefinition;
	
	@Autowired
	public Moh711IndicatorDefinition(Moh711CohortDefinition moh711CohortDefinition) {
		this.moh711CohortDefinition = moh711CohortDefinition;
	}
	
	public CohortIndicator getAllPatients() {
		
		return cohortIndicator("All",
		    map(moh711CohortDefinition.getAllPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
}
