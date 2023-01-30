package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimension;
import org.openmrs.module.financials.reporting.library.indicator.Moh706IndicatorDefinitions;
import org.openmrs.module.financials.reporting.utils.EhrAddonUtils;
import org.openmrs.module.financials.utils.EhrReportingUtils;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class Moh706DatasetDefinition {
	
	private final Moh706IndicatorDefinitions moh706Indicator;
	
	private final EhrAddonDimension ehrAddonDimesion;
	
	@Autowired
	public Moh706DatasetDefinition(Moh706IndicatorDefinitions moh706Indicator, EhrAddonDimension ehrAddonDimesion) {
		this.moh706Indicator = moh706Indicator;
		this.ehrAddonDimesion = ehrAddonDimesion;
	}
	
	/**
	 * Get moh 706 a dataset
	 * 
	 * @return @{@link DataSetDefinition}
	 */
	public DataSetDefinition getMoh706bDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParam = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("MOH706");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addDimension("age", ReportUtils.map(ehrAddonDimesion.getAge(), "effectiveDate=${endDate}"));
		
		//URINE ANALYSIS
		dsd.addColumn("UAGL", "1.2 Glucose",
		    ReportUtils.map(moh706Indicator.getAllUrineAnalysisGlucoseTestsPositives(), indParam), "");
		dsd.addColumn("UAKET", "1.3 Ketones",
		    ReportUtils.map(moh706Indicator.getAllUrineAnalysisKetonesTestsPositives(), indParam), "");
		dsd.addColumn("UAPTN", "1.4 Proteins",
		    ReportUtils.map(moh706Indicator.getAllUrineAnalysisProteinsTestsPositives(), indParam), "");
		dsd.addColumn("UAPC", "1.6 Pus Cells (<5/hpf)", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(1000538, Arrays.asList(1000537, 164371)), indParam), "");
		dsd.addColumn("UASH", "1.7 S. haematobium", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(1000540, Arrays.asList(1000537, 164371)), indParam), "");
		dsd.addColumn("UATV", "1.8 T. vaginatis", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(163689, Arrays.asList(1499, 1160, 1408)), indParam), "");
		dsd.addColumn("UAYC", "1.9 Urinalysis Yeast Cells", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(163686, Arrays.asList(1363, 1362, 1364)), indParam), "");
		dsd.addColumn("UAB", "1.10 Bacteria", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(165561, Arrays.asList(1363, 1362, 1364)), indParam), "");
		//PARASITOLOGY
		//Malaria Test
		EhrReportingUtils.addRow(dsd, "BST", "Parastology - Malaria test totals",
		    ReportUtils.map(moh706Indicator.getAllMalariaTests(), indParam), EhrAddonUtils.getAgeUnderOver5Columns());
		EhrReportingUtils.addRow(dsd, "BSP", "Parastology - Malaria test positive",
		    ReportUtils.map(moh706Indicator.getAllMalariaTestsPositiveCases(), indParam),
		    EhrAddonUtils.getAgeUnderOver5Columns());
		
		dsd.addColumn("BSRT", "3.3 Malaria Rapid Diagnostic Tests totals",
		    ReportUtils.map(moh706Indicator.getAllRapidMalariaTests(), indParam), "");
		dsd.addColumn("BSRTP", "3.3 Malaria Rapid Diagnostic Tests positives",
		    ReportUtils.map(moh706Indicator.getAllRapidMalariaTestsPositiveCases(), indParam), "");
		dsd.addColumn("TSPP", "3.4 Taenia SPP", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(1000453, Arrays.asList(163748, 164371)), indParam), "");
		dsd.addColumn("HNN", "3.5 Hymenolepis nana", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(1000454, Arrays.asList(163748, 164371)), indParam), "");
		dsd.addColumn("HOW", "3.6 Hookworm", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(1000456, Arrays.asList(163748, 164371)), indParam), "");
		dsd.addColumn("RW", "3.7 Round worms", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(1000457, Arrays.asList(163748, 164371)), indParam), "");
		dsd.addColumn("SM", "3.8 S. mansoni", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(1000452, Arrays.asList(163748, 164371)), indParam), "");
		dsd.addColumn("TT", "3.9 Trichuris trichura", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAnswerIndicator(1000458, Arrays.asList(163748, 164371)), indParam), "");
		/*dsd.addColumn("AMB", "3.10 Amoeba",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000458, 163748,164371), indParam),
						"");*/
		//Serology
		dsd.addColumn("VDRLT", "7.1 VDRL Totals",
		    ReportUtils.map(moh706Indicator.getPatientsWithObsIndicator(299), indParam), "");
		dsd.addColumn("VDRLP", "7.1 VDRL Positive",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(299, Arrays.asList(1228)), indParam), "");
		dsd.addColumn("TPHAT", "7.2 TPHA Totals",
		    ReportUtils.map(moh706Indicator.getPatientsWithObsIndicator(1031), indParam), "");
		dsd.addColumn(
		    "TPHAP",
		    "7.2 TPHA Positive",
		    ReportUtils.map(
		        moh706Indicator.getResponsesBasedOnAnswerIndicator(1031,
		            Arrays.asList(1311, 1312, 1313, 1314, 1315, 1316, 1317, 163622, 163622, 163623, 163624)), indParam), "");
		dsd.addColumn("HIVT", "7.4 HIV Totals",
		    ReportUtils.map(moh706Indicator.getPatientsWithObsIndicator(1169), indParam), "");
		dsd.addColumn("HIVP", "7.4 HIV Positive",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1169, Arrays.asList(703)), indParam), "");
		dsd.addColumn("BRUT", "7.5 Brucella Totals",
		    ReportUtils.map(moh706Indicator.getPatientsWithObsIndicator(305), indParam), "");
		dsd.addColumn("BRUP", "7.5 Brucella Positive",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(305, Arrays.asList(1228)), indParam), "");
		dsd.addColumn("RFT", "7.6 Rheumatoid factor Totals",
		    ReportUtils.map(moh706Indicator.getPatientsWithObsIndicator(161470), indParam), "");
		dsd.addColumn("RFP", "7.6 Rheumatoid factor Positive",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(161470, Arrays.asList(703)), indParam), "");
		dsd.addColumn("HPT", "7.7 Helicobacter pylori Totals",
		    ReportUtils.map(moh706Indicator.getPatientsWithObsIndicator(163348), indParam), "");
		dsd.addColumn("HPP", "7.7 Helicobacter pylori Positive",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(163348, Arrays.asList(703)), indParam), "");
		dsd.addColumn("HPBT", "7.9 Hepatits B test Totals", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAlistOfQuestions(Arrays.asList(159430, 1322, 161472)), indParam), "");
		dsd.addColumn(
		    "HPBP",
		    "7.9 Hepatits B test Positive",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(
		        Arrays.asList(159430, 1322, 161472), Arrays.asList(703)), indParam), "");
		dsd.addColumn("HPCT", "7.10 Hepatits C test Totals",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAlistOfQuestions(Arrays.asList(161471, 1325)), indParam), "");
		dsd.addColumn(
		    "HPCP",
		    "7.10 Hepatits C test Positive",
		    ReportUtils.map(
		        moh706Indicator.getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(Arrays.asList(161471, 1325),
		            Arrays.asList(703)), indParam), "");
		dsd.addColumn("HCGT", "7.11 HCG test Totals",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAlistOfQuestions(Arrays.asList(45)), indParam), "");
		dsd.addColumn("HCGP", "7.11 HCG test Positive", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(Arrays.asList(45), Arrays.asList(703)),
		    indParam), "");
		
		//Blood chemistry
		
		//4.Haematology
		dsd.addColumn("FBCT", "4.1 Haematology tests Full blood count total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(21), indParam), "");
		dsd.addColumn("FBC5", "4.1 Haematology tests Full blood count <5 g/dl",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(21, 0, 5), indParam), "");
		dsd.addColumn("FBC510", "4.1 Haematology tests Full blood count <5 g/dl",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(21, 5, 10), indParam), "");
		dsd.addColumn("CD4T", "4.3 Haematology tests CD4 count total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(5497), indParam), "");
		dsd.addColumn("CD4T500", "4.3 Haematology tests CD4 count < 500",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(5497, 0, 500), indParam),
		    "");
		
		//Other Haematology tests
		dsd.addColumn("STT", "4.4 Other Haematology Tests Sickling test Totals",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAlistOfQuestions(Arrays.asList(160225)), indParam), "");
		dsd.addColumn("STP", "4.4 Other Haematology Tests Sickling test Totals", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(Arrays.asList(45), Arrays.asList(703)),
		    indParam), "");
		dsd.addColumn("PBFT", "4.5 Other Haematology Tests Peripherial blood films Totals",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAlistOfQuestions(Arrays.asList(1000071)), indParam), "");
		dsd.addColumn("BMAT", "4.6 BMA Totals",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAlistOfQuestions(Arrays.asList(163420)), indParam), "");
		
		dsd.addColumn("COAG", "4.7 Other Haematology Tests Coagulation",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAlistOfQuestions(Arrays.asList(163666)), indParam), "");
		
		dsd.addColumn("RCT", "4.8 Other Haematology Tests Reticulocyte Count total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(166395), indParam), "");
		dsd.addColumn("BGT", "4.10 Blood Grouping Total Blood Groups total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(300), indParam), "");
		dsd.addColumn("BGT", "4.11 Blood Grouping Blood Units Grouped total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(160232), indParam), "");
		//Blood Screening at facility
		dsd.addColumn("BSHIVP", "4.18 Blood Screening at facility HIV Positive",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1169, Arrays.asList(703)), indParam), "");
		
		dsd.addColumn(
		    "BSHPBP",
		    "4.19 Blood Screening at facility Hepatiti B Positive",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(
		        Arrays.asList(159430, 1322, 161472), Arrays.asList(703)), indParam), "");
		
		dsd.addColumn(
		    "BSHPCP",
		    "4.20 Blood Screening at facility Hepatitis C Positive",
		    ReportUtils.map(
		        moh706Indicator.getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(Arrays.asList(161471, 1325),
		            Arrays.asList(703)), indParam), "");
		dsd.addColumn("BSSP", "4.21 Blood Screening at facility Syphilis Positive", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(Arrays.asList(163626, 299, 1031),
		        Arrays.asList(703, 1228, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 163621, 163622, 163623, 163624)),
		    indParam), "");
		
		//2. BLOOD CHEMISTRY
		
		dsd.addColumn("BCBST", "2.1 BLOOD CHEMISTRY - Blood Sugar Test - Blood Sugar total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(887), indParam), "");
		dsd.addColumn("BCBSL", "2.1 BLOOD CHEMISTRY - Blood Sugar Test - Low Blood Sugar total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(887, 0, 4), indParam), "");
		dsd.addColumn("BCBSH", "2.1 BLOOD CHEMISTRY - Blood Sugar Test - Low Blood Sugar total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(887, 6, 20), indParam), "");
		dsd.addColumn("OGTT", "2.2 BLOOD CHEMISTRY - Blood Sugar Test - OGTT total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(163594), indParam), "");
		dsd.addColumn("OGTTL", "2.2 BLOOD CHEMISTRY - Blood Sugar Test - Low OGTT total",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(163594, 20, 70), indParam),
		    "");
		dsd.addColumn("OGTTH", "2.2 BLOOD CHEMISTRY - Blood Sugar Test - High OGTT total", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(163594, 120, 450), indParam), "");
		dsd.addColumn("BCLC", "2.4 BLOOD CHEMISTRY - Renal Function Test - Low Creatine",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(790, 0, 0.74), indParam),
		    "");
		dsd.addColumn("BCHC", "2.4 BLOOD CHEMISTRY -  Renal Function Test - High Creatine",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(790, 1.35, 2.0), indParam),
		    "");
		dsd.addColumn("BCLU", "2.5 BLOOD CHEMISTRY -  Renal Function Test - Low Urea",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(163699, 10, 20), indParam),
		    "");
		dsd.addColumn(
		    "BCHU",
		    "2.5 BLOOD CHEMISTRY -  Renal Function Test - High Urea",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(163699, 41, 200), indParam),
		    "");
		dsd.addColumn("BCLS", "2.6 BLOOD CHEMISTRY -  Renal Function Test - Low Sodium",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159656, 0, 2), indParam),
		    "");
		dsd.addColumn("BCHS", "2.6 BLOOD CHEMISTRY - Renal Function Test - High Sodium",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159656, 3, 100), indParam),
		    "");
		
		dsd.addColumn("BCLP", "2.7 BLOOD CHEMISTRY - Renal Function Test -  Low Potassium",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159659, 0, 2), indParam),
		    "");
		dsd.addColumn("BCHP", "2.7 BLOOD CHEMISTRY - Renal Function Test -  High Potassium",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159659, 3, 100), indParam),
		    "");
		
		dsd.addColumn("BCLCH", "2.8 BLOOD CHEMISTRY - Renal Function Test -  Low Chlorides",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159657, 0, 2), indParam),
		    "");
		dsd.addColumn("BCHCH", "2.8 BLOOD CHEMISTRY - Renal Function Test -  High Chlorides",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159657, 3, 100), indParam),
		    "");
		
		dsd.addColumn("LFTLTB", "2.10 BLOOD CHEMISTRY - Liver Function Test -  Low Total Bilirubin",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(655, 0, 2), indParam), "");
		dsd.addColumn("LFTLTB", "2.10 BLOOD CHEMISTRY - Liver Function Test -  High Total Bilirubin",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(655, 3, 100), indParam), "");
		dsd.addColumn("LFTLASAT", "2.11 BLOOD CHEMISTRY - Liver Function Test -  Low ASAT (SGOT)",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(653, 0, 24), indParam), "");
		dsd.addColumn("LFTHASAT", "2.11 BLOOD CHEMISTRY - Liver Function Test -  High ASAT (SGOT)",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(653, 24, 50), indParam), "");
		dsd.addColumn("LFTLSGPT", "2.12 BLOOD CHEMISTRY - Liver Function Test -  Low ALAT (SGPT)",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(654, 0, 1), indParam), "");
		dsd.addColumn("LFTHSGPT", "2.12 BLOOD CHEMISTRY - Liver Function Test -  High ALAT (SGPT)",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(654, 2, 40), indParam), "");
		dsd.addColumn("LFTLSP", "2.13 BLOOD CHEMISTRY - Liver Function Test -  Low Serum Protein",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(717, 0, 1), indParam), "");
		dsd.addColumn("LFTHSP", "2.13 BLOOD CHEMISTRY - Liver Function Test -  High Serum Protein",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(717, 2, 40), indParam), "");
		dsd.addColumn("LFTLA", "2.14 BLOOD CHEMISTRY - Liver Function Test -  Low Albumin",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(848, 0, 2.7), indParam), "");
		dsd.addColumn("LFTHA", "2.14 BLOOD CHEMISTRY - Liver Function Test -  High Albumin",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(848, 2.9, 6.0), indParam),
		    "");
		dsd.addColumn("LFTLAP", "2.15 BLOOD CHEMISTRY - Liver Function Test -  Low Alkaline Phosphatase",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(785, 0, 2.7), indParam), "");
		dsd.addColumn("LFTHAP", "2.15 BLOOD CHEMISTRY - Liver Function Test -  High Alkaline Phosphatase",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(785, 300, 541), indParam),
		    "");
		dsd.addColumn("LPTTC", "2.17 BLOOD CHEMISTRY - Lipid Profile Test -  Total Cholestrol ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(1006), indParam), "");
		dsd.addColumn("LPTLC", "2.17 BLOOD CHEMISTRY - Lipid Profile Test -  Low Cholestrol ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(1006, 0, 1), indParam), "");
		dsd.addColumn("LFTHC", "2.17 BLOOD CHEMISTRY - Lipid Profile Test -  High Cholestrol",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(1006, 5.18, 10), indParam),
		    "");
		
		dsd.addColumn("LPTT", "2.18 BLOOD CHEMISTRY - Lipid Profile Test -  Total Triglycerides ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(166039), indParam), "");
		dsd.addColumn("LPTLT", "2.18 BLOOD CHEMISTRY - Lipid Profile Test -  Low Triglycerides ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(166039, 0, 30), indParam),
		    "");
		dsd.addColumn("LFTHT", "2.18 BLOOD CHEMISTRY - Lipid Profile Test -  High Triglycerides", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(166039, 160, 200), indParam), "");
		
		dsd.addColumn("LPTLDL", "2.19 BLOOD CHEMISTRY - Lipid Profile Test -  Total LDL ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(166045), indParam), "");
		dsd.addColumn("LPTLLDL", "2.19 BLOOD CHEMISTRY - Lipid Profile Test -  Low LDL ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(166045, 0, 30), indParam),
		    "");
		dsd.addColumn("LFTHLDL", "2.19 BLOOD CHEMISTRY - Lipid Profile Test -  High LDL", ReportUtils.map(
		    moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(166045, 150, 200), indParam), "");
		
		dsd.addColumn("HTTT3", "2.20 BLOOD CHEMISTRY - Hormonal Test -  Total T3 ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(161503), indParam), "");
		dsd.addColumn("HTLT3", "2.20 BLOOD CHEMISTRY - Hormonal Test -  Low T3 ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(161503, 0, 0.1), indParam),
		    "");
		dsd.addColumn(
		    "HTHT3",
		    "2.20 BLOOD CHEMISTRY - Hormonal Test -  High T3",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(161503, 0.6, 10), indParam),
		    "");
		
		dsd.addColumn("HTTT4", "2.21 BLOOD CHEMISTRY - Hormonal Test -  Total T4 ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(161504), indParam), "");
		dsd.addColumn("HTLT4", "2.21 BLOOD CHEMISTRY - Hormonal Test -  Low T4 ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(161504, 0, 0.1), indParam),
		    "");
		dsd.addColumn(
		    "HTHT4",
		    "2.21 BLOOD CHEMISTRY - Hormonal Test -  High T4",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(161504, 0.6, 10), indParam),
		    "");
		
		dsd.addColumn("HTTTSH", "2.22 BLOOD CHEMISTRY - Hormonal Test -  Total TSH ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(161505), indParam), "");
		dsd.addColumn("HTLTSH", "2.22 BLOOD CHEMISTRY - Hormonal Test -  Low TSH ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(161505, 0, 0.1), indParam),
		    "");
		dsd.addColumn(
		    "HTHTSH",
		    "2.22 BLOOD CHEMISTRY - Hormonal Test -  High TSH",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(161505, 0.6, 10), indParam),
		    "");
		dsd.addColumn("HTTPSA", "2.23 BLOOD CHEMISTRY - Hormonal Test -  Total PSA ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(160913), indParam), "");
		dsd.addColumn("HTLPSA", "2.23 BLOOD CHEMISTRY - Hormonal Test -  Low PSA ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(160913, 0, 0.1), indParam),
		    "");
		dsd.addColumn(
		    "HTHPSA",
		    "2.23 BLOOD CHEMISTRY - Hormonal Test -  High PSA",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(160913, 0.6, 10), indParam),
		    "");
		dsd.addColumn("HTTCEA", "2.24 BLOOD CHEMISTRY - Tumor Makers -  Total CEA ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(160915), indParam), "");
		dsd.addColumn("HTLCEA", "2.24 BLOOD CHEMISTRY - Tumor Makers -  Low CEA ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(160915, 0, 0.1), indParam),
		    "");
		dsd.addColumn(
		    "HTHCEA",
		    "2.24 BLOOD CHEMISTRY - Tumor Makers -  High CEA",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(160915, 0.6, 10), indParam),
		    "");
		
		dsd.addColumn("CSFTP", "2.25 BLOOD CHEMISTRY - CSF Chemistry -  Total Proteins ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(159646), indParam), "");
		dsd.addColumn("CSFLP", "2.25 BLOOD CHEMISTRY - CSF Chemistry -  Low Proteins",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159646, 0, 0.1), indParam),
		    "");
		dsd.addColumn(
		    "CSFHP",
		    "2.25 BLOOD CHEMISTRY - CSF Chemistry -  High Proteins",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159646, 0.6, 10), indParam),
		    "");
		dsd.addColumn("CSFTC", "2.26 BLOOD CHEMISTRY - CSF Chemistry -  Total Glucose ",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestion(159647), indParam), "");
		dsd.addColumn("CSFLC", "2.26 BLOOD CHEMISTRY - CSF Chemistry -  Low Glucose",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159647, 0, 0.1), indParam),
		    "");
		dsd.addColumn(
		    "CSFHC",
		    "2.26 BLOOD CHEMISTRY - CSF Chemistry -  High Glucose",
		    ReportUtils.map(moh706Indicator.getResponsesBasedOnValueNumericQuestionBetweenLimits(159647, 0.6, 10), indParam),
		    "");
		
		return dsd;
	}
}
