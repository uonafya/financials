package org.openmrs.module.financials.reporting.utils;

import org.openmrs.module.financials.ColumnParameters;

import java.util.Arrays;
import java.util.List;

public class EhrAddonUtils {
	
	public static List<ColumnParameters> getAdultChildrenColumns() {
		ColumnParameters day1 = new ColumnParameters("day1", "Day 1", "day=1", "01");
		ColumnParameters day2 = new ColumnParameters("day2", "Day 2", "day=2", "02");
		ColumnParameters day3 = new ColumnParameters("day3", "Day 3", "day=3", "03");
		ColumnParameters day4 = new ColumnParameters("day4", "Day 4", "day=4", "04");
		ColumnParameters day5 = new ColumnParameters("day5", "Day 5", "day=5", "05");
		ColumnParameters day6 = new ColumnParameters("day6", "Day 6", "day=6", "06");
		ColumnParameters day7 = new ColumnParameters("day7", "Day 7", "day=7", "07");
		ColumnParameters day8 = new ColumnParameters("day8", "Day 8", "day=8", "08");
		ColumnParameters day9 = new ColumnParameters("day9", "Day 9", "day=9", "09");
		ColumnParameters day10 = new ColumnParameters("day10", "Day 10", "day=10", "10");
		ColumnParameters day11 = new ColumnParameters("day11", "Day 11", "day=11", "11");
		ColumnParameters day12 = new ColumnParameters("day12", "Day 12", "day=12", "12");
		ColumnParameters day13 = new ColumnParameters("day13", "Day 13", "day=13", "13");
		ColumnParameters day14 = new ColumnParameters("day14", "Day 14", "day=14", "14");
		ColumnParameters day15 = new ColumnParameters("day15", "Day 15", "day=15", "15");
		ColumnParameters day16 = new ColumnParameters("day16", "Day 16", "day=16", "16");
		ColumnParameters day17 = new ColumnParameters("day17", "Day 17", "day=17", "17");
		ColumnParameters day18 = new ColumnParameters("day18", "Day 18", "day=18", "18");
		ColumnParameters day19 = new ColumnParameters("day19", "Day 19", "day=19", "19");
		ColumnParameters day20 = new ColumnParameters("day20", "Day 20", "day=20", "20");
		ColumnParameters day21 = new ColumnParameters("day21", "Day 21", "day=21", "21");
		ColumnParameters day22 = new ColumnParameters("day22", "Day 22", "day=22", "22");
		ColumnParameters day23 = new ColumnParameters("day23", "Day 23", "day=23", "23");
		ColumnParameters day24 = new ColumnParameters("day24", "Day 24", "day=24", "24");
		ColumnParameters day25 = new ColumnParameters("day25", "Day 25", "day=25", "25");
		ColumnParameters day26 = new ColumnParameters("day26", "Day 26", "day=26", "26");
		ColumnParameters day27 = new ColumnParameters("day27", "Day 27", "day=27", "27");
		ColumnParameters day28 = new ColumnParameters("day28", "Day 28", "day=28", "28");
		ColumnParameters day29 = new ColumnParameters("day29", "Day 29", "day=29", "29");
		ColumnParameters day30 = new ColumnParameters("day30", "Day 30", "day=30", "30");
		ColumnParameters day31 = new ColumnParameters("day31", "Day 31", "day=31", "31");
		return Arrays.asList(day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
		    day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31);
	}
	
	public static List<ColumnParameters> getGeneralOutPatientFilters() {
		
		ColumnParameters under5MaleNew = new ColumnParameters("under5MaleNew", "below 5 and new",
		        "state=NEW|age=<5|gender=M", "01");
		ColumnParameters under5MaleRevisit = new ColumnParameters("over5MaleRevisit", "below 5 and revisit",
		        "state=RVT|age=<5|gender=M", "02");
		ColumnParameters under5FemaleNew = new ColumnParameters("under5FemaleNew", "below 5 and new",
		        "state=NEW|age=<5|gender=F", "03");
		ColumnParameters under5FemaleRevisit = new ColumnParameters("over5FemaleRevisit", "below 5 and revisit",
		        "state=RVT|age=<5|gender=F", "04");
		ColumnParameters over5MaleNew = new ColumnParameters("over5MaleNew", "above 5 and new", "state=NEW|age=>5|gender=M",
		        "05");
		ColumnParameters over5MaleRevisit = new ColumnParameters("over5MaleRevisit", "above 5 and revisit",
		        "state=RVT|age=>5|gender=M", "06");
		ColumnParameters over5FemaleNew = new ColumnParameters("over5MaleNew", "above 5 and new",
		        "state=NEW|age=>5|gender=F", "07");
		ColumnParameters over5FemaleRevisit = new ColumnParameters("over5MaleRevisit", "above 5 and revisit",
		        "state=RVT|age=>5|gender=F", "08");
		ColumnParameters over60New = new ColumnParameters("over60New", "above 60 and new", "state=NEW|age=>60", "09");
		ColumnParameters over60Revisit = new ColumnParameters("over60Revisit", "above 60 and revisit", "state=RVT|age=>60",
		        "10");
		
		return Arrays.asList(under5MaleNew, under5MaleRevisit, under5FemaleNew, under5FemaleRevisit, over5MaleNew,
		    over5MaleRevisit, over5FemaleNew, over5FemaleRevisit, over5MaleRevisit, over60New, over60Revisit);
		
	}
	
