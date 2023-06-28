package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.GeneralRevenuePerUnit;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepartmentFinanceSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		model.addAttribute("departments", Context.getService(HospitalCoreService.class).getAllDepartment());
	}
	
	public List<SimpleObject> getDepartmentsSummaries(@RequestParam(value = "fromDate", required = false) Date fromDate,
	        @RequestParam(value = "toDate", required = false) Date toDate,
	        @RequestParam(value = "dept", required = false) String dept, UiUtils ui) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		EhrDepartment ehrDepartment = hospitalCoreService.getDepartmentById(Integer.valueOf(dept));
		List<PatientServiceBillItem> getBilledItemsPerDepartmentSummaries = new ArrayList<PatientServiceBillItem>(
		        hospitalCoreService.getPatientServiceBillByDepartment(ehrDepartment, fromDate, toDate));
		GeneralRevenuePerUnit generalRevenuePerUnit;
		List<GeneralRevenuePerUnit> summarizedResults = new ArrayList<GeneralRevenuePerUnit>();
		if (!getBilledItemsPerDepartmentSummaries.isEmpty()) {
			for (PatientServiceBillItem patientServiceBillItem : getBilledItemsPerDepartmentSummaries) {
				if (patientServiceBillItem != null && patientServiceBillItem.getDepartment() != null) {
					generalRevenuePerUnit = new GeneralRevenuePerUnit();
					generalRevenuePerUnit.setTransactionDate(FinancialsUtils.formatDateWithTime(patientServiceBillItem
					        .getCreatedDate()));
					generalRevenuePerUnit.setDepartment(patientServiceBillItem.getDepartment().getName());
					generalRevenuePerUnit.setServicePaidFor(patientServiceBillItem.getService().getName());
					generalRevenuePerUnit.setTotalAmount(patientServiceBillItem.getActualAmount());
					
					summarizedResults.add(generalRevenuePerUnit);
					
				}
				
			}
		}
		return SimpleObject.fromCollection(summarizedResults, ui, "transactionDate", "department", "servicePaidFor",
		    "totalAmount");
	}
}
