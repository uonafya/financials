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
		dsd.addColumn("UAGL", "Urinalysis glucose presence",
		    ReportUtils.map(moh706Indicator.getAllUrineAnalysisGlucoseTestsPositives(), indParam), "");
		dsd.addColumn("UAKET", "Urinalysis ketones presence",
		    ReportUtils.map(moh706Indicator.getAllUrineAnalysisKetonesTestsPositives(), indParam), "");
		dsd.addColumn("UAPTN", "Urinalysis ketones proteins",
		    ReportUtils.map(moh706Indicator.getAllUrineAnalysisProteinsTestsPositives(), indParam), "");
		//PARASITOLOGY
		//Malaria Test
		EhrReportingUtils.addRow(dsd, "BST", "Parastology - Malaria test totals",
		    ReportUtils.map(moh706Indicator.getAllMalariaTests(), indParam), EhrAddonUtils.getAgeUnderOver5Columns());
		EhrReportingUtils.addRow(dsd, "BSP", "Parastology - Malaria test positive",
		    ReportUtils.map(moh706Indicator.getAllMalariaTestsPositiveCases(), indParam),
		    EhrAddonUtils.getAgeUnderOver5Columns());
		EhrReportingUtils.addRow(dsd, "BSRT", "Parastology - Malaria Rapid test",
		    ReportUtils.map(moh706Indicator.getAllRapidMalariaTests(), indParam), EhrAddonUtils.getAgeUnderOver5Columns());
		EhrReportingUtils.addRow(dsd, "BSRTP", "Parastology - Malaria Rapid test positive",
		    ReportUtils.map(moh706Indicator.getAllRapidMalariaTestsPositiveCases(), indParam),
		    EhrAddonUtils.getAgeUnderOver5Columns());
		//Serology
		
		return dsd;
	}
}