	public static List<ColumnParameters> getSpecialClinicPatientFilters() {
		
		ColumnParameters entNew = new ColumnParameters("ENTNew", "ENT Clinic", "state=NEW|clinic=ENT", "01");
		ColumnParameters entRevisit = new ColumnParameters("ENTRevisit", "ENT Clinic", "state=RVT|clinic=ENT", "02");
		
		ColumnParameters eyeClinicNew = new ColumnParameters("eyeClinicNew", "Eye Clinic", "state=NEW|clinic=EYE", "03");
		ColumnParameters eyeClinicRevisit = new ColumnParameters("eyeClinicRevisit", "Eye Clinic", "state=RVT|clinic=EYE",
		        "04");
		
		ColumnParameters tbAndLeprosyNew = new ColumnParameters("tbAndLeprosyNew", "TB and Leprosy", "state=NEW|clinic=TBL",
		        "05");
		ColumnParameters tbAndLeprosyRevisit = new ColumnParameters("tbAndLeprosyRevisit", "TB and Leprosy",
		        "state=RVT|clinic=TBL", "06");
		
		ColumnParameters sexuallyTransmittedNew = new ColumnParameters("sexuallyTransmittedNew",
		        "Sexually Transmitted infection", "state=NEW|clinic=STI", "07");
		ColumnParameters sexuallyTransmittedRevisit = new ColumnParameters("sexuallyTransmittedRevisit",
		        "Sexually Transmitted infection", "state=RVT|clinic=STI", "08");
		
		ColumnParameters cccNew = new ColumnParameters("cccNew", "Comprehensive Care Clinic (CCC)", "state=NEW|clinic=CCC",
		        "09");
		ColumnParameters cccRevisit = new ColumnParameters("cccRevisit", "Comprehensive Care Clinic (CCC)",
		        "state=RVT|clinic=CCC", "10");
		
		ColumnParameters psychiatryNew = new ColumnParameters("psychiatryNew", "Psychiatry", "state=NEW|clinic=PSY", "11");
		ColumnParameters psychiatryRevisit = new ColumnParameters("psychiatryRevisit", "Psychiatry", "state=RVT|clinic=PSY",
		        "12");
		
		ColumnParameters orthopaedicNew = new ColumnParameters("orthopaedicNew", "Orthopaedic Clinic",
		        "state=NEW|clinic=ORT", "13");
		ColumnParameters orthopaedicRevisit = new ColumnParameters("orthopaedicRevisit", "Orthopaedic Clinic",
		        "state=RVT|clinic=ORT", "14");
		
		ColumnParameters occupationalTherapyNew = new ColumnParameters("occupationalTherapyNew",
		        "Occupational Therapy Clinic", "state=NEW|clinc=OCP", "15");
		ColumnParameters occupationalTherapyRevisit = new ColumnParameters("occupationalTherapyRevisit",
		        "Occupational Therapy Clinic", "state=RVT|clinic=OCP", "16");
		
		ColumnParameters physiotherapyNew = new ColumnParameters("physiotherapyNew", "Physiotherapy Clinic",
		        "state=NEW|clinic=PHYS", "17");
		ColumnParameters physiotherapyRevisit = new ColumnParameters("physiotherapyRevisit", "Physiotherapy Clinic",
		        "state=RVT|clinic=PHYS", "18");
		
		/*ColumnParameters medicalClinicsNew = new ColumnParameters("medicalClinicsNew", "Medical Clinics", "state=NEW|clinic=MC", "19");
		ColumnParameters medicalClinicsRevisit = new ColumnParameters("medicalClinicsRevisit", "Medical Clinics",
		        "state=RVT|clinic=MC", "20");*/
		
		ColumnParameters surgicalClinicsNew = new ColumnParameters("surgicalClinicsNew", "Surgical Clinics",
		        "state=NEW|clinic=SC", "21");
		ColumnParameters surgicalClinicsRevisit = new ColumnParameters("surgicalClinicsRevisit", "Surgical Clinics",
		        "state=RVT|clinic=SC", "22");
		
		ColumnParameters paediatricsNew = new ColumnParameters("paediatricsNew", "Paediatrics", "state=NEW|clinic=PAED",
		        "23");
		ColumnParameters paediatricsRevisit = new ColumnParameters("paediatricsRevisit", "Paediatrics",
		        "state=RVT|clinic=PAED", "24");
		
		ColumnParameters obstetricsGynaecologyNew = new ColumnParameters("obstetricsGynaecologyNew",
		        "Obstetrics/Gynaecology", "state=NEW|clinic=OG", "25");
		ColumnParameters obstetricsGynaecologyRevisit = new ColumnParameters("obstetricsGynaecologyRevisit",
		        "Obstetrics/Gynaecology", "state=RVT|clinic=OG", "26");
		
		return Arrays.asList(entNew, entRevisit, entNew, eyeClinicNew, eyeClinicRevisit, tbAndLeprosyNew,
		    tbAndLeprosyRevisit, sexuallyTransmittedNew, sexuallyTransmittedRevisit, cccNew, cccRevisit, psychiatryNew,
		    psychiatryRevisit, orthopaedicNew, orthopaedicRevisit, occupationalTherapyNew, occupationalTherapyRevisit,
		    psychiatryNew, physiotherapyNew, physiotherapyRevisit, surgicalClinicsNew, surgicalClinicsRevisit,
		    paediatricsNew, paediatricsRevisit, obstetricsGynaecologyNew, obstetricsGynaecologyRevisit);
		
	}
	
}
