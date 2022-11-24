package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.ui.framework.fragment.FragmentModel;

public class CashierSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		
		UserService userService = Context.getUserService();
		model.addAttribute("cashier", userService.getUsersByRole(userService.getRole("EHR Cashier")));
	}
}
