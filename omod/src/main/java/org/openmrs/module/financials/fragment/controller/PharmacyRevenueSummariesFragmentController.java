package org.openmrs.module.financials.fragment.controller;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PharmacyBillSummary;
import org.openmrs.module.financials.utils.FinancialsUtils;
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
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String startAt = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()) + " 00:00:00";
		String endAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
		
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>(pullSummaries(startAt, endAt));
		
		model.addAttribute("departmentSummaries", pharmacyTransactions);
	}
	
	public List<SimpleObject> fetchPharmacySummariesByDateRange(
	        @RequestParam(value = "startDate", required = false) String startDate,
	        @RequestParam(value = "endDate", required = false) String endDate, UiUtils uiUtils) {
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>(
		        pullSummaries(startDate, endDate));
		
		return SimpleObject.fromCollection(pharmacyTransactions, uiUtils, "createdOn", "drugName", "formulationName",
		    "issueQuantity", "totalPrice");
	}
	
	private List<PharmacyBillSummary> pullSummaries(@RequestParam(value = "startDate", required = false) String startDate,
	        @RequestParam(value = "endDate", required = false) String endDate) {
		InventoryCommonService inventoryCommonService = Context.getService(InventoryCommonService.class);
		
		List<InventoryStoreDrugPatient> storeDrugPatientList = inventoryCommonService.getAllIssueByDateRange(startDate,
		    endDate);
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>();
		PharmacyBillSummary billSummary;
		if (!storeDrugPatientList.isEmpty()) {
			for (InventoryStoreDrugPatient isdp : storeDrugPatientList) {
				if (isdp != null && isdp.getStatuss() != null) {
					List<InventoryStoreDrugPatientDetail> inventoryStoreDrugPatientDetailList = new ArrayList<InventoryStoreDrugPatientDetail>(
					        inventoryCommonService.getDrugDetailOfPatient(isdp));
					for (InventoryStoreDrugPatientDetail isdpDetail : inventoryStoreDrugPatientDetailList) {
						billSummary = new PharmacyBillSummary();
						InventoryStoreDrugTransactionDetail detail = null;
						if (isdpDetail != null && isdpDetail.getStoreDrugPatient() != null
						        && isdpDetail.getTransactionDetail() != null
						        && isdpDetail.getTransactionDetail().getDrug() != null
						        && isdpDetail.getTransactionDetail().getDrug().getDrugCore() != null) {
							detail = isdpDetail.getTransactionDetail();
						}
						
						if (detail != null) {
							if (detail.getCreatedOn() != null) {
								billSummary.setCreatedOn(FinancialsUtils.formatDateWithTime(detail.getCreatedOn()));
							} else {
								billSummary.setCreatedOn("");
							}
							if (detail.getDrug() != null && StringUtils.isNotBlank(detail.getDrug().getName())) {
								billSummary.setDrugName(detail.getDrug().getName());
							} else {
								billSummary.setDrugName("");
							}
							if (detail.getFormulation() != null) {
								billSummary.setFormulationName(detail.getFormulation().getName() + ":"
								        + detail.getFormulation().getDozage());
							} else {
								billSummary.setFormulationName("");
							}
							if (detail.getIssueQuantity() != null) {
								billSummary.setIssueQuantity(detail.getIssueQuantity());
							} else {
								billSummary.setIssueQuantity(0);
							}
							if (detail.getTotalPrice() != null) {
								billSummary.setTotalPrice(detail.getTotalPrice());
							}
							//add the items
							pharmacyTransactions.add(billSummary);
						}
					}
					
				}
			}
		}
		return pharmacyTransactions;
	}
}
