package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PharmacyBillSummary;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugTransactionDetail;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PharmacyRevenueSummariesFragmentController {
	
	public void controller(PageModel model) {
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String startAt = formatter.format(cal.getTime());
		String endAt = formatter.format(new Date());
		
		pharmacyTransactions.addAll(pullSummaries(startAt, endAt));
		
		model.addAttribute("departmentSummaries", pharmacyTransactions);
	}
	
	public List<SimpleObject> fetchPharmacySummariesByDateRange(
	        @RequestParam(value = "startDate", required = false) String startDate,
	        @RequestParam(value = "endDate", required = false) String endDate, UiUtils uiUtils) {
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>();
		pharmacyTransactions.addAll(pullSummaries(startDate, endDate));
		
		return SimpleObject.fromCollection(pharmacyTransactions, uiUtils, "createdOn", "drugName", "formulationName",
		    "issueQuantity", "totalPrice");
	}
	
	private List<PharmacyBillSummary> pullSummaries(@RequestParam(value = "startDate", required = false) String startDate,
	        @RequestParam(value = "endDate", required = false) String endDate) {
		InventoryCommonService inventoryCommonService = Context.getService(InventoryCommonService.class);
		
		List<InventoryStoreDrugPatient> storeDrugPatientList = inventoryCommonService.getAllIssueByDateRange(startDate,
		    endDate);
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>();
		
		for (InventoryStoreDrugPatient isdp : storeDrugPatientList) {
			if (isdp.getStatuss() != null) {
				for (InventoryStoreDrugPatientDetail isdpDetail : inventoryCommonService.getDrugDetailOfPatient(isdp)) {
					PharmacyBillSummary billSummary = new PharmacyBillSummary();
					InventoryStoreDrugTransactionDetail detail = isdpDetail.getTransactionDetail();
					billSummary.setCreatedOn(detail.getCreatedOn());
					billSummary.setDrugName(detail.getDrug().getName());
					billSummary.setFormulationName(detail.getFormulation().getName()+":"+detail.getFormulation().getDozage());
					billSummary.setIssueQuantity(detail.getIssueQuantity());
					billSummary.setTotalPrice(detail.getTotalPrice());
					pharmacyTransactions.add(billSummary);
				}
				
			}
		}
		return pharmacyTransactions;
	}
}
