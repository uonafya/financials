package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.diagnosis.lists.DiagnosisLists;
import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimension;
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
	
	private final Moh705IndicatorDefinitions moh705aIndicator;
	
	private final EhrAddonDimension ehrAddonDimesion;
	
	@Autowired
	public Moh705bDatasetDefinition(Moh705IndicatorDefinitions moh705aIndicator, EhrAddonDimension ehrAddonDimesion) {
		this.moh705aIndicator = moh705aIndicator;
		this.ehrAddonDimesion = ehrAddonDimesion;
	}
	
	/**
	 * Get moh 705 b dataset
	 * 
	 * @return @{@link DataSetDefinition}
	 */
	public DataSetDefinition getMoh705bDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParam = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("MOH705B");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addDimension("day", ReportUtils.map(ehrAddonDimesion.encountersOfMonthPerDay(), indParam));
		
		EncounterType opdEncounter = Context.getEncounterService().getEncounterTypeByUuid(
		    "ba45c278-f290-11ea-9666-1b3e6e848887");
		;
		// populate datasets
		EhrReportingUtils.addRow(
		    dsd,
		    "DA",
		    "Diarrhoea",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDiarrheaDiagnosisList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "TBA", "Tuberculosis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getTuberculosisDiagnosisList(),
		        opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DYA",
		    "Dysentery",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDysenteryList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "CLA",
		    "Cholera",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getCholeraList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "MMA",
		    "Meningococcal Meningitis",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(
		        DiagnosisLists.getMeningococcalMeningitisList(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "MOA",
		    "Other Meningitis",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherMenigitisList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "NTA",
		    "Neonatal Tetanus",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getNeonatalTetanusList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "PMA",
		    "Poliomyelitis",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPoliomyelitisList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "CPA",
		    "Chicken Pox",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getChickenPoxList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "MEA",
		    "Measles",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMeaslesList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "HEA",
		    "Hepatitis",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getHepatitisList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "MPSA",
		    "Mumps",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMumpsList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "SUA",
		    "Suspected malaria",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosisForMalaria(DiagnosisLists.getMalariaList(),
		            DiagnosisLists.getSuspectedMalariaResults(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "COA",
		    "Confirmed Malaria positive",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosisForMalaria(DiagnosisLists.getMalariaList(),
		            DiagnosisLists.getConfirmedMalariaResults(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "MPA",
		    "Malaria In Pregnancy",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMalariaInPregnancyList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "TYA",
		    "Typhoid Fever",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getTyphoidList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "STIA",
		    "STI",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(
		        DiagnosisLists.getSexuallyTransmittedInfectionsList(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "URA", "Urinary tract infection", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getUrinaryTractInfectionList(),
		        opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "BIA",
		    "Bilharzia",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getBilharziaList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "INA",
		    "Intestinal worms",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getInterstinalwormsList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "MLA",
		    "Malnutrition",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMalnutritionList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "ANEA",
		    "Aneamia",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAnaemiaList(),
		            opdEncounter.getEncounterTypeId()), indParam),
		    
		    EhrAddonUtils.getAdultChildrenColumns());
		EhrReportingUtils.addRow(
		    dsd,
		    "EYA",
		    "Eye Infections",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getEyeInfectionsList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "EIA", "Ear Infection Conditions", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getEarInfectionConditionsList(),
		        opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "UPA",
		    "Upper Respiratory Tract Infections",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(
		        DiagnosisLists.getUpperRespiratoryTractInfectionsList(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "ASA",
		    "Asthma",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAsthmaList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "PNA",
		    "Pneumonia",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPneumoniaList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "OTRA",
		    "Other Dis Of Respiratory System",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(
		        DiagnosisLists.getOtherDisOfRespiratorySystemList(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "ABA",
		    "Abortion",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAbortionList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DPA",
		    "Dis Of Puerperium & Childbath",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(
		        DiagnosisLists.getDisOfPuerperiumChildbathList(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "HYA",
		    "Hypertension",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getHypertensionList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "MDA",
		    "Mental Disorders",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getMentalDisordersList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DEA",
		    "Dental Disorders",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDentalDisordersList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "JIA",
		    "Jiggers Infestation",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getJiggersInfestationList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DSA",
		    "Disease Of The Skin",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDiseaseOfTheSkinList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "AJPA",
		    "Anthritis Joint Pains",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAnthritisJointPainsList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "POA",
		    "Poisoning",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPoisoningList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "ROA",
		    "Road Traffic Injuries",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getRoadTrafficInjuriesList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "OIA",
		    "Other Injuries",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherInjuriesList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "SEA",
		    "Sexual Assault",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getSexualAssaultList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "VRA",
		    "Violence Related Injuries",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(
		        DiagnosisLists.getViolenceRelatedInjuriesList(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "BUA",
		    "Burns",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getBurnsList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "SNA",
		    "Snake Bites",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getSnakeBitesList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DOA",
		    "Dog Bites",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDogBitesList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "OBA",
		    "Other Bites",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherBitesList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DTA",
		    "Diabetes",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDiabetesList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "EPA",
		    "Epilepsy",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getEpilepsyList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "BRLA",
		    "Brucellosis",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getBrucellosisList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "CAA",
		    "Cardiovascular Conditions",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(
		        DiagnosisLists.getCardiovascularConditionsList(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CNSA", "Central Nervous System Conditions", ReportUtils.map(moh705aIndicator
		        .getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOtherCentralNervousSystemConditionsList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "OVA",
		    "Overweight",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getOvrerweightList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "MSA",
		    "Muscular Skeletal Conditions",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(
		        DiagnosisLists.getMuscularSkeletalConditionsList(), opdEncounter.getEncounterTypeId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "FIA",
		    "Fistula Birth Related",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getFistulaBirthRelatedList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "NSA",
		    "Neoplams",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getNeoplamsList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "PHA",
		    "Physical Disability",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getPhysicalDisabilityList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "TRA",
		    "Tryponomiasis",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getTryponomiasisList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "KAA",
		    "Kalazar Leishmaniasis",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getKalazarLeishmaniasisList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "YEA",
		    "Yellow Fever",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getYellowFeverList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "VHA", "Viral Haemorrhagic Fever", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getViralHaemorrhagicFeverList(),
		        opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DRA",
		    "Death due to road traffic injuries",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithReferrals(
		        getConcept("1599AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId(),
		        getConcept("1603AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "AODA",
		    "All other diseases for adults",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithOtherDiagnosis(
		        DiagnosisLists.getAllOtherDiseasesListForAdults(), opdEncounter.getEncounterTypeId()), indParam),
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
		
		//additional indicators added
		EhrReportingUtils.addRow(
		    dsd,
		    "AM",
		    "AMOEBIASIS",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAmoebiasis(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "RVF",
		    "Rift valley fever",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getRiftValleyFeverList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "CKU",
		    "Chikungunya fever",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getChikungunyaFeverList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DENF",
		    "Dengue fever",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDengueFeverList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CL", "Cutaneous Leishmaniasis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getCutaneousLeishmaniasisList(),
		        opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "ANT",
		    "Suspected Anthrax",
		    ReportUtils.map(
		        moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAnthraxList(),
		            opdEncounter.getEncounterTypeId()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		return dsd;
		
	}
}
