package org.openmrs.module.financials.model;

import org.openmrs.Patient;

public class NhifPatientSummarySimplifier {
	
	private String names;
	
	private String identifierValue;
	
	public String getNames() {
		return names;
	}
	
	public void setNames(String names) {
		this.names = names;
	}
	
	public String getIdentifierValue() {
		return identifierValue;
	}
	
	public void setIdentifierValue(String identifierValue) {
		this.identifierValue = identifierValue;
	}
	
	public String getNhifNumber() {
		return nhifNumber;
	}
	
	public void setNhifNumber(String nhifNumber) {
		this.nhifNumber = nhifNumber;
	}
	
	public String getVisitType() {
		return visitType;
	}
	
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	
	public String getTotals() {
		return totals;
	}
	
	public void setTotals(String totals) {
		this.totals = totals;
	}
	
	private String nhifNumber;
	
	private String visitType;
	
	private String totals;
	
	public String getVisitDate() {
		return visitDate;
	}
	
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	
	private String visitDate;
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	private Patient patient;
}
