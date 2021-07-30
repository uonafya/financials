package org.openmrs.module.financials.reports;

import org.openmrs.module.financials.reporting.library.dataset.Moh717DatasetDefinition;
import org.openmrs.module.financials.reporting.library.dataset.TurnAroundTimeDatasetDefinition;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.builder.AbstractReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
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
@Builds({ "ehraddons.common.report.turn.around.time" })
public class SetupTurnAroundTimeReport extends AbstractReportBuilder {
	
	private TurnAroundTimeDatasetDefinition turnAroundTimeDatasetDefinition;
	
	@Autowired
	public SetupTurnAroundTimeReport(TurnAroundTimeDatasetDefinition turnAroundTimeDatasetDefinition) {
		this.turnAroundTimeDatasetDefinition = turnAroundTimeDatasetDefinition;
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor reportDescriptor,
	        ReportDefinition reportDefinition) {
		PatientDataSetDefinition all = turnAroundTimeDatasetDefinition.turnAroundTime();
		all.addRowFilter(turnAroundTimeDatasetDefinition.allPatientsCohort());
		return Arrays.asList(map((DataSetDefinition) all, "startDate=${startDate},endDate=${endDate}"));
	}
}
