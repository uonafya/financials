package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
import org.openmrs.module.financials.reporting.library.indicator.Moh711IndicatorDefinition;
import org.openmrs.module.financials.utils.EhrReportingUtils;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyaemr.Dictionary;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.openmrs.module.financials.ColumnParameters;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh711DatasetDefinition {
	
	private Moh711IndicatorDefinition moh711IndicatorDefinition;
	
	private EhrAddonDimesion ehrAddonDimesion;
	
	@Autowired
	public Moh711DatasetDefinition(Moh711IndicatorDefinition moh711IndicatorDefinition, EhrAddonDimesion ehrAddonDimesion) {
		this.moh711IndicatorDefinition = moh711IndicatorDefinition;
		this.ehrAddonDimesion = ehrAddonDimesion;
	}
	
	public DataSetDefinition getMohAncPmtctDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParams = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("A");
		dsd.setDescription("ANC/PMCT");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addDimension("age", ReportUtils.map(ehrAddonDimesion.getStandardAge(), "effectiveDate=${endDate}"));
		dsd.addDimension("gender", ReportUtils.map(ehrAddonDimesion.getGender()));
		dsd.addDimension("state", map(ehrAddonDimesion.newOrRevisits(), "startDate=${startDate},endDate=${endDate}"));
		
		//add the required dimension
		ColumnParameters f10To14 = new ColumnParameters("FA1014", "10-14 years, female", "gender=F|age=10-14", "01");
		ColumnParameters f15To19 = new ColumnParameters("FA1519", "15-19 years, female", "gender=F|age=15-19", "02");
		ColumnParameters f20To24 = new ColumnParameters("FA2024", "20-24 years, female", "gender=F|age=20-24", "03");
		ColumnParameters total = new ColumnParameters("FAT", "Total female", "", "04");
		
		//Add state dimensions
		ColumnParameters fNew = new ColumnParameters("FANEW", "New cases female", "state=NEW", "01");
		ColumnParameters fRevisit = new ColumnParameters("FARVT", "Revisit cases female", "state=RVT", "02");
		ColumnParameters fTotal = new ColumnParameters("FATT", "Total cases female", "", "03");
		
		List<ColumnParameters> femaleColumns = Arrays.asList(f10To14, f15To19, f20To24, total);
		List<ColumnParameters> femaleStatesColumns = Arrays.asList(fNew, fRevisit, fTotal);
		
		EhrReportingUtils.addRow(dsd, "A1-1", "No. of ANC Clients", ReportUtils.map(
		    moh711IndicatorDefinition.getAllAncClients(Dictionary.getConcept(Dictionary.PMTCT_PROGRAM)), indParams),
		    femaleStatesColumns);
		dsd.addColumn("A2", "Number of clients given IPT (1st dose)",
		    map(moh711IndicatorDefinition.getAllImmunizedPatientsOnIPT(1), ""), "");
		dsd.addColumn("A3", "Number of clients given IPT (2nd dose)",
		    map(moh711IndicatorDefinition.getAllImmunizedPatientsOnIPT(2), ""), "");
		dsd.addColumn("A4", "Number of clients given IPT (3rd dose)",
		    map(moh711IndicatorDefinition.getAllImmunizedPatientsOnIPT(3), ""), "");
		
		return dsd;
	}
	
}
