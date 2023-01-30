package org.openmrs.module.financials.reporting.library.dataset;

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
public class Moh705aDatasetDefinition {
	
	private final Moh705IndicatorDefinitions moh705aIndicator;
	
	private final EhrAddonDimension ehrAddonDimesion;
	
	@Autowired
	public Moh705aDatasetDefinition(Moh705IndicatorDefinitions moh705aIndicator, EhrAddonDimension ehrAddonDimesion) {
		this.moh705aIndicator = moh705aIndicator;
		this.ehrAddonDimesion = ehrAddonDimesion;
	}
	
	/**
	 * Get moh 705 a dataset
	 * 
	 * @return @{@link org.openmrs.module.reporting.dataset.definition.DataSetDefinition}
	 */
	public DataSetDefinition getMoh705aDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParam = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("MOH705A");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addDimension("day", ReportUtils.map(ehrAddonDimesion.encountersOfMonthPerDay(), indParam));
		
		dsd.addColumn("DIARRHEA", "DIARRHEA ALL", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getDiarrheaDiagnosisList()), indParam), "");
		EhrReportingUtils.addRow(dsd, "DC", "Diarrhoea", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getDiarrheaDiagnosisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "TC", "Tuberculosis", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getTuberculosisDiagnosisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DYC", "Dysentery", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getDysenteryList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "CLC", "Cholera", ReportUtils.map(
		            moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getCholeraList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MCC", "Meningococcal Meningitis",
		    ReportUtils.map(
		        moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getMeningococcalMeningitisList()),
		        indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OMC", "Other Menignitis", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getOtherMenigitisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "NNC", "NeonatalTetanus", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getNeonatalTetanusList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "PMC", "Poliomyelitis", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getPoliomyelitisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CPC", "Chicken Pox", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getChickenPoxList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "MSC", "Measles", ReportUtils.map(
		            moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getMeaslesList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "HPC", "Hepatitis", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getHepatitisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MPC", "Mumps",
		    ReportUtils.map(moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getMumpsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "SMC", "Suspected Malaria", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosisForMalaria(DiagnosisLists.getMalariaList(),
		        DiagnosisLists.getSuspectedMalariaResults()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CMC", "Confirmed Malaria", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosisForMalaria(DiagnosisLists.getMalariaList(),
		        DiagnosisLists.getConfirmedMalariaResults()), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "UTC", "Urinary Tract Infection", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getUrinaryTractInfectionList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "TYC", "Typhoid Fever", ReportUtils.map(
		            moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getTyphoidList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "BLC", "Bilharzia", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getBilharziaList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "IWC", "Interstinal worms", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getInterstinalwormsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MNC", "Malnutrition", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getMalnutritionList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "ANC", "Anaemia", ReportUtils.map(
		            moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getAnaemiaList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "EC", "Eye Infections", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getEyeInfectionsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "EIC", "Ear Infections Conditions",
		    ReportUtils.map(
		        moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getEarInfectionsConditionsList()),
		        indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "URC", "Upper Respiratory Tract Infections", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getUpperRespiratoryTractInfectionsList()),
		    indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "ASC", "Asthma",
		    ReportUtils.map(moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getAsthmaList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "TSC", "Tonsilities", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getTonsilitiesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "PNC", "Pneumonia", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getPneumoniaList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "MDC", "Mental Disorders", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getMentalDisordersList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DDC", "DentalDisorders", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getDentalDisordersList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "JIC", "Jiggers Infestation", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getJiggersInfestationList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DOC", "Disease Of The Skin", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getDiseaseOfTheSkinList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "PC", "Poisoning", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getPoisoningList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "RTC", "Road TrafficI Injuries", ReportUtils.map(
		            moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getRoadTrafficInjuriesList()),
		            indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OIC", "Other Injuries", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getOtherInjuriesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "SAC", "Sexual Asualt", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getSexualAssaultList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "BC", "Burns",
		    ReportUtils.map(moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getBurnsList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "SBC", "Snake Bites", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getSnakeBitesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DBC", "Dog BITES", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getDogBitesList()), indParam), EhrAddonUtils
		        .getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OBC", "Other Bites", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getOtherBitesList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DTC", "Diabetes", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getDiabetesList()), indParam), EhrAddonUtils
		        .getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "EPC", "Epilepsy", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getEpilepsyList()), indParam), EhrAddonUtils
		        .getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OCDC", "Other Convulsive Disorders",
		    ReportUtils.map(
		        moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getOtherConvulsiveDisordersList()),
		        indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "BRC", "Brucellosis", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getBrucellosisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "RKC", "Rickets", ReportUtils.map(
		            moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getRicketsList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "VRC", "Violence related  injuries",
		    ReportUtils.map(
		        moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getViolenceRelatedInjuriesList()),
		        indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CRPC", "Cerebral Palsy", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getCerebralPalsyList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "ATC", "Autism",
		    ReportUtils.map(moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getAutismList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "TRC", "Tryponomiasis", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getTryponomiasisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "KLC", "Kalazar leishmaniasis", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getKalazarLeishmaniasisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "YFC", "Yellow Fever", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getYellowFeverList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "VHC", "Viral Haemorrhagic Fever", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getViralHaemorrhagicFeverList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "DRTC",
		    "Deaths due to Road Trafic injuries",
		    ReportUtils.map(moh705aIndicator.getAllChildrenPatientsReferrals(
		        getConcept("1599AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId(),
		        getConcept("1603AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "OVC", "Overweight", ReportUtils.map(
		    moh705aIndicator.getAllChildrenPatientsWithDiagnosis(DiagnosisLists.getOvrerweightList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "AODC", "All other diseases for children", ReportUtils.map(
		    moh705aIndicator.getAllChildredPatientsWithOtherDiagnosis(DiagnosisLists.getAllOtherDiseasesListForChildren()),
		    indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "NFAC", "No of first attendances",
		    ReportUtils.map(moh705aIndicator.getNewChildrenPatients(), indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "RAC", "Re-attendances",
		    ReportUtils.map(moh705aIndicator.getRevisitsChildrenPatients(), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "RFHC",
		    "Referrals from other health facility",
		    ReportUtils.map(moh705aIndicator.getAllChildrenPatientsReferrals(
		        getConcept("160481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId(),
		        getConcept("1537AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "RFCC",
		    "Referrals from Community Unit",
		    ReportUtils.map(moh705aIndicator.getAllChildrenPatientsReferrals(
		        getConcept("160481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId(),
		        getConcept("4fcf003e-71cf-47a5-a967-47d24aa61092").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(
		    dsd,
		    "RTC",
		    "Referrals to Community Unit",
		    ReportUtils.map(moh705aIndicator.getAllChildrenPatientsReferrals(
		        getConcept("477a7484-0f99-4026-b37c-261be587a70b").getConceptId(),
		        getConcept("4fcf003e-71cf-47a5-a967-47d24aa61092").getConceptId()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		//Dehydrated related diagnosis
		EhrReportingUtils.addRow(dsd, "DWSOD", "Diarrhoea with some dehydration",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.mildDehydration()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "DWSED", "Diarrhoea with severe dehydration", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.severeDehydration()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "GAS", "Gastroenteritis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getGastroenteritisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "SP", "Severe pneumonia", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getSeverePneumoniaList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "LTI", "Lower Respiratory Tract Infections", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getSLowerTractInfectionList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "NS", "Neonatal Sepsis", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getNeutalSepsisList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "AM", "AMOEBIASIS",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAmoebiasis()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DS", "Down’s syndrome", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDownSyndromeList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "RF", "Rheumatic Fever", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getReumonicFeverList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "RVF", "Rift valley fever", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getRiftValleyFeverList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "CKU", "Chikungunya fever", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getChikungunyaFeverList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "DENF", "Dengue fever", ReportUtils.map(
		    moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getDengueFeverList()), indParam), EhrAddonUtils
		        .getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "CL", "Cutaneous Leishmaniasis", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getCutaneousLeishmaniasisList()),
		            indParam), EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils.addRow(dsd, "ANT", "Suspected Anthrax",
		    ReportUtils.map(moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getAnthraxList()), indParam),
		    EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "HYPO", "Hypoxaemia (Spo2<90%)", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getHypoxaemiaList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		EhrReportingUtils
		        .addRow(dsd, "SCC", "Suspected Childhood Cancers", ReportUtils.map(
		            moh705aIndicator.getAllAdultPatientsWithDiagnosis(DiagnosisLists.getHypoxaemiaList()), indParam),
		            EhrAddonUtils.getAdultChildrenColumns());
		
		return dsd;
		
	}
	
}
