package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.module.financials.metadata.EhrAddonsMetadata;
import org.openmrs.module.financials.reporting.calculation.EncountersBasedOnDaySuppliedCalculation;
import org.openmrs.module.financials.reporting.library.queries.Moh705Queries;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.cohort.definition.CalculationCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Moh705bCohortDefinition {
	
	/**
	 * Get adult patients who have given diagnosis - MOH705A
	 * 
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getPatientsWhoHaveDiagnosis(List<Integer> list) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get children patients who have diagnosis based on list of concepts");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setQuery(Moh705Queries.getPatientsAgedAboveFiveWhoMatchDiagnosisBasedOnConcepts(EhrAddonsMetadata
		        .getDiagnosisConceptClass().getConceptClassId(), list));
		return cd;
	}
	
	/**
	 * Patients who have encounters on date
	 * 
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getPatientsHavingEncountersOnDate(int day) {
		CalculationCohortDefinition cd = new CalculationCohortDefinition("encounters",
		        new EncountersBasedOnDaySuppliedCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addCalculationParameter("day", day);
		return cd;
	}
	
	/**
	 * Get patients for the days per the calendar
	 * 
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getAdultPatientsWhoHaveDiagnosisOnAgivenDay(List<Integer> list, int day) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Chidren with diagnosis on a given day");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("A", ReportUtils.map(getPatientsWhoHaveDiagnosis(list), "startDate=${startDate},endDate=${endDate}"));
		cd.addSearch("B", ReportUtils.map(getPatientsHavingEncountersOnDate(day), "endDate=${endDate}"));
		cd.setCompositionString("A AND B");
		return cd;
		
	}
	
}
