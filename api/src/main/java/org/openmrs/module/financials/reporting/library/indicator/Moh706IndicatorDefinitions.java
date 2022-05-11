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
		return cohortIndicator(
		    "All patients who have urinalysis glucose",
		    map(moh706CohortDefinition.getAllUrineAnalysisGlucoseTestsPositives(),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllUrineAnalysisKetonesTestsPositives() {
		return cohortIndicator(
		    "All patients who have urinalysis ketones",
		    map(moh706CohortDefinition.getAllUrineAnalysisKetonesTestsPositives(),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllUrineAnalysisProteinsTestsPositives() {
		return cohortIndicator(
		    "All patients who have urinalysis Proteins",
		    map(moh706CohortDefinition.getAllUrineAnalysisProteinsTestsPositives(),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllMalariaTests() {
		return cohortIndicator("All patients who have malaria test done",
		    map(moh706CohortDefinition.getAllMalariaTests(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllRapidMalariaTests() {
		return cohortIndicator("All patients who have rapid malaria test done",
		    map(moh706CohortDefinition.getAllRapidMalariaTests(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllMalariaTestsPositiveCases() {
		return cohortIndicator("All patients who have malaria test done and are positive",
		    map(moh706CohortDefinition.getAllMalariaTestsPositiveCases(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllRapidMalariaTestsPositiveCases() {
		return cohortIndicator("All patients who have rapid malaria test done and are positive",
		    map(moh706CohortDefinition.getAllRapidMalariaTestsPositiveCases(), "startDate=${startDate},endDate=${endDate}"));
	}
}
