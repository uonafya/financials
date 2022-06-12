package org.openmrs.module.financials.page.controller;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AppPage("financials.home")
public class PatientInvoicePageController {
	
	public void controller(PageModel pageModel,  @RequestParam("patientId") Patient patient) {
        BillingService billingService = Context.getService(BillingService.class);

        pageModel.addAttribute("patient", patient);

        List<PatientServiceBill> bills = billingService.getAllPatientServiceBill().stream().filter(bill ->
                bill.getPatient() == patient ).collect(Collectors.toList());
        List<PatientServiceBillItem> patientBills = new ArrayList<>();
        for (PatientServiceBill bill :
                bills) {
            patientBills.addAll(bill.getBillItems());
        }

        pageModel.addAttribute("billedItems",patientBills);
    }
}
