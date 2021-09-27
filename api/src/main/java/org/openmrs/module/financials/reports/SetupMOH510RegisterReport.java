package org.openmrs.module.financials.reports;

import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyaemr.reporting.calculation.converter.GenderConverter;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
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

import static org.openmrs.module.financials.reports.SetupMalariaReport.DATE_FORMAT;

@Component
@Builds({ "ehraddons.common.report.510" })
public class SetupMOH510RegisterReport extends AbstractHybridReportBuilder {
	
	private CommonDatasetDefinition commonDatasetDefinition;
	
	@Autowired
	public SetupMOH510RegisterReport(CommonDatasetDefinition commonDatasetDefinition) {
		this.commonDatasetDefinition = commonDatasetDefinition;
	}
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor hybridReportDescriptor,
	        PatientDataSetDefinition patientDataSetDefinition) {
		return null;
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		PatientDataSetDefinition dsd = getImmunizationRegister();
		dsd.setName("imr");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		report.setBaseCohortDefinition(all510PatientsCohort());
		
		return Arrays.asList(ReportUtils.map((DataSetDefinition) dsd, "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(commonDatasetDefinition.getFacilityMetadata(), ""));
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	private PatientDataSetDefinition getImmunizationRegister() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		DataConverter nameFormatter = new ObjectFormatter("{familyName}, {givenName}, {middleName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "", new GenderConverter());
		dsd.addColumn("Date of Birth", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		return dsd;
		
	}
	
	private Mapped<CohortDefinition> all510PatientsCohort() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setName("Admitted patients");
		cd.setQuery("SELECT p.patient_id FROM patient p INNER JOIN person pe ON p.patient_id=pe.patient_id  WHERE TIMESTAMPDIFF(YEAR, pe.birthdate, :endDate) < 5");
		return ReportUtils.map((CohortDefinition) cd, "startDate=${startDate},endDate=${endDate}");
	}
}
