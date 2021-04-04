package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
import org.openmrs.module.financials.reporting.library.indicator.Moh717IndicatorDefinition;
import org.openmrs.module.financials.reporting.utils.EhrAddonUtils;
import org.openmrs.module.financials.utils.EhrReportingUtils;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh717DatasetDefinition {
	
	private Moh717IndicatorDefinition moh717IndicatorDefinition;
	
	private EhrAddonDimesion ehrAddonDimesion;
	
	@Autowired
	public Moh717DatasetDefinition(Moh717IndicatorDefinition moh717IndicatorDefinition, EhrAddonDimesion ehrAddonDimesion) {
		this.moh717IndicatorDefinition = moh717IndicatorDefinition;
		this.ehrAddonDimesion = ehrAddonDimesion;
	}
	
	public DataSetDefinition constructMoh717Dataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		dsd.setName("MOH717");
		dsd.addParameter(new Parameter("startDate", "Start date", Date.class));
		dsd.addParameter(new Parameter("endDate", "end date", Date.class));
		String indParams = "startDate=${startDate},endDate=${endDate}";
		dsd.addDimension("state", map(ehrAddonDimesion.getNewOrRevisitPatients(), "endDate=${endDate}"));
		dsd.addDimension("age", map(ehrAddonDimesion.getAge(), "effectiveDate=${endDate}"));
		
		EhrReportingUtils.addRow(dsd, "OS", "OUTPATIENT SERVICES",
		    ReportUtils.map(moh717IndicatorDefinition.getAllPatients(), indParams),
		    EhrAddonUtils.getGeneralOutPatientFilters());
		
		//TODO replace hardcoded mch  clinic
		EhrReportingUtils.addRow(
		    dsd,
		    "SPC",
		    "SPECIAL CLINICS",
		    ReportUtils.map(
		        moh717IndicatorDefinition.getSpecialClinicPatients(EhrAddonsConstants.getConcept(
		            EhrAddonsConstants._EhrAddOnConcepts.MCH_CLINIC).getConceptId()), indParams),
		    EhrAddonUtils.getSpecialClinicPatientFilters());
		return dsd;
	}
	
}
