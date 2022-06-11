package org.openmrs.module.financials;

import java.math.BigDecimal;

public class GeneralRevenuePerUnit {
	
	private String transactionDate;
	
	public String getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	private String department;
	
	private String servicePaidFor;
	
	private BigDecimal totalAmount;
	
	public String getServicePaidFor() {
		return servicePaidFor;
	}
	
	public void setServicePaidFor(String servicePaidFor) {
		this.servicePaidFor = servicePaidFor;
	}
}
