package org.openmrs.module.financials.reporting.library.dimesions;

import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.reporting.library.cohorts.Moh717CohortDefinition;
import org.openmrs.module.financials.reporting.library.common.EhrAddonCommons;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.openmrs.module.financials.EhrAddonsConstants.getConcept;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class EhrAddonDimesion {
	
	private EhrAddonCommons commonLibrary;
	
	private Moh717CohortDefinition moh717CohortDefinition;
	
	@Autowired
	public EhrAddonDimesion(EhrAddonCommons commonLibrary, Moh717CohortDefinition moh717CohortDefinition) {
		this.commonLibrary = commonLibrary;
		this.moh717CohortDefinition = moh717CohortDefinition;
	}
	
	/**
	 * Patients with encounters on a given date
	 * 
	 * @return @{@link org.openmrs.module.reporting.indicator.dimension.CohortDimension}
	 */
	public CohortDefinitionDimension encountersOfMonthPerDay() {
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.setName("Patient with encounters on date of day");
		dim.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dim.addCohortDefinition("1", map(getPatientsSeenOnDay(0), "startDate=${startDate}"));
		dim.addCohortDefinition("2", map(getPatientsSeenOnDay(1), "startDate=${startDate}"));
		dim.addCohortDefinition("3", map(getPatientsSeenOnDay(2), "startDate=${startDate}"));
		dim.addCohortDefinition("4", map(getPatientsSeenOnDay(3), "startDate=${startDate}"));
		dim.addCohortDefinition("5", map(getPatientsSeenOnDay(4), "startDate=${startDate}"));
		dim.addCohortDefinition("6", map(getPatientsSeenOnDay(5), "startDate=${startDate}"));
		dim.addCohortDefinition("7", map(getPatientsSeenOnDay(6), "startDate=${startDate}"));
		dim.addCohortDefinition("8", map(getPatientsSeenOnDay(7), "startDate=${startDate}"));
		dim.addCohortDefinition("9", map(getPatientsSeenOnDay(8), "startDate=${startDate}"));
		dim.addCohortDefinition("10", map(getPatientsSeenOnDay(9), "startDate=${startDate}"));
		dim.addCohortDefinition("11", map(getPatientsSeenOnDay(10), "startDate=${startDate}"));
		dim.addCohortDefinition("12", map(getPatientsSeenOnDay(11), "startDate=${startDate}"));
		dim.addCohortDefinition("13", map(getPatientsSeenOnDay(12), "startDate=${startDate}"));
		dim.addCohortDefinition("14", map(getPatientsSeenOnDay(13), "startDate=${startDate}"));
		dim.addCohortDefinition("15", map(getPatientsSeenOnDay(14), "startDate=${startDate}"));
		dim.addCohortDefinition("16", map(getPatientsSeenOnDay(15), "startDate=${startDate}"));
		dim.addCohortDefinition("17", map(getPatientsSeenOnDay(16), "startDate=${startDate}"));
		dim.addCohortDefinition("18", map(getPatientsSeenOnDay(17), "startDate=${startDate}"));
		dim.addCohortDefinition("19", map(getPatientsSeenOnDay(18), "startDate=${startDate}"));
		dim.addCohortDefinition("20", map(getPatientsSeenOnDay(19), "startDate=${startDate}"));
		dim.addCohortDefinition("21", map(getPatientsSeenOnDay(20), "startDate=${startDate}"));
		dim.addCohortDefinition("22", map(getPatientsSeenOnDay(21), "startDate=${startDate}"));
		dim.addCohortDefinition("23", map(getPatientsSeenOnDay(22), "startDate=${startDate}"));
		dim.addCohortDefinition("24", map(getPatientsSeenOnDay(23), "startDate=${startDate}"));
		dim.addCohortDefinition("25", map(getPatientsSeenOnDay(24), "startDate=${startDate}"));
		dim.addCohortDefinition("26", map(getPatientsSeenOnDay(25), "startDate=${startDate}"));
		dim.addCohortDefinition("27", map(getPatientsSeenOnDay(26), "startDate=${startDate}"));
		dim.addCohortDefinition("28", map(getPatientsSeenOnDay(27), "startDate=${startDate}"));
		dim.addCohortDefinition("29", map(getPatientsSeenOnDay(28), "startDate=${startDate}"));
		dim.addCohortDefinition("30", map(getPatientsSeenOnDay(29), "startDate=${startDate}"));
		dim.addCohortDefinition("31", map(getPatientsSeenOnDay(30), "startDate=${startDate}"));
		
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
		dim.addCohortDefinition("<5", map(commonLibrary.createXtoYAgeCohort(0, 4), "effectiveDate=${effectiveDate}"));
		dim.addCohortDefinition(">5", map(commonLibrary.createXtoYAgeCohort(5, 59), "effectiveDate=${effectiveDate}"));
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
	
	/**
	 * Get patients who fall in a special clinic
	 * 
	 * @return @{@link org.openmrs.module.reporting.indicator.dimension.CohortDimension}
	 */
	public CohortDefinitionDimension getSpecialClinicVisits() {
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.setName("Special clinic visits");
		dim.addParameter(new Parameter("onOrAfter", "After date", Date.class));
		dim.addParameter(new Parameter("onOrBefore", "Before date", Date.class));
		dim.addCohortDefinition(
		    "ENT",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENT)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "EYE",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.EYE_CLINIC)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "TBL",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.TBL)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "STI",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.STI)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "CCC",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.CCC_CLINIC)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "PSY",
		    map(moh717CohortDefinition
		            .getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.PSYCHIATRIC_CLINIC)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "ORT",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ORT)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "OCP",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.OCP)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "PHYS",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.PHYS)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "SC",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.SC)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "PAED",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.PAED)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "OG",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.OG)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "NUC",
		    map(moh717CohortDefinition
		            .getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.NUTRITION_PROGRAM)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "ONC",
		    map(moh717CohortDefinition
		            .getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ONCOLOGY_CLINIC)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		dim.addCohortDefinition(
		    "RENAL",
		    map(moh717CohortDefinition.getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.RENAL_CLINIC)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		return dim;
	}
	
	public CohortDefinition getPatientsSeenOnDay(int day) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Get patients seen on a given day");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id"
		        + " WHERE p.voided= 0 AND e.voided = 0 AND e.encounter_datetime BETWEEN DATE_ADD(:startDate, INTERVAL "
		        + day + " DAY) AND DATE_ADD(DATE_ADD(DATE_ADD(:startDate, INTERVAL " + day
		        + " DAY), INTERVAL 23 HOUR), INTERVAL 59 MINUTE) ");
		return cd;
	}
	
	public CohortDefinitionDimension newOrRevisits() {
		EncounterType registrationInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "8efa1534-f28f-11ea-b25f-af56118cf21b");
		EncounterType revisitInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "98d42234-f28f-11ea-b609-bbd062a0383b");
		CohortDefinitionDimension dim = new CohortDefinitionDimension();
		dim.setName("New or revists patients");
		dim.addParameter(new Parameter("startDate", "After date", Date.class));
		dim.addParameter(new Parameter("endDate", "Before date", Date.class));
		dim.addCohortDefinition(
		    "RVT",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.REVISIT_PATIENT)
		            .getConceptId(), registrationInitial.getEncounterTypeId(), revisitInitial.getEncounterTypeId()),
		        "startDate=${startDate},endDate=${endDate+1d}"));
		dim.addCohortDefinition(
		    "NEW",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.NEW_PATIENT).getConceptId(),
		        registrationInitial.getEncounterTypeId(), revisitInitial.getEncounterTypeId()),
		        "startDate=${startDate},endDate=${endDate+1d}"));
		return dim;
	}
}
