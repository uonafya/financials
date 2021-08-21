package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh717CohortDefinition;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;

@Component
public class Moh717IndicatorDefinition {
	
	private Moh717CohortDefinition moh717CohortDefinition;
	
	@Autowired
	public Moh717IndicatorDefinition(Moh717CohortDefinition moh717CohortDefinition) {
		this.moh717CohortDefinition = moh717CohortDefinition;
	}
	
	public CohortIndicator getAllNewPatients() {
		return cohortIndicator("All patients new patients for 717 report",
		    ReportUtils.map(moh717CohortDefinition.getNewPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllRevisitPatients() {
		return cohortIndicator("All patients rebvisit patients for 717 report",
		    ReportUtils.map(moh717CohortDefinition.getRevisitPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//special clinic new patients
	public CohortIndicator getSpecialClinicNewPatients() {
		return cohortIndicator("Special clinics new patients report", ReportUtils.map(
		    moh717CohortDefinition.getNewSpecialClinicPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//special clinic new revisit patients
	public CohortIndicator getSpecialClinicRevisitPatients() {
		return cohortIndicator("Special clinics revisit patients report", ReportUtils.map(
		    moh717CohortDefinition.getRevistSpecialClinicPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
}
