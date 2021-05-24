package org.openmrs.module.financials.fragment.controller;

import org.openmrs.PersonAttributeType;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PatientBillSummary;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class PatientFinanceSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		
		List<PatientServiceBill> allBils = Context.getService(BillingService.class).getAllPatientServiceBill();
		PersonService personService = Context.getPersonService();
		List<PatientBillSummary> allBills = new ArrayList<PatientBillSummary>();
		
		//get person attribute for patient category and sub category
		PersonAttributeType paymentCategory = personService
		        .getPersonAttributeTypeByUuid("09cd268a-f0f5-11ea-99a8-b3467ddbf779");
		PersonAttributeType paymentSubCategory = personService
		        .getPersonAttributeTypeByUuid("972a32aa-6159-11eb-bc2d-9785fed39154");
		
		for (PatientServiceBill patientServiceBill : allBils) {
			
			PatientBillSummary patientBillSummary = null;
			patientBillSummary = new PatientBillSummary();
			patientBillSummary.setBillId(patientServiceBill.getPatientServiceBillId());
			patientBillSummary.setPatient(patientServiceBill.getPatient().getPersonName().getFullName());
			if (patientServiceBill.getPatient().getAttribute(paymentCategory) != null) {
				patientBillSummary.setCategory(patientServiceBill.getPatient().getAttribute(paymentCategory).getValue());
			} else {
				patientBillSummary.setCategory("");
			}
			if (patientServiceBill.getPatient().getAttribute(paymentSubCategory) != null) {
				patientBillSummary.setSubCategory(patientServiceBill.getPatient().getAttribute(paymentSubCategory)
				        .getValue());
			} else {
				patientBillSummary.setSubCategory("");
			}
			patientBillSummary.setWaiver(String.valueOf(patientServiceBill.getWaiverAmount()));
			patientBillSummary.setActualAmount(String.valueOf(patientServiceBill.getActualAmount()));
			patientBillSummary.setPaidAmount(String.valueOf(patientServiceBill.getAmount()));
			patientBillSummary.setRebate(String.valueOf(patientServiceBill.getRebateAmount()));
			patientBillSummary.setTransactionDate(String.valueOf(patientServiceBill.getReceipt().getPaidDate()));
			patientBillSummary.setIdentifier(patientServiceBill.getPatient().getPatientIdentifier().getIdentifier());
			patientBillSummary.setPatientId(patientServiceBill.getPatient().getPatientId());
			//add this build object to the list
			allBills.add(patientBillSummary);
		}
		
		model.addAttribute("bills", allBills);
		
	}
	
	public SimpleObject getBilledItemsPerServiceBill(
	        @RequestParam(value = "billId", required = false) Integer patientServiceBill, UiUtils ui) {
		
		List<PatientServiceBillItem> patientServiceBillItems = Context.getService(BillingService.class)
		        .getPatientBillableServicesByPatientServiceBill(
		            Context.getService(BillingService.class).getPatientServiceBillById(patientServiceBill));
		
		List<SimpleObject> items = SimpleObject.fromCollection(patientServiceBillItems, ui, "service", "unitPrice",
		    "amount", "actualAmount", "quantity", "name", "createdDate");
		
		return SimpleObject.create("items", items);
	}
}
