package org.openmrs.module.financials.fragment.controller;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.ehrconfigs.utils.EhrConfigsUtils;
import org.openmrs.module.ehrinventory.InventoryService;
import org.openmrs.module.financials.PatientBillSummary;
import org.openmrs.module.financials.model.ClinicalSummarySimplifier;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.openmrs.module.hospitalcore.model.OpdDrugOrder;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	        @RequestParam(value = "uuid", required = false) String conceptUuid,
	        @RequestParam(value = "enType", required = false) String enType,
			UiUtils ui) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		Concept concept = Context.getConceptService().getConceptByUuid(conceptUuid);
		EncounterType encounterType = Context.getEncounterService().getEncounterTypeByUuid(enType);
		ClinicalSummarySimplifier clinicalSummarySimplifier = null;
		List<ClinicalSummarySimplifier> clinicalSummarySimplifiers = new ArrayList<ClinicalSummarySimplifier>();
		if (concept != null && encounterType != null) {
			List<Obs> getAllObsForSummary = new ArrayList<Obs>(hospitalCoreService.getObsBasedOnClassAndDateRange(startDate,
			    endDate, concept, encounterType));
			//put the list in the map so that we get a key and size of the value.
			HashMap<Integer, List<String>> hashMapCoded = new HashMap<Integer, List<String>>(
			        EhrConfigsUtils.listMapDiagnosisAndProcedures(getAllObsForSummary));

			HashMap<Integer, List<String>> hashMapForTestsAndRadiology = new HashMap<Integer, List<String>>(
					EhrConfigsUtils.listTestsAndQuestionsConcepts(getAllObsForSummary));


			for (Map.Entry<Integer, List<String>> listMap : hashMapCoded.entrySet()) {
				clinicalSummarySimplifier = new ClinicalSummarySimplifier();
				clinicalSummarySimplifier.setConceptId(listMap.getKey());
				clinicalSummarySimplifier.setConceptName(StringUtils.capitalize(Context.getConceptService().getConcept(listMap.getKey())
				        .getDisplayString()));
				clinicalSummarySimplifier.setListSize(listMap.getValue().size());
				clinicalSummarySimplifiers.add(clinicalSummarySimplifier);
			}
			Comparator<ClinicalSummarySimplifier> sizeComparator = (c1, c2) -> (int) (c1.getListSize() - c2.getListSize());
			clinicalSummarySimplifiers.sort(Collections.reverseOrder(sizeComparator));
		}
		return SimpleObject.fromCollection(clinicalSummarySimplifiers, ui, "conceptId", "conceptName", "listSize");
	}
	
	public List<SimpleObject> fetchPrescriptionSummariesByDateRange(
			@RequestParam(value = "fromDate", required = false) Date startDate,
			@RequestParam(value = "toDate", required = false) Date endDate,
			UiUtils ui) {
		List<OpdDrugOrder> drugOrders = Context.getService(PatientDashboardService.class).getOpdDrugOrderByDateRange( startDate, endDate, 1);
		ClinicalSummarySimplifier clinicalSummarySimplifier = null;
		List<ClinicalSummarySimplifier> clinicalSummarySimplifiers = new ArrayList<ClinicalSummarySimplifier>();
		if (!drugOrders.isEmpty()) {

			//put the list in the map so that we get a key and size of the value.
			HashMap<Integer, List<String>> hashMapDrugs = new HashMap<Integer, List<String>>(
					EhrConfigsUtils.listMapOfDrugPrescription(drugOrders));

			for (Map.Entry<Integer, List<String>> listMap : hashMapDrugs.entrySet()) {
				clinicalSummarySimplifier = new ClinicalSummarySimplifier();
				clinicalSummarySimplifier.setConceptId(listMap.getKey());
				clinicalSummarySimplifier.setConceptName(StringUtils.capitalize(Context.getService(InventoryService.class).getDrugById(listMap.getKey()).getName()));
				clinicalSummarySimplifier.setListSize(listMap.getValue().size());
				clinicalSummarySimplifiers.add(clinicalSummarySimplifier);
			}
			Comparator<ClinicalSummarySimplifier> sizeComparator = (c1, c2) -> (int) (c1.getListSize() - c2.getListSize());
			clinicalSummarySimplifiers.sort(Collections.reverseOrder(sizeComparator));
		}
		return SimpleObject.fromCollection(clinicalSummarySimplifiers, ui, "conceptId", "conceptName", "listSize");
	}
	
	public List<SimpleObject> fetchClinicalSummariesForLabAndRadiologyByDateRange(
			@RequestParam(value = "fromDate", required = false) Date startDate,
			@RequestParam(value = "toDate", required = false) Date endDate,
			@RequestParam(value = "uuid", required = false) String conceptClassUuid,
			@RequestParam(value = "enType", required = false) String enType,
			UiUtils ui) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		ConceptClass conceptClass = Context.getConceptService().getConceptClassByUuid(conceptClassUuid);
		EncounterType encounterType = Context.getEncounterService().getEncounterTypeByUuid(enType);
		ClinicalSummarySimplifier clinicalSummarySimplifier = null;
		List<ClinicalSummarySimplifier> clinicalSummarySimplifiers = new ArrayList<ClinicalSummarySimplifier>();
		if (conceptClass != null && encounterType != null) {
			List<Obs> getAllObsForSummary = new ArrayList<Obs>(hospitalCoreService.getObsBasedOnClassAndDateRangeForTestsAndRadiology(startDate,
					endDate, conceptClass, encounterType));

			HashMap<Integer, List<String>> hashMapForTestsAndRadiology = new HashMap<Integer, List<String>>(
					EhrConfigsUtils.listTestsAndQuestionsConcepts(getAllObsForSummary));


			for (Map.Entry<Integer, List<String>> listMap : hashMapForTestsAndRadiology.entrySet()) {
				clinicalSummarySimplifier = new ClinicalSummarySimplifier();
				clinicalSummarySimplifier.setConceptId(listMap.getKey());
				clinicalSummarySimplifier.setConceptName(StringUtils.capitalize(Context.getConceptService().getConcept(listMap.getKey())
						.getDisplayString()));
				clinicalSummarySimplifier.setListSize(listMap.getValue().size());
				clinicalSummarySimplifiers.add(clinicalSummarySimplifier);
			}
			Comparator<ClinicalSummarySimplifier> sizeComparator = (c1, c2) -> (int) (c1.getListSize() - c2.getListSize());
			clinicalSummarySimplifiers.sort(Collections.reverseOrder(sizeComparator));
		}
		return SimpleObject.fromCollection(clinicalSummarySimplifiers, ui, "conceptId", "conceptName", "listSize");
	}
}
