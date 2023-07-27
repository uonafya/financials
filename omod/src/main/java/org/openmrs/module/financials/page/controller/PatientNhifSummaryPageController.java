package org.openmrs.module.financials.page.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

@AppPage("financials.home")
public class PatientNhifSummaryPageController {
	
	public void controller(PageModel model, @RequestParam("identifier") String identifier,
	        @RequestParam("whichDate") String whichDate) {
		model.addAttribute("patient", Context.getPatientService().getPatients(identifier).get(0));
	}
}
