package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.diagnosis.lists.DiagnosisLists;
import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
import org.openmrs.module.financials.reporting.library.indicator.Moh705IndicatorDefinitions;
import org.openmrs.module.financials.reporting.utils.EhrAddonUtils;
import org.openmrs.module.financials.utils.EhrReportingUtils;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.openmrs.module.financials.EhrAddonsConstants.getConcept;

@Component
public class Moh705bDatasetDefinition {
	
	private Moh705IndicatorDefinitions moh705aIndicator;
	
	private EhrAddonDimesion ehrAddonDimesion;
	
	@Autowired
	public Moh705bDatasetDefinition(Moh705IndicatorDefinitions moh705aIndicator, EhrAddonDimesion ehrAddonDimesion) {
		this.moh705aIndicator = moh705aIndicator;
		this.ehrAddonDimesion = ehrAddonDimesion;
	}
	
	/**
	 * Get moh 705 a dataset
	 * 
	 * @return @{@link DataSetDefinition}
	 */
	public DataSetDefinition getMoh705bDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParam = "startDate=${startDate},endDate=${endDate}";
		//dsd.addDimension("days", ReportUtils.map(ehrAddonDimesion.encountersOfMonthPerDay(), "endDate=${endDate}"));
		dsd.setName("MOH705B");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addDimension("day", ReportUtils.map(ehrAddonDimesion.encountersOfMonthPerDay(), "startDate=${startDate}"));
		;
		// populate datasets
		EhrReportingUtils.addRow(dsd, "DA", "Diarrhoea", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDiarrheaDiagnosisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "TBA", "Tuberculosis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getTuberculosisDiagnosisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DYA", "Dysentery",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDysenteryList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CLA", "Cholera",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getCholeraList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MMA", "Meningococcal Meningitis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMeningococcalMeningitisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MOA", "Other Meningitis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherMenigitisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "NTA", "Neonatal Tetanus", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getNeonatalTetanusList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "PMA", "Poliomyelitis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPoliomyelitisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "CPA", "Chicken Pox", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getChickenPoxList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MEA", "Measles",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMeaslesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "HEA", "Hepatitis",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getHepatitisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MPSA", "Mumps",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMumpsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		EhrReportingUtils.addRow(dsd, "FEA", "Fevers",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getFeversList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "SUA",
		    "Suspected malaria",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosisForMalaria(DiagnosisLists.getMalariaList(),
		            DiagnosisLists.getSuspectedMalariaResults()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "COA",
		    "Confirmed Malaria positive",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosisForMalaria(DiagnosisLists.getMalariaList(),
		            DiagnosisLists.getConfirmedMalariaResults()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MPA", "Malaria In Pregnancy", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMalariaInPregnancyList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "TYA", "Typhoid Fever",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getTyphoidList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "STIA", "STI", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getSexuallyTransmittedInfectionsList()),
		    indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "URA", "Urinary tract infection", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getUrinaryTractInfectionList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "BIA", "Bilharzia",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getBilharziaList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "INA", "Intestinal worms", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getInterstinalwormsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MLA", "Malnutrition", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMalnutritionList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "ANEA", "Aneamia",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAnaemiaList()), indParam),
		    
		    EhrAddonUtils.getAdultChildrenColumns());
		EhrReportingUtils.addRow(dsd, "EYA", "Eye Infections", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getEyeInfectionsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OEYA", "Other Eye Conditions", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherEyeConditionsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "EIA", "Ear Infection Conditions", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getEarInfectionConditionsList()),
		            indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "UPA", "Upper Respiratory Tract Infections", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getUpperRespiratoryTractInfectionsList()),
		    indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "ASA", "Asthma",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAsthmaList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "PNA", "Pneumonia",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPneumoniaList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OTRA", "Other Dis Of Respiratory System",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherDisOfRespiratorySystemList()),
		        indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "ABA", "Abortion",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAbortionList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DPA", "Dis Of Puerperium & Childbath", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDisOfPuerperiumChildbathList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "HYA", "Hypertension", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getHypertensionList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MDA", "Mental Disorders", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMentalDisordersList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DEA", "Dental Disorders", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDentalDisordersList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "JIA", "Jiggers Infestation", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getJiggersInfestationList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DSA", "Disease Of The Skin", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDiseaseOfTheSkinList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "AJPA", "Anthritis Joint Pains", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAnthritisJointPainsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "POA", "Poisoning",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPoisoningList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "ROA", "Road Traffic Injuries", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getRoadTrafficInjuriesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OIA", "Other Injuries", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherInjuriesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "SEA", "Sexual Assault", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getSexualAssaultList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "VRA", "Violence Related Injuries", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getViolenceRelatedInjuriesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "BUA", "Burns",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getBurnsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "SNA", "Snake Bites", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getSnakeBitesList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DOA", "Dog Bites",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDogBitesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "OBA", "Other Bites", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherBitesList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DTA", "Diabetes",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDiabetesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "EPA", "Epilepsy",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getEpilepsyList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "NHA", "Newly Diagnosed Hiv", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getNewlyDiagnosedHivList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "BRLA", "Brucellosis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getBrucellosisList()), indParam), EhrAddonUtils
		        .getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CAA", "Cardiovascular Conditions", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getCardiovascularConditionsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CNSA", "Central Nervous System Conditions", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherCentralNervousSystemConditionsList()),
		    indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OVA", "Overweight", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOvrerweightList()), indParam), EhrAddonUtils
		        .getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MSA", "Muscular Skeletal Conditions",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMuscularSkeletalConditionsList()),
		        indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "FIA", "Fistula Birth Related", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getFistulaBirthRelatedList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "NSA", "Neoplams",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getNeoplamsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "PHA", "Physical Disability", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPhysicalDisabilityList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "TRA", "Tryponomiasis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getTryponomiasisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "KAA", "Kalazar Leishmaniasis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getKalazarLeishmaniasisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DAA", "Daracuncolosis Guinea Worm", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDaracuncolosisGuineaWormList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "YEA", "Yellow Fever", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getYellowFeverList()), indParam), EhrAddonUtils
		        .getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "VHA", "Viral Haemorrhagic Fever", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getViralHaemorrhagicFeverList()),
		            indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "PLA", "Plague",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPlagueList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DRA",
		    "Death due to road traffic injuries",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithReferrals(
		        getConcept("1599AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId(),
		        getConcept("1603AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "AODA", "All other diseases", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithOtherDiagnosis(DiagnosisLists.getAllOtherDiseasesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "NFAA", "No. Of First Attendances",
		    ReportUtils.map(moh705aIndicator.getNewAdultsPatients(), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "RETA", "Re-Attendances",
		            ReportUtils.map(moh705aIndicator.getRevisitsAdultsPatients(), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "RFHA",
		    "Referrals From Other Health Facility",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithReferrals(
		        getConcept("160481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId(),
		        getConcept("1537AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "RFCA",
		    "Referrals From Other Community Unit",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithReferrals(
		        getConcept("160481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId(),
		        getConcept("4fcf003e-71cf-47a5-a967-47d24aa61092").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "RTCA",
		    "Referrals To Community Unit",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithReferrals(
		        getConcept("477a7484-0f99-4026-b37c-261be587a70b").getConceptId(),
		        getConcept("4fcf003e-71cf-47a5-a967-47d24aa61092").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		return dsd;
		
	}
}
