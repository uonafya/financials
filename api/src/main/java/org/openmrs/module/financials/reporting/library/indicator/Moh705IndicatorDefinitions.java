package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh705CohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh705IndicatorDefinitions {
	
	private Moh705CohortDefinition moh705aCohortDefinition;
	
	@Autowired
	public Moh705IndicatorDefinitions(Moh705CohortDefinition moh705aCohortDefinition) {
		this.moh705aCohortDefinition = moh705aCohortDefinition;
	}
	
	//Diagnonosis 705A
	public CohortIndicator getAllChildrenPatientsWithDiagnosis(List<Integer> list) {
		return cohortIndicator(
		    "Diagnosis",
		    map(moh705aCohortDefinition.getPatientsWhoHaveDiagnosis705AWithAge(list),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllChildrenPatientsWithDiagnosisForMalaria(List<Integer> list, List<Integer> ans) {
		return cohortIndicator("Diagnosis for malaria for 705 A",
		    map(moh705aCohortDefinition.getMalariaDiagnosis705A(list, ans), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//Diagnonosis 705B
	public CohortIndicator getAllAdultPatientsWithDiagnosis(List<Integer> list) {
		return cohortIndicator(
		    "Diagnosis",
		    map(moh705aCohortDefinition.getPatientsWhoHaveDiagnosis705BWithAge(list),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllAdultPatientsWithDiagnosisForMalaria(List<Integer> list, List<Integer> ans) {
		return cohortIndicator("Diagnosis for malaria for 705 B",
		    map(moh705aCohortDefinition.getMalariaDiagnosis705B(list, ans), "startDate=${startDate},endDate=${endDate}"));
	}
	
}
