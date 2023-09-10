package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.reporting.utils.EhrAddonUtils;
import org.openmrs.module.financials.utils.EhrReportingUtils;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Moh705DatasetDefinition {
	
	public DataSetDefinition constructMoh705AllDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		dsd.setName("MOH705c");
		dsd.addParameter(new Parameter("startDate", "Start date", Date.class));
		dsd.addParameter(new Parameter("endDate", "Start date", Date.class));
		String indParams = "startDate=${startDate},endDate=${endDate}";
		
		EncounterType opdEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "ba45c278-f290-11ea-9666-1b3e6e848887");
		
		/*EhrReportingUtils.addRow(dsd, "NFAC", "No of first attendances",
		        ReportUtils.map(moh705aIndicator.getNewChildrenPatients(), indParam), EhrAddonUtils.getAdultChildrenColumns());

		EhrReportingUtils.addRow(dsd, "RAC", "Re-attendances",
		        ReportUtils.map(moh705aIndicator.getRevisitsChildrenPatients(), indParam),
		        EhrAddonUtils.getAdultChildrenColumns());*/
		
		return dsd;
	}
}
