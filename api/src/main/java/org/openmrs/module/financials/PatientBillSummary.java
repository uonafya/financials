package org.openmrs.module.financials;

public class PatientBillSummary {
	
	private Integer billId;
	
	private String patient;
	
	private String category;
	
	private String subCategory;
	
	public Integer getBillId() {
		return billId;
	}
	
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	
	public String getPatient() {
		return patient;
	}
	
	public void setPatient(String patient) {
		this.patient = patient;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSubCategory() {
		return subCategory;
	}
	
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	public String getWaiver() {
		return waiver;
	}
	
	public void setWaiver(String waiver) {
		this.waiver = waiver;
	}
	
	public String getActualAmount() {
		return actualAmount;
	}
	
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	
	public String getPaidAmount() {
		return paidAmount;
	}
	
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	private String waiver;
	
	private String actualAmount;
	
	private String paidAmount;
	
	public String getRebate() {
		return rebate;
	}
	
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	
	private String rebate;
	
	public String getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	private String transactionDate;
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	private String identifier;
	
	public Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	private Integer patientId;
}
