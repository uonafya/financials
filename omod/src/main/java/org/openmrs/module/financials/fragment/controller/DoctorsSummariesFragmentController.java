package org.openmrs.module.financials.fragment.controller;

import org.openmrs.Encounter;
import org.openmrs.Provider;
import org.openmrs.api.ProviderService;
import org.openmrs.api.context.Context;
import org.openmrs.module.ehrconfigs.utils.EhrConfigsUtils;
import org.openmrs.module.financials.model.ProviderPatientSimplifier;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.InventoryDrugCategory;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorsSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		ProviderService providerService = Context.getProviderService();
		Map<Integer, String> userDetails = new HashMap<Integer, String>();
		for (Provider provider : providerService.getAllProviders()) {
			Integer providerId = provider.getProviderId();
			String names = provider.getName();
			
			//populate the map
			userDetails.put(providerId, names);
		}
		model.addAttribute("providers", userDetails);
	}
	
	public List<SimpleObject> fetchPatientsPerProviderEntry(
	        @RequestParam(value = "fromDate", required = false) Date fromDate,
	        @RequestParam(value = "toDate", required = false) Date toDate,
	        @RequestParam(value = "provider", required = false) Provider provider, UiUtils uiUtils) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		List<SimpleObject> processPatientProviderList = null;
		List<Encounter> encounterList = hospitalCoreService.getProviderEncounters(fromDate, toDate, provider);
		ProviderPatientSimplifier providerPatientSimplifier = null;
		List<ProviderPatientSimplifier> providerPatientSimplifierList = new ArrayList<ProviderPatientSimplifier>();
		for (Encounter encounter : encounterList) {
			if (encounter.getEncounterType().equals(Context.getEncounterService().getEncounterTypeByUuid(""))) {
				providerPatientSimplifier = new ProviderPatientSimplifier();
				providerPatientSimplifier.setPatientIdentifier(EhrConfigsUtils.getPreferredPatientIdentifier(encounter
				        .getPatient()));
				providerPatientSimplifier.setPatientNames(encounter.getPatient().getPersonName().getFullName());
				providerPatientSimplifier.setSex(encounter.getPatient().getGender());
				providerPatientSimplifier
				        .setDob(FinancialsUtils.formatDateInDDMMYYYY(encounter.getPatient().getBirthdate()));
				providerPatientSimplifier.setAge(String.valueOf(encounter.getPatient().getAge(new Date())));
				providerPatientSimplifier.setEncounterDate(FinancialsUtils.formatDateWithTime(encounter
				        .getEncounterDatetime()));
				
				providerPatientSimplifierList.add(providerPatientSimplifier);
			}
		}
		processPatientProviderList = SimpleObject.fromCollection(providerPatientSimplifierList, uiUtils, "patientNames",
		    "patientIdentifier", "encounterDate", "dob", "age", "sex");
		
		return processPatientProviderList;
	}
}
