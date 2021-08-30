package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MohOpthalimicDatasetDefinition {
	
	private EhrAddonDimesion ehrAddonDimesion;
	
	@Autowired
	MohOpthalimicDatasetDefinition(EhrAddonDimesion ehrAddonDimesion) {
		this.ehrAddonDimesion = ehrAddonDimesion;
	}
	
	public DataSetDefinition getMohOpthlamicDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParam = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("MOHOP");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		return dsd;
	}
}
