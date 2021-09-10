package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.MohOpthlamicCohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class MohOpthlamicIndicatorDefinition {
	
	@Autowired
	private MohOpthlamicCohortDefinition mohOpthlamicCohortDefinition;
	
	//All patients who visited eye clinic
	public CohortIndicator getAllPatientsWhovistedEyeDepartiment() {
		return cohortIndicator("All patients in eye clinic",
		    map(mohOpthlamicCohortDefinition.getAllPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
}
