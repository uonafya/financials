package org.openmrs.module.financials.page.controller;

import org.openmrs.Patient;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

@AppPage("financials.home")
public class PatientInvoicePageController {
	
	public void controller(PageModel pageModel, @RequestParam("patientId") Patient patient) {
		
		pageModel.addAttribute("patient", patient);
	}
}
