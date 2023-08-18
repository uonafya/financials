package org.openmrs.module.financials.reports;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.reporting.calculation.CurrentDrugsCalculation;
import org.openmrs.module.financials.reporting.calculation.RevisitPatientCalculation;
import org.openmrs.module.financials.reporting.calculation.VillageAndLandmarkCalculation;
import org.openmrs.module.financials.reporting.converter.DrugListConverter;
import org.openmrs.module.financials.reporting.converter.EncounterDateConverter;
import org.openmrs.module.financials.reporting.converter.ObsValueListConverter;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.calculation.library.TelephoneNumberCalculation;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.kenyaemr.reporting.data.converter.IdentifierConverter;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.common.TimeQualifier;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.converter.ObsValueConverter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.EncountersForPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ObsForPersonDataDefinition;
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

import static org.openmrs.module.financials.reports.SetupMalariaReportRegister.DATE_FORMAT;

@Component
@Builds({ "financials.common.report.240" })
public class SetupMOH240RegisterReport extends AbstractHybridReportBuilder {
	
	private CommonDatasetDefinition commonDatasetDefinition;
	
	@Autowired
	public SetupMOH240RegisterReport(CommonDatasetDefinition commonDatasetDefinition) {
		this.commonDatasetDefinition = commonDatasetDefinition;
	}
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor hybridReportDescriptor,
	        PatientDataSetDefinition patientDataSetDefinition) {
		return null;
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		PatientDataSetDefinition dsd = LabRegister();
		dsd.setName("lrr");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		report.setBaseCohortDefinition(ReportUtils.map(getLabOrderEncounter(), "startDate=${startDate},endDate=${endDate}"));
		
		return Arrays.asList(ReportUtils.map((DataSetDefinition) dsd, "startDate=${startDate},endDate=${endDate}"));
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	private PatientDataSetDefinition LabRegister() {
		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.setName("lrr");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addRowFilter(getLabOrderEncounter(), "startDate=${startDate},endDate=${endDate+23h}");
		DataConverter nameFormatter = new ObjectFormatter("{familyName}, {givenName}, {middleName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);
		
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		dsd.addColumn("identifier", getRevisit("ID"), "endDate=${endDate+23h}", new CalculationResultConverter());
		dsd.addColumn("Date", getEncounterDate(), "onOrAfter=${startDate},onOrBefore=${endDate+23h}",
		    new EncounterDateConverter());
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "", null);
		dsd.addColumn("DOB", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("age", new AgeDataDefinition(), "");
		dsd.addColumn("village", new CalculationDataDefinition("village", new VillageAndLandmarkCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("telephone", new CalculationDataDefinition("telephone", new TelephoneNumberCalculation()), "",
		    new CalculationResultConverter());
		dsd.addColumn("STS", getRevisit("ST"), "endDate=${endDate+23h}", new CalculationResultConverter());
		dsd.addColumn("INV",
		    getObservation(EhrAddonsConstants.getConcept("0179f241-8c1d-47c1-8128-841f6508e251"), TimeQualifier.ANY),
		    "onOrAfter=${startDate},onOrBefore=${endDate+23h}", new ObsValueListConverter());
		dsd.addColumn(
		    "DIAG",
		    getObservation(EhrAddonsConstants.getConcept(EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS),
		        TimeQualifier.LAST), "onOrAfter=${startDate},onOrBefore=${endDate+23h}", new ObsValueConverter());
		dsd.addColumn("DR", getDrugs(), "endDate=${endDate+23h}", new DrugListConverter());
		return dsd;
		
	}
	
	private DataDefinition getDrugs() {
		CalculationDataDefinition cd = new CalculationDataDefinition("DR", new CurrentDrugsCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		return cd;
		
	}
	
	private DataDefinition getEncounterDate() {
		EncounterType labEncounter = Context.getEncounterService().getEncounterTypeByUuid(
		    "11d3f37a-f282-11ea-a825-1b5b1ff1b854");
		EncountersForPatientDataDefinition dsd = new EncountersForPatientDataDefinition();
		dsd.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		dsd.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		dsd.setTypes(Arrays.asList(labEncounter));
		dsd.setWhich(TimeQualifier.LAST);
		return dsd;
	}
	
	private DataDefinition getRevisit(String flag) {
		CalculationDataDefinition cd = new CalculationDataDefinition("RVT", new RevisitPatientCalculation());
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.addCalculationParameter("flag", flag);
		return cd;
		
	}
	
	private DataDefinition getObservation(Concept question, TimeQualifier timeQualifier) {
		ObsForPersonDataDefinition obs = new ObsForPersonDataDefinition();
		obs.addParameter(new Parameter("onOrAfter", "After Date", Date.class));
		obs.addParameter(new Parameter("onOrBefore", "Before Date", Date.class));
		obs.setWhich(timeQualifier);
		obs.setQuestion(question);
		return obs;
		
	}
	
	private CohortDefinition getLabOrderEncounter() {
		EncounterType labEncounter = Context.getEncounterService().getEncounterTypeByUuid(
		    "11d3f37a-f282-11ea-a825-1b5b1ff1b854");
		SqlCohortDefinition sqlEncounterQuery = new SqlCohortDefinition();
		sqlEncounterQuery.setName("Get unique lab encounter types");
		sqlEncounterQuery.addParameter(new Parameter("startDate", "Start Date", Date.class));
		sqlEncounterQuery.addParameter(new Parameter("endDate", "End Date", Date.class));
		sqlEncounterQuery
		        .setQuery("SELECT p.patient_id FROM patient p INNER JOIN  encounter e ON p.patient_id=e.patient_id "
		                + " WHERE e.encounter_datetime BETWEEN :startDate AND :endDate AND e.voided=0 AND p.voided = 0 "
		                + " AND e.encounter_type IN(" + labEncounter.getEncounterTypeId() + ")");
		return sqlEncounterQuery;
	}
}
