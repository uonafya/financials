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
		dsd.addColumn("UAPC", "1.6 Pus Cells (<5/hpf)",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000538,1000537,164371), indParam), "");
		dsd.addColumn("UASH", "1.7 S. haematobium",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000540,1000537,164371), indParam), "");
		dsd.addColumn("UATV", "1.8 T. vaginatis",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(163689,1499,1160,1408), indParam), "");
		dsd.addColumn("UAYC", "1.9 Urinalysis Yeast Cells",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(163686,1363,1362,1364), indParam), "");
		dsd.addColumn("UAB", "1.10 Bacteria",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(165561,1363,1362,1364), indParam), "");
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
		    ReportUtils.map(moh706Indicator.getAllRapidMalariaTestsPositiveCases(), indParam),
		    "");
		dsd.addColumn("TSPP", "3.4 Taenia SPP",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000453, 163748,164371), indParam),
						"");
		dsd.addColumn("HNN", "3.5 Hymenolepis nana",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000454, 163748,164371), indParam),
						"");
		dsd.addColumn("HOW", "3.6 Hookworm",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000456, 163748,164371), indParam),
						"");
		dsd.addColumn("RW", "3.7 Round worms",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000457, 163748,164371), indParam),
						"");
		dsd.addColumn("SM", "3.8 S. mansoni",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000452, 163748,164371), indParam),
						"");
		dsd.addColumn("TT", "3.9 Trichuris trichura",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000458, 163748,164371), indParam),
						"");
		/*dsd.addColumn("AMB", "3.10 Amoeba",
						ReportUtils.map(moh706Indicator.getResponsesBasedOnAnswerIndicator(1000458, 163748,164371), indParam),
						"");*/
		//Serology
		
		return dsd;
	}
}
