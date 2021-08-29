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
	
	private Moh705CohortDefinition moh705CohortDefinition;
	
	@Autowired
	public Moh705IndicatorDefinitions(Moh705CohortDefinition moh705aCohortDefinition) {
		this.moh705CohortDefinition = moh705aCohortDefinition;
	}
	
	//Diagnonosis 705A
	public CohortIndicator getAllChildrenPatientsWithDiagnosis(List<Integer> list) {
		return cohortIndicator(
		    "Diagnosis",
		    map(moh705CohortDefinition.getPatientsWhoHaveDiagnosis705AWithAge(list),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllChildrenPatientsWithDiagnosisForMalaria(List<Integer> list, List<Integer> ans) {
		return cohortIndicator("Diagnosis for malaria for 705 A",
		    map(moh705CohortDefinition.getMalariaDiagnosis705A(list, ans), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//Diagnonosis 705B
	public CohortIndicator getAllAdultPatientsWithDiagnosis(List<Integer> list) {
		return cohortIndicator(
		    "Diagnosis",
		    map(moh705CohortDefinition.getPatientsWhoHaveDiagnosis705BWithAge(list),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllAdultPatientsWithDiagnosisForMalaria(List<Integer> list, List<Integer> ans) {
		return cohortIndicator("Diagnosis for malaria for 705 B",
		    map(moh705CohortDefinition.getMalariaDiagnosis705B(list, ans), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getNewChildrenPatients() {
		return cohortIndicator("New children patients",
		    map(moh705CohortDefinition.getNewChildrenPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getNewAdultsPatients() {
		return cohortIndicator("New adults patients",
		    map(moh705CohortDefinition.getNewAdultsrenPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getRevisitsChildrenPatients() {
		return cohortIndicator("Revisit children patients",
		    map(moh705CohortDefinition.getRevisitsChildrenPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getRevisitsAdultsPatients() {
		return cohortIndicator("Revisit adults patients",
		    map(moh705CohortDefinition.getRevisitAdultsrenPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
}
