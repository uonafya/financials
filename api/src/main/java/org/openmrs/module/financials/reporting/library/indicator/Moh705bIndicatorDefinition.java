package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh705bCohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;
import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;

@Component
public class Moh705bIndicatorDefinition {
	
	private Moh705bCohortDefinition moh705bCohortDefinition;
	
	@Autowired
	public Moh705bIndicatorDefinition(Moh705bCohortDefinition moh705bCohortDefinition) {
		this.moh705bCohortDefinition = moh705bCohortDefinition;
	}
	
	//Diagnonosis
	public CohortIndicator getAllPatientsWithDiagnosis(List<Integer> list, int day) {
		return cohortIndicator(
		    "Diagnosis",
		    map(moh705bCohortDefinition.getAdultPatientsWhoHaveDiagnosisOnAgivenDay(list, day),
		        "startDate=${startDate},endDate=${endDate}"));
	}
}
