package org.openmrs.module.financials.fragment.controller;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.utils.FinancialsUtils;
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
		
		Date startOfDay = FinancialsUtils.getStartOfDay(new Date());
		Date endOfDay = FinancialsUtils.getEndOfDay(new Date());
		if (startDate != null) {
			startOfDay = FinancialsUtils.getStartOfDay(startDate);
		}
		if (endDate != null) {
			endOfDay = FinancialsUtils.getEndOfDay(endDate);
		}
		
		Integer opd = 0;
		Integer specialClinic = 0;
		Integer ipd = 0;
		Integer mopc = 0;
		Integer lab = 0;
		Integer procedure = 0;
		Integer radiology = 0;
		
		EncounterType opdEncounterType = Context.getEncounterService().getEncounterTypeByUuid(
		    "ba45c278-f290-11ea-9666-1b3e6e848887");
		
		List<Encounter> getOpdEncounters = Context.getEncounterService().getEncounters(
		    formEncounterSearchCriteria(startOfDay, endOfDay, opdEncounterType));
		List<Obs> getAllSpecialClinicObs = FinancialsUtils.getObsList(startOfDay, endOfDay,
		    FinancialsUtils.getConcept("b5e0cfd3-1009-4527-8e36-83b5e902b3ea"));
		List<Obs> getAllMopcObs = FinancialsUtils.getObsList(startOfDay, endOfDay,
		    FinancialsUtils.getConcept("03880388-07ce-4961-abe7-0e58f787dd23"),
		    FinancialsUtils.getConcept("66710a6d-5894-4f7d-a874-b449df77314d"));
		
		if (!getOpdEncounters.isEmpty()) {
			opd = getOpdEncounters.size();
		}
		if (!getAllSpecialClinicObs.isEmpty()) {
			specialClinic = getAllSpecialClinicObs.size();
		}
		if (!getAllMopcObs.isEmpty()) {
			mopc = getAllMopcObs.size();
		}
		
		SimpleObject simpleObject = new SimpleObject();
		simpleObject.put("opd", opd);
		simpleObject.put("spc", specialClinic);
		simpleObject.put("ipd", ipd);
		simpleObject.put("mopc", mopc);
		simpleObject.put("lab", lab);
		simpleObject.put("procedure", procedure);
		simpleObject.put("radiology", radiology);
		
		return simpleObject;
		
	}
	
	private EncounterSearchCriteria formEncounterSearchCriteria(Date startDate, Date endDate, EncounterType encounterType) {
		return new EncounterSearchCriteriaBuilder().setEncounterTypes(Arrays.asList(encounterType)).setFromDate(startDate)
		        .setToDate(endDate).setIncludeVoided(false).createEncounterSearchCriteria();
	}
}
