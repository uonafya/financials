package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.model.NhifPatientSummarySimplifier;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.PatientCategoryDetails;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class NhifSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		
	}
	
	private List<NhifPatientSummarySimplifier> getNhifObjects(List<PatientCategoryDetails> patientCategoryDetailsList) {
		List<NhifPatientSummarySimplifier> nhifPatientSummarySimplifierList = new ArrayList<NhifPatientSummarySimplifier>();
		NhifPatientSummarySimplifier nhifPatientSummarySimplifier = null;
		if (patientCategoryDetailsList != null && patientCategoryDetailsList.size() > 0) {
			for (PatientCategoryDetails patientCategoryDetails : patientCategoryDetailsList) {
				nhifPatientSummarySimplifier = new NhifPatientSummarySimplifier();
				nhifPatientSummarySimplifier.setVisitDate(FinancialsUtils.formatDateWithTime(patientCategoryDetails
				        .getDateCreated()));
				nhifPatientSummarySimplifier.setNames(FinancialsUtils.formatPersonName(patientCategoryDetails.getPatient()
				        .getPersonName()));
				nhifPatientSummarySimplifier.setIdentifierValue(FinancialsUtils
				        .formatPatientIdentifier(patientCategoryDetails.getPatient()));
				nhifPatientSummarySimplifier.setNhifNumber(patientCategoryDetails.getNhifNumber());
				nhifPatientSummarySimplifier.setVisitType(getVisitType(patientCategoryDetails.getVisitType()));
				nhifPatientSummarySimplifier.setPatient(patientCategoryDetails.getPatient());
				
				//add to the list
				nhifPatientSummarySimplifierList.add(nhifPatientSummarySimplifier);
				
			}
		}
		return nhifPatientSummarySimplifierList;
	}
	
	public List<SimpleObject> fetchNhifPatientsPerDateRange(
	        @RequestParam(value = "fromDate", required = false) String startDate,
	        @RequestParam(value = "toDate", required = false) String endDate, UiUtils uiUtils) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		
		List<PatientCategoryDetails> getAllPatientWhoHaveNhif = hospitalCoreService.getAllPatientCategoryDetails(
		    "payingCategory", "NHIF patient", startDate, endDate);
		
		return SimpleObject.fromCollection(getNhifObjects(getAllPatientWhoHaveNhif), uiUtils, "patient", "names",
		    "identifierValue", "nhifNumber", "visitType", "visitDate");
		
	}
	
	private String getVisitType(String str) {
		String value = "";
		if (str.equals("1")) {
			value = "New Patient";
		} else if (str.equals("2")) {
			value = "Revisit Patient";
		}
		return value;
	}
}
