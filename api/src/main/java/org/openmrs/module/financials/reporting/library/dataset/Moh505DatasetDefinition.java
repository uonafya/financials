package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimension;
import org.openmrs.module.financials.reporting.library.indicator.Moh505IndicatorDefinition;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Moh505DatasetDefinition {
	
	private final EhrAddonDimension ehrAddonDimension;
	
	private final Moh505IndicatorDefinition moh505IndicatorDefinition;
	
	@Autowired
	public Moh505DatasetDefinition(EhrAddonDimension ehrAddonDimension, Moh505IndicatorDefinition moh505IndicatorDefinition) {
		this.ehrAddonDimension = ehrAddonDimension;
		this.moh505IndicatorDefinition = moh505IndicatorDefinition;
	}
	
	public DataSetDefinition getMoh505Dataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParam = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("MOH505");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		dsd.addColumn("Sample", "Sample ALL", ReportUtils.map(moh505IndicatorDefinition.getAllSamplePatients(), indParam),
		    "");
		
		return dsd;
	}
}
