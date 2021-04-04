package org.openmrs.module.financials.page.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;

import java.util.List;

@AppPage("financials.home")
public class FinancialsPageController {
	
	public void controller(UiUtils ui, PageModel model) {
		
		BillingService billingService = Context.getService(BillingService.class);
		
		List<PatientServiceBill> allBils = billingService.getAllPatientServiceBill();
		
		model.addAttribute("bills", allBils);
	}
}
