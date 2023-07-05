package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh505CohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh505IndicatorDefinition {
	
	private final Moh505CohortDefinition moh505CohortDefinition;
	
	@Autowired
	public Moh505IndicatorDefinition(Moh505CohortDefinition moh505CohortDefinition) {
		this.moh505CohortDefinition = moh505CohortDefinition;
	}
	
	public CohortIndicator getAllSamplePatients() {
		return cohortIndicator("Samples",
		    map(moh505CohortDefinition.getSampleNumbers(), "startDate=${startDate},endDate=${endDate}"));
	}
}
