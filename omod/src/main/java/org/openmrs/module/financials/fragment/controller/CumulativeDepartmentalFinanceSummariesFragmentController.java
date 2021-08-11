package org.openmrs.module.financials.fragment.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.GeneralRevenuePerUnit;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class CumulativeDepartmentalFinanceSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		
		model.addAttribute("cashiers", getAllUsersWithRoleCashier());
	}
	
	//cumulativeSummaries
	public List<SimpleObject> fetchCumulativeSummariesByDateRange(
	        @RequestParam(value = "cashier", required = false) Integer cashierId,
	        @RequestParam(value = "fromDate", required = false) String fromDate,
	        @RequestParam(value = "toDate", required = false) String toDate, UiUtils uiUtils) {
		List<OpdTestOrder> allOpdOrders = Context.getService(HospitalCoreService.class).getAllOpdOrdersByDateRange(false,
		    fromDate, toDate);
		List<PatientServiceBillItem> patientServiceBillItems = Context.getService(HospitalCoreService.class)
		        .getAllPatientServiceBillItemsByDate(false, fromDate, toDate);
		GeneralRevenuePerUnit generalRevenuePerUnit = null;
		List<GeneralRevenuePerUnit> cumulativeSummarries = new ArrayList<GeneralRevenuePerUnit>();
		
		for (OpdTestOrder opdTestOrder : allOpdOrders) {
			for (PatientServiceBillItem patientServiceBillItem : patientServiceBillItems) {
				if (opdTestOrder.getBillableService().equals(patientServiceBillItem.getService())
				        && opdTestOrder.getFromDept() != null) {
					generalRevenuePerUnit = new GeneralRevenuePerUnit();
					generalRevenuePerUnit.setTransactionDate(opdTestOrder.getScheduleDate());
					if (opdTestOrder.getConcept().getConceptId() == Context.getConceptService()
					        .getConceptByUuid("0179f241-8c1d-47c1-8128-841f6508e251").getConceptId()) {
						generalRevenuePerUnit.setDepartment("LABORATORY");
					} else if (opdTestOrder.getConcept().getConceptId() == (Context.getConceptService().getConceptByUuid(
					    "1651AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getConceptId())) {
						generalRevenuePerUnit.setDepartment("PROCEDURE ROOM");
					} else {
						generalRevenuePerUnit.setDepartment(opdTestOrder.getFromDept());
					}
					generalRevenuePerUnit.setTotalAmount(patientServiceBillItem.getActualAmount());
					
					break;
				}
			}
			
			cumulativeSummarries.add(generalRevenuePerUnit);
		}
		
		Map<String, Double> departmentsTotalsMap = getRevenueTotalsPerDepartment(cumulativeSummarries);
		List<GeneralRevenuePerUnit> units = new ArrayList<GeneralRevenuePerUnit>();
		
		for (Map.Entry<String, Double> entry : departmentsTotalsMap.entrySet()) {
			GeneralRevenuePerUnit object = new GeneralRevenuePerUnit();
			object.setDepartment(entry.getKey());
			object.setTotalAmount(BigDecimal.valueOf(entry.getValue()));
			units.add(object);
		}
		
		return SimpleObject.fromCollection(units, uiUtils, "department", "totalAmount");
	}
	
	public Map<String, Double> getRevenueTotalsPerDepartment(List<GeneralRevenuePerUnit> summarizedResults) {
		
		//get all departments as names in a list
		Set<String> departments = new HashSet<String>();
		for (GeneralRevenuePerUnit unit : summarizedResults) {
			if (unit != null) {
				departments.add(unit.getDepartment());
			}
		}
		Map<String, Double> totalsPerDepartment = new HashMap<String, Double>();
		for (String str : departments) {
			double valueHolder = 0.0;
			for (GeneralRevenuePerUnit unit : summarizedResults) {
				if (str.equals(unit.getDepartment())) {
					valueHolder += unit.getTotalAmount().doubleValue();
				}
			}
			totalsPerDepartment.put(str, valueHolder);
			
		}
		return totalsPerDepartment;
	}
	
	public List<User> getAllUsersWithRoleCashier() {
		List<User> cashiers = new ArrayList<User>();
		Role cashierRole = Context.getUserService().getRole("EHR Cashier");
		for (User user : Context.getUserService().getUsersByRole(cashierRole)) {
			cashiers.add(user);
		}
		return cashiers;
	}
	
}
