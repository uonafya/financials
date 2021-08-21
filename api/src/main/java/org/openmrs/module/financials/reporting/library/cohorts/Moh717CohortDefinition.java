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
	
	EncounterType patientQueueEncounter = Context.getEncounterService().getEncounterTypeByUuid(
	    "356d447a-b494-11ea-8337-f7bcaf3e8fec");
	
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
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get new patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("ALL", ReportUtils.map(getAllPatients(patientQueueEncounter.getEncounterTypeId()),
		    "startDate=${startDate},endDate=${endDate+1d}"));
		cd.addSearch(
		    "NEW",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.NEW_PATIENT).getConceptId(),
		        patientQueueEncounter.getEncounterTypeId()), "startDate=${startDate},endDate=${endDate+1d}"));
		cd.setCompositionString("ALL AND NEW");
		return cd;
		
	}
	
	/**
	 * Get new patients
	 * 
	 * @return @{@link CohortDefinition}
	 */
	public CohortDefinition getRevisitPatients() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get revisit patients");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("ALL", ReportUtils.map(getAllPatients(patientQueueEncounter.getEncounterTypeId()),
		    "startDate=${startDate},endDate=${endDate+1d}"));
		cd.addSearch(
		    "RVT",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.REVISIT_PATIENT)
		            .getConceptId(), patientQueueEncounter.getEncounterTypeId()),
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
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get new patients on special clinics");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("SPC", map(getSpecialClinicPatients(), "startDate=${startDate},endDate=${endDate+1d}"));
		cd.addSearch(
		    "NEW",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.NEW_PATIENT).getConceptId(),
		        patientQueueEncounter.getEncounterTypeId()), "startDate=${startDate},endDate=${endDate+1d}"));
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
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Get revisit patients on special clinics");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("SPC", map(getSpecialClinicPatients(), "startDate=${startDate},endDate=${endDate+1d}"));
		cd.addSearch(
		    "RVT",
		    map(commonLibrary.getPatientStates(getConcept(EhrAddonsConstants._EhrAddOnConcepts.REVISIT_PATIENT)
		            .getConceptId(), patientQueueEncounter.getEncounterTypeId()),
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
}
