package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh705CohortDefinition;
import org.openmrs.module.financials.reporting.library.cohorts.Moh706CohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh706IndicatorDefinitions {
	
	private Moh706CohortDefinition moh706CohortDefinition;
	
	@Autowired
	public Moh706IndicatorDefinitions(Moh706CohortDefinition moh706CohortDefinition) {
		this.moh706CohortDefinition = moh706CohortDefinition;
	}
	
	public CohortIndicator getAllPatients() {
		return cohortIndicator("All patients", map(moh706CohortDefinition.getAllPatients(), ""));
	}
}
