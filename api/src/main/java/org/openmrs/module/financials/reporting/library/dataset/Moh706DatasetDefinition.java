package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
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
	
	private final EhrAddonDimesion ehrAddonDimesion;
	
	@Autowired
	public Moh706DatasetDefinition(Moh706IndicatorDefinitions moh706Indicator, EhrAddonDimesion ehrAddonDimesion) {
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
		
		return dsd;
	}
}
