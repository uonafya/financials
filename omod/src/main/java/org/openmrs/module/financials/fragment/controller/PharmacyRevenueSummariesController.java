package org.openmrs.module.financials.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugTransactionDetail;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class PharmacyRevenueSummariesController {
	
	public void controller(PageModel model) {
		fetchPharmacySummaries("", "", new UiUtils() {});
	}
	
	public List<SimpleObject> fetchPharmacySummaries(@RequestParam(value = "startDate", required = false) String startDate,
	        @RequestParam(value = "endDate", required = false) String endDate, UiUtils uiUtils) {
		
		InventoryCommonService inventoryCommonService = Context.getService(InventoryCommonService.class);
		
		List<InventoryStoreDrugPatient> storeDrugPatientList = inventoryCommonService.getAllIssueByDateRange(startDate,
		    endDate);
		List<InventoryStoreDrugTransactionDetail> pharmacyTransactions = new ArrayList<InventoryStoreDrugTransactionDetail>();
		
		for (InventoryStoreDrugPatient isdp : storeDrugPatientList) {
			for (InventoryStoreDrugPatientDetail isdpDetail : inventoryCommonService.getDrugDetailOfPatient(isdp)) {
				System.out.println("###" + isdpDetail.getTransactionDetail().toString());
				pharmacyTransactions.add(isdpDetail.getTransactionDetail());
			}
			
		}
		
		return SimpleObject.fromCollection(pharmacyTransactions, uiUtils, "createdOn", "drug.name", "formulation.name", "issueQuantity",
		    "totalPrice");
	}
}
