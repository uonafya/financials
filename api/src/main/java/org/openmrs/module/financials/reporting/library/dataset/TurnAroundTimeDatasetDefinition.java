package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.EncounterType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.module.ehrconfigs.metadata.EhrCommonMetadata;
import org.openmrs.module.financials.reporting.converter.EncounterDateConverter;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.encounter.definition.EncounterDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.EncountersForPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.EncounterDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TurnAroundTimeDatasetDefinition {
	
	public PatientDataSetDefinition turnAroundTime() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("tat");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		String defParam = "onOrAfter=${startDate},onOrBefore=${endDate}";
		
		EncounterType registration = MetadataUtils.existing(EncounterType.class, "356d447a-b494-11ea-8337-f7bcaf3e8fec");
		EncounterType triage = MetadataUtils.existing(EncounterType.class,
		    EhrCommonMetadata._EhrEncounterTypes.TRIAGEENCOUNTER);
		EncounterType opd = MetadataUtils.existing(EncounterType.class, EhrCommonMetadata._EhrEncounterTypes.OPDENCOUNTER);
		EncounterType lab = MetadataUtils.existing(EncounterType.class, EhrCommonMetadata._EhrEncounterTypes.LABENCOUNTER);
		
		PatientIdentifierType upn = MetadataUtils.existing(PatientIdentifierType.class,
		    CommonMetadata._PatientIdentifierType.OPENMRS_ID);
		DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
		DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(
		        upn.getName(), upn), identifierFormatter);
		
		DataConverter formatter = new ObjectFormatter("{familyName}, {givenName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), formatter);
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Identifier", identifierDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter("dd-MM-yyyy"));
		dsd.addColumn("Age", new AgeDataDefinition(), "", null);
		dsd.addColumn("Registration", getEncounterDate(registration), defParam, new EncounterDateConverter());
		dsd.addColumn("Traige", getEncounterDate(triage), defParam, new EncounterDateConverter());
		dsd.addColumn("OPD", getEncounterDate(opd), defParam, new EncounterDateConverter());
		dsd.addColumn("Lab", getEncounterDate(lab), defParam, new EncounterDateConverter());
		return dsd;
	}
	
	private DataDefinition getEncounterDate(EncounterType encounterType) {
		EncountersForPatientDataDefinition dsd = new EncountersForPatientDataDefinition();
		dsd.setName("Get encounter for the date");
		dsd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		dsd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		dsd.addType(encounterType);
		dsd.setWhich(TimeQualifier.LAST);
		return dsd;
	}
	
	public Mapped<CohortDefinition> allPatientsCohort() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("All Patients");
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate");
		return ReportUtils.map((CohortDefinition) cd, "startDate=${startDate},endDate=${endDate}");
	}
}
