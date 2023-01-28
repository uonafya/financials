package org.openmrs.module.financials.reports;

import org.openmrs.PatientIdentifierType;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.SqlPatientDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Builds({ "financials.common.report.705.b.all" })
public class SetupAllDiagnosisForAdultsReport extends AbstractHybridReportBuilder {
	
	private CommonDatasetDefinition commonDatasetDefinition;
	
	@Autowired
	public SetupAllDiagnosisForAdultsReport(CommonDatasetDefinition commonDatasetDefinition) {
		this.commonDatasetDefinition = commonDatasetDefinition;
	}
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor hybridReportDescriptor,
	        PatientDataSetDefinition patientDataSetDefinition) {
		return null;
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		PatientDataSetDefinition allAdultsDiagnosis = allPatientsDiagnisChildre();
		allAdultsDiagnosis.addParameter(new Parameter("startDate", "Start Date", Date.class));
		allAdultsDiagnosis.addParameter(new Parameter("endDate", "End Date", Date.class));
		allAdultsDiagnosis.setName("All adults data set bulider");
		allAdultsDiagnosis.addRowFilter(allAdultsDiagnosisPatientsCohort());
		report.setBaseCohortDefinition(allAdultsDiagnosisPatientsCohort());
		return Arrays.asList(
		    ReportUtils.map((DataSetDefinition) allAdultsDiagnosis, "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(commonDatasetDefinition.getFacilityMetadata(), ""));
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	protected PatientDataSetDefinition allPatientsDiagnisChildre() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("ball1");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		
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
		dsd.addColumn("Diagnosis", getDiagnosis(), "startDate=${startDate},endDate=${endDate}", null);
		dsd.addColumn("Date", getDiagnosisDate(), "startDate=${startDate},endDate=${endDate}", null);
		
		return dsd;
	}
	
	protected Mapped<CohortDefinition> allAdultsDiagnosisPatientsCohort() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Active Adults Patients");
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id INNER JOIN obs o ON e.encounter_id=o.encounter_id "
		        + " INNER JOIN concept_name cn ON cn.concept_id = o.value_coded "
		        + " INNER JOIN person pe ON p.patient_id=pe.person_id "
		        + " INNER JOIN concept c ON c.concept_id = cn.concept_id "
		        + " WHERE e.encounter_datetime BETWEEN :startDate AND :endDate "
		        + " AND o.value_coded IS NOT NULL "
		        + " AND cn.locale = 'en' AND cn.locale_preferred = 1 "
		        + " AND TIMESTAMPDIFF(YEAR, pe.birthdate, :endDate) >= 5 " + " AND c.class_id IN(4)");
		return ReportUtils.map((CohortDefinition) cd, "startDate=${startDate},endDate=${endDate}");
	}
	
	private DataDefinition getDiagnosis() {
		SqlPatientDataDefinition cd = new SqlPatientDataDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Diagnosis");
		cd.setSql("SELECT p.patient_id, cn.name FROM patient p " + " INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id "
		        + " INNER JOIN concept_name cn ON cn.concept_id = o.value_coded "
		        + " INNER JOIN concept c ON c.concept_id = cn.concept_id "
		        + " WHERE e.encounter_datetime BETWEEN :startDate AND :endDate "
		        + " AND o.value_coded IS NOT NULL AND c.class_id IN(4)"
		        + " AND cn.locale = 'en' AND cn.locale_preferred = 1 ");
		return cd;
	}
	
	private DataDefinition getDiagnosisDate() {
		SqlPatientDataDefinition cd = new SqlPatientDataDefinition();
		cd.setName("Diagnosis");
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setSql("SELECT p.patient_id, e.encounter_datetime FROM patient p "
		        + " INNER JOIN encounter e ON p.patient_id=e.patient_id "
		        + " INNER JOIN obs o ON e.encounter_id=o.encounter_id "
		        + " INNER JOIN concept_name cn ON cn.concept_id = o.value_coded "
		        + " INNER JOIN concept c ON c.concept_id = cn.concept_id "
		        + " WHERE e.encounter_datetime BETWEEN :startDate AND :endDate "
		        + " AND o.value_coded IS NOT NULL AND c.class_id IN(4)"
		        + " AND cn.locale = 'en' AND cn.locale_preferred = 1 ");
		return cd;
	}
}
