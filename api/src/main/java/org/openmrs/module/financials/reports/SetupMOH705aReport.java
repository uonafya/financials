package org.openmrs.module.financials.reports;

import org.openmrs.module.financials.reporting.library.dataset.Moh705aDatasetDefinition;
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
@Builds({ "ehraddons.common.705a" })
public class SetupMOH705aReport extends AbstractReportBuilder {
	
	private Moh705aDatasetDefinition moh705aDatasetDefinition;
	
	@Autowired
	public SetupMOH705aReport(Moh705aDatasetDefinition moh705aDatasetDefinition) {
		this.moh705aDatasetDefinition = moh705aDatasetDefinition;
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor reportDescriptor,
	        ReportDefinition reportDefinition) {
		return Arrays.asList(map(moh705aDatasetDefinition.getMoh705aDataset(),
		    "startDate=${startDate},endDate=${endDate+23h}"));
	}
}
