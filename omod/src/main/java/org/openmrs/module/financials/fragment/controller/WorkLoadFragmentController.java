package org.openmrs.module.financials.fragment.controller;

import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class WorkLoadFragmentController {
	
	public void controller(FragmentModel model) {
	}
	
	public SimpleObject fetchWorkLoadSummariesByDateRange(
	        @RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate, UiUtils ui) {
		
		Integer opd = 0;
		Integer specialClinic = 0;
		Integer ipd = 0;
		Integer mopc = 0;
		Integer lab = 0;
		Integer procedure = 0;
		Integer radiology = 0;
		
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
}
