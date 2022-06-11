package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.GeneralRevenuePerUnit;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.ArrayList;
import java.util.List;

public class DepartmentFinanceSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		
		List<PatientServiceBillItem> patientServiceBillItems = Context.getService(HospitalCoreService.class)
		        .getPatientServiceBillByDepartment(null, null, null);
		GeneralRevenuePerUnit generalRevenuePerUnit;
		List<GeneralRevenuePerUnit> summarizedResults = new ArrayList<GeneralRevenuePerUnit>();
		
		for (PatientServiceBillItem patientServiceBillItem : patientServiceBillItems) {
			generalRevenuePerUnit = new GeneralRevenuePerUnit();
			generalRevenuePerUnit.setTransactionDate(FinancialsUtils.formatDateWithTime(patientServiceBillItem
			        .getCreatedDate()));
			generalRevenuePerUnit.setDepartment(patientServiceBillItem.getDepartment().getName());
			generalRevenuePerUnit.setServicePaidFor(patientServiceBillItem.getService().getName());
			generalRevenuePerUnit.setTotalAmount(patientServiceBillItem.getActualAmount());
			
			summarizedResults.add(generalRevenuePerUnit);
		}
		model.addAttribute("summaryAccounts", summarizedResults);
		model.addAttribute("departments", Context.getService(HospitalCoreService.class).getAllDepartment());
	}
}
