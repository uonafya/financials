package org.openmrs.module.financials.reports;

import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.financials.reporting.library.dataset.Moh705bDatasetDefinition;
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
@Builds({ "ehraddons.common.705b" })
public class SetupMOH705bReport extends AbstractReportBuilder {
	
	private Moh705bDatasetDefinition moh705bDatasetDefinition;
	
	@Autowired
	public SetupMOH705bReport(Moh705bDatasetDefinition moh705bDatasetDefinition,
	    CommonDatasetDefinition commonDatasetDefinition) {
		this.moh705bDatasetDefinition = moh705bDatasetDefinition;
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor reportDescriptor,
	        ReportDefinition reportDefinition) {
		return Arrays.asList(map(moh705bDatasetDefinition.getMoh705bDataset(), "startDate=${startDate},endDate=${endDate}"));
		//map(commonDatasetDefinition.getFacilityMetadata(), ""));
	}
	
}
