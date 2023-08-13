package org.openmrs.module.financials.fragment.controller;

import org.openmrs.ConceptClass;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.ehrconfigs.utils.EhrConfigsUtils;
import org.openmrs.module.financials.PatientBillSummary;
import org.openmrs.module.financials.model.ClinicalSummarySimplifier;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		
	}
	
	public List<SimpleObject> fetchGeneralSummariesByDateRange(
	        @RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate, UiUtils ui) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		
		List<PatientServiceBillItem> getBilledItemsPerGeneralDepartment = hospitalCoreService
		        .getPatientServiceBillByDepartment(hospitalCoreService.getDepartmentByName("General"), startDate, endDate);
		List<PatientBillSummary> allGeneralBills = new ArrayList<PatientBillSummary>();
		for (PatientServiceBillItem patientServiceBillItem : getBilledItemsPerGeneralDepartment) {
			PatientBillSummary patientBillSummary = new PatientBillSummary();
			patientBillSummary.setBillId(patientServiceBillItem.getPatientServiceBillItemId());
			patientBillSummary.setPatient(patientServiceBillItem.getPatientServiceBill().getPatient().getPersonName()
			        .getFullName());
			//patientBillSummary.setCategory(patientServiceBillItem.getPatientServiceBill().getPatientCategory());
			//patientBillSummary.setSubCategory(patientServiceBillItem.getPatientServiceBill().getPatientSubCategory());
			patientBillSummary.setWaiver(String.valueOf(patientServiceBillItem.getPatientServiceBill().getWaiverAmount()));
			patientBillSummary.setActualAmount(String.valueOf(patientServiceBillItem.getActualAmount()));
			patientBillSummary.setPaidAmount(String.valueOf(patientServiceBillItem.getAmount()));
			patientBillSummary.setRebate(String.valueOf(patientServiceBillItem.getPatientServiceBill().getRebateAmount()));
			patientBillSummary.setTransactionDate(String.valueOf(patientServiceBillItem.getCreatedDate()));
			patientBillSummary.setServiceOffered(patientServiceBillItem.getService().getName());
			patientBillSummary.setIdentifier(patientServiceBillItem.getPatientServiceBill().getPatient()
			        .getPatientIdentifier().getIdentifier());
			//add this build object to the list
			allGeneralBills.add(patientBillSummary);
			
		}
		return SimpleObject.fromCollection(allGeneralBills, ui, "transactionDate", "serviceOffered", "identifier",
		    "patient", "waiver", "actualAmount", "paidAmount");
	}
	
	public List<SimpleObject> fetchClinicalSummariesByDateRange(
	        @RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate,
	        @RequestParam(value = "uuid", required = false) String uuid,
	        @RequestParam(value = "enType", required = false) String enType, UiUtils ui) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		ConceptClass conceptClass = Context.getConceptService().getConceptClassByUuid(uuid);
		System.out.println("The class uuid>>>" + uuid);
		System.out.println("The encounter uuid>>>" + enType);
		EncounterType encounterType = Context.getEncounterService().getEncounterTypeByUuid(enType);
		ClinicalSummarySimplifier clinicalSummarySimplifier = null;
		List<ClinicalSummarySimplifier> clinicalSummarySimplifiers = new ArrayList<ClinicalSummarySimplifier>();
		if (conceptClass != null && encounterType != null) {
			System.out.println("The class and encounter type is >>" + conceptClass.getName() + " >>> and >>"
			        + encounterType.getName());
			List<Obs> getAllObsForSummary = new ArrayList<Obs>(hospitalCoreService.getObsBasedOnClassAndDateRange(startDate,
			    endDate, conceptClass, encounterType));
			//put the list in the map so that we get a key and size of the value.
			HashMap<Integer, List<String>> hashMap = new HashMap<Integer, List<String>>(
			        EhrConfigsUtils.listMap(getAllObsForSummary));
			for (Map.Entry<Integer, List<String>> listMap : hashMap.entrySet()) {
				clinicalSummarySimplifier = new ClinicalSummarySimplifier();
				clinicalSummarySimplifier.setConceptId(listMap.getKey());
				clinicalSummarySimplifier.setConceptName(Context.getConceptService().getConcept(listMap.getKey())
				        .getDisplayString());
				clinicalSummarySimplifier.setListSize(hashMap.entrySet().size());
				clinicalSummarySimplifiers.add(clinicalSummarySimplifier);
			}
			
		}
		return SimpleObject.fromCollection(clinicalSummarySimplifiers, ui, "conceptId", "conceptName", "listSize");
	}
}
