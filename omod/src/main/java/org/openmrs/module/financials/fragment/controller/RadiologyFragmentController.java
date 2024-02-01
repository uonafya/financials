package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PatientBillSummary;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RadiologyFragmentController {
	
	public void controller(FragmentModel model) {
	}
	
	public List<SimpleObject> fetchRadiologySummariesByDateRange(
	        @RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate, UiUtils ui) {
		
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		
		List<PatientServiceBillItem> getBilledItemsPerDepartment = hospitalCoreService.getPatientServiceBillByDepartment(
		    hospitalCoreService.getDepartmentByName("Radiology"), startDate, endDate);
		List<PatientBillSummary> allRadiologyBills = new ArrayList<PatientBillSummary>();
		Set<PatientBillSummary> allFilteredRadiologyBillsSet = new HashSet<PatientBillSummary>();
		List<PatientBillSummary> allRadiologySetToList = new ArrayList<PatientBillSummary>();
		
		for (PatientServiceBillItem patientServiceBillItem : getBilledItemsPerDepartment) {
			PatientBillSummary patientBillSummary = new PatientBillSummary();
			patientBillSummary.setBillId(patientServiceBillItem.getPatientServiceBillItemId());
			patientBillSummary.setPatient(patientServiceBillItem.getPatientServiceBill().getPatient().getPersonName()
			        .getFullName());
			patientBillSummary.setCategory(patientServiceBillItem.getPatientServiceBill().getPatientCategory());
			patientBillSummary.setSubCategory(patientServiceBillItem.getPatientServiceBill().getPatientSubCategory());
			patientBillSummary.setWaiver(String.valueOf(patientServiceBillItem.getPatientServiceBill().getWaiverAmount()));
			patientBillSummary.setActualAmount(String.valueOf(patientServiceBillItem.getActualAmount()));
			patientBillSummary.setPaidAmount(String.valueOf(patientServiceBillItem.getAmount()));
			patientBillSummary.setRebate(String.valueOf(patientServiceBillItem.getPatientServiceBill().getRebateAmount()));
			patientBillSummary.setTransactionDate(String.valueOf(patientServiceBillItem.getCreatedDate()));
			patientBillSummary.setServiceOffered(patientServiceBillItem.getService().getName());
			patientBillSummary.setIdentifier(patientServiceBillItem.getPatientServiceBill().getPatient()
			        .getPatientIdentifier().getIdentifier());
			//add this build object to the list
			allRadiologyBills.add(patientBillSummary);
			
		}
		if(!allRadiologyBills.isEmpty()) {
			allFilteredRadiologyBillsSet.addAll(allRadiologyBills);
		}
		List<SimpleObject> simpleObjectList = new ArrayList<>();
		if(!allFilteredRadiologyBillsSet.isEmpty()) {
			allRadiologySetToList.addAll(allFilteredRadiologyBillsSet);
		}
		if (!(allRadiologySetToList.isEmpty())) {
			simpleObjectList = SimpleObject.fromCollection(allRadiologySetToList, ui, "transactionDate", "serviceOffered", "identifier",
					"patient", "category", "subCategory", "waiver", "actualAmount", "paidAmount");
		}
		return simpleObjectList;
	}
}
