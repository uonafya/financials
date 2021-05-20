package org.openmrs.module.financials.page.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AppPage("financials.home")
public class BilledItemsPageController {
	
	public void controller(UiUtils ui, PageModel model, @RequestParam("billedId") Integer billId) {
		BillingService billingService = Context.getService(BillingService.class);
		List<PatientServiceBillItem> getPatientBillableServicesByBillId = billingService
		        .getPatientBillableServicesByPatientServiceBill(billingService.getPatientServiceBillById(billId));
		model.addAttribute("billedItems", getPatientBillableServicesByBillId);
		
	}
}
