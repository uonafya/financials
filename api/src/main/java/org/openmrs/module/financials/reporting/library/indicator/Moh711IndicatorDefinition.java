package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.Concept;
import org.openmrs.Program;
import org.openmrs.module.financials.reporting.library.cohorts.Moh711CohortDefinition;
import org.openmrs.module.reporting.common.RangeComparator;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh711IndicatorDefinition {
	
	private Moh711CohortDefinition moh711CohortDefinition;
	
	@Autowired
	public Moh711IndicatorDefinition(Moh711CohortDefinition moh711CohortDefinition) {
		this.moh711CohortDefinition = moh711CohortDefinition;
	}
	
	public CohortIndicator getAllAncClients(Concept... entryPoints) {
		
		return cohortIndicator("ANC Clients",
		    map(moh711CohortDefinition.getAllAncPmtctClients(entryPoints), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllImmunizedPatientsOnIPT(int sequence) {
		
		return cohortIndicator("Immunized Clients on IPT",
		    map(moh711CohortDefinition.getIptVaccinesGivenToMothers(sequence), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllClientsWithMumericValuesComparedToAthreshold(Concept concept, int threshold,
	        RangeComparator rangeComparator) {
		
		return cohortIndicator(
		    "Numeric values comapred to threshold",
		    map(moh711CohortDefinition.getAllClientsWithHbLessThanAthreshold(concept, threshold, rangeComparator),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getPatientWithCodedObs(Concept concept, Concept... answers) {
		
		return cohortIndicator(
		    "Number of patients with coded obs over time",
		    map(moh711CohortDefinition.getPatientWithCodedObs(concept, answers), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getPatientWithCodedObsAndProgram(Program program, Concept question, Concept... ans) {
		
		return cohortIndicator(
		    "Number of patients with coded obs over time and program",
		    map(moh711CohortDefinition.getMotherServicesProgramAndServices(program, question, ans),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getPatientGestationPeriod(int period) {
		
		return cohortIndicator("Number of patients with gestation period of 12 weeks",
		    map(moh711CohortDefinition.getGestationPeriod(period), "endDate=${endDate}"));
	}
	public CohortIndicator getPatientWithSgbv() {

		return cohortIndicator("Number of gender based violence reported",
				map(moh711CohortDefinition.getSgbvCases(), "startDate=${startDate},endDate=${endDate}"));
	}
	
}
