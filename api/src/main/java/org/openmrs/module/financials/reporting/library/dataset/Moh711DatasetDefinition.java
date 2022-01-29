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
	
	//A
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
		
		//Add state dimensions
		ColumnParameters fNew = new ColumnParameters("FANEW", "New cases female", "state=NEW", "01");
		ColumnParameters fRevisit = new ColumnParameters("FARVT", "Revisit cases female", "state=RVT", "02");
		ColumnParameters fTotal = new ColumnParameters("FATT", "Total cases female", "", "03");
		
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
		Concept ironFollate = Dictionary.getConcept("104677AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Concept ironGiven = Dictionary.getConcept("161004AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
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
		        RangeComparator.LESS_THAN), indParams), "");
		dsd.addColumn(
		    "A7",
		    "No. of Clients completed 4 Antenatal Visits",
		    map(moh711IndicatorDefinition.getAllClientsWithMumericValuesComparedToAthreshold(ancNumber, 4,
		        RangeComparator.EQUAL), indParams), "");
		dsd.addColumn("A8", "No.LLINs distributed to under 1 year",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(immunization, llins), indParams), "age=0-1");
		
		dsd.addColumn("A9", "No. of LLINs distributed to ANC clients",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(immunization, llins), indParams), "gender=F|age=15+");
		
		dsd.addColumn("A10", "No. of clients who tested for syphilis",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(syphilisTest), indParams), "gender=F");
		
		dsd.addColumn("A11", "No. of clients who tested for syphilis positive",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(syphilisTest, syphilisTestPositive), indParams), "gender=F");
		dsd.addColumn("A12", "Total women done breast examination",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(breastExamDone, yes), indParams), "gender=F");
		dsd.addColumn("A13", "No. of adolescents (10-14 years) presenting with pregnancy at 1st ANC Visit",
		    map(moh711IndicatorDefinition.getPatientWithCodedObsAndProgram(ancProgram, serviceType, anc), indParams),
		    "gender=F|age=10-14");
		dsd.addColumn("A14", "No. of adolescents (15-19 years) presenting with pregnancy at 1st ANC Visit",
		    map(moh711IndicatorDefinition.getPatientWithCodedObsAndProgram(ancProgram, serviceType, anc), indParams),
		    "gender=F|age=15-19");
		dsd.addColumn("A15", "No. of adolescents (20-24 years) presenting with pregnancy at 1st ANC Visit",
		    map(moh711IndicatorDefinition.getPatientWithCodedObsAndProgram(ancProgram, serviceType, anc), indParams),
		    "gender=F|age=20-24");
		dsd.addColumn("A16", "No. of Women presenting with pregnancy  at 1ST ANC in the First Trimeseter(<= 12 Weeks)",
		    map(moh711IndicatorDefinition.getPatientGestationPeriod(12), indParams), "gender=F");
		dsd.addColumn("A17", "No. of clients issued with Iron",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(ironGiven, yes), indParams), "gender=F");
		dsd.addColumn("A16", "No. of clients issued with Folic",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(ironFollate, yes), indParams), "gender=F");
		dsd.addColumn("A19", "No. of clients issued with Combined Ferrous Folate",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(immunization, ironFollate), indParams), "gender=F");
		dsd.addColumn("A20", "No. of pregnant women presenting in ANC with complication associated with FGM",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(immunization, ironFollate), indParams), "gender=F");
		
		return dsd;
	}
	
	//B
	public DataSetDefinition getMohMeternityAndNewBornsDataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParams = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("B");
		dsd.setDescription("Maternity and Newborn");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		dsd.addColumn(
		    "B1",
		    "Normal Deliveries",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(Dictionary.getConcept(Dictionary.METHOD_OF_DELIVERY),
		        Dictionary.getConcept("1170AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), indParams), "gender=F");
		dsd.addColumn(
		    "B2",
		    "Caesarean Sections",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(Dictionary.getConcept(Dictionary.METHOD_OF_DELIVERY),
		        Dictionary.getConcept("1171AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), indParams), "gender=F");
		dsd.addColumn(
		    "B3",
		    "Breech Delivery",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(Dictionary.getConcept(Dictionary.METHOD_OF_DELIVERY),
		        Dictionary.getConcept("1172AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), indParams), "gender=F");
		dsd.addColumn(
		    "B4",
		    "Assisted Vaginal Deliveries (Vacuum Extraction)",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(Dictionary.getConcept(Dictionary.METHOD_OF_DELIVERY),
		        Dictionary.getConcept("118159AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), indParams), "gender=F");
		dsd.addColumn(
		    "B6",
		    "Live Births",
		    map(moh711IndicatorDefinition.getPatientWithCodedObs(
		        Dictionary.getConcept("159917AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
		        Dictionary.getConcept("151849AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), indParams), "");
		dsd.addColumn(
		    "B7",
		    "No. of Low birth weight Babies (below 2500 grams)",
		    map(moh711IndicatorDefinition.getAllClientsWithMumericValuesComparedToAthreshold(
		        Dictionary.getConcept("5916AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), 2500, RangeComparator.LESS_THAN), indParams),
		    "");
		
		return dsd;
	}
	
	//C
	public DataSetDefinition getSexualAndGenderBasedViolence() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		String indParams = "startDate=${startDate},endDate=${endDate}";
		dsd.setName("C");
		dsd.setDescription("Maternity and Newborn");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		dsd.addDimension("age", ReportUtils.map(ehrAddonDimesion.getStandardAge(), "effectiveDate=${endDate}"));
		//add the required dimension
		ColumnParameters zeroTo9 = new ColumnParameters("zeroTo9", "0-9 years", "age=0-9", "01");
		ColumnParameters tenTo17 = new ColumnParameters("tenTo17", "10-17 years", "age=10-17", "02");
		ColumnParameters eighteenTo49 = new ColumnParameters("eighteenTo49", "18-49 years", "age=18-49", "03");
		ColumnParameters fiftyPlus = new ColumnParameters("fiftyPlus", "50+ years", "age=50+", "04");
		ColumnParameters total = new ColumnParameters("FAT", "Total", "", "05");
		
		//sum all the columns into a list
		List<ColumnParameters> disagg = Arrays.asList(zeroTo9, tenTo17, eighteenTo49, fiftyPlus, total);
		
		EhrReportingUtils.addRow(dsd, "CO", "Total SGBV survivors seen(Rape, attempted rape , defilement and assault)",
		    ReportUtils.map(moh711IndicatorDefinition.getPatientWithSgbv(), indParams), disagg);
		return dsd;
	}
	
}
