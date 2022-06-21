package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PatientBillSummary;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SummaryFragmentController {
	
	public void controller(FragmentModel model) {
		
	}
	
	public List<SimpleObject> getPatientServiceBillByDepartmentTable(@RequestParam(value = "fromDate", required = false) Date startDate,
																@RequestParam(value = "toDate", required = false) Date endDate, UiUtils ui) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);

		List<PatientServiceBillItem> getBilledItemsPerDepartment = hospitalCoreService.getPatientServiceBillByDepartment(
						hospitalCoreService.getDepartmentByName("Registration"), startDate, endDate);

		List<PatientBillSummary> allRegistrationBills = new ArrayList<PatientBillSummary>();
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
			allRegistrationBills.add(patientBillSummary);
		}
		List<SimpleObject> simpleObjectList = new ArrayList<>();
		if (!(allRegistrationBills.isEmpty())) {
			simpleObjectList = SimpleObject.fromCollection(allRegistrationBills, ui, "billId", "patient", "category", "subCategory",
							"studentAttributeName", "serviceOffered", "waiver", "actualAmount", "paidAmount", "rebate", "transactionDate",
							"transactionDate", "identifier", "patientId");
		}

		return simpleObjectList;

	}
	
	public SimpleObject getPatientServiceBillByDepartmentTotals(
	        @RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate, UiUtils ui) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		List<PatientServiceBillItem> getBilledItemsPerDepartment = hospitalCoreService.getPatientServiceBillByDepartment(
		    hospitalCoreService.getDepartmentByName("Registration"), startDate, endDate);
		ConceptService conceptService = Context.getConceptService();
		double registrationFees = 0.0;
		double revisitFees = 0.0;
		double specialClinicFees = 0.0;
		SimpleObject simpleObject = new SimpleObject();
		for (PatientServiceBillItem patientServiceBillItem : getBilledItemsPerDepartment) {
			if (FinancialsUtils.registrationFeeConcepts().contains(
			    conceptService.getConcept(patientServiceBillItem.getService().getConceptId()))) {
				registrationFees += patientServiceBillItem.getAmount().doubleValue();
				simpleObject.put("regFees", registrationFees);
			}
			if (FinancialsUtils.revisitFeeConcepts().contains(
			    conceptService.getConcept(patientServiceBillItem.getService().getConceptId()))) {
				revisitFees += patientServiceBillItem.getAmount().doubleValue();
				simpleObject.put("revFees", revisitFees);
			}
			if (FinancialsUtils.specialClinicFeeConcepts().contains(
			    conceptService.getConcept(patientServiceBillItem.getService().getConceptId()))) {
				specialClinicFees += patientServiceBillItem.getAmount().doubleValue();
				simpleObject.put("specialFees", specialClinicFees);
			}
		}
		return simpleObject;
	}
}
