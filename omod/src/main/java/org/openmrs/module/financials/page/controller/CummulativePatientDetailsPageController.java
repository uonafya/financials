package org.openmrs.module.financials.page.controller;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@AppPage("financials.home")
public class CummulativePatientDetailsPageController {
	
	public void controller(UiUtils ui, PageModel model, @RequestParam("patientId") Patient patient) {
		BillingService billingService = Context.getService(BillingService.class);
		
		List<PatientServiceBillItem> getPatientBillableServicesByPatientServiceBillAll = new ArrayList<PatientServiceBillItem>();
		
		List<PatientServiceBill> patientServiceBillList = billingService.listPatientServiceBillByPatient(0, 4, patient);
		
		for (PatientServiceBill patientServiceBill : patientServiceBillList) {
			getPatientBillableServicesByPatientServiceBillAll.addAll(billingService
			        .getPatientBillableServicesByPatientServiceBill(patientServiceBill));
		}
		model.addAttribute("allItems", getPatientBillableServicesByPatientServiceBillAll);
	}
}
