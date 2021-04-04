package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh705aCohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh705IndicatorDefinitions {
	
	private Moh705aCohortDefinition moh705aCohortDefinition;
	
	@Autowired
	public Moh705IndicatorDefinitions(Moh705aCohortDefinition moh705aCohortDefinition) {
		this.moh705aCohortDefinition = moh705aCohortDefinition;
	}
	
	//Diagnonosis 705A
	public CohortIndicator getAllChildrenPatientsWithDiagnosis(List<Integer> list) {
		return cohortIndicator("Diagnosis",
		    map(moh705aCohortDefinition.getPatientsWhoHaveDiagnosis705A(list), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//Diagnonosis 705B
	public CohortIndicator getAllAdultPatientsWithDiagnosis(List<Integer> list) {
		return cohortIndicator("Diagnosis",
		    map(moh705aCohortDefinition.getPatientsWhoHaveDiagnosis705B(list), "startDate=${startDate},endDate=${endDate}"));
	}
}
