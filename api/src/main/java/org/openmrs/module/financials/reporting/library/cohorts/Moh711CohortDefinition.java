package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.Program;
import org.openmrs.api.PatientSetService;
import org.openmrs.module.financials.reporting.calculation.GestationPeriodCalculation;
import org.openmrs.module.financials.reporting.library.common.EhrAddonCommons;
import org.openmrs.module.kenyacore.report.cohort.definition.CalculationCohortDefinition;
import org.openmrs.module.kenyaemr.Dictionary;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.NumericObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.RangeComparator;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh711CohortDefinition {
	
	private EhrAddonCommons ehrAddonCommons;
	
	@Autowired
	public Moh711CohortDefinition(EhrAddonCommons ehrAddonCommons) {
		this.ehrAddonCommons = ehrAddonCommons;
	}
	
	public CohortDefinition getAllAncPmtctClients(Concept... entryPoints) {
		Program program = MetadataUtils.existing(Program.class, "e8751e5c-fbda-11ea-9bba-ff7e8cea17d3");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("MOH 711 patients - ANC and PMTCT Clients");
		cd.addSearch("PMTCTHIV", map(referredFrom(entryPoints), "onOrAfter=${startDate},onOrBefore=${endDate}"));
		cd.addSearch("ANC",
		    map(ehrAddonCommons.programEnrollment(program), "enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate}"));
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
	
	public CohortDefinition getPatientWithCodedObs(Concept question, Concept... answers) {
		CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
		cd.setName("Has observation of " + question.getName().getName());
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
		cd.setQuestion(question);
		cd.setValueList(Arrays.asList(answers));
		cd.setOperator(SetComparator.IN);
		return cd;
	}
	
	public CohortDefinition getIptVaccinesGivenToMothers(int squence) {
		Program program = MetadataUtils.existing(Program.class, "335517a1-04bc-438b-9843-1ba49fb7fcd9");
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("IPT Vaccines given to the maother");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("program",
		    map(ehrAddonCommons.programEnrollment(program), "enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate}"));
		cd.addSearch("dose1", map(getIptDosesSql(1), ""));
		cd.addSearch("dose2", map(getIptDosesSql(2), ""));
		cd.addSearch("dose3", map(getIptDosesSql(3), ""));
		if (squence == 1) {
			cd.setCompositionString("(program AND dose1) AND NOT (dose2 OR dose3)");
		} else if (squence == 2) {
			cd.setCompositionString("(program AND dose2) AND NOT (dose1 OR dose3)");
		} else if (squence == 3) {
			cd.setCompositionString("(program AND dose3) AND NOT (dose1 OR dose2)");
		}
		
		return cd;
	}
	
	private CohortDefinition getIptDosesSql(int threshold) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		EncounterType iptEncounterType = MetadataUtils.existing(EncounterType.class, "aadeafbe-a3b1-4c57-bc76-8461b778ebd6");
		cd.setName("IPT doses in sequency");
		cd.setQuery("SELECT hold.patient_id FROM (SELECT p.patient_id, COUNT(e.encounter_id) FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " WHERE p.voided=0 AND e.voided=0 AND e.encounter_type="
		        + iptEncounterType.getEncounterTypeId()
		        + " GROUP BY p.patient_id HAVING COUNT(e.encounter_id)=" + threshold + ") hold");
		return cd;
	}
	
	public CohortDefinition getAllClientsWithHbLessThanAthreshold(Concept question, int threshold,
	        RangeComparator rangeComparator) {
		NumericObsCohortDefinition cd = new NumericObsCohortDefinition();
		cd.setName("Threshold value for the " + question.getName().getName() + " of value <" + threshold);
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.setTimeModifier(PatientSetService.TimeModifier.ANY);
		cd.setQuestion(question);
		cd.setOperator1(rangeComparator);
		cd.setValue1((double) threshold);
		return cd;
	}
	
	public CohortDefinition getMotherServicesProgramAndServices(Program program, Concept question, Concept... ans) {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		cd.setName("Program and services offered to the pregnant women");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("PROGRAM",
		    map(ehrAddonCommons.programEnrollment(program), "enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate}"));
		cd.addSearch("CODED", map(getPatientWithCodedObs(question, ans), "onOrAfter=${startDate},onOrBefore=${endDate}"));
		cd.setCompositionString("PROGRAM OR CODED");
		return cd;
	}
	
	public CohortDefinition getGestationPeriod(Integer period) {
		CalculationCohortDefinition cd = new CalculationCohortDefinition("Gestation", new GestationPeriodCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addCalculationParameter("period", period);
		return cd;
		
	}

	//GBV cohorts
	public CohortDefinition getSgbvCases() {
		CompositionCohortDefinition cd = new CompositionCohortDefinition();
		String mappings = "startDate=${onOrAfter},endDate=${onOrBefore}";
		EncounterType gbv1 = MetadataUtils.existing(EncounterType.class, "03767614-1384-4ce3-aea9-27e2f4e67d01");
		EncounterType gbv2 = MetadataUtils.existing(EncounterType.class, "bec91024-5433-11ec-8ddd-bf8f24d733fa");
		cd.setName("All the SGBV cases reported");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addSearch("CODED", map(getPatientWithCodedObs(Dictionary.getConcept("17b33cd3-1af9-4a1b-a65b-b5e30540b189"), Dictionary.getConcept("126582AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), mappings));
		cd.addSearch("ENCOUNTER", map(hasEncounter(gbv1, gbv2), mappings));
		cd.setCompositionString("CODED OR ENCOUNTER");

		return cd;
	}

	/**
	 * Patients who have an encounter between ${onOrAfter} and ${onOrBefore}
	 * @param types the encounter types
	 * @return the cohort definition
	 */
	public CohortDefinition hasEncounter(EncounterType... types) {
		EncounterCohortDefinition cd = new EncounterCohortDefinition();
		cd.setName("has encounter between dates");
		cd.setTimeQualifier(TimeQualifier.ANY);
		cd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		cd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		if (types.length > 0) {
			cd.setEncounterTypeList(Arrays.asList(types));
		}
		return cd;
	}
}
