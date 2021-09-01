package org.openmrs.module.financials.fragment.controller;

import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;

public class StudentRevenueSummariesFragmentController extends PatientFinanceSummariesFragmentController {
	
	@Override
	public void controller(FragmentModel model) {
		super.controller(model);
	}
	
	@Override
	public SimpleObject getBilledItemsPerServiceBill(Integer patientServiceBill, UiUtils ui) {
		return super.getBilledItemsPerServiceBill(patientServiceBill, ui);
	}
}
