package org.openmrs.module.financials.model;

public class CashierBillSummarySimplifier {
	
	private String patientName;
	
	private String amount;
	
	private String category;
	
	public String getPatientName() {
		return patientName;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
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
	
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	
	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	
	public String getPaymentMode() {
		return paymentMode;
	}
	
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public String getReceiptNumber() {
		return receiptNumber;
	}
	
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
	public String getWaiverAmount() {
		return waiverAmount;
	}
	
	public void setWaiverAmount(String waiverAmount) {
		this.waiverAmount = waiverAmount;
	}
	
	public String getTransactionCode() {
		return transactionCode;
	}
	
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	
	private String subCategory;
	
	private String transactionDateTime;
	
	private String paymentMode;
	
	private String receiptNumber;
	
	private String waiverAmount;
	
	private String transactionCode;
}
