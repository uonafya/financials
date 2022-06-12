package org.openmrs.module.financials.page.controller;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@AppPage("financials.home")
public class BilledItemsPageController {
	
	BillingService billingService;
	
	public void controller(UiUtils ui, PageModel model, @RequestParam("billedId") Integer billId,
	        @RequestParam("patientId") Patient patient) {
		billingService = Context.getService(BillingService.class);
		List<PatientServiceBillItem> getPatientBillableServicesByBillId = billingService
		        .getPatientBillableServicesByPatientServiceBill(billingService.getPatientServiceBillById(billId));
		model.addAttribute("billedItems", getPatientBillableServicesByBillId);
		model.addAttribute("patientId", patient.getPatientId());
		
	}
	
	public List<SimpleObject> getPatientBillItemsByDateRange(
	        @RequestParam(value = "patientId", required = false) Patient patient,
	        @RequestParam(value = "startDate", required = false) Date startDate,
	        @RequestParam(value = "endDate", required = false) Date endDate, UiUtils ui) {
		List<PatientServiceBill> patientServiceBills = billingService.getPatientBillsPerDateRange(patient, startDate,
		    endDate);
		
		return SimpleObject.fromCollection(patientServiceBills, ui, "createdDate", "name", "quantity", "unitPrice",
		    "actualAmount", "patientServiceBill");
	}
}
