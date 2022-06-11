package org.openmrs.module.financials.fragment.controller;

import org.apache.commons.lang3.StringUtils;
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
		String STUDENT_ID = "88546440-0271-11eb-b43f-c392cfe8f5df";
		
		List<PatientServiceBill> allBils = Context.getService(BillingService.class).getPatientBillsPerDateRange(null, null,
		    null);
		PersonService personService = Context.getPersonService();
		List<PatientBillSummary> allBills = new ArrayList<PatientBillSummary>();
		
		for (PatientServiceBill patientServiceBill : allBils) {
			if (patientServiceBill.getPatient().getPatientIdentifier() != null) {
				PatientBillSummary patientBillSummary = new PatientBillSummary();
				patientBillSummary.setBillId(patientServiceBill.getPatientServiceBillId());
				patientBillSummary.setPatient(patientServiceBill.getPatient().getPersonName().getFullName());
				if (StringUtils.isNotBlank(patientServiceBill.getPatientCategory())) {
					patientBillSummary.setCategory(patientServiceBill.getPatientCategory());
				} else {
					patientBillSummary.setCategory("");
				}
				if (StringUtils.isNotBlank(patientServiceBill.getPatientSubCategory())) {
					patientBillSummary.setSubCategory(patientServiceBill.getPatientSubCategory());
				} else {
					patientBillSummary.setSubCategory("");
				}
				patientBillSummary.setWaiver(String.valueOf(patientServiceBill.getWaiverAmount()));
				patientBillSummary.setActualAmount(String.valueOf(patientServiceBill.getActualAmount()));
				patientBillSummary.setPaidAmount(String.valueOf(patientServiceBill.getAmount()));
				patientBillSummary.setRebate(String.valueOf(patientServiceBill.getRebateAmount()));
				if (patientServiceBill.getReceipt() != null) {
					patientBillSummary.setTransactionDate(String.valueOf(patientServiceBill.getReceipt().getPaidDate()));
				}
				patientBillSummary.setIdentifier(patientServiceBill.getPatient().getPatientIdentifier().getIdentifier());
				patientBillSummary.setPatientId(patientServiceBill.getPatient().getPatientId());
				
				PersonAttributeType studentIdAttributeType = Context.getPersonService().getPersonAttributeTypeByUuid(
				    STUDENT_ID);
				if ((patientServiceBill.getPatient().getAttribute(studentIdAttributeType) != null)) {
					patientBillSummary.setStudentAttributeName(patientServiceBill.getPatient()
					        .getAttribute(studentIdAttributeType).getValue());
				} else {
					patientBillSummary.setStudentAttributeName("");
				}
				//add this build object to the list
				allBills.add(patientBillSummary);
			}
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
	
	public void updateModelBillAttributes(List<PatientServiceBill> allBils) {
		
	}
}
