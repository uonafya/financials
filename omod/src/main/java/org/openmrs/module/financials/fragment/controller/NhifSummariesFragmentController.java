package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.model.NhifPatientSimplifier;
import org.openmrs.module.financials.utils.Utils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.PatientCategoryDetails;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhifSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		Date today = new Date();
		Date startOfDay = Utils.getStartOfDay(today);
		Date endOfDay = Utils.getEndOfDay(today);
		
		List<PatientCategoryDetails> getAllPatientWhoHaveNhif = hospitalCoreService.getAllPatientCategoryDetails(
		    "payingCategory", "NHIF patient", startOfDay, endOfDay);
		
		model.addAttribute("nhifPatients", getNhifObjects(getAllPatientWhoHaveNhif));
		
	}
	
	private List<NhifPatientSimplifier> getNhifObjects(List<PatientCategoryDetails> patientCategoryDetailsList) {
		List<NhifPatientSimplifier> nhifPatientSimplifierList = new ArrayList<NhifPatientSimplifier>();
		NhifPatientSimplifier nhifPatientSimplifier = null;
		if (patientCategoryDetailsList != null && patientCategoryDetailsList.size() > 0) {
			for (PatientCategoryDetails patientCategoryDetails : patientCategoryDetailsList) {
				nhifPatientSimplifier = new NhifPatientSimplifier();
				nhifPatientSimplifier.setVisitDate(Utils.formatDateWithTime(patientCategoryDetails.getDateCreated()));
				nhifPatientSimplifier.setNames(Utils.formatPersonName(patientCategoryDetails.getPatient().getPersonName()));
				nhifPatientSimplifier.setIdentifierValue(Utils.formatPatientIdentifier(patientCategoryDetails.getPatient()));
				nhifPatientSimplifier.setNhifNumber(patientCategoryDetails.getNhifNumber());
				nhifPatientSimplifier.setVisitType(patientCategoryDetails.getVisitType());
				
				//add to the list
				nhifPatientSimplifierList.add(nhifPatientSimplifier);
				
			}
		}
		return nhifPatientSimplifierList;
	}
}
