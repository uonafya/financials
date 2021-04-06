package org.openmrs.module.financials.page.controller;

import org.openmrs.PersonAttributeType;
import org.openmrs.api.PersonService;
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
		PersonService personService = Context.getPersonService();
		List<PatientBillSummary> allBills = new ArrayList<PatientBillSummary>();
		
		//get person attribute for patient category and sub category
		PersonAttributeType paymentCategory = personService
		        .getPersonAttributeTypeByUuid("09cd268a-f0f5-11ea-99a8-b3467ddbf779");
		PersonAttributeType paymentSubCategory = personService
		        .getPersonAttributeTypeByUuid("972a32aa-6159-11eb-bc2d-9785fed39154");
		
		for (PatientServiceBill patientServiceBill : allBils) {
			PatientBillSummary patientBillSummary = new PatientBillSummary();
			patientBillSummary.setBillId(patientServiceBill.getPatientServiceBillId());
			patientBillSummary.setPatient(patientServiceBill.getPatient().getPersonName().getFullName());
			patientBillSummary.setCategory(patientServiceBill.getPatient().getAttribute(paymentCategory).getValue());
			patientBillSummary.setSubCategory(patientServiceBill.getPatient().getAttribute(paymentSubCategory).getValue());
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
