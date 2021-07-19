package org.openmrs.module.financials.reports;

import org.openmrs.module.financials.reporting.library.dataset.CommonDatasetDefinition;
import org.openmrs.module.financials.reporting.library.dataset.Moh717DatasetDefinition;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.builder.AbstractReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.definition.ReportDefinition;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;

//@Component
@Builds({ "ehraddons.common.717" })
public class SetupMOH717Report extends AbstractReportBuilder {
	
	private Moh717DatasetDefinition moh717DatasetDefinition;
	
	private CommonDatasetDefinition commonDatasetDefinition;
	
	//@Autowired
	public SetupMOH717Report(Moh717DatasetDefinition moh717DatasetDefinition, CommonDatasetDefinition commonDatasetDefinition) {
		this.moh717DatasetDefinition = moh717DatasetDefinition;
		this.commonDatasetDefinition = commonDatasetDefinition;
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class));
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor reportDescriptor,
	        ReportDefinition reportDefinition) {
		return Arrays.asList(
		    map(moh717DatasetDefinition.constructMoh717Dataset(), "startDate=${startDate},endDate=${endDate}"),
		    map(commonDatasetDefinition.getFacilityMetadata(), ""));
	}
	
}
