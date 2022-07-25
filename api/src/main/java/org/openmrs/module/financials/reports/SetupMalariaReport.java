package org.openmrs.module.financials.reports;

import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.reporting.converter.ObsCommentsConverter;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.hiv.CountyAddressCalculation;
import org.openmrs.module.kenyaemr.calculation.library.hiv.SubCountyAddressCalculation;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.DateConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.converter.ObsValueConverter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ObsForPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.data.visit.definition.SqlVisitDataDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.VisitDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.query.visit.definition.BasicVisitQuery;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.financials.reports.SetupANCRegisterReport.ENC_DATE_FORMAT;

@Component
@Builds({ "ehraddons.common.report.malaria" })
public class SetupMalariaReport extends AbstractHybridReportBuilder {
	
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	private CommonDatasetDefinition commonDatasetDefinition;
	
	@Autowired
	public SetupMalariaReport(CommonDatasetDefinition commonDatasetDefinition) {
		this.commonDatasetDefinition = commonDatasetDefinition;
	}
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor hybridReportDescriptor,
	        PatientDataSetDefinition patientDataSetDefinition) {
		return null;
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	@Override
	protected void addColumns(HybridReportDescriptor report, PatientDataSetDefinition dsd) {
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		
		report.setBaseCohortDefinition(ReportUtils.map(allMalariaPatientsCohort(),
		    "startDate=${startDate},endDate=${endDate+23h}"));
		return Arrays.asList(ReportUtils.map(allPatients(), "startDate=${startDate},endDate=${endDate+23h}"));
	}
	
	protected DataSetDefinition allPatients() {
		VisitDataSetDefinition dsd = new VisitDataSetDefinition();
		dsd.setName("mal");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addSortCriteria("Visit Date", SortCriteria.SortDirection.ASC);
		
		PatientIdentifierType upn = MetadataUtils.existing(PatientIdentifierType.class,
		    CommonMetadata._PatientIdentifierType.PATIENT_CLINIC_NUMBER);
		DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
		DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(
		        upn.getName(), upn), identifierFormatter);
		
		DataConverter formatter = new ObjectFormatter("{familyName}, {middleName} {givenName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), formatter);
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("Visit Date", getVisitDate(), "", new DateConverter(ENC_DATE_FORMAT));
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Identifier", identifierDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("Age", new AgeDataDefinition(), "", null);
		dsd.addColumn("SubCounty", new CalculationDataDefinition("SubCounty", new SubCountyAddressCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("County", new CalculationDataDefinition("County", new CountyAddressCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("Results", new ObsForPersonDataDefinition("Results", TimeQualifier.LAST, Context.getConceptService()
		        .getConceptByUuid("32AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), null, null), "", new ObsValueConverter());
		dsd.addColumn("Comments", new ObsForPersonDataDefinition("Comments", TimeQualifier.LAST, Context.getConceptService()
		        .getConceptByUuid("32AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), null, null), "", new ObsCommentsConverter());
		dsd.addColumn("Onset Date", new ObsForPersonDataDefinition("Onset Date", TimeQualifier.LAST, Context
		        .getConceptService().getConceptByUuid("164428AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), null, null), "",
		    new ObsCommentsConverter());
		
		dsd.addRowFilter(new BasicVisitQuery(), "startedOnOrAfter=${startDate},startedOnOrBefore=${endDate}");
		return dsd;
	}
	
	protected CohortDefinition allMalariaPatientsCohort() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Active Patients");
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN encounter e ON p.patient_id=e.patient_id INNER JOIN obs o ON e.encounter_id=o.encounter_id WHERE e.encounter_datetime BETWEEN :startDate AND :endDate AND o.concept_id IN(32,1643)");
		return cd;
	}
	
	private DataDefinition getVisitDate() {
		SqlVisitDataDefinition visitDataDefinition = new SqlVisitDataDefinition();
		visitDataDefinition
		        .setSql("SELECT p.patient_id, v.date_started FROM patient p INNER JOIN visit v ON p.patient_id=v.patient_id WHERE p.voided=0 AND v.voided=0");
		return visitDataDefinition;
	}
}
