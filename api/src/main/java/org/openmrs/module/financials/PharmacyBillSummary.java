package org.openmrs.module.financials;

import org.openmrs.Patient;

import java.math.BigDecimal;

public class PharmacyBillSummary {
	
	private String createdOn;
	
	private String drugName, formulationName;
	
	private int issueQuantity;
	
	private BigDecimal totalPrice;
	
	public String getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getDrugName() {
		return drugName;
	}
	
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	public String getFormulationName() {
		return formulationName;
	}
	
	public void setFormulationName(String formulationName) {
		this.formulationName = formulationName;
	}
	
	public int getIssueQuantity() {
		return issueQuantity;
	}
	
	public void setIssueQuantity(int issueQuantity) {
		this.issueQuantity = issueQuantity;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "PharmacyBillSummary{" + "createdOn='" + createdOn + '\'' + ", drugName='" + drugName + '\''
		        + ", formulationName='" + formulationName + '\'' + ", issueQuantity='" + issueQuantity + '\''
		        + ", totalPrice='" + totalPrice + '\'' + '}';
	}
	
	private String patientNames;
	
	public String getPatientNames() {
		return patientNames;
	}
	
	public void setPatientNames(String patientNames) {
		this.patientNames = patientNames;
	}
	
	public String getPatientIdentifier() {
		return patientIdentifier;
	}
	
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	
	public String getWaiverAmount() {
		return waiverAmount;
	}
	
	public void setWaiverAmount(String waiverAmount) {
		this.waiverAmount = waiverAmount;
	}
	
	public String getTotalAMount() {
		return totalAMount;
	}
	
	public void setTotalAMount(String totalAMount) {
		this.totalAMount = totalAMount;
	}
	
	private String patientIdentifier;
	
	private String waiverAmount;
	
	private String totalAMount;
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	private Patient patient;
}
