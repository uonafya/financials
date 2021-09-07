package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.api.PatientSetService;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.reporting.library.common.EhrAddonCommons;
import org.openmrs.module.financials.reporting.library.queries.Moh717Queries;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

import static org.openmrs.module.financials.EhrAddonsConstants.getConcept;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh717CohortDefinition {
	
	@Autowired
	private EhrAddonCommons commonLibrary;
	
	public CohortDefinition getAllPatients(int initialQueue) {
		SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition();
		sqlCohortDefinition.setName("All patients for 717 report");
		sqlCohortDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sqlCohortDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		sqlCohortDefinition
		        .setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate AND e.encounter_type = "
		                + initialQueue);
		return sqlCohortDefinition;
	}
	
	/**
	 * Get new patients
	 * 
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getNewPatients() {
		EncounterType registrationInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "8efa1534-f28f-11ea-b25f-af56118cf21b");
		EncounterType revisitInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "98d42234-f28f-11ea-b609-bbd062a0383b");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get new patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("ALL", ReportUtils.map(getAllPatients(registrationInitial.getEncounterTypeId()),
		    "startDate=${startDate},endDate=${endDate+1d}"));
		cd.addSearch(
		    "NEW",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.NEW_PATIENT).getConceptId(),
		        registrationInitial.getEncounterTypeId(), revisitInitial.getEncounterTypeId()),
		        "startDate=${startDate},endDate=${endDate+1d}"));
		cd.setCompositionString("ALL AND NEW");
		return cd;
		
	}
	
	/**
	 * Get new patients
	 * 
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getRevisitPatients() {
		EncounterType registrationInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "8efa1534-f28f-11ea-b25f-af56118cf21b");
		EncounterType revisitInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "98d42234-f28f-11ea-b609-bbd062a0383b");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get revisit patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("ALL", ReportUtils.map(getAllPatients(registrationInitial.getEncounterTypeId()),
		    "startDate=${startDate},endDate=${endDate+1d}"));
		cd.addSearch(
		    "RVT",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.REVISIT_PATIENT)
		            .getConceptId(), registrationInitial.getEncounterTypeId(), revisitInitial.getEncounterTypeId()),
		        "startDate=${startDate},endDate=${endDate+1d}"));
		cd.setCompositionString("ALL AND RVT");
		return cd;
		
	}
	
	/**
	 * Get special clinic patients
	 * 
	 * @param
	 * @return
	 */
	public CohortDefinition getSpecialClinicPatients() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Special Clinic Patients");
		cd.addParameter(new Parameter("startDate", "startDate", Date.class));
		cd.addParameter(new Parameter("endDate", "endDate", Date.class));
		cd.setQuery(Moh717Queries.getSpecialClinicPatients(EhrAddonsConstants.getConcept(
		    EhrAddonsConstants._EhrAddOnConcepts.SPECIAL_CLINIC).getConceptId()));
		
		return cd;
	}
	
	/**
	 * Get special clinic new patients
	 * 
	 * @param
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getNewSpecialClinicPatients() {
		EncounterType registrationInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "8efa1534-f28f-11ea-b25f-af56118cf21b");
		EncounterType revisitInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "98d42234-f28f-11ea-b609-bbd062a0383b");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get new patients on special clinics");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("SPC", map(getSpecialClinicPatients(), "startDate=${startDate},endDate=${endDate+1d}"));
		cd.addSearch(
		    "NEW",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.NEW_PATIENT).getConceptId(),
		        registrationInitial.getEncounterTypeId(), revisitInitial.getEncounterTypeId()),
		        "startDate=${startDate},endDate=${endDate+1d}"));
		cd.setCompositionString("SPC AND NEW");
		return cd;
	}
	
	/**
	 * Get special clinic revisit patients
	 * 
	 * @param
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getRevistSpecialClinicPatients() {
		EncounterType registrationInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "8efa1534-f28f-11ea-b25f-af56118cf21b");
		EncounterType revisitInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "98d42234-f28f-11ea-b609-bbd062a0383b");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get revisit patients on special clinics");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("SPC", map(getSpecialClinicPatients(), "startDate=${startDate},endDate=${endDate+1d}"));
		cd.addSearch(
		    "RVT",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.REVISIT_PATIENT)
		            .getConceptId(), registrationInitial.getEncounterTypeId(), revisitInitial.getEncounterTypeId()),
		        "startDate=${startDate},endDate=${endDate+1d}"));
		cd.setCompositionString("SPC AND RVT");
		return cd;
	}
	
	public CohortDefinition getSpecialClinicVisits(Concept concept) {
		CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
		cd.addParameter(new Parameter("onOrAfter", "After date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before date", Date.class));
		cd.setName("Special clinic visits by clinic type");
		cd.setQuestion(EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.SPECIAL_CLINIC));
		cd.setOperator(SetComparator.IN);
		cd.setTimeModifier(PatientSetService.TimeModifier.LAST);
		cd.setValueList(Arrays.asList(concept));
		return cd;
	}
	
	private CohortDefinition getMopcFromTriageOrOpd() {
		SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition();
		sqlCohortDefinition.setName("Special clinic from triage and opd");
		sqlCohortDefinition.addParameter(new Parameter("onOrAfter", "Start Date", Date.class));
		sqlCohortDefinition.addParameter(new Parameter("onOrBefore", "End Date", Date.class));
		sqlCohortDefinition.setQuery(Moh717Queries.getMopcClinicQuery(getConcept(EhrAddonsConstants._EhrAddOnConcepts.MOPC)
		        .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MOPC_TRAIGE).getConceptId()));
		return sqlCohortDefinition;
	}
	
	public CohortDefinition getMopSpecialClinic() {
		EncounterType registrationInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "8efa1534-f28f-11ea-b25f-af56118cf21b");
		EncounterType revisitInitial = Context.getEncounterService().getEncounterTypeByUuid(
		    "98d42234-f28f-11ea-b609-bbd062a0383b");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.addParameter(new Parameter("onOrAfter", "After date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before date", Date.class));
		cd.setName("MOPC clinic");
		cd.addSearch(
		    "CLINIC",
		    map(getSpecialClinicVisits(getConcept(EhrAddonsConstants._EhrAddOnConcepts.MOPC)),
		        "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		cd.addSearch("TOPD", map(getMopcFromTriageOrOpd(), "onOrAfter=${onOrAfter},onOrBefore=${onOrBefore+1d}"));
		
		cd.setCompositionString("(CLINIC OR TOPD)");
		return cd;
	}
	
	public CohortDefinition getSpecialCLinicPatientsOutOfRange() {
		SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition();
		sqlCohortDefinition.setName("Special clinic reports out of range");
		sqlCohortDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sqlCohortDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		sqlCohortDefinition.setQuery(Moh717Queries.getSpecialClinicPatientsOutsideRange(
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.SPECIAL_CLINIC).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENT).getConceptId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.EYE_CLINIC).getConceptId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.TBL).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.STI).getConceptId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.CCC_CLINIC).getConceptId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.PSYCHIATRIC_CLINIC).getConceptId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.ORT).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.OCP).getConceptId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.PHYS).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.SC).getConceptId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.PAED).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.PAED).getConceptId(), EhrAddonsConstants
		            .getConcept(EhrAddonsConstants._EhrAddOnConcepts.MOPC).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.NUTRITION_PROGRAM).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.ONCOLOGY_CLINIC).getConceptId(),
		    EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.RENAL_CLINIC).getConceptId()));
		return sqlCohortDefinition;
	}
}
