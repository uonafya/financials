package org.openmrs.module.financials.reporting.calculation;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.ListResult;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.financials.diagnosis.lists.DiagnosisLists;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyacore.calculation.CalculationUtils;
import org.openmrs.module.kenyacore.calculation.Calculations;
import org.openmrs.module.kenyaemr.calculation.EmrCalculationUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FeversForPatientCalculation extends AbstractPatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues,
	        PatientCalculationContext context) {
		CalculationResultMap ret = new CalculationResultMap();
		//6042, 160249, 160250, 1000483
		Concept c6042 = Context.getConceptService().getConcept(6042);
		Concept c160249 = Context.getConceptService().getConcept(160249);
		Concept c160250 = Context.getConceptService().getConcept(160250);
		Concept c1000483 = Context.getConceptService().getConcept(1000483);
		
		CalculationResultMap map1 = Calculations.allObs(c6042, cohort, context);
		CalculationResultMap map2 = Calculations.allObs(c160249, cohort, context);
		CalculationResultMap map3 = Calculations.allObs(c160250, cohort, context);
		CalculationResultMap map4 = Calculations.allObs(c1000483, cohort, context);
		
		for (Integer pId : cohort) {
			String ans = "N";
			ListResult listResult1 = (ListResult) map1.get(pId);
			ListResult listResult2 = (ListResult) map2.get(pId);
			ListResult listResult3 = (ListResult) map3.get(pId);
			ListResult listResult4 = (ListResult) map4.get(pId);
			
			List<Obs> obsList1 = CalculationUtils.extractResultValues(listResult1);
			List<Obs> obsList2 = CalculationUtils.extractResultValues(listResult2);
			List<Obs> obsList3 = CalculationUtils.extractResultValues(listResult3);
			List<Obs> obsList4 = CalculationUtils.extractResultValues(listResult4);
			
			//get one list that has all the observations put together
			List<Obs> resultantList = new ArrayList<Obs>();
			resultantList.addAll(obsList1);
			resultantList.addAll(obsList2);
			resultantList.addAll(obsList3);
			resultantList.addAll(obsList4);
			for (Obs obs : resultantList) {
				for (Integer list : DiagnosisLists.getFeversList()) {
					if (obs.getValueCoded() != null && obs.getValueCoded().getConceptId().equals(list)
					        && obs.getObsDatetime().compareTo(context.getNow()) <= 0) {
						ans = "Y";
					}
					break;
				}
			}
			ret.put(pId, new SimpleResult(ans, this));
		}
		return ret;
	}
}
