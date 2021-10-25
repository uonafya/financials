package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Program;
import org.openmrs.api.PatientSetService;
import org.openmrs.module.kenyaemr.Dictionary;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.ProgramEnrollmentCohortDefinition;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh711CohortDefinition {
	
	public CohortDefinition getAllAncPmtctClients(Concept... entryPoints) {
		Program program = MetadataUtils.existing(Program.class, "e8751e5c-fbda-11ea-9bba-ff7e8cea17d3");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("MOH 711 patients - ANC and PMTCT Clients");
		cd.addSearch("PMTCTHIV", map(referredFrom(entryPoints), "onOrAfter=${startDate},onOrBefore=${endDate}"));
		cd.addSearch("ANC", map(programEnrollment(program), "enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate}"));
		cd.setCompositionString("PMTCTHIV OR ANC");
		return cd;
	}
	
	/**
	 * Patients referred from the given entry point onto the various programs
	 * 
	 * @param entryPoints the entry point concepts
	 * @return the cohort definition
	 */
	public CohortDefinition referredFrom(Concept... entryPoints) {
		EncounterType hivEnrollEncType = MetadataUtils.existing(EncounterType.class,
		    HivMetadata._EncounterType.HIV_ENROLLMENT);
		Concept methodOfEnrollment = Dictionary.getConcept(Dictionary.METHOD_OF_ENROLLMENT);
		
		CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
		cd.setName("referred from");
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
		cd.setQuestion(methodOfEnrollment);
		cd.setValueList(Arrays.asList(entryPoints));
		cd.setOperator(SetComparator.IN);
		cd.setEncounterTypeList(Collections.singletonList(hivEnrollEncType));
		return cd;
	}
	
	/**
	 * Patients enrolled in different programs within the EHR
	 */
	public CohortDefinition programEnrollment(Program... programs) {
		ProgramEnrollmentCohortDefinition cd = new ProgramEnrollmentCohortDefinition();
		cd.setName("enrolled in program between dates");
		cd.addParameter(new Parameter("enrolledOnOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("enrolledOnOrBefore", "Before Date", Date.class));
		if (programs.length > 0) {
			cd.setPrograms(Arrays.asList(programs));
		}
		return cd;
	}
}
