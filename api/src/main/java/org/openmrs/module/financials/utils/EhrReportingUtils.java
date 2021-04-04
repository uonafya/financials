package org.openmrs.module.financials.utils;

import org.openmrs.module.financials.ColumnParameters;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.indicator.CohortIndicator;

import java.util.Date;
import java.util.List;

public class EhrReportingUtils {
	
	public EhrReportingUtils() {
	}
	
	public static CohortIndicator cohortIndicator(String name, Mapped<CohortDefinition> cohort) {
		CohortIndicator ind = new CohortIndicator(name);
		ind.addParameter(new Parameter("startDate", "Start Date", Date.class));
		ind.addParameter(new Parameter("endDate", "End Date", Date.class));
		ind.setCohortDefinition(cohort);
		return ind;
	}
	
	/**
	 * Adds a row to a dataset based on an indicator and a list of column parameters
	 * 
	 * @param cohortDsd the dataset
	 * @param baseName the base columm name
	 * @param baseLabel the base column label
	 * @param indicator the indicator
	 * @param columns the column parameters
	 */
	public static void addRow(CohortIndicatorDataSetDefinition cohortDsd, String baseName, String baseLabel,
	        Mapped<CohortIndicator> indicator, List<ColumnParameters> columns) {
		
		for (ColumnParameters column : columns) {
			String name = baseName + "-" + column.getColumn();
			String label = baseLabel + " (" + column.getLabel() + ")";
			cohortDsd.addColumn(name, label, indicator, column.getDimensions());
		}
	}
	
}
