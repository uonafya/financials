package org.openmrs.module.financials.calculation;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyacore.calculation.BooleanResult;

import java.util.Collection;
import java.util.Map;

public class EligibleForEhrAddonProgramCalculation extends AbstractPatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> collection, Map<String, Object> map,
	        PatientCalculationContext patientCalculationContext) {
		
		CalculationResultMap ret = new CalculationResultMap();
		
		for (Integer pid : collection) {
			boolean eligible = false;
			Patient patient = Context.getPatientService().getPatient(pid);
			
			if (patient.getAge() > 5000) {
				eligible = true;
			}
			ret.put(pid, new BooleanResult(eligible, this));
		}
		
		return ret;
	}
}
