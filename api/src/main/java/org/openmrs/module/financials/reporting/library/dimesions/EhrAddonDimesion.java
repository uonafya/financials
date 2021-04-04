package org.openmrs.module.financials.reporting.library.dimesions;

import org.openmrs.module.financials.reporting.library.common.EhrAddonCommons;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class EhrAddonDimesion {
	
	@Autowired
	private EhrAddonCommons commonLibrary;
	
	/**
	 * Patients with encounters on a given date
	 * 
	 * @return @{@link org.openmrs.module.reporting.indicator.dimension.CohortDimension}
	 */
	public CohortDefinitionDimension encountersOfMonthPerDay() {
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.setName("Patient with encounters on date of day");
		dim.addParameter(new Parameter("endDate", "End Date", Date.class));
		dim.addCohortDefinition("1", map(commonLibrary.getPatientsHavingEncountersOnDate(1), "endDate=${endDate}"));
		dim.addCohortDefinition("2", map(commonLibrary.getPatientsHavingEncountersOnDate(2), "endDate=${endDate}"));
		dim.addCohortDefinition("3", map(commonLibrary.getPatientsHavingEncountersOnDate(3), "endDate=${endDate}"));
		dim.addCohortDefinition("4", map(commonLibrary.getPatientsHavingEncountersOnDate(4), "endDate=${endDate}"));
		dim.addCohortDefinition("5", map(commonLibrary.getPatientsHavingEncountersOnDate(5), "endDate=${endDate}"));
		dim.addCohortDefinition("6", map(commonLibrary.getPatientsHavingEncountersOnDate(6), "endDate=${endDate}"));
		dim.addCohortDefinition("7", map(commonLibrary.getPatientsHavingEncountersOnDate(7), "endDate=${endDate}"));
		dim.addCohortDefinition("8", map(commonLibrary.getPatientsHavingEncountersOnDate(8), "endDate=${endDate}"));
		dim.addCohortDefinition("9", map(commonLibrary.getPatientsHavingEncountersOnDate(9), "endDate=${endDate}"));
		dim.addCohortDefinition("10", map(commonLibrary.getPatientsHavingEncountersOnDate(10), "endDate=${endDate}"));
		dim.addCohortDefinition("11", map(commonLibrary.getPatientsHavingEncountersOnDate(11), "endDate=${endDate}"));
		dim.addCohortDefinition("12", map(commonLibrary.getPatientsHavingEncountersOnDate(12), "endDate=${endDate}"));
		dim.addCohortDefinition("13", map(commonLibrary.getPatientsHavingEncountersOnDate(13), "endDate=${endDate}"));
		dim.addCohortDefinition("14", map(commonLibrary.getPatientsHavingEncountersOnDate(14), "endDate=${endDate}"));
		dim.addCohortDefinition("15", map(commonLibrary.getPatientsHavingEncountersOnDate(15), "endDate=${endDate}"));
		dim.addCohortDefinition("16", map(commonLibrary.getPatientsHavingEncountersOnDate(16), "endDate=${endDate}"));
		dim.addCohortDefinition("17", map(commonLibrary.getPatientsHavingEncountersOnDate(17), "endDate=${endDate}"));
		dim.addCohortDefinition("18", map(commonLibrary.getPatientsHavingEncountersOnDate(18), "endDate=${endDate}"));
		dim.addCohortDefinition("19", map(commonLibrary.getPatientsHavingEncountersOnDate(19), "endDate=${endDate}"));
		dim.addCohortDefinition("20", map(commonLibrary.getPatientsHavingEncountersOnDate(20), "endDate=${endDate}"));
		dim.addCohortDefinition("21", map(commonLibrary.getPatientsHavingEncountersOnDate(21), "endDate=${endDate}"));
		dim.addCohortDefinition("22", map(commonLibrary.getPatientsHavingEncountersOnDate(22), "endDate=${endDate}"));
		dim.addCohortDefinition("23", map(commonLibrary.getPatientsHavingEncountersOnDate(23), "endDate=${endDate}"));
		dim.addCohortDefinition("24", map(commonLibrary.getPatientsHavingEncountersOnDate(24), "endDate=${endDate}"));
		dim.addCohortDefinition("25", map(commonLibrary.getPatientsHavingEncountersOnDate(25), "endDate=${endDate}"));
		dim.addCohortDefinition("26", map(commonLibrary.getPatientsHavingEncountersOnDate(26), "endDate=${endDate}"));
		dim.addCohortDefinition("27", map(commonLibrary.getPatientsHavingEncountersOnDate(27), "endDate=${endDate}"));
		dim.addCohortDefinition("28", map(commonLibrary.getPatientsHavingEncountersOnDate(28), "endDate=${endDate}"));
		dim.addCohortDefinition("29", map(commonLibrary.getPatientsHavingEncountersOnDate(29), "endDate=${endDate}"));
		dim.addCohortDefinition("30", map(commonLibrary.getPatientsHavingEncountersOnDate(30), "endDate=${endDate}"));
		dim.addCohortDefinition("31", map(commonLibrary.getPatientsHavingEncountersOnDate(31), "endDate=${endDate}"));
		
		return dim;
	}
	
	/**
	 * Patients who are new or revisits with a month
	 * 
	 * @return @{@link org.openmrs.module.reporting.indicator.dimension.CohortDimension}
	 */
	public CohortDefinitionDimension getNewOrRevisitPatients() {
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.setName("Patient with new or revisit status");
		dim.addParameter(new Parameter("endDate", "End Date", Date.class));
		dim.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dim.addCohortDefinition("NEW", map(commonLibrary.getNewPatients(), "startDate=${startDate},endDate=${endDate}"));
		dim.addCohortDefinition("RVT", map(commonLibrary.getRevisitPatients(), "startDate=${startDate},endDate=${endDate}"));
		return dim;
	}
	
	/**
	 * Get age based on the based on DOB and context date
	 * 
	 * @return @{@link org.openmrs.module.reporting.indicator.dimension.CohortDimension}
	 */
	public CohortDefinitionDimension getAge() {
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
		dim.setName("Patient age - dim");
		dim.addCohortDefinition("<5", map(commonLibrary.createXtoYAgeCohort(0, 5), "effectiveDate=${effectiveDate}"));
		dim.addCohortDefinition(">5", map(commonLibrary.createXtoYAgeCohort(5, 60), "effectiveDate=${effectiveDate}"));
		dim.addCohortDefinition(">60", map(commonLibrary.createXtoYAgeCohort(60, 600), "effectiveDate=${effectiveDate}"));
		return dim;
	}
	
	/**
	 * Get patient gender
	 * 
	 * @return @{@link org.openmrs.module.reporting.indicator.dimension.CohortDimension}
	 */
	public CohortDefinitionDimension getGender() {
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.setName("gender");
		dim.addCohortDefinition("F", map(commonLibrary.femaleCohort(), ""));
		dim.addCohortDefinition("M", map(commonLibrary.maleCohort(), ""));
		return dim;
	}
}
