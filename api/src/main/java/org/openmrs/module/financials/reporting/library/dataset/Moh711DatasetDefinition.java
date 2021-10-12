package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.diagnosis.lists.DiagnosisLists;
import org.openmrs.module.financials.reporting.library.cohorts.Moh711CohortDefinition;
import org.openmrs.module.financials.reporting.library.indicator.Moh711IndicatorDefinition;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Moh711DatasetDefinition {
	
	private Moh711IndicatorDefinition moh711IndicatorDefinition;
	
	@Autowired
	public Moh711DatasetDefinition(Moh711IndicatorDefinition moh711IndicatorDefinition) {
		this.moh711IndicatorDefinition = moh711IndicatorDefinition;
	}
	
	public DataSetDefinition getMoh711Dataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParam = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("MOH711");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		dsd.addColumn("PALL", "ALL PATIENTS", ReportUtils.map(moh711IndicatorDefinition.getAllPatients(), indParam), "");
		return dsd;
	}
}
