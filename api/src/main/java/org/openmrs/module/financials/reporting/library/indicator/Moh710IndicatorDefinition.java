package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh710CohortDefiniton;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh710IndicatorDefinition {
	
	private Moh710CohortDefiniton moh710CohortLibrary;
	
	@Autowired
	public Moh710IndicatorDefinition(Moh710CohortDefiniton moh710CohortLibrary) {
		this.moh710CohortLibrary = moh710CohortLibrary;
	}
	
	//Indicator Libraries based on Queries and MOH710 dimensions
	
	/*Given BCG*/
	public CohortIndicator givenBCGVaccine() {
		
		return cohortIndicator("Given BCG",
		    map(moh710CohortLibrary.givenBCGVaccineCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given OPV*/
	public CohortIndicator givenOPV() {
		
		return cohortIndicator("Given OPV at Birth",
		    map(moh710CohortLibrary.givenOPVCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given OPV1*/
	public CohortIndicator givenOPV1() {
		
		return cohortIndicator("Given OPV1",
		    map(moh710CohortLibrary.givenOPV1Cl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given OPV2*/
	public CohortIndicator givenOPV2() {
		
		return cohortIndicator("Given OPV2",
		    map(moh710CohortLibrary.givenOPV2Cl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given OPV3*/
	public CohortIndicator givenOPV3() {
		
		return cohortIndicator("Given OPV3",
		    map(moh710CohortLibrary.givenOPV3Cl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given IPV*/
	public CohortIndicator givenIpv() {
		
		return cohortIndicator("Given IPV",
		    map(moh710CohortLibrary.givenIpvCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Dpt-Hep-Hib 1 Vaccine*/
	public CohortIndicator givenDptHepHibVaccine1() {
		
		return cohortIndicator("Given Dpt-Hep-Hib 1",
		    map(moh710CohortLibrary.givenDptHepHibVaccine1Cl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Given Dpt-Hep-Hib 2 vaccine*/
	public CohortIndicator givenDptHepHibVaccine2() {
		
		return cohortIndicator("Given Dpt-Hep-Hib 2",
		    map(moh710CohortLibrary.givenDptHepHibVaccine2Cl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Given Dpt-Hep-Hib 3 vaccine*/
	public CohortIndicator givenDptHepHibVaccine3() {
		
		return cohortIndicator("Given Dpt-Hep-Hib 3",
		    map(moh710CohortLibrary.givenDptHepHibVaccine3Cl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Pneumococcal 1*/
	public CohortIndicator givenPneumococcal1Vaccine() {
		
		return cohortIndicator("Given Pneumococcal 1",
		    map(moh710CohortLibrary.givenPneumococcal1VaccineCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Pneumococcal 2*/
	public CohortIndicator givenPneumococcal2Vaccine() {
		
		return cohortIndicator("Given Pneumococcal 2",
		    map(moh710CohortLibrary.givenPneumococcal2VaccineCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Pneumococcal 3*/
	public CohortIndicator givenPneumococcal3Vaccine() {
		
		return cohortIndicator("Given Pneumococcal 3",
		    map(moh710CohortLibrary.givenPneumococcal3VaccineCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Rota 1*/
	public CohortIndicator givenRota1VirusVaccine() {
		
		return cohortIndicator("Given Rota 1",
		    map(moh710CohortLibrary.givenRota1VirusVaccineCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Rota 2*/
	public CohortIndicator givenRota2VirusVaccine() {
		
		return cohortIndicator("Given Rota 2",
		    map(moh710CohortLibrary.givenRota2VirusVaccineCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Vitamin A at 6 Months*/
	public CohortIndicator givenVitAAt6MAge() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenVitAAt6MAgeCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Yellow Fever vaccine*/
	public CohortIndicator givenYellowFeverVaccine() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenYellowFeverVaccineCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Measles-Rubella 1 vaccine*/
	public CohortIndicator givenMeaslesRubella1Vaccine() {
		
		return cohortIndicator("Given Measles Rubella 1",
		    map(moh710CohortLibrary.givenMeaslesRubella1VaccineCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Fully immunized child*/
	public CohortIndicator fullyImmunized() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.fullyImmunizedCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Vitamin A at 1 years (200,000IU)*/
	public CohortIndicator givenVitAAt12Months() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenVitAAt12MonthsCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Vitamin A at 1 1/2 years (200,000IU)*/
	public CohortIndicator givenVitAAt18Months() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenVitAAt18MonthsCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Given Vitamin A at 2 years to 5 years (200,000IU)*/
	public CohortIndicator givenVitAAt2To5Years() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenVitAAt2To5YearsCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Vitamin A Supplemental Lactating Mothers(200,000 IU)*/
	public CohortIndicator givenVitASupplementalLac() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenVitASupplementalLacCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Measles - Rubella 2(at 1 1/2 - 2 years)*/
	public CohortIndicator givenMeaslesRubella2VaccineAge18To24Months() {
		
		return cohortIndicator(
		    null,
		    map(moh710CohortLibrary.givenMeaslesRubella2VaccineAge18To24MonthsCl(),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Measles-Rubella 2 Above 2 years*/
	public CohortIndicator givenMeaslesRubellaVaccine2AndAgedOver2Years() {
		
		return cohortIndicator(
		    null,
		    map(moh710CohortLibrary.givenMeaslesRubellaVaccine2AndAgedOver2YearsCl(),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Tetanus Toxoid for pregnant women first dose*/
	public CohortIndicator givenTTXFirstDose() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenTTXFirstDoseCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Tetanus Toxoid for pregnant women second dose*/
	public CohortIndicator givenTTXSecondDose() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenTTXSecondDoseCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Tetanus Toxoid plus(Booster) for pregnant women*/
	public CohortIndicator givenTTXPlus() {
		
		return cohortIndicator(null, map(moh710CohortLibrary.givenTTXPlusCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//Adverse events following immunization
	
	/*2 -5 years (200,000 IU)*/
	public CohortIndicator givenVitASupplemental() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.givenVitASupplementalCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Issued with LLITN in this Visit (under 1 year)*/
	public CohortIndicator givenLLITN() {
		
		return cohortIndicator(null, map(moh710CohortLibrary.givenLLITNCl(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/*Squint/White Eye Reflection under 1 year*/
	public CohortIndicator squintWhiteEyeReflection() {
		
		return cohortIndicator(null,
		    map(moh710CohortLibrary.squintWhiteEyeReflectionCl(), "startDate=${startDate},endDate=${endDate}"));
	}
}
