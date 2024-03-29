package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.ColumnParameters;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
import org.openmrs.module.financials.reporting.library.indicator.Moh711IndicatorDefinition;
import org.openmrs.module.financials.reporting.library.indicator.Moh717IndicatorDefinition;
import org.openmrs.module.financials.utils.EhrReportingUtils;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.openmrs.module.financials.EhrAddonsConstants.getConcept;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh717DatasetDefinition {
	
	private Moh717IndicatorDefinition moh717IndicatorDefinition;
	
	private Moh711IndicatorDefinition moh711IndicatorDefinition;
	
	private EhrAddonDimesion ehrAddonDimesion;
	
	@Autowired
	public Moh717DatasetDefinition(Moh717IndicatorDefinition moh717IndicatorDefinition, EhrAddonDimesion ehrAddonDimesion,
	    Moh711IndicatorDefinition moh711IndicatorDefinition) {
		this.moh717IndicatorDefinition = moh717IndicatorDefinition;
		this.ehrAddonDimesion = ehrAddonDimesion;
		this.moh711IndicatorDefinition = moh711IndicatorDefinition;
	}
	
	public DataSetDefinition constructMoh717Dataset() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		dsd.setName("MOH717");
		dsd.addParameter(new Parameter("startDate", "Start date", Date.class));
		dsd.addParameter(new Parameter("endDate", "end date", Date.class));
		String indParams = "startDate=${startDate},endDate=${endDate}";
		
		dsd.addDimension("age", map(ehrAddonDimesion.getAge(), "effectiveDate=${endDate}"));
		dsd.addDimension("gender", map(ehrAddonDimesion.getGender(), ""));
		dsd.addDimension("clinic",
		    map(ehrAddonDimesion.getSpecialClinicVisits(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
		dsd.addDimension("state", map(ehrAddonDimesion.newOrRevisits(), "startDate=${startDate},endDate=${endDate}"));
		
		EhrReportingUtils.addRow(dsd, "OSN", "OUTPATIENT SERVICES NEW PATIENTS",
		    ReportUtils.map(moh717IndicatorDefinition.getAllNewPatients(), indParams), getGeneralOutPatientFilters());
		
		EhrReportingUtils.addRow(dsd, "OSR", "OUTPATIENT SERVICES REVIST PATIENTS",
		    ReportUtils.map(moh717IndicatorDefinition.getAllRevisitPatients(), indParams), getGeneralOutPatientFilters());
		
		EhrReportingUtils.addRow(dsd, "SPCN", "SPECIAL CLINICS NEW PATIENTS",
		    ReportUtils.map(moh717IndicatorDefinition.getSpecialClinicNewPatients(), indParams),
		    getSpecialClinicPatientFilters());
		
		EhrReportingUtils.addRow(dsd, "SPCR", "SPECIAL CLINICS REVISIT PATIENTS",
		    ReportUtils.map(moh717IndicatorDefinition.getSpecialClinicRevisitPatients(), indParams),
		    getSpecialClinicPatientFilters());
		
		dsd.addColumn("AOSCN", "All Other special Clinic - New",
		    ReportUtils.map(moh717IndicatorDefinition.getSpecialClinicOutOfRangePatients(), indParams), "state=NEW");
		dsd.addColumn("AOSCR", "All Other special Clinic - Revisit",
		    ReportUtils.map(moh717IndicatorDefinition.getSpecialClinicOutOfRangePatients(), indParams), "state=RVT");
		
		dsd.addColumn("MOPCNEW", "All MOPC - NEW",
		    ReportUtils.map(moh717IndicatorDefinition.getSpecialClinicMopc(), indParams), "state=NEW");
		
		dsd.addColumn("MOPCRVT", "All MOPC - REVISIT",
		    ReportUtils.map(moh717IndicatorDefinition.getSpecialClinicMopc(), indParams), "state=RVT");
		
		dsd.addColumn(
		    "DENTALNEW",
		    "Attendances (Excluding fillings and extractions) - NEW",
		    ReportUtils.map(moh717IndicatorDefinition.getDentalVisits(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.DENTAL_OPD).getConceptId(),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.DENTAL_SPECIAL_CLINIC).getConceptId()), indParams),
		    "state=NEW");
		
		dsd.addColumn(
		    "DENTALRVT",
		    "Attendances (Excluding fillings and extractions) - REVISIT",
		    ReportUtils.map(moh717IndicatorDefinition.getDentalVisits(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.DENTAL_OPD).getConceptId(),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.DENTAL_SPECIAL_CLINIC).getConceptId()), indParams),
		    "state=RVT");
		
		//additional indicators on 717 report
		dsd.addColumn("DENTALFILLNEW", "Filling - NEW", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Temporary_Filling_Per_Tooth),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Amalgam_filling),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Composite_Filling),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Glass_Lonomer_Filling))), indParams), "state=NEW");
		dsd.addColumn("DENTALFILLRVT", "Filling - RVT", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Temporary_Filling_Per_Tooth),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Amalgam_filling),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Composite_Filling),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Glass_Lonomer_Filling))), indParams), "state=RVT");
		dsd.addColumn("DENTALEXTNEW", "Extraction - NEW", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Extra_Tooth_extraction),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tooth_Extraction_simple),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Excision_of_Tooth))), indParams), "state=NEW");
		dsd.addColumn("DENTALEXTRVT", "Extraction - RVT", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Extra_Tooth_extraction),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Tooth_Extraction_simple),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Excision_of_Tooth))), indParams), "state=RVT");
		dsd.addColumn("MEDEXAM", "MEdical Examination", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.MEDICAL_EXAMINATION_ROUTINE),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Student_Medical_Assessment))), indParams), "");
		dsd.addColumn("DRESSING", "Dressing", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.CLEAN_AND_DRESSING),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Suture_wound_with_dressing),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Wound_Dressing),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Change_of_Dressing),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Burn_dressing),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Dressing_change_under_anesthesia),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Dressing_change_for_open_wound_of_breast),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Cleaning_and_Dressing),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Casualty_Dressing),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.ENT_Dressing),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Removal_Of_Ear_Dressing),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Wound_Dressing_ENT),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Dressing_Per_Session_Female_Medical_Ward))), indParams), "");
		dsd.addColumn("REMSTI", "Removal of Stitches", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Removal_Of_Stitches),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Removal_Of_Corneal_Stitches))), indParams), "");
		dsd.addColumn("INJEC", "Injection", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.INJECTION),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Sub_Conjuctiral_Sub_Tenon_Injections),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Intra_arterial_injection),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Intralesional_injection))), indParams), "");
		dsd.addColumn("STITCH", "Stitching", ReportUtils.map(moh711IndicatorDefinition.getPatientWithCodedObs(
		    getConcept(EhrAddonsConstants._EhrAddOnConcepts.Procedure_not_performed), Arrays.asList(
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.STITCHING),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Stitching_Casualty),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Stitching_In_Minor_Theatre),
		        getConcept(EhrAddonsConstants._EhrAddOnConcepts.Dental_stitching))), indParams), "");
		
		return dsd;
	}
	
	private List<ColumnParameters> getGeneralOutPatientFilters() {
		
		ColumnParameters under5Male = new ColumnParameters("under5Mal", "below 5 male", "age=<5|gender=M", "01");
		ColumnParameters under5Female = new ColumnParameters("under5Female", "below 5 female", "age=<5|gender=F", "02");
		ColumnParameters over5Male = new ColumnParameters("over5Male", "above 5 male", "age=>5|gender=M", "03");
		ColumnParameters over5Female = new ColumnParameters("over5Female", "above 5 female", "age=>5|gender=F", "04");
		
		ColumnParameters over60 = new ColumnParameters("over60", "above 60 years", "age=>60", "05");
		
		return Arrays.asList(under5Male, under5Female, over5Male, over5Female, over60);
		
	}
	
	private List<ColumnParameters> getSpecialClinicPatientFilters() {
		ColumnParameters entClinic = new ColumnParameters("ENT", "ENT Clinic", "clinic=ENT", "01");
		ColumnParameters eyeClinic = new ColumnParameters("EYE", "Eye Clinic", "clinic=EYE", "02");
		
		ColumnParameters tbClinic = new ColumnParameters("tbAndLeprosy", "TB and Leprosy Clinic", "clinic=TBL", "03");
		
		ColumnParameters stiClinic = new ColumnParameters("STI", "STI Clinic", "clinic=STI", "04");
		
		ColumnParameters cccClinic = new ColumnParameters("CCC", "CCC Clinic", "clinic=CCC", "05");
		
		ColumnParameters psychiatryClinic = new ColumnParameters("psychiatryClinic", "Psychiatry Clinic", "clinic=PSY", "06");
		
		ColumnParameters orthopaedicClinic = new ColumnParameters("orthopaedicClinic", "Orthopaedic Clinic", "clinic=ORT",
		        "07");
		
		ColumnParameters occupationalTherapyClinic = new ColumnParameters("occupationalTherapyClinic",
		        "Occupational Therapy Clinic", "clinic=OCP", "08");
		
		ColumnParameters physiotherapyClinic = new ColumnParameters("physiotherapyClinic", "Physiotherapy Clinic",
		        "clinic=PHYS", "09");
		
		ColumnParameters surgicalClinic = new ColumnParameters("surgicalClinics", "Surgical Clinics", "clinic=SC", "11");
		
		ColumnParameters paediatricsClinic = new ColumnParameters("paediatricsClinic", "Paediatrics Clinic", "clinic=PAED",
		        "12");
		
		ColumnParameters obstetricsGynaecologyClinic = new ColumnParameters("obstetricsGynaecologyClinic",
		        "Obstetrics/Gynaecology clinic", "clinic=OG", "13");
		
		ColumnParameters nutritionClinic = new ColumnParameters("nutritionClinic", "Nutrition clinic", "clinic=NUC", "14");
		
		ColumnParameters oncologyClinic = new ColumnParameters("oncologyClinic", "Oncology clinic", "clinic=ONC", "15");
		
		ColumnParameters renalClinic = new ColumnParameters("renalClinic", "Renal clinic", "clinic=RENAL", "16");
		
		return Arrays.asList(entClinic, eyeClinic, tbClinic, stiClinic, cccClinic, psychiatryClinic, orthopaedicClinic,
		    occupationalTherapyClinic, physiotherapyClinic, surgicalClinic, paediatricsClinic, obstetricsGynaecologyClinic,
		    nutritionClinic, oncologyClinic, renalClinic);
	}
}
