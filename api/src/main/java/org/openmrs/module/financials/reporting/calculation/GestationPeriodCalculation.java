package org.openmrs.module.financials.reporting.calculation;

import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyacore.calculation.Calculations;
import org.openmrs.module.kenyaemr.Dictionary;
import org.openmrs.module.kenyaemr.calculation.EmrCalculationUtils;
import org.openmrs.module.kenyaemr.metadata.MchMetadata;
import org.openmrs.module.metadatadeploy.MetadataUtils;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class GestationPeriodCalculation extends AbstractPatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> map,
	        PatientCalculationContext context) {
		
		CalculationResultMap ret = new CalculationResultMap();
		
		Integer period = (Integer) map.get("period");
		boolean found = false;
		
		CalculationResultMap lastEncounter = Calculations.lastEncounter(
		    MetadataUtils.existing(EncounterType.class, MchMetadata._EncounterType.MCHMS_ENROLLMENT), cohort, context);
		CalculationResultMap lastLmpObs = Calculations.lastObs(Dictionary.getConcept(Dictionary.LAST_MONTHLY_PERIOD),
		    cohort, context);
		for (Integer pId : cohort) {
			
			Encounter lastAncEncounter = EmrCalculationUtils.encounterResultForPatient(lastEncounter, pId);
			Date lmpDate = EmrCalculationUtils.datetimeObsResultForPatient(lastLmpObs, pId);
			if (lastAncEncounter != null && lastAncEncounter.getEncounterDatetime() != null && lmpDate != null
			        && period != null) {
				DateTime encounterDate = new DateTime(lastAncEncounter.getEncounterDatetime());
				DateTime lmp = new DateTime(lmpDate);
				
				int weeks = Weeks.weeksBetween(encounterDate, lmp).getWeeks();
				if (weeks <= period) {
					found = true;
				}
			}
			
			ret.put(pId, new SimpleResult(found, this));
		}
		
		return ret;
	}
}
