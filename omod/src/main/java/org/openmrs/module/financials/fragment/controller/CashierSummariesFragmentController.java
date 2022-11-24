package org.openmrs.module.financials.fragment.controller;

import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PharmacyBillSummary;
import org.openmrs.module.financials.model.CashierBillSummarySimplifier;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashierSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		
		UserService userService = Context.getUserService();
		model.addAttribute("cashier", userService.getUsersByRole(userService.getRole("EHR Cashier")));
	}
	
	public List<SimpleObject> fetchPaymentSummariesByDateRangeAndUser(
	        @RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate,
	        @RequestParam(value = "cashier", required = false) Integer cashierUser, UiUtils uiUtils) {
		;
		UserService userService = Context.getUserService();
		User selectedUser = userService.getUser(cashierUser);
		
		List<CashierBillSummarySimplifier> patientServiceBillList = new ArrayList<CashierBillSummarySimplifier>(getAllBills(
		    selectedUser, startDate, endDate));
		
		return SimpleObject.fromCollection(patientServiceBillList, uiUtils, "patientName", "amount", "category",
		    "subCategory", "transactionDateTime", "paymentMode", "receiptNumber", "waiverAmount", "transactionCode");
	}
	
	private List<CashierBillSummarySimplifier> getAllBills(User user, Date startDate, Date endDate) {
		BillingService billingService = Context.getService(BillingService.class);
		List<CashierBillSummarySimplifier> simplifierList = new ArrayList<CashierBillSummarySimplifier>();
		CashierBillSummarySimplifier cashierBillSummarySimplifier = null;
		
		for (PatientServiceBill patientServiceBill : billingService.getPatientBillsPerUserAndDateRange(user, startDate,
		    endDate)) {
			cashierBillSummarySimplifier = new CashierBillSummarySimplifier();
			cashierBillSummarySimplifier.setPatientName(FinancialsUtils.formatPersonName(patientServiceBill.getPatient()
			        .getPersonName()));
			cashierBillSummarySimplifier.setAmount(String.valueOf(patientServiceBill.getActualAmount()));
			cashierBillSummarySimplifier.setCategory(patientServiceBill.getPatientCategory());
			cashierBillSummarySimplifier.setSubCategory(patientServiceBill.getPatientSubCategory());
			cashierBillSummarySimplifier.setTransactionDateTime(FinancialsUtils.formatDateWithTime(patientServiceBill
			        .getCreatedDate()));
			cashierBillSummarySimplifier.setPaymentMode(patientServiceBill.getPaymentMode());
			cashierBillSummarySimplifier.setReceiptNumber(String.valueOf(patientServiceBill.getReceipt().getId()));
			cashierBillSummarySimplifier.setWaiverAmount(String.valueOf(patientServiceBill.getWaiverAmount()));
			cashierBillSummarySimplifier.setTransactionCode(patientServiceBill.getTransactionCode());
			
			simplifierList.add(cashierBillSummarySimplifier);
		}
		
		return simplifierList;
	}
}
