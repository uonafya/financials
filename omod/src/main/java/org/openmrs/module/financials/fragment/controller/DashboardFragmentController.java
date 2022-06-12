package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DashboardFragmentController {
	
	public void controller(FragmentModel model) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		Date startOfDay = FinancialsUtils.getStartOfDay(new Date());
		Date endOfDay = FinancialsUtils.getEndOfDay(new Date());
		
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
	
	public SimpleObject getDepartmentTotalsOnDateRange(
	        @RequestParam(value = "startDate", required = false) String startdate,
	        @RequestParam(value = "endDate", required = false) String enddate) throws ParseException {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		
		Date startDate = new SimpleDateFormat("dd MMM yyyy").parse(startdate);
		Date endDate = new SimpleDateFormat("dd MMM yyyy").parse(enddate);
		
		SimpleObject simpleObject = new SimpleObject();
		
		simpleObject.put(
		    "pharmacy",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Pharmacy")));
		simpleObject.put(
		    "procedure",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Procedure")));
		simpleObject.put(
		    "nhif",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("NHIF")));
		simpleObject.put(
		    "radiology",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Radiology")));
		simpleObject.put(
		    "laboratory",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Laboratory")));
		simpleObject.put(
		    "registration",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Registration")));
		return simpleObject;
	}
}
