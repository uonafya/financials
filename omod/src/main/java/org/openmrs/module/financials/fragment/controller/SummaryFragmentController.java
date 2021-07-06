package org.openmrs.module.financials.fragment.controller;

import org.openmrs.PersonAttributeType;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.GeneralRevenuePerUnit;
import org.openmrs.module.financials.PatientBillSummary;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SummaryFragmentController {

	public void controller(FragmentModel model) {
		List<PatientServiceBill> allBils = Context.getService(BillingService.class).getAllPatientServiceBill();
		PersonService personService = Context.getPersonService();
		List<PatientBillSummary> allBills = new ArrayList<PatientBillSummary>();
		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");

		//get person attribute for patient category and sub category
		PersonAttributeType paymentCategory = personService
				.getPersonAttributeTypeByUuid("09cd268a-f0f5-11ea-99a8-b3467ddbf779");
		PersonAttributeType paymentSubCategory = personService
				.getPersonAttributeTypeByUuid("972a32aa-6159-11eb-bc2d-9785fed39154");

		for (PatientServiceBill patientServiceBill : allBils) {
			String createdDate = formatterDate.format(patientServiceBill.getCreatedDate());
			String today = formatterDate.format(new Date());
			PatientBillSummary patientBillSummary;
			if (createdDate.equals(today) && patientServiceBill.getCreatedDate() != null) {
				patientBillSummary = new PatientBillSummary();
				patientBillSummary.setBillId(patientServiceBill.getPatientServiceBillId());
				patientBillSummary.setPatient(patientServiceBill.getPatient().getPersonName().getFullName());
				if (patientServiceBill.getPatient().getAttribute(paymentCategory) != null) {
					patientBillSummary.setCategory(patientServiceBill.getPatient().getAttribute(paymentCategory).getValue());
				} else {
					patientBillSummary.setCategory("");
				}
				if (patientServiceBill.getPatient().getAttribute(paymentSubCategory) != null) {
					patientBillSummary.setSubCategory(patientServiceBill.getPatient().getAttribute(paymentSubCategory)
							.getValue());
				} else {
					patientBillSummary.setSubCategory("");
				}
				patientBillSummary.setWaiver(String.valueOf(patientServiceBill.getWaiverAmount()));
				patientBillSummary.setActualAmount(String.valueOf(patientServiceBill.getActualAmount()));
				patientBillSummary.setPaidAmount(String.valueOf(patientServiceBill.getAmount()));
				patientBillSummary.setRebate(String.valueOf(patientServiceBill.getRebateAmount()));
				patientBillSummary.setTransactionDate(String.valueOf(patientServiceBill.getCreatedDate()));
				patientBillSummary.setIdentifier(patientServiceBill.getPatient().getPatientIdentifier().getIdentifier());
				//add this build object to the list
				allBills.add(patientBillSummary);
			}
		}

		model.addAttribute("bills", allBills);

		List<OpdTestOrder> allOpdOrders = Context.getService(HospitalCoreService.class).getAllOpdOrdersByDateRange(true);
		List<PatientServiceBillItem> patientServiceBillItems = Context.getService(HospitalCoreService.class)
				.getAllPatientServiceBillItemsByDate(true);
		GeneralRevenuePerUnit generalRevenuePerUnit = null;
		List<GeneralRevenuePerUnit> summarizedResults = new ArrayList<GeneralRevenuePerUnit>();

		for (OpdTestOrder opdTestOrder : allOpdOrders) {
			for (PatientServiceBillItem patientServiceBillItem : patientServiceBillItems) {
				if (opdTestOrder.getBillableService().equals(patientServiceBillItem.getService())
						&& opdTestOrder.getFromDept() != null) {
					generalRevenuePerUnit = new GeneralRevenuePerUnit();
					generalRevenuePerUnit.setTransactionDate(opdTestOrder.getScheduleDate());
					generalRevenuePerUnit.setDepartment(opdTestOrder.getFromDept());
					generalRevenuePerUnit.setTotalAmount(patientServiceBillItem.getActualAmount());
					break;
				}
			}
			summarizedResults.add(generalRevenuePerUnit);
		}
		model.addAttribute("summaryAccounts", summarizedResults);

		//get all departiments as names in a list
		Set<String> departiments = new HashSet<String>();
		for (GeneralRevenuePerUnit unit : summarizedResults) {
			if (unit != null) {
				departiments.add(unit.getDepartment());
			}
		}
		Map<String, Double> totalsPerDepartment = new HashMap<String, Double>();
		for (String str : departiments) {
			double valueHolder = 0.0;
			for (GeneralRevenuePerUnit unit : summarizedResults) {
				if (str.equals(unit.getDepartment())) {
					valueHolder += unit.getTotalAmount().doubleValue();
				}
			}
			totalsPerDepartment.put(str, valueHolder);

		}
		model.addAttribute("totalSumPerDepartiment", totalsPerDepartment);

	}
}
