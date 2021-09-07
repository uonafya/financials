package org.openmrs.module.financials.reporting.library.dataset;

import org.openmrs.module.financials.ColumnParameters;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
