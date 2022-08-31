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
		
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>(pullSummaries("01/05/2022",
		    "02/05/2022"));
		model.addAttribute("departmentSummaries", pharmacyTransactions);
	}
	
	public List<SimpleObject> fetchPharmacySummariesByDateRange(
	        @RequestParam(value = "fromDate", required = false) String startDate,
	        @RequestParam(value = "toDate", required = false) String endDate, UiUtils uiUtils) {
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>(
		        pullSummaries(startDate, endDate));
		
		return SimpleObject.fromCollection(pharmacyTransactions, uiUtils, "createdOn", "patientNames", "patientIdentifier",
		    "waiverAmount", "totalAMount");
	}
	
	private List<PharmacyBillSummary> pullSummaries(String startDate, String endDate) {
		/*String[] startDateParts = startDate.split("-");
		String[] endDateParts = endDate.split("-");
		String startDateFormat = startDateParts[2] + "/" + startDateParts[1] + "/" + startDateParts[0];
		String endDateFormat = endDateParts[2] + "/" + endDateParts[1] + "/" + endDateParts[0];*/
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
