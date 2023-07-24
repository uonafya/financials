package org.openmrs.module.financials.fragment.controller;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Encounter;
import org.openmrs.Provider;
import org.openmrs.Visit;
import org.openmrs.api.ProviderService;
import org.openmrs.api.context.Context;
import org.openmrs.module.ehrconfigs.utils.EhrConfigsUtils;
import org.openmrs.module.financials.model.ProviderPatientSimplifier;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.InventoryDrugCategory;
import org.openmrs.module.hospitalcore.util.DateUtils;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openmrs.module.financials.utils.FinancialsUtils.formatDateInAmericanFormat;

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
	        @RequestParam(value = "fromDate", required = false) String fromDate,
	        @RequestParam(value = "toDate", required = false) String toDate,
	        @RequestParam(value = "provider", required = false) Provider provider, UiUtils uiUtils) {
		Date startDate = null;
		Date endDate = null;
		
		if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
			startDate = DateUtils.getDateFromString(fromDate, "dd/MM/yyyy");
			endDate = DateUtils.getDateFromString(toDate, "dd/MM/yyyy");
		}
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		List<Visit> visitList = hospitalCoreService.getProviderEncounters(startDate, endDate, provider,
		    Arrays.asList(Context.getEncounterService().getEncounterTypeByUuid("ba45c278-f290-11ea-9666-1b3e6e848887")));
		Map<String, Visit> uniqueVisit = new HashMap<String, Visit>();
		
		List<SimpleObject> processPatientProviderList = null;
		ProviderPatientSimplifier providerPatientSimplifier = null;
		List<ProviderPatientSimplifier> providerPatientSimplifierList = new ArrayList<ProviderPatientSimplifier>();
		for (Visit visit : visitList) {
			uniqueVisit.put(formatDateInAmericanFormat(visit.getStartDatetime()), visit);
		}
		for (Map.Entry<String, Visit> visitEntry : uniqueVisit.entrySet()) {
			
			providerPatientSimplifier = new ProviderPatientSimplifier();
			providerPatientSimplifier.setPatientIdentifier(EhrConfigsUtils.getPreferredPatientIdentifier(visitEntry
			        .getValue().getPatient()));
			providerPatientSimplifier.setPatientNames(visitEntry.getValue().getPatient().getPersonName().getFullName());
			providerPatientSimplifier.setSex(visitEntry.getValue().getPatient().getGender());
			providerPatientSimplifier.setDob(FinancialsUtils.formatDateInDDMMYYYY(visitEntry.getValue().getPatient()
			        .getBirthdate()));
			providerPatientSimplifier.setAge(String.valueOf(visitEntry.getValue().getPatient().getAge(new Date())));
			providerPatientSimplifier.setEncounterDate(FinancialsUtils.formatDateInAmericanFormat(visitEntry.getValue()
			        .getStartDatetime()));
			
			providerPatientSimplifierList.add(providerPatientSimplifier);
		}
		processPatientProviderList = SimpleObject.fromCollection(providerPatientSimplifierList, uiUtils, "patientNames",
		    "patientIdentifier", "encounterDate", "dob", "age", "sex");
		
		return processPatientProviderList;
	}
}
