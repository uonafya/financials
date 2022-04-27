package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.model.NhifPatientSummarySimplifier;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.PatientCategoryDetails;
import org.openmrs.ui.framework.fragment.FragmentModel;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class NhifSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		Date today = new Date();
		Date startOfDay = FinancialsUtils.getStartOfDay(today);
		Date endOfDay = FinancialsUtils.getEndOfDay(today);
		
		List<PatientCategoryDetails> getAllPatientWhoHaveNhif = hospitalCoreService.getAllPatientCategoryDetails(
		    "payingCategory", "NHIF patient", startOfDay, endOfDay);
		
		model.addAttribute("nhifPatients", getNhifObjects(getAllPatientWhoHaveNhif));
		
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
				nhifPatientSummarySimplifier.setVisitType(patientCategoryDetails.getVisitType());
				
				//add to the list
				nhifPatientSummarySimplifierList.add(nhifPatientSummarySimplifier);
				
			}
		}
		return nhifPatientSummarySimplifierList;
	}
}
