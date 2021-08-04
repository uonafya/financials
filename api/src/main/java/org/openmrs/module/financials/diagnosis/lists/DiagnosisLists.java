package org.openmrs.module.financials.diagnosis.lists;

import org.openmrs.module.financials.EhrAddonsConstants;

import java.util.Arrays;
import java.util.List;

import static org.openmrs.module.financials.EhrAddonsConstants.getConcept;

public class DiagnosisLists {
	
	public static List<Integer> getDiarrheaDiagnosisList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.CHRONIC_DIARRHOEA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_DIARRHEA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.FUNCTIONAL_DIARRHOEA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Hemorrhagic_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Bacterial_Gastroenteritis).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.DIARRHEA_AMOEBIASIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.DIARRHOEA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.DIARRHOEA_AND_GASTROENTERITIS_OF_PRESUMED_INFECTIOUS_ORIGIN)
		            .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.DIARRHEA_AND_GASTROENTERITIS_OF_PRESUMED_INFECTIOUS_ORIGIN_1)
		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_for_less_than_one_month)
		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.CHRONIC_DIARRHEA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.WHO_HIV_CRYPTOSPORIDIOSIS_WITH_DIARRHEA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Clostridium_Difficile_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Antibiotic_Associated_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Psychogenic_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Protozoal_Diarrhoea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_of_Infectious_Origin).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Infectious_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_Resulting_from_Infection_of_the_Bowel_Mucosa)
		            .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Campylobacter_Diarrhoea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Dyspeptic_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Allergic_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Traveler_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Toddler_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Severe_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Secretory_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Raw_Milk_Associated_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Postcholecystectomy_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Poisoning_by_Antidiarrheal_Drug).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Post_Vagotomy_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_for_more_2_weeks).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_for_2_weeks).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.postvagotomy_diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.nausea_vomiting_and_diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.irritable_bowel_syndrome_without_diarrhoea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.diarrhea_due_to_drug).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Irritable_Bowel_Syndrome_with_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.CHRONIC_DIARRHEA_1).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Acute_diarrhea_more_than_three_stools_in_24_hours)
		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.Adverse_Reaction_of_Antidiarrheal_Drug)
		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.Bacterial_diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.diarrhea_bacterial).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Chronic_Diarrhea_of_Infants_and_Young_Children).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhoea_chronic_1_month_unexplained).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Chronic_diarrhea_of_unknown_origin).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Congenital_Secretory_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Congenital_Secretory_Diarrhea_Chloride_Type).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Congenital_Secretory_Diarrhea_Sodium_Type).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_2).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_After_Gastrointestinal_Tract_Surgery).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_due_to_Alcohol_Intake).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_due_to_Staphylococcus).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_in_Diabetes).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diarrhea_of_presumed_infectious_origin).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Dietetic_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Drug_and_Toxin_Induced_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Dysenteric_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Epidemic_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Functional_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Bloody_diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Hemorrhagic_diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Hill_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Diabetic_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Infective_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Neonatal_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Nervous_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Non_Infective_Diarrhea).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Osmotic_Diarrhea).getConceptId());
	}
	
	public static List<Integer> getTuberculosisDiagnosisList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.RESPIRATORY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EPTB).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.HIV_STAGING_TUBERCULOSIS_WITHIN_YEAR).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tuberculosis_Polyserositis).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tuberculous_Pleurisy_in_Primary_Progressive_Tuberculosis)
		            .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Erythema_Nodosum_with_Hypersensitivity_Reaction_in_Tuberculosis)
		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tuberculosis_of_Joint).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Addisons_Disease_due_to_Tuberculosis).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tuberculosis_of_Urinary_Organs).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tuberculosis_of_Ureter).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tuberculosis_of_Thyroid_Gland).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tuberculosis_of_Spleen).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TSS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TSS1).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TSS2).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TSS3).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TSS4).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TSS5).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TSS6).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB1).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB2).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB3).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB4).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB5).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB6).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB7).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB8).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB9).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB10).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB11).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB12).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB13).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB14).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB15).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB16).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB17).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB18).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB19).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB20).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB21).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB22).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB23).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB24).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB25).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB26).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB27).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB28).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB29).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB30).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB31).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB32).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB33).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB34).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB35).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB36).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB37).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB38).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB39).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB40).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB41).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB42).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB43).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB44).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB44).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB45).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB46).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB47).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB48).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB49).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB50).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB51).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB52).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB53).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB54).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB55).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB56).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB57).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB58).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB59).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB60).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB61).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB62).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB63).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB64).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB65).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB66).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB67).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB68).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB69).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB70).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB71).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB72).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB73).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB74).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB75).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB76).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB77).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB78).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB79).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB80).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB81).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB82).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB83).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB84).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB85).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB86).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB87).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB88).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB89).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB90).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB91).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB92).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB93).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB94).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB95).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB96).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB97).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB98).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB99).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB100).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB101).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB102).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB103).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB104).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB105).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB106).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB107).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB108).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB109).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB110).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB111).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB112).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB113).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB114).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB115).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB116).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB117).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB118).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB119).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB120).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB121).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB122).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB123).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB124).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB125).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB126).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB127).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB128).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB129).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB130).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB131).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB132).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB133).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB134).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB135).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB136).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB137).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB138).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB139).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB140).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB141).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB142).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB143).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB144).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB145).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB146).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB147).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB148).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB149).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB150).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB151).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB152).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB153).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB154).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB155).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB156).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB157).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB158).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB159).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB160).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB161).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB162).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB163).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB164).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB165).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB166).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB167).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB168).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB169).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TB170).getConceptId()
		
		);
	}
	
	public static List<Integer> getDysenteryList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.DYSENTERY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.AMOEBIC_DYSENTERY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.SHIGELLA_DYSENTERY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.BACILLARY_DYSENTERY).getConceptId());
	}
	
	public static List<Integer> getCholeraList() {
		return Arrays
		        .asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.DIARRHEA_CHOLERA).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.CHOLERA).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Cholera_due_to_Vibrio_Cholerae).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Need_for_Vaccination_for_Cholera).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Pseudopancreatic_Cholera_Syndrome).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Poisoning_by_Cholera_Vaccine).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Hog_Cholera).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Cholera_due_to_Vibrio_Cholerae_El_Tor).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Cholera_Contact).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Cholera_Carrier).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.CHOLERA_1).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.Adverse_Reaction_to_Cholera_Vaccine).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.accidental_cholera_vaccine_poisoning).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.cholera_due_to_vibrio_cholerae_01_biovar_cholerae)
		                    .getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.cholera_due_to_vibrio_cholerae_01_biovar_El_Tor)
		                    .getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.cholera_vaccine_adverse_reaction).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.cholera_vaccine_poisoning_of_undetermined_intent)
		                    .getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.intentional_cholera_vaccine_poisoning).getConceptId()
		        
		        );
	}
	
	public static List<Integer> getMeningococcalMeningitisList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getOtherMenigitisList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getPoliomyelitisList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getNeonatalTetanusList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getChickenPoxList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getMeaslesList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getHepatitisList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getMumpsList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getFeversList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getMalariaList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MALARIA_MILD).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MALARIA_SEVERE).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MALARIA_PRESUMED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MALARIA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MALARIA_RULE_OUT).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MALARIA_R_O).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MALARIA_CLINICAL).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.CLINICAL_MALARIA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.UNSPECIFIED_MALARIA).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Maternal_Malaria_with_Delivery).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malarial_Fever).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Complicated_malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MALARIA_1).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Haemolytic_Anaemia_due_to_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Falciparum_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Vivax_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Therapeutically_Induced_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Simian_malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Quartan_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malariae_malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Poisoning_by_Antimalarial_Drug).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Ovale_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Other_malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Mixed_Malaria).getConceptId(),
		    getConcept(
		        EhrAddonsConstants._EhrAddOnConcepts.Maternal_Malaria_with_Delivery_with_Current_Postpartum_Complication)
		            .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Maternal_Malaria_During_Pregnancy_Baby_Not_Yet_Delivered)
		            .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malaria_in_pregnancy).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malarial_Nephrosis).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malarial_Hepatitis).getConceptId(),
		    getConcept(
		        EhrAddonsConstants._EhrAddOnConcepts.Malaria_in_Mother_Complicating_Pregnancy_Childbirth_and_or_Puerperium)
		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malaria_during_pregnancy)
		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.Induced_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.hyper_reactive_malarial_splenomegaly).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.HO_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Congenital_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Cerebral_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Algid_Malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Plasmodium_Falciparum_Malaria_with_Cerebral_Complications)
		            .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Plasmodium_Malariae_Malaria_with_Nephropathy).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.congenital_falciparum_malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Plasmodium_vivax_malaria_with_rupture_of_spleen).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Uncomplicated_malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malaria_uncomplicated).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malaria_confirmed).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Presumptive_malaria_with_minor_digestive_system).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Malaria_confirmed_with_minor_digestive_symptoms).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Presumptive_malaria_with_pregnancy).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Confirmed_malaria_with_pregnancy).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Presumptive_malaria_with_minor_digestive_symptoms_pregnant)
		            .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Confirmed_malaria_with_minor_digestive_symptoms_pregnant)
		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.Severe_malaria).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Non_falciparum_malaria).getConceptId());
	}
	
	public static List<Integer> getUrinaryTractInfectionList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getTyphoidList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getBilharziaList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getInterstinalwormsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getMalnutritionList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getAnaemiaList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getEyeInfectionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getOtherEyeConditionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getEarInfectionsConditionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getUpperRespiratoryTractInfectionsList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getAsthmaList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getTonsilitiesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getChromosomalAbnormalitiesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getCongenitalAnomaliesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getDiarrhoeaList() {
		return Arrays.asList(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    //		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENCOUNTER_FOR_SCREENING_FOR_RESPIRATORY_TUBERCULOSIS)
		    //		            .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.MILIARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getPneumoniaList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getOtherDisOfRespiratorySystemList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getAbortionList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
		
	}
	
	public static List<Integer> getDisOfPuerperiumChildbathList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getHypertensionList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getMentalDisordersList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
		
	}
	
	public static List<Integer> getDentalDisordersList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
		
	}
	
	public static List<Integer> getJiggersInfestationList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
		
	}
	
	public static List<Integer> getDiseaseOfTheSkinList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getAnthritisJointPainsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getPoisoningList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getRoadTrafficInjuriesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
		
	}
	
	public static List<Integer> getOtherInjuriesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
		
	}
	
	public static List<Integer> getSexualAssaultList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getViolenceRelatedInjuriesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getBurnsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getSnakeBitesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getDogBitesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
		
	}
	
	public static List<Integer> getOtherBitesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
		
	}
	
	public static List<Integer> getDiabetesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getEpilepsyList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getNewlyDiagnosedHivList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getBrucellosisList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getCardiovascularConditionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getCentralNervousSystemConditionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getOvrerweightList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getMuscularSkeletalConditionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getFistulaBirthRelatedList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getNeoplamsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getPhysicalDisabilityList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getTryponomiasisList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getKalazarLeishmaniasisList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getDaracuncolosisGuineaWormList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getYellowFeverList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getViralHaemorrhagicFeverList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getPlagueList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getDeathDueToRoadTrafficInjuriesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getAllOtherDiseasesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getNoOfFirstAttendancesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getReAttendancesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getReferralsFromOtherHealthFacilityList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getReferralsFromOtherCommunityUnitList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getReferralsToCommunityUnitList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getMalariaInPregnancyList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getSexuallyTransmittedInfectionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getAneamiaList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getEarInfectionConditionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getOtheEyeConditionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getRicketsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getAutismList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getOtherConvulsiveDisordersList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getCerebralPalsyList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getOtherCentralNervousSystemConditionsList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getDeathsDuetoRoadTraficInjuriesList() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED)
		        .getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.EXTRAPULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.MULTI_DRUG_RESISTANT_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.PULMONARY_TUBERCULOSIS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_THE_GENITOURINARY_SYSTEM).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_OF_OTHER_ORGANS).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS_IN_PREGNANCY).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.ACUTE_MILIARY_TUBERCULOSIS_UNSPECIFIED).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.TUBERCULOSIS).getConceptId()
		
		);
	}
	
	public static List<Integer> getConfirmedMalariaResults() {
		return Arrays.asList(getConcept(EhrAddonsConstants._EhrAddOnConcepts.POSITIVE).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Positive_for_Plasmodium_falciparum).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Positive_for_Plasmodium_vivax).getConceptId(),
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Positive_for_both_Plasmodium_falciparum_and_Plasmodium_vivax)
		            .getConceptId());
	}
	
	public static List<Integer> getSuspectedMalariaResults() {
		return Arrays
		        .asList(
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.NEGATIVE).getConceptId(),
		            getConcept(EhrAddonsConstants._EhrAddOnConcepts.INDETERMINATE).getConceptId(),
		            getConcept(
		                EhrAddonsConstants._EhrAddOnConcepts.Test_not_performed_due_to_lack_of_availability_of_test_materials)
		                    .getConceptId(), getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed)
		                    .getConceptId());
	}
}
