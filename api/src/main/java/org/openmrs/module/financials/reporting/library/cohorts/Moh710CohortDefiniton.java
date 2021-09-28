package org.openmrs.module.financials.reporting.library.cohorts;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Moh710CohortDefiniton {
	
	//Queries for MOH710
	/*Given BCG*/
	public CohortDefinition givenBCGVaccineCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.BCG) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("BCG");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given BCG");
		
		return cd;
	}
	
	/*Given OPV at birth*/
	public CohortDefinition givenOPVCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.OPV_birth) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("OPV-0");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given OPV at birth");
		
		return cd;
	}
	
	/*Given OPV 1*/
	public CohortDefinition givenOPV1Cl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.OPV_1) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("OPV-1");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given OPV 1");
		
		return cd;
	}
	
	/*Given OPV 2*/
	public CohortDefinition givenOPV2Cl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.OPV_2) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("OPV-2");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given OPV 2");
		
		return cd;
	}
	
	/*Given OPV 3*/
	public CohortDefinition givenOPV3Cl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.OPV_3) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("OPV-3");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given OPV 3");
		
		return cd;
	}
	
	/*Given IPV*/
	public CohortDefinition givenIpvCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.IPV) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("IPV");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given IPV");
		
		return cd;
	}
	
	/*Given Dpt-Hep-Hib 1*/
	public CohortDefinition givenDptHepHibVaccine1Cl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.DPT_Hep_B_Hib_1) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("DHH-1");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Dpt-Hep-Hib 1");
		
		return cd;
	}
	
	/*Given Dpt-Hep-Hib 2*/
	public CohortDefinition givenDptHepHibVaccine2Cl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.DPT_Hep_B_Hib_2) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("DHH-2");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Dpt-Hep-Hib 2");
		
		return cd;
	}
	
	/*Given Dpt-Hep-Hib 3*/
	public CohortDefinition givenDptHepHibVaccine3Cl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.DPT_Hep_B_Hib_3) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("DHH-3");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Dpt-Hep-Hib 3");
		
		return cd;
	}
	
	/*Given Pneumococcal 1*/
	public CohortDefinition givenPneumococcal1VaccineCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.PCV_10_1) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("PCV-1");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Pneumococcal 1");
		
		return cd;
	}
	
	/*Given Pneumococcal 2*/
	public CohortDefinition givenPneumococcal2VaccineCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.PCV_10_2) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("PCV-2");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Pneumococcal 2");
		
		return cd;
	}
	
	/*Given Pneumococcal 3*/
	public CohortDefinition givenPneumococcal3VaccineCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.PCV_10_3) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("PCV-3");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Pneumococcal 3");
		
		return cd;
	}
	
	/*Given Rota 1 vaccine*/
	public CohortDefinition givenRota1VirusVaccineCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.ROTA_1) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("ROTA-1");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Rota 1 vaccine");
		
		return cd;
	}
	
	/*Given Rota 2 vaccine*/
	public CohortDefinition givenRota2VirusVaccineCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.ROTA_2) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("ROTA-2");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Rota 2 vaccine");
		
		return cd;
	}
	
	/*Given Vitamin A at 6 Months*/
	public CohortDefinition givenVitAAt6MAgeCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.VitaminA_6_months) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("VA6M");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Vitamin A at 6 Months");
		
		return cd;
	}
	
	/*Given Yellow Fever vaccine*/
	public CohortDefinition givenYellowFeverVaccineCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id,i.Measles_rubella_1 from kenyaemr_etl.etl_hei_immunization i where date(i.Yellow_fever) between date(:startDate) and date(:endDate);";
		
		cd.setName("YF");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Yellow Fever vaccine");
		
		return cd;
	}
	
	/*Given Measles-Rubella 1 vaccine*/
	public CohortDefinition givenMeaslesRubella1VaccineCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.Measles_rubella_1) between date(:startDate) and date(:endDate);";
		
		cd.setName("MR-1");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Measles-Rubella 1 vaccine");
		
		return cd;
	}
	
	/*Fully immunized child*/
	public CohortDefinition fullyImmunizedCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.fully_immunized) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("FIC");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Fully immunized child");
		
		return cd;
	}
	
	/*Given Vitamin A at 1 years (200,000IU)*/
	public CohortDefinition givenVitAAt12MonthsCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.VitaminA_1_yr) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("VA-1Y");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Vitamin A at 1 years (200,000IU)");
		
		return cd;
	}
	
	/*Given Vitamin A at 2 years to 5 years (200,000IU)*/
	public CohortDefinition givenVitAAt18MonthsCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.VitaminA_1_and_half_yr) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("VA-2Y-5Y");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Vitamin A at 2 years to 5 years (200,000IU)");
		
		return cd;
	}
	
	/*Given Measles - Rubella 2(at 1 1/2 - 2 years)*/
	public CohortDefinition givenVitAAt2To5YearsCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.VitaminA_2_to_5_yr) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("MR-2-1.5Y>2Y");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Measles - Rubella 2(at 1 1/2 - 2 years)");
		
		return cd;
	}
	
	/*Measles - Rubella 2(at 1 1/2 - 2 years)*/
	public CohortDefinition givenMeaslesRubella2VaccineAge18To24MonthsCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.Measles_rubella_2) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("MR-2-1.5Y>2Y");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Given Measles - Rubella 2(at 1 1/2 - 2 years)");
		
		return cd;
	}
	
	/*Measles-Rubella 2 Above 2 years*/
	public CohortDefinition givenMeaslesRubellaVaccine2AndAgedOver2YearsCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = "select i.patient_id from kenyaemr_etl.etl_hei_immunization i where date(i.Measles_rubella_2) between date(:startDate) and date(:endDate) group by i.patient_id;";
		
		cd.setName("MR-2->2Y");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Measles-Rubella 2 Above 2 years");
		
		return cd;
	}
	
	/*Tetanus Toxoid for pregnant women first dose*/
	public CohortDefinition givenTTXFirstDoseCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = ";";
		
		cd.setName("TTX_FPW-1st");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Tetanus Toxoid for pregnant women first dose");
		
		return cd;
	}
	
	/*Tetanus Toxoid for pregnant women second dose*/
	public CohortDefinition givenTTXSecondDoseCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = ";";
		
		cd.setName("TTX_FPW-2nd");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Tetanus Toxoid for pregnant women second dose");
		
		return cd;
	}
	
	/*Tetanus Toxoid plus(Booster) for pregnant women*/
	public CohortDefinition givenTTXPlusCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = ";";
		
		cd.setName("TTX_FPW-TTPLUS");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Tetanus Toxoid plus(Booster) for pregnant women");
		
		return cd;
	}
	
	/*Given Vitamin A supplement 2 -5 years (200,000 IU)*/
	public CohortDefinition givenVitASupplementalCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = ";";
		
		cd.setName("VA-2-5Y");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("2 -5 years (200,000 IU)");
		
		return cd;
	}
	
	/*Vitamin A Supplemental Lactating Mothers(200,000 IU)*/
	public CohortDefinition givenVitASupplementalLacCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = ";";
		
		cd.setName("VA-LAC");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Vitamin A Supplemental Lactating Mothers(200,000 IU)");
		
		return cd;
	}
	
	/*Issued with LLITN in this Visit (under 1 year)*/
	public CohortDefinition givenLLITNCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = ";";
		
		cd.setName("LLITN-LT1Y");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Issued with LLITN in this Visit (under 1 year)");
		
		return cd;
	}
	
	/*Squint/White Eye Reflection under 1 year*/
	public CohortDefinition squintWhiteEyeReflectionCl() {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		String sqlQuery = ";";
		
		cd.setName("SER-<1Y");
		cd.setQuery(sqlQuery);
		cd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		cd.addParameter(new Parameter("endDate", "End Date", Date.class));
		cd.setDescription("Squint/White Eye Reflection under 1 year");
		
		return cd;
	}
}
