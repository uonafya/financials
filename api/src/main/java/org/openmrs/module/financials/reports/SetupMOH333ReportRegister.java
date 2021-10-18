package org.openmrs.module.financials.reports;

import org.openmrs.PatientIdentifierType;
import org.openmrs.PersonAttributeType;
import org.openmrs.Program;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.reporting.calculation.VillageAndLandmarkCalculation;
import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.financials.reporting.library.indicator.Moh333MaternityIndicatorDefinition;
import org.openmrs.module.kenyacore.report.HybridReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractHybridReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.data.patient.definition.CalculationDataDefinition;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.kenyaemr.reporting.calculation.converter.GenderConverter;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.MaternityRegisterCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.CalculationResultConverter;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.KenyaEMRMaritalStatusDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityANCParityDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityARVProphylaxisIssuedAtMaternityDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityARVProphylaxisIssuedFromANCDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityARVProphylaxisToBabyAtMaternityDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityAdmissionDateDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityAdmissionNumberDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityApgarScoreDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityBabyConditionDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityBabySexDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityBabyWithDeformityDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityBirthNotificationNumberDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityBirthWeightDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityBloodLossDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityCTXToMotherDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityCommentsDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityConditionAfterDeliveryDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityCounselledOnInfantFeedingDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityDeathAuditedDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityDeliveryConductedByDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityDeliveryDateDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityDeliveryModeDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityDeliveryTimeDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityDiagnosisDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityDischargeDateDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityDurationOfLabourDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityEDDUltrasoundDateDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityGestationAtBirthDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityGravidaDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityHIVFinalResultsDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityHIVStatusAtANCDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityHIVTestOneDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityHIVTestTwoDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityInitiatedBFWithinOneHourDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityLMPDateDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityNumberOfANCVisitsDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityOtherDeliveryComplicationsDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityPartnerHIVTestResultsDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityPartnerTestedForHIVDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityPlacentaCompleteDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityReferredFromDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityReferredToDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityStatusOfBabyDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityTEOGivenAtBirthDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityVDRLRPRResultsDataDefinition;
import org.openmrs.module.kenyaemr.reporting.data.converter.definition.maternity.MaternityVitaminADataDefinition;
import org.openmrs.module.kenyaemr.reporting.library.pmtct.MaternityIndicatorLibrary;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.BirthdateConverter;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.DateConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.AgeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.BirthdateDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonAttributeDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PersonIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
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
@Builds({ "ehraddons.common.report.333" })
public class SetupMOH333ReportRegister extends AbstractHybridReportBuilder {
	
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	private Moh333MaternityIndicatorDefinition maternityIndicatorLibrary;
	
	@Autowired
	public SetupMOH333ReportRegister(Moh333MaternityIndicatorDefinition maternityIndicatorLibrary) {
		this.maternityIndicatorLibrary = maternityIndicatorLibrary;
	}
	
	@Override
	protected Mapped<CohortDefinition> buildCohort(HybridReportDescriptor descriptor, PatientDataSetDefinition dsd) {
		dsd.setName("mr");
		return allPatientsCohort();
	}
	
