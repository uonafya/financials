package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh706CohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh706IndicatorDefinitions {
	
	private Moh706CohortDefinition moh706CohortDefinition;
	
	@Autowired
	public Moh706IndicatorDefinitions(Moh706CohortDefinition moh706CohortDefinition) {
		this.moh706CohortDefinition = moh706CohortDefinition;
	}
	
	public CohortIndicator getAllUrineAnalysisGlucoseTestsPositives() {
		return cohortIndicator("All patients who have urinalysis glucose",
		    map(moh706CohortDefinition.getAllUrineAnalysisGlucoseTestsPositives(), ""));
	}
}
