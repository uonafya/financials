package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.Concept;
import org.openmrs.api.PatientSetService;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.metadata.EhrAddonsMetadata;
import org.openmrs.module.financials.reporting.library.queries.Moh717Queries;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class Moh717CohortDefinition {
	
	public CohortDefinition getAllPatients() {
		SqlCohortDefinition sqlCohortDefinition = new SqlCohortDefinition();
		sqlCohortDefinition.setName("All patients for 717 report");
		sqlCohortDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sqlCohortDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		sqlCohortDefinition
		        .setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate");
		return sqlCohortDefinition;
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
