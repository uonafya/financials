package org.openmrs.module.financials.page.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PatientBillSummary;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;

import java.util.ArrayList;
import java.util.List;

@AppPage("financials.home")
public class FinancialsPageController {
	
	public void controller(UiUtils ui, PageModel model) {
		
		List<PatientServiceBill> allBils = Context.getService(BillingService.class).getAllPatientServiceBill();
		List<PatientBillSummary> allBills = new ArrayList<PatientBillSummary>();
		PatientBillSummary patientBillSummary = new PatientBillSummary();
		
		for (PatientServiceBill patientServiceBill : allBils) {
			patientBillSummary.setBillId(patientServiceBill.getPatientServiceBillId());
			patientBillSummary.setPatient(patientServiceBill.getPatient().getFamilyName());
			patientBillSummary.setCategory(patientServiceBill.getPatientCategory());
			patientBillSummary.setSubCategory(patientServiceBill.getPatientSubCategory());
			patientBillSummary.setWaiver(String.valueOf(patientServiceBill.getWaiverAmount()));
			patientBillSummary.setActualAmount(String.valueOf(patientServiceBill.getActualAmount()));
			patientBillSummary.setPaidAmount(String.valueOf(patientServiceBill.getAmount()));
			patientBillSummary.setRebate(String.valueOf(patientServiceBill.getRebateAmount()));
			patientBillSummary.setTransactionDate(String.valueOf(patientServiceBill.getReceipt().getPaidDate()));
			patientBillSummary.setIdentifier(patientServiceBill.getPatient().getPatientIdentifier().getIdentifier());
			//add this build object to the list
			allBills.add(patientBillSummary);
		}
		
		model.addAttribute("bills", allBills);
	}
}
