package org.openmrs.module.financials.fragment.controller;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Patient;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PatientBillSummary;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PatientFinanceSummariesFragmentController {
	
	public void controller(FragmentModel model) {
		
	}
	
	public SimpleObject getBilledItemsPerServiceBill(
	        @RequestParam(value = "billId", required = false) Integer patientServiceBill, UiUtils ui) {
		
		List<PatientServiceBillItem> patientServiceBillItems = Context.getService(BillingService.class)
		        .getPatientBillableServicesByPatientServiceBill(
		            Context.getService(BillingService.class).getPatientServiceBillById(patientServiceBill));
		
		List<SimpleObject> items = SimpleObject.fromCollection(patientServiceBillItems, ui, "service", "unitPrice",
		    "amount", "actualAmount", "quantity", "name", "createdDate");
		
		return SimpleObject.create("items", items);
	}
	
	public List<SimpleObject> getPatientBillsByDateTimeRange(
            @RequestParam(value = "fromDate", required = false) Date startDate,
            @RequestParam(value = "toDate", required = false) Date endDate, UiUtils ui) {
        String STUDENT_ID = "88546440-0271-11eb-b43f-c392cfe8f5df";

        List<PatientServiceBill> allBils = Context.getService(BillingService.class).getPatientBillsPerDateRange(null,
                startDate, endDate);
        List<PatientBillSummary> allBills = new ArrayList<PatientBillSummary>();

        for (PatientServiceBill patientServiceBill : allBils) {
            if (patientServiceBill.getPatient().getPatientIdentifier() != null) {
                PatientBillSummary patientBillSummary = new PatientBillSummary();
                patientBillSummary.setBillId(patientServiceBill.getPatientServiceBillId());
                patientBillSummary.setPatient(patientServiceBill.getPatient().getPersonName().getFullName());
                if (StringUtils.isNotBlank(patientServiceBill.getPatientCategory())) {
                    patientBillSummary.setCategory(patientServiceBill.getPatientCategory());
                } else {
                    patientBillSummary.setCategory("");
                }
                if (StringUtils.isNotBlank(patientServiceBill.getPatientSubCategory())) {
                    patientBillSummary.setSubCategory(patientServiceBill.getPatientSubCategory());
                } else {
                    patientBillSummary.setSubCategory("");
                }
                patientBillSummary.setWaiver(String.valueOf(patientServiceBill.getWaiverAmount()));
                patientBillSummary.setActualAmount(String.valueOf(patientServiceBill.getActualAmount()));
                patientBillSummary.setPaidAmount(String.valueOf(patientServiceBill.getAmount()));
                patientBillSummary.setRebate(String.valueOf(patientServiceBill.getRebateAmount()));
                if (patientServiceBill.getReceipt() != null) {
                    patientBillSummary.setTransactionDate(String.valueOf(patientServiceBill.getReceipt().getPaidDate()));
                }
                patientBillSummary.setIdentifier(patientServiceBill.getPatient().getPatientIdentifier().getIdentifier());
                patientBillSummary.setPatientId(patientServiceBill.getPatient().getPatientId());

                PersonAttributeType studentIdAttributeType = Context.getPersonService().getPersonAttributeTypeByUuid(
                        STUDENT_ID);
                if ((patientServiceBill.getPatient().getAttribute(studentIdAttributeType) != null)) {
                    patientBillSummary.setStudentAttributeName(patientServiceBill.getPatient()
                            .getAttribute(studentIdAttributeType).getValue());
                } else {
                    patientBillSummary.setStudentAttributeName("");
                }
                //add this build object to the list
                allBills.add(patientBillSummary);
            }
        }
        List<SimpleObject> simpleObjectList = new ArrayList<>();
        if (!(allBills.isEmpty())) {
            simpleObjectList = SimpleObject.fromCollection(allBills, ui, "billId", "patient", "category", "subCategory",
                    "studentAttributeName", "serviceOffered", "waiver", "actualAmount", "paidAmount", "rebate", "transactionDate",
                    "transactionDate", "identifier", "patientId");
        }

        return simpleObjectList;
    }
	
	public List<SimpleObject> getItemizedPatientBillsByDateTimeRange(
            @RequestParam(value = "patientId", required = false) Patient patient,
            @RequestParam(value = "fromDate", required = false) Date startDate,
            @RequestParam(value = "ifNhif", required = false) Boolean isNhif,
            @RequestParam(value = "toDate", required = false) Date endDate, UiUtils ui) {
        BillingService billingService = Context.getService(BillingService.class);

        List<SimpleObject> simpleObjectList = new ArrayList<>();
        List<PatientServiceBill> bills = new ArrayList<>();
        if (!isNhif) {
            bills = billingService.getPatientBillsPerDateRange(patient, startDate, endDate).stream().filter(bill ->
                    bill.getPatient() == patient).collect(Collectors.toList());
        } else {
            bills = billingService.getPatientBillsPerDateRange(patient, startDate, endDate).stream().filter(bill ->
                    bill.getPatient() == patient && (bill.getPaymentMode().equalsIgnoreCase("NHIF") || bill.getPatientSubCategory().equalsIgnoreCase("NHIF patient"))).collect(Collectors.toList());
        }
        List<PatientServiceBillItem> patientBills = new ArrayList<>();
        for (PatientServiceBill bill : bills) {
            patientBills.addAll(bill.getBillItems());
        }

        if (!(bills.isEmpty())) {
            simpleObjectList = SimpleObject.fromCollection(patientBills, ui, "patientServiceBill.patientServiceBillId","patientServiceBillItemId", "createdDate", "name", "quantity", "unitPrice", "actualAmount","patientServiceBill.waiverAmount");
        }

        return simpleObjectList;
    }
	
	public List<SimpleObject> getAllBillsItemsByDateRange(
	        @RequestParam(value = "startDate", required = false) Date startDate,
	        @RequestParam(value = "endDate", required = false) Date endDate, UiUtils ui) {
		List<PatientServiceBillItem> patientServiceBills = Context.getService(HospitalCoreService.class)
		        .getPatientServiceBillByDepartment(null, startDate, endDate);
		return SimpleObject.fromCollection(patientServiceBills, ui, "createdDate", "name", "quantity", "unitPrice",
		    "actualAmount", "patientServiceBill");
	}
}
