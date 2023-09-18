package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh705CohortDefinition;
import org.openmrs.module.financials.reporting.library.cohorts.Moh717CohortDefinition;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh717IndicatorDefinition {
	
	private Moh717CohortDefinition moh717CohortDefinition;
	
	private final Moh705CohortDefinition moh705CohortDefinition;
	
	@Autowired
	public Moh717IndicatorDefinition(Moh717CohortDefinition moh717CohortDefinition,
	    Moh705CohortDefinition moh705CohortDefinition) {
		this.moh717CohortDefinition = moh717CohortDefinition;
		this.moh705CohortDefinition = moh705CohortDefinition;
	}
	
	public CohortIndicator getAllNewPatients() {
		return cohortIndicator("All patients new patients for 717 report",
		    ReportUtils.map(moh717CohortDefinition.getNewPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllRevisitPatients() {
		return cohortIndicator("All patients rebvisit patients for 717 report",
		    ReportUtils.map(moh717CohortDefinition.getRevisitPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//special clinic new patients
	public CohortIndicator getSpecialClinicNewPatients() {
		return cohortIndicator("Special clinics new patients report", ReportUtils.map(
		    moh717CohortDefinition.getNewSpecialClinicPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//special clinic new revisit patients
	public CohortIndicator getSpecialClinicRevisitPatients() {
		return cohortIndicator("Special clinics revisit patients report", ReportUtils.map(
		    moh717CohortDefinition.getRevistSpecialClinicPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//Get all patients who does NOt match the climics provided
	public CohortIndicator getSpecialClinicOutOfRangePatients() {
		return cohortIndicator("Special clinics that are out of range", ReportUtils.map(
		    moh717CohortDefinition.getSpecialClinicPatientsOutOfRange(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	//Get all patients who have MOPC clinic visit
	public CohortIndicator getSpecialClinicMopc() {
		return cohortIndicator("Special clinic MOPC",
		    ReportUtils.map(moh717CohortDefinition.getMopSpecialClinic(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
	}
	
	public CohortIndicator getDentalVisits(int c1, int c2, int encounter) {
		return cohortIndicator("Special clinic Dental", ReportUtils.map(
		    moh717CohortDefinition.getDentalSpecialClinic(c1, c2, encounter), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getNewChildrenPatients() {
		return cohortIndicator("MOH 717 New children patients",
		    map(moh705CohortDefinition.getNewChildrenPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getRevisitsChildrenPatients() {
		return cohortIndicator("MOH 717 Revisit children patients",
		    map(moh705CohortDefinition.getRevisitsChildrenPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getNewAdultsPatients() {
		return cohortIndicator("New adults patients",
		    map(moh705CohortDefinition.getNewAdultsPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getRevisitsAdultsPatients() {
		return cohortIndicator("Revisit adults patients",
		    map(moh705CohortDefinition.getRevisitAdultsPatients(), "startDate=${startDate},endDate=${endDate}"));
	}
}
