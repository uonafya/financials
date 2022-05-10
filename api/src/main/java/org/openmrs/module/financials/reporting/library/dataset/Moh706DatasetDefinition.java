package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
import org.openmrs.module.financials.reporting.library.indicator.Moh705IndicatorDefinitions;
import org.openmrs.module.financials.reporting.library.indicator.Moh706IndicatorDefinitions;
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
	
	@Autowired
	public Moh706DatasetDefinition(Moh706IndicatorDefinitions moh706Indicator) {
		this.moh706Indicator = moh706Indicator;
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
		
		dsd.addColumn("UAGL", "Urinalysis glucose presence",
		    ReportUtils.map(moh706Indicator.getAllUrineAnalysisGlucoseTestsPositives(), indParam), "");
		return dsd;
	}
}
