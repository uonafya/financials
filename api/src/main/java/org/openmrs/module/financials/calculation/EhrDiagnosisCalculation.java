package org.openmrs.module.financials.calculation;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.ListResult;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyacore.calculation.CalculationUtils;
import org.openmrs.module.kenyacore.calculation.Calculations;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.common.DateUtil;
import org.openmrs.module.reporting.common.DurationUnit;

import java.util.*;

public class EhrDiagnosisCalculation extends AbstractPatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> map,
	        PatientCalculationContext context) {
		CalculationResultMap ret = new CalculationResultMap();
		
		CalculationResultMap finalDiagnosis = Calculations.allObs(
		    MetadataUtils.existing(Concept.class, EhrAddonsConstants._EhrAddOnConcepts.FINA_DIAGNOSIS), cohort, context);
		CalculationResultMap provisionalDiagnosis = Calculations.allObs(
		    MetadataUtils.existing(Concept.class, EhrAddonsConstants._EhrAddOnConcepts.PROVISIONAL_DIAGNOSIS), cohort,
		    context);
		CalculationResultMap problemAddedDiagnosis = Calculations.allObs(
		    MetadataUtils.existing(Concept.class, EhrAddonsConstants._EhrAddOnConcepts.PROBLEM_ADDED), cohort, context);
		CalculationResultMap otherDiagnosis = Calculations.allObs(
		    MetadataUtils.existing(Concept.class, "17b33cd3-1af9-4a1b-a65b-b5e30540b189"), cohort, context);
		
		for (Integer ptId : cohort) {
			ListResult finalListResults = (ListResult) finalDiagnosis.get(ptId);
			ListResult provisionalListResults = (ListResult) provisionalDiagnosis.get(ptId);
			ListResult problemAddedListResults = (ListResult) problemAddedDiagnosis.get(ptId);
			ListResult otherListResults = (ListResult) otherDiagnosis.get(ptId);
			
			Date aMonthAgo = DateUtil.adjustDate(context.getNow(), -1, DurationUnit.MONTHS);
			
			List<Obs> finalResultsList = CalculationUtils.extractResultValues(finalListResults);
			List<Obs> provisionalResultsList = CalculationUtils.extractResultValues(provisionalListResults);
			List<Obs> problemAddedResultsList = CalculationUtils.extractResultValues(problemAddedListResults);
			List<Obs> otherResultsList = CalculationUtils.extractResultValues(otherListResults);
			List<String> diagnosisStringsForThePatient = new ArrayList<String>();
			
			List<Obs> resultantResultsList = new ArrayList<Obs>();
			resultantResultsList.addAll(finalResultsList);
			resultantResultsList.addAll(provisionalResultsList);
			resultantResultsList.addAll(problemAddedResultsList);
			resultantResultsList.addAll(otherResultsList);
			
			//order the diagnosis by dates entered
			Collections.sort(resultantResultsList, new Comparator<Obs>() {
				
				public int compare(Obs o1, Obs o2) {
					if (o1.getObsDatetime() == null || o2.getObsDatetime() == null)
						return 0;
					return o1.getObsDatetime().compareTo(o2.getObsDatetime());
				}
			});
			
			for (Obs obs : resultantResultsList) {
				if (obs.getValueCoded() != null && obs.getObsDatetime() != null
				        && obs.getObsDatetime().compareTo(aMonthAgo) >= 0
				        && obs.getObsDatetime().compareTo(context.getNow()) <= 0) {
					diagnosisStringsForThePatient.add(obs.getValueCoded().getDisplayString());
				}
			}
			
			ret.put(ptId, new SimpleResult(StringUtils.join(diagnosisStringsForThePatient, ","), this));
		}
		return ret;
	}
}
