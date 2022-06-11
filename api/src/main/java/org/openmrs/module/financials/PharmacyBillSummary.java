package org.openmrs.module.financials;

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
}
