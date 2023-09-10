package org.openmrs.module.financials.fragment.controller;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.PharmacyBillSummary;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatient;
import org.openmrs.module.hospitalcore.model.InventoryStoreDrugPatientDetail;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DashboardFragmentController {
	
	public void controller(FragmentModel model) throws ParseException {
	}
	
	private Double getDepartmentTotalsOnDateRange(HospitalCoreService hospitalCoreService, Date startDate, Date endDate,
	        EhrDepartment ehrDepartment) {
		double totals = 0.0;
		List<PatientServiceBillItem> getBilledItemsPerDepartment = hospitalCoreService.getPatientServiceBillByDepartment(
		    ehrDepartment, startDate, endDate);
		if (getBilledItemsPerDepartment.size() > 0) {
			for (PatientServiceBillItem patientServiceBillItem : getBilledItemsPerDepartment) {
				if (patientServiceBillItem.getActualAmount() != null) {
					totals += patientServiceBillItem.getActualAmount().doubleValue();
				}
			}
		}
		return totals;
	}
	
	public SimpleObject getDepartmentTotalsOnDateRange(@RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate) throws ParseException {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		
		SimpleObject simpleObject = new SimpleObject();
		
		simpleObject.put("pharmacy", getPharmacyTotalsWithinDateRange(startDate, endDate));
		simpleObject.put(
		    "procedure",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Procedure")));
		simpleObject.put(
		    "radiology",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Radiology")));
		simpleObject.put(
		    "laboratory",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Laboratory")));
		simpleObject.put(
		    "registration",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Registration")));
		simpleObject.put(
		    "general",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("General")));
		
		simpleObject.put(
		    "dental",
		    getDepartmentTotalsOnDateRange(hospitalCoreService, startDate, endDate,
		        hospitalCoreService.getDepartmentByName("Dental")));
		return simpleObject;
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
					patientPharmacySummary.setTotalAMount(String.valueOf(cummulativeSumPerPatient));
					
					pharmacyTransactionsList.add(patientPharmacySummary);
				}
			}
		}
		return pharmacyTransactionsList;
	}
	
	private double getPharmacyTotalsWithinDateRange(Date startDate, Date endDate) {
		String startDateStr = FinancialsUtils.formatDateInDDMMYYYY(new Date());
		String endDateStr = FinancialsUtils.formatDateInDDMMYYYY(new Date());
		double sumTotals = 0.0;
		if (startDate != null && endDate != null) {
			startDateStr = FinancialsUtils.formatDateInDDMMYYYY(startDate);
			endDateStr = FinancialsUtils.formatDateInDDMMYYYY(endDate);
		}
		List<PharmacyBillSummary> pharmacyTransactions = new ArrayList<PharmacyBillSummary>(pullSummaries(startDateStr,
		    endDateStr));
		for (PharmacyBillSummary pharmacyBillSummary : pharmacyTransactions) {
			sumTotals += Double.parseDouble(pharmacyBillSummary.getTotalAMount());
		}
		return sumTotals;
	}
	
	public SimpleObject getAdminSummariesOnDateRange(@RequestParam(value = "fromDate", required = false) Date startDate,
	        @RequestParam(value = "toDate", required = false) Date endDate) {
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		SimpleObject simpleObject = new SimpleObject();
		
		simpleObject.put("opdVisits", getTotalOpdVisitsWithinDateRange(startDate, endDate, hospitalCoreService).size());
		simpleObject.put("allVisits", getTotalVisitsWithinDateRange(startDate, endDate, hospitalCoreService).size());
		simpleObject.put("allWalkIn", getTotalWalkInWithinDateRange(startDate, endDate, hospitalCoreService).size());
		simpleObject.put("revisitPatients", getTotalRevisitWithinDateRange(startDate, endDate, hospitalCoreService).size());
		simpleObject.put("newPatients", getTotalNewWithinDateRange(startDate, endDate, hospitalCoreService).size());
		
		return simpleObject;
	}
	
	private Set<Patient> getTotalOpdVisitsWithinDateRange(Date startDate, Date endDate,
	        HospitalCoreService hospitalCoreService) {
		List<Encounter> encounterList = hospitalCoreService.getProviderEncounters(startDate, endDate, null,
		    Arrays.asList(Context.getEncounterService().getEncounterTypeByUuid("ba45c278-f290-11ea-9666-1b3e6e848887")));
		Set<Patient> patientSet = new HashSet<Patient>();
		for (Encounter encounter : encounterList) {
			patientSet.add(encounter.getPatient());
		}
		return patientSet;
	}
	
	private Set<Patient> getTotalRevisitWithinDateRange(Date startDate, Date endDate, HospitalCoreService hospitalCoreService) {
		List<Encounter> encounterList = hospitalCoreService.getProviderEncounters(startDate, endDate, null,
		    Arrays.asList(Context.getEncounterService().getEncounterTypeByUuid("98d42234-f28f-11ea-b609-bbd062a0383b")));
		Set<Patient> patientSet = new HashSet<Patient>();
		for (Encounter encounter : encounterList) {
			patientSet.add(encounter.getPatient());
		}
		return patientSet;
	}
	
	private Set<Patient> getTotalNewWithinDateRange(Date startDate, Date endDate, HospitalCoreService hospitalCoreService) {
		List<Encounter> encounterList = hospitalCoreService.getProviderEncounters(startDate, endDate, null,
		    Arrays.asList(Context.getEncounterService().getEncounterTypeByUuid("98efa1534-f28f-11ea-b25f-af56118cf21b")));
		Set<Patient> patientSet = new HashSet<Patient>();
		for (Encounter encounter : encounterList) {
			patientSet.add(encounter.getPatient());
		}
		return patientSet;
	}
	
	private Set<Patient> getTotalVisitsWithinDateRange(Date startDate, Date endDate, HospitalCoreService hospitalCoreService) {
		List<Encounter> encounterList = hospitalCoreService.getProviderEncounters(startDate, endDate, null, null);
		Set<Patient> patientSet = new HashSet<Patient>();
		for (Encounter encounter : encounterList) {
			patientSet.add(encounter.getPatient());
		}
		return patientSet;
	}
	
	private Set<Patient> getTotalWalkInWithinDateRange(Date startDate, Date endDate, HospitalCoreService hospitalCoreService) {
		
		Set<Patient> patientSetTotalWalkIn = getTotalVisitsWithinDateRange(startDate, endDate, hospitalCoreService);
		patientSetTotalWalkIn.removeAll(getTotalVisitsWithinDateRange(startDate, endDate, hospitalCoreService));
		
		return patientSetTotalWalkIn;
	}
}