	protected Mapped<CohortDefinition> allPatientsCohort() {
		CohortDefinition cd = new MaternityRegisterCohortDefinition();
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		return ReportUtils.map(cd, "startDate=${startDate},endDate=${endDate}");
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		
		PatientDataSetDefinition allPatients = maternityDataSetDefinition();
		allPatients.setName("mr");
		allPatients.addRowFilter(allPatientsCohort());
		//allPatients.addRowFilter(buildCohort(descriptor));
		DataSetDefinition allPatientsDSD = allPatients;
		
		return Arrays.asList(ReportUtils.map(allPatientsDSD, "startDate=${startDate},endDate=${endDate}"),
		    ReportUtils.map(maternityDataSet(), ""));
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	protected PatientDataSetDefinition maternityDataSetDefinition() {
		
		PatientDataSetDefinition dsd = new PatientDataSetDefinition("mr");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		PatientIdentifierType upn = MetadataUtils.existing(PatientIdentifierType.class,
		    HivMetadata._PatientIdentifierType.UNIQUE_PATIENT_NUMBER);
		DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
		DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(
		        upn.getName(), upn), identifierFormatter);
		
		DataConverter nameFormatter = new ObjectFormatter("{familyName}, {givenName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);
		PersonAttributeType phoneNumber = MetadataUtils.existing(PersonAttributeType.class,
		    CommonMetadata._PersonAttributeType.TELEPHONE_CONTACT);
		dsd.addColumn("id", new PersonIdDataDefinition(), "");
		
		dsd.addColumn("Unique Patient No", identifierDef, "");
		dsd.addColumn("Sex", new GenderDataDefinition(), "");
		// new columns
		dsd.addColumn("Admission Number", new MaternityAdmissionNumberDataDefinition(), "");
		dsd.addColumn("Date of Admission", new MaternityAdmissionDateDataDefinition(), "", new DateConverter(DATE_FORMAT));
		dsd.addColumn("Number of ANC Visits", new MaternityNumberOfANCVisitsDataDefinition(), "");
		dsd.addColumn("Name", nameDef, "");
		dsd.addColumn("Telephone No", new PersonAttributeDataDefinition(phoneNumber), "");
		dsd.addColumn("Date of Birth", new BirthdateDataDefinition(), "", new BirthdateConverter(DATE_FORMAT));
		dsd.addColumn("Age", new AgeDataDefinition(), "");
		dsd.addColumn("Marital Status", new KenyaEMRMaritalStatusDataDefinition(), "");
		dsd.addColumn("Parity", new MaternityANCParityDataDefinition(), "");
		dsd.addColumn("Gravida", new MaternityGravidaDataDefinition(), "");
		dsd.addColumn("LMP", new MaternityLMPDateDataDefinition(), "", new DateConverter(DATE_FORMAT));
		dsd.addColumn("Ultra Sound", new MaternityEDDUltrasoundDateDataDefinition(), "", new DateConverter(DATE_FORMAT));
		dsd.addColumn("Diagnosis", new MaternityDiagnosisDataDefinition(), "");
		dsd.addColumn("Duration of Labour", new MaternityDurationOfLabourDataDefinition(), "");
		dsd.addColumn("Delivery Date", new MaternityDeliveryDateDataDefinition(), "", new DateConverter(DATE_FORMAT));
		dsd.addColumn("Delivery Time", new MaternityDeliveryTimeDataDefinition(), "");
		dsd.addColumn("Gestation at Birth in weeks", new MaternityGestationAtBirthDataDefinition(), "");
		dsd.addColumn("Mode of Delivery", new MaternityDeliveryModeDataDefinition(), "");
		dsd.addColumn("Placenta Complete", new MaternityPlacentaCompleteDataDefinition(), "");
		dsd.addColumn("Blood Loss", new MaternityBloodLossDataDefinition(), "");
		dsd.addColumn("Condition after delivery", new MaternityConditionAfterDeliveryDataDefinition(), "");
		dsd.addColumn("Deaths Audited", new MaternityDeathAuditedDataDefinition(), "");
		dsd.addColumn("Other Delivery Complications", new MaternityOtherDeliveryComplicationsDataDefinition(), "");
		dsd.addColumn("Baby Sex", new MaternityBabySexDataDefinition(), "");
		dsd.addColumn("Birth weight", new MaternityBirthWeightDataDefinition(), "");
		dsd.addColumn("Baby Condition", new MaternityBabyConditionDataDefinition(), "");
		dsd.addColumn("Initiated BF <1 Hr", new MaternityInitiatedBFWithinOneHourDataDefinition(), "");
		dsd.addColumn("TEO Given at Birth", new MaternityTEOGivenAtBirthDataDefinition(), "");
		dsd.addColumn("Baby with deformity", new MaternityBabyWithDeformityDataDefinition(), "");
		dsd.addColumn("APGAR Score", new MaternityApgarScoreDataDefinition(), "");
		dsd.addColumn("VDRL/RPR Results", new MaternityVDRLRPRResultsDataDefinition(), "");
		dsd.addColumn("HIV Status at ANC", new MaternityHIVStatusAtANCDataDefinition(), "");
		dsd.addColumn("HIV Test One", new MaternityHIVTestOneDataDefinition(), "");
		dsd.addColumn("HIV Test Two", new MaternityHIVTestTwoDataDefinition(), "");
		dsd.addColumn("HIV Final Results", new MaternityHIVFinalResultsDataDefinition(), "");
		dsd.addColumn("ARV Prophylaxis Issued from ANC", new MaternityARVProphylaxisIssuedFromANCDataDefinition(), "");
		dsd.addColumn("ARV Prophylaxis Issued at Maternity", new MaternityARVProphylaxisIssuedAtMaternityDataDefinition(),
		    "");
		dsd.addColumn("ARV Prophylaxis To Baby in Maternity", new MaternityARVProphylaxisToBabyAtMaternityDataDefinition(),
		    "");
		dsd.addColumn("CTX To Mother", new MaternityCTXToMotherDataDefinition(), "");
		dsd.addColumn("Vitamin A", new MaternityVitaminADataDefinition(), "");
		dsd.addColumn("Partner Tested For HIV", new MaternityPartnerTestedForHIVDataDefinition(), "");
		dsd.addColumn("Partner HIV Test Results", new MaternityPartnerHIVTestResultsDataDefinition(), "");
		dsd.addColumn("Counselled on Infant Feeding", new MaternityCounselledOnInfantFeedingDataDefinition(), "");
		dsd.addColumn("Delivery Conducted by", new MaternityDeliveryConductedByDataDefinition(), "");
		dsd.addColumn("Birth Notification Number", new MaternityBirthNotificationNumberDataDefinition(), "");
		dsd.addColumn("Discharged Date", new MaternityDischargeDateDataDefinition(), "");
		dsd.addColumn("Status of Baby at Discharge", new MaternityStatusOfBabyDataDefinition(), "");
		dsd.addColumn("Referred From", new MaternityReferredFromDataDefinition(), "");
		dsd.addColumn("Referred To", new MaternityReferredToDataDefinition(), "");
		dsd.addColumn("Comments", new MaternityCommentsDataDefinition(), "");
		
		return dsd;
	}
	
	protected DataSetDefinition maternityDataSet() {
		CohortIndicatorDataSetDefinition cohortDsd = new CohortIndicatorDataSetDefinition();
		cohortDsd.setName("cohortIndicator");
		cohortDsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cohortDsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		String indParams = "";
		
		cohortDsd.addColumn("clientsWithAPH", "Clients With APH",
		    ReportUtils.map(maternityIndicatorLibrary.clientsWithAPH(), indParams), "");
		cohortDsd.addColumn("clientsWithPPH", "Clients With PPH",
		    ReportUtils.map(maternityIndicatorLibrary.clientsWithPPH(), indParams), "");
		cohortDsd.addColumn("clientsWithEclampsia", "Clients With Eclampsia",
		    ReportUtils.map(maternityIndicatorLibrary.clientsWithEclampsia(), indParams), "");
		cohortDsd.addColumn("clientsWithRapturedUterus", "Clients With Raptured Uterus",
		    ReportUtils.map(maternityIndicatorLibrary.clientsWithRapturedUterus(), indParams), "");
		cohortDsd.addColumn("clientsWithObstructedLabour", "Clients With Obstructed Labour",
		    ReportUtils.map(maternityIndicatorLibrary.clientsWithObstructedLabour(), indParams), "");
		cohortDsd.addColumn("clientsWithSepsis", "Clients With Sepsis",
		    ReportUtils.map(maternityIndicatorLibrary.clientsWithSepsis(), indParams), "");
		cohortDsd.addColumn("clientsAlive", "Clients Alive",
		    ReportUtils.map(maternityIndicatorLibrary.clientsAlive(), indParams), "");
		cohortDsd.addColumn("clientsDead", "Clients Dead",
		    ReportUtils.map(maternityIndicatorLibrary.clientsDead(), indParams), "");
		cohortDsd.addColumn("preTermBabies", "Pre-term Babies",
		    ReportUtils.map(maternityIndicatorLibrary.preTermBabies(), indParams), "");
		cohortDsd.addColumn("underWeightBabies", "Underweight Babies",
		    ReportUtils.map(maternityIndicatorLibrary.underWeightBabies(), indParams), "");
		cohortDsd.addColumn("liveBirths", "Live Births", ReportUtils.map(maternityIndicatorLibrary.liveBirths(), indParams),
		    "");
		cohortDsd.addColumn("stillBirths", "Still Births",
		    ReportUtils.map(maternityIndicatorLibrary.stillBirths(), indParams), "");
		cohortDsd.addColumn("initialTestAtMaternity", "Initial Test At Maternity",
		    ReportUtils.map(maternityIndicatorLibrary.initialTestAtMaternity(), indParams), "");
		cohortDsd.addColumn("positiveResultsAtMaternity", "HIV Positive Results at Maternity",
		    ReportUtils.map(maternityIndicatorLibrary.positiveResultsAtMaternity(), indParams), "");
		cohortDsd.addColumn("hivPositiveDeliveries", "HIV Positive Deliveries",
		    ReportUtils.map(maternityIndicatorLibrary.hivPositiveDeliveries(), indParams), "");
		cohortDsd.addColumn("adolescentsNewHivPositiveAtMaternity", "HIV Positive new Adolescents at Maternity",
		    ReportUtils.map(maternityIndicatorLibrary.adolescentsNewHivPositiveAtMaternity(), indParams), "");
		cohortDsd.addColumn("startedHAARTMaternity", "Started HAART at Maternity",
		    ReportUtils.map(maternityIndicatorLibrary.startedHAARTMaternity(), indParams), "");
		cohortDsd.addColumn("infantARVProphylaxisMaternity", "Given Infant ARV Prophylaxis Maternity",
		    ReportUtils.map(maternityIndicatorLibrary.infantARVProphylaxisMaternity(), indParams), "");
		cohortDsd.addColumn("normalDeliveries", "Normal Deliveries",
		    ReportUtils.map(maternityIndicatorLibrary.normalDeliveries(), indParams), "");
		cohortDsd.addColumn("caesareanSections", "Caesarean Sections",
		    ReportUtils.map(maternityIndicatorLibrary.caesareanSections(), indParams), "");
		cohortDsd.addColumn("breechDeliveries", "Breech Deliveries",
		    ReportUtils.map(maternityIndicatorLibrary.breechDeliveries(), indParams), "");
		cohortDsd.addColumn("assistedVaginalDeliveries", "Assisted Vaginal Deliveries",
		    ReportUtils.map(maternityIndicatorLibrary.assistedVaginalDeliveries(), indParams), "");
		
		return cohortDsd;
	}
}
