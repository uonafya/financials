package org.openmrs.module.financials.reports;

import org.openmrs.module.financials.reporting.library.dataset.Moh706DatasetDefinition;
import org.openmrs.module.financials.reporting.library.dataset.Moh717DatasetDefinition;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.builder.AbstractReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
@Builds({ "ehraddons.common.706" })
public class SetupMOH706Report extends AbstractReportBuilder {
	
	private Moh706DatasetDefinition moh706DatasetDefinition;
	
	@Autowired
	public SetupMOH706Report(Moh706DatasetDefinition moh706DatasetDefinition) {
		this.moh706DatasetDefinition = moh706DatasetDefinition;
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor reportDescriptor,
	        ReportDefinition reportDefinition) {
		return Arrays.asList(map(moh706DatasetDefinition.getMoh706bDataset(), "startDate=${startDate},endDate=${endDate}"));
	}
}
