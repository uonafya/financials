package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.utils.Utils;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.Date;
import java.util.List;

public class DashboardFragmentController {
	
	public void controller(FragmentModel model) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		Date startOfDay = Utils.getStartOfDay(new Date());
		Date endOfDay = Utils.getEndOfDay(new Date());
		
		model.addAttribute(
		    "registration",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startOfDay, endOfDay,
		        hospitalCoreService.getDepartmentByName("Registration")));
		model.addAttribute(
		    "laboratory",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startOfDay, endOfDay,
		        hospitalCoreService.getDepartmentByName("Laboratory")));
		model.addAttribute(
		    "radiology",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startOfDay, endOfDay,
		        hospitalCoreService.getDepartmentByName("Radiology")));
		model.addAttribute(
		    "pharmacy",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startOfDay, endOfDay,
		        hospitalCoreService.getDepartmentByName("Pharmacy")));
		model.addAttribute(
		    "nhif",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startOfDay, endOfDay,
		        hospitalCoreService.getDepartmentByName("NHIF")));
		model.addAttribute(
		    "procedure",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startOfDay, endOfDay,
		        hospitalCoreService.getDepartmentByName("Procedure")));
	}
	
	private Double getDepartmentTotalsOnDateRange(HospitalCoreService hospitalCoreService, Date startDate, Date endDate,
	        EhrDepartment ehrDepartment) {
		double totals = 0.0;
		BillingService billingService = Context.getService(BillingService.class);
		List<PatientServiceBillItem> getBilledItemsPerDepartment = hospitalCoreService.getPatientServiceBillByDepartment(
		    ehrDepartment, startDate, endDate);
		if (getBilledItemsPerDepartment.size() > 0) {
			for (PatientServiceBillItem patientServiceBillItem : getBilledItemsPerDepartment) {
				if (patientServiceBillItem.getActualAmount() != null) {
					totals += patientServiceBillItem.getActualAmount().doubleValue();
				}
			}
		}
		return totals;
	}
}
