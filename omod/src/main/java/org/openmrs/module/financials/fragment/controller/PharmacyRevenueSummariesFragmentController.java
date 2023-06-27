package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PharmacyBillSummary;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PharmacyRevenueSummariesFragmentController {
	
	public void controller(PageModel model) {
		
	}
	
	public List<SimpleObject> fetchPharmacySummariesByDateRange(
	        @RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate, UiUtils uiUtils) {
		String fromDate = FinancialsUtils.formatDateInDDMMYYYY(startDate);
		String toDate = FinancialsUtils.formatDateInDDMMYYYY(endDate);
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>(pullSummaries(fromDate, toDate));
		
		return SimpleObject.fromCollection(pharmacyTransactions, uiUtils, "createdOn", "patientNames", "patientIdentifier",
		    "waiverAmount", "totalAMount");
	}
	
	private List<PharmacyBillSummary> pullSummaries(String startDate, String endDate) {
		InventoryCommonService inventoryCommonService = Context.getService(InventoryCommonService.class);
		
		List<InventoryStoreDrugPatient> inventoryStoreDrugPatientList = inventoryCommonService.getAllIssueByDateRange(
		    startDate, endDate);
		List<PharmacyBillSummary> pharmacyTransactionsList = new ArrayList<PharmacyBillSummary>();
		
		PharmacyBillSummary patientPharmacySummary;
		if (!inventoryStoreDrugPatientList.isEmpty()) {
			for (InventoryStoreDrugPatient isdp : inventoryStoreDrugPatientList) {
				if (isdp.getStatuss() != null && isdp.getStatuss() == 1) {
					
					List<InventoryStoreDrugPatientDetail> inventoryStoreDrugPatientDetailList = new ArrayList<InventoryStoreDrugPatientDetail>(
					        inventoryCommonService.getDrugDetailOfPatient(isdp));
					double productOfSum = 0.0;
					double cummulativeSumPerPatient = 0.0;
					for (InventoryStoreDrugPatientDetail inventoryStoreDrugPatientDetail : inventoryStoreDrugPatientDetailList) {
						productOfSum = inventoryStoreDrugPatientDetail.getQuantity()
						        * inventoryStoreDrugPatientDetail.getTransactionDetail().getCostToPatient().doubleValue();
						cummulativeSumPerPatient += productOfSum;
					}
					patientPharmacySummary = new PharmacyBillSummary();
					patientPharmacySummary.setCreatedOn(FinancialsUtils.formatDateWithTime(isdp.getCreatedOn()));
					patientPharmacySummary.setPatientNames(isdp.getName());
					patientPharmacySummary.setPatientIdentifier(isdp.getIdentifier());
					patientPharmacySummary.setWaiverAmount(isdp.getWaiverAmount().toString());
					patientPharmacySummary.setTotalAMount(String.valueOf(cummulativeSumPerPatient));
					
					pharmacyTransactionsList.add(patientPharmacySummary);
				}
			}
		}
		return pharmacyTransactionsList;
	}
}
