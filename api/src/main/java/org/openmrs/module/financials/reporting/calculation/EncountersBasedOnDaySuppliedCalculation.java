package org.openmrs.module.financials.reporting.calculation;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyacore.calculation.BooleanResult;
import org.openmrs.module.kenyacore.calculation.Calculations;
import org.openmrs.module.kenyaemr.calculation.EmrCalculationUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class EncountersBasedOnDaySuppliedCalculation extends AbstractPatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues,
	        PatientCalculationContext context) {
		CalculationResultMap resultMap = new CalculationResultMap();
		Integer day = (Integer) parameterValues.get("day");
		
		CalculationResultMap encounter = Calculations.lastEncounter(null, cohort, context);
		for (Integer pId : cohort) {
			boolean found = false;
			Encounter patientEncounter = EmrCalculationUtils.encounterResultForPatient(encounter, pId);
			if (patientEncounter != null && patientEncounter.getEncounterDatetime() != null && day != null) {
				if (formatDate(patientEncounter.getEncounterDatetime()).equals(
				    formatDate(getDateBasedOnValue(context.getNow(), day)))) {
					found = true;
				}
				
			}
			
			resultMap.put(pId, new BooleanResult(found, this));
		}
		return resultMap;
	}
	
	private Date getDateBasedOnValue(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(year, month, day);
		
		return calendar1.getTime();
	}
	
	private String formatDate(Date date) {
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		return formatter.format(date);
	}
}
