package org.openmrs.module.financials;

import java.math.BigDecimal;
import java.util.Date;

public class GeneralRevenuePerUnit {
	
	private Date transactionDate;
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) {
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
