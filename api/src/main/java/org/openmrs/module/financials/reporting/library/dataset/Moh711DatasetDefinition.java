package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.Concept;
import org.openmrs.Program;
import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
import org.openmrs.module.financials.reporting.library.indicator.Moh711IndicatorDefinition;
import org.openmrs.module.financials.utils.EhrReportingUtils;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyaemr.Dictionary;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.common.RangeComparator;
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
		
		//concepts declarations
		Concept hb = Dictionary.getConcept("21AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept ancNumber = Dictionary.getConcept("1425AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept immunization = Dictionary.getConcept("984AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept llins = Dictionary.getConcept("160428AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept syphilisTest = Dictionary.getConcept("299AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept syphilisTestPositive = Dictionary.getConcept("1228AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept breastExamDone = Dictionary.getConcept("163590AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept yes = Dictionary.getConcept("1065AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept serviceType = Dictionary.getConcept("160478AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept anc = Dictionary.getConcept("1622AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
		//program diclarartion
		Program ancProgram = MetadataUtils.existing(Program.class, "335517a1-04bc-438b-9843-1ba49fb7fcd9");
		
		EhrReportingUtils.addRow(dsd, "A1-1", "No. of ANC Clients", ReportUtils.map(
		    moh711IndicatorDefinition.getAllAncClients(Dictionary.getConcept(Dictionary.PMTCT_PROGRAM)), indParams),
		    femaleStatesColumns);
		dsd.addColumn("A2", "Number of clients given IPT (1st dose)",
		    map(moh711IndicatorDefinition.getAllImmunizedPatientsOnIPT(1), indParams), "");
		dsd.addColumn("A3", "Number of clients given IPT (2nd dose)",
		    map(moh711IndicatorDefinition.getAllImmunizedPatientsOnIPT(2), indParams), "");
		dsd.addColumn("A4", "Number of clients given IPT (3rd dose)",
		    map(moh711IndicatorDefinition.getAllImmunizedPatientsOnIPT(3), indParams), "");
		dsd.addColumn(
		    "A6",
		    "No. of Clients with Hb < 11 g/dl",
		    map(moh711IndicatorDefinition.getAllClientsWithMumericValuesComparedToAthreshold(hb, 11,
		        RangeComparator.LESS_THAN), ""), "");
		dsd.addColumn(
		    "A7",
		    "No. of Clients completed 4 Antenatal Visits",
		    map(moh711IndicatorDefinition.getAllClientsWithMumericValuesComparedToAthreshold(ancNumber, 4,
		        RangeComparator.EQUAL), ""), "");
		dsd.addColumn("A8", "No.LLINs distributed to under 1 year",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(immunization, llins), ""), "age=0-1");
		
		dsd.addColumn("A9", "No. of LLINs distributed to ANC clients",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(immunization, llins), ""), "gender=F|age=15+");
		
		dsd.addColumn("A10", "No. of clients who tested for syphilis",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(syphilisTest), ""), "gender=F");
		
		dsd.addColumn("A11", "No. of clients who tested for syphilis positive",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(syphilisTest, syphilisTestPositive), ""), "gender=F");
		dsd.addColumn("A12", "Total women done breast examination",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(breastExamDone, yes), ""), "gender=F");
		dsd.addColumn("A13", "No. of adolescents (10-14 years) presenting with pregnancy at 1st ANC Visit",
		    map(moh711IndicatorDefinition.getPatientWithCodedObsAndProgram(ancProgram, serviceType, anc), ""),
		    "gender=F|age=10-14");
		dsd.addColumn("A14", "No. of adolescents (15-19 years) presenting with pregnancy at 1st ANC Visit",
		    map(moh711IndicatorDefinition.getPatientWithCodedObsAndProgram(ancProgram, serviceType, anc), ""),
		    "gender=F|age=15-19");
		dsd.addColumn("A15", "No. of adolescents (20-24 years) presenting with pregnancy at 1st ANC Visit",
		    map(moh711IndicatorDefinition.getPatientWithCodedObsAndProgram(ancProgram, serviceType, anc), ""),
		    "gender=F|age=20-24");
		dsd.addColumn("A16", "No. of Women presenting with pregnancy  at 1ST ANC in the First Trimeseter(<= 12 Weeks)",
		    map(moh711IndicatorDefinition.getPatientGestationPeriod(), ""), "gender=F");
		
		return dsd;
	}
	
}
