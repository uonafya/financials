package org.openmrs.module.financials.page.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

@AppPage("financials.home")
public class PatientVisitSummariesPageController {
	
	public void controller(PageModel model, @RequestParam(value = "identifier", required = false) String identifier) {
		
		model.addAttribute("patientId", Context.getPatientService().getPatients(identifier).get(0));
		
	}
}
