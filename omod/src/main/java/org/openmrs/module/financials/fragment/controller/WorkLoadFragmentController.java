package org.openmrs.module.financials.fragment.controller;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.IpdService;
import org.openmrs.module.hospitalcore.PatientQueueService;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.parameter.EncounterSearchCriteria;
import org.openmrs.parameter.EncounterSearchCriteriaBuilder;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class WorkLoadFragmentController {
	
	public void controller(FragmentModel model) {
	}
	
	public SimpleObject fetchWorkLoadSummariesByDateRange(
	        @RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate, UiUtils ui) {
		PatientQueueService patientQueueService = Context.getService(PatientQueueService.class);
		Date startOfDay = FinancialsUtils.getStartOfDay(new Date());
		Date endOfDay = FinancialsUtils.getEndOfDay(new Date());
		if (startDate != null) {
			startOfDay = FinancialsUtils.getStartOfDay(startDate);
		}
		if (endDate != null) {
			endOfDay = FinancialsUtils.getEndOfDay(endDate);
		}
		
		int ipd = 0;
		int lab = 0;
		int procedure = 0;
		int radiology = 0;
		int mchMothers = 0;
		int mchChildren = 0;
		int deliveries = 0;
		int immunization = 0;
		int anc = 0;
		int pnc = 0;
		int preventiveServices = 0;
		EncounterType labEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "11d3f37a-f282-11ea-a825-1b5b1ff1b854");
		EncounterType radiologyEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "012bb9f4-f282-11ea-a6d6-3b4fa4aefb5a");
		EncounterType mchMotherEnrollmentEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "3ee036d8-7c13-4393-b5d6-036f2fe45126");
		EncounterType mchChildEnrollmentEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "8553d869-bdc8-4287-8505-910c7c998aff");
		EncounterType mchMotherFollowupEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "c6d09e05-1f25-4164-8860-9f32c5a02df0");
		EncounterType mchChildFollowupEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "755b59e6-acbb-4853-abaf-be302039f902");
		EncounterType mchChildImmunizationEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "b4f3859e-861c-4a63-bdff-eb7392030d47");
		EncounterType ancEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "e8f98494-af35-4bb8-9fc7-c409c8fed843");
		EncounterType pncEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "72aa78e0-ee4b-47c3-9073-26f3b9ecc4a7");
		EncounterType deliveriesEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "496c7cc3-0eea-4e84-a04c-2292949e2f7f");
		EncounterType preventiveServicesEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "d3ea25c7-a3e8-4f57-a6a9-e802c3565a30");
		List<Encounter> getLabEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay, Arrays.asList(labEncounterType)));
		List<Obs> getAllProceduresObs = FinancialsUtils.getObsList(startOfDay, endOfDay,
		    FinancialsUtils.getConcept("1651AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
		List<Encounter> getRadiologyEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay, Arrays.asList(radiologyEncounterType)));
		
		List<Encounter> getMchMotherEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay,
		        Arrays.asList(mchMotherEnrollmentEncounterType, mchMotherFollowupEncounterType)));
		List<Encounter> getMchChildEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay,
		        Arrays.asList(mchChildEnrollmentEncounterType, mchChildFollowupEncounterType)));
		List<Encounter> getDeliveriesEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay, Arrays.asList(deliveriesEncounterType)));
		List<Encounter> getImmunizationEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay, Arrays.asList(mchChildImmunizationEncounterType)));
		List<Encounter> getAncEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay, Arrays.asList(ancEncounterType)));
		List<Encounter> getpncEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay, Arrays.asList(pncEncounterType)));
		List<Encounter> getPreventiveEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay, Arrays.asList(preventiveServicesEncounterType)));
		
		List<IpdPatientAdmitted> getAllAdmittedPatients = Context.getService(IpdService.class)
		        .getAdmittedPatientsByDateRange(startOfDay, endOfDay);
		
		if (!getLabEncounters.isEmpty()) {
			lab = getLabEncounters.size();
		}
		if (!getAllProceduresObs.isEmpty()) {
			procedure = getAllProceduresObs.size();
		}
		if (!getRadiologyEncounters.isEmpty()) {
			radiology = getRadiologyEncounters.size();
		}
		if (!getMchMotherEncounters.isEmpty()) {
			mchMothers = getMchMotherEncounters.size();
		}
		if (!getMchChildEncounters.isEmpty()) {
			mchChildren = getMchChildEncounters.size();
		}
		if (!getDeliveriesEncounters.isEmpty()) {
			deliveries = getDeliveriesEncounters.size();
		}
		if (!getImmunizationEncounters.isEmpty()) {
			immunization = getImmunizationEncounters.size();
		}
		if (!getAncEncounters.isEmpty()) {
			anc = getAncEncounters.size();
		}
		if (!getpncEncounters.isEmpty()) {
			pnc = getpncEncounters.size();
		}
		if (!getPreventiveEncounters.isEmpty()) {
			preventiveServices = getPreventiveEncounters.size();
		}
		if (!getAllAdmittedPatients.isEmpty()) {
			ipd = getAllAdmittedPatients.size();
		}
		
		SimpleObject simpleObject = new SimpleObject();
		simpleObject.put("opd", patientQueueService.getPatientQueueLogCounts(startOfDay, endOfDay, null));
		simpleObject.put("ipd", ipd);
		simpleObject.put(
		    "mopc",
		    patientQueueService.getPatientQueueLogCounts(startOfDay, endOfDay,
		        FinancialsUtils.getConcept("66710a6d-5894-4f7d-a874-b449df77314d")));
		simpleObject.put("lab", lab);
		simpleObject.put("procedure", procedure);
		simpleObject.put("radiology", radiology);
		simpleObject.put(
		    "dental",
		    patientQueueService.getPatientQueueLogCounts(startOfDay, endOfDay,
		        FinancialsUtils.getConcept("30aff715-92de-4662-aa33-fa6b6179fed0")));
		simpleObject.put(
		    "eye",
		    patientQueueService.getPatientQueueLogCounts(startOfDay, endOfDay,
		        FinancialsUtils.getConcept("36f7a4c9-a64b-4351-bd08-fe185c7470dc")));
		simpleObject.put(
		    "ent",
		    patientQueueService.getPatientQueueLogCounts(startOfDay, endOfDay,
		        FinancialsUtils.getConcept("160455AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")));
		
		simpleObject.put("mchm", mchMothers);
		simpleObject.put("mchc", mchChildren);
		simpleObject.put("deliveries", deliveries);
		simpleObject.put("immunization", immunization);
		simpleObject.put("anc", anc);
		simpleObject.put("pnc", pnc);
		simpleObject.put("preventiveServices", preventiveServices);
		return simpleObject;
		
	}
	
	private EncounterSearchCriteria formEncounterSearchCriteria(Date startDate, Date endDate,
	        List<EncounterType> encounterType) {
		return new EncounterSearchCriteriaBuilder().setEncounterTypes(encounterType).setFromDate(startDate)
		        .setToDate(endDate).setIncludeVoided(false).createEncounterSearchCriteria();
	}
}
