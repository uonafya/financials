package org.openmrs.module.financials.reports;

import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.reporting.library.cohorts.Moh705CohortDefinition;
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
@Builds({ "financials.common.705b" })
public class SetupMOH705bReport extends AbstractReportBuilder {
	
	private final Moh705bDatasetDefinition moh705bDatasetDefinition;
	
	private final Moh705CohortDefinition moh705CohortDefinition;
	
	@Autowired
	public SetupMOH705bReport(Moh705bDatasetDefinition moh705bDatasetDefinition,
	    Moh705CohortDefinition moh705CohortDefinition) {
		this.moh705bDatasetDefinition = moh705bDatasetDefinition;
		this.moh705CohortDefinition = moh705CohortDefinition;
	}
	
	@Override
	protected List<Parameter> getParameters(ReportDescriptor reportDescriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class), new Parameter("dateBasedReporting", "", String.class));
	}
	
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor reportDescriptor,
	        ReportDefinition reportDefinition) {
		EncounterType opdEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "ba45c278-f290-11ea-9666-1b3e6e848887");
		reportDefinition.setBaseCohortDefinition(map(
		    moh705CohortDefinition.getAllPatientsWithDiagnosis(opdEncounterType.getEncounterTypeId()),
		    "startDate=${startDate},endDate=${endDate+23h}"));
		return Arrays.asList(map(moh705bDatasetDefinition.getMoh705bDataset(),
		    "startDate=${startDate},endDate=${endDate+23h}"));
	}
	
}
