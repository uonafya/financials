package org.openmrs.module.financials.reporting.calculation;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PatientIdentifierCalculation extends AbstractPatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> map,
	        PatientCalculationContext context) {
		
		CalculationResultMap ret = new CalculationResultMap();
		
		for (Integer pId : cohort) {
			String identifier = "";
			Set<PatientIdentifier> patientIdentifierSet = Context.getPatientService().getPatient(pId).getIdentifiers();
			for (PatientIdentifier patientIdentifier : patientIdentifierSet) {
				if (StringUtils.isNotBlank(patientIdentifier.getIdentifier())) {
					if (patientIdentifier.getIdentifierType().equals(
					    Context.getPatientService().getPatientIdentifierTypeByUuid(
					        CommonMetadata._PatientIdentifierType.PATIENT_CLINIC_NUMBER))) {
						identifier = patientIdentifier.getIdentifier();
						break;
					} else {
						identifier = patientIdentifier.getIdentifier();
					}
				}
			}
			
			ret.put(pId, new SimpleResult(identifier, this));
		}
		return ret;
	}
}
