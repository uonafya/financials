package org.openmrs.module.financials.reporting.calculation;

import org.openmrs.Patient;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Person;
import org.openmrs.Visit;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.ListResult;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyacore.calculation.CalculationUtils;
import org.openmrs.module.kenyacore.report.data.patient.definition.VisitsForPatientDataDefinition;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.metadatadeploy.MetadataUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RevisitPatientCalculation extends AbstractPatientCalculation {
	
	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues,
	        PatientCalculationContext context) {
		
		VisitsForPatientDataDefinition definition = new VisitsForPatientDataDefinition();
		CalculationResultMap data = CalculationUtils.evaluateWithReporting(definition, cohort, parameterValues, null,
		    context);
		CalculationResultMap ret = new CalculationResultMap();
		
		PatientIdentifierType opdNumber = MetadataUtils.existing(PatientIdentifierType.class,
		    CommonMetadata._PatientIdentifierType.PATIENT_CLINIC_NUMBER);
		
		for (Integer ptid : cohort) {
			String opdNumnberValue = "";
			ListResult result = (ListResult) data.get(ptid);
			List<Visit> visits = CalculationUtils.extractResultValues(result);
			List<Visit> visitsWithinAyear = new ArrayList<Visit>();
			for (Visit visit : visits) {
				if (visit.getStartDatetime().compareTo(getAyearFromToday(context.getNow())) >= 0
				        && visit.getStartDatetime().compareTo(context.getNow()) <= 0) {
					visitsWithinAyear.add(visit);
				}
			}
			if (visitsWithinAyear.size() > 1
			        && Context.getPatientService().getPatient(ptid).getPatientIdentifier(opdNumber) != null) {
				opdNumnberValue = Context.getPatientService().getPatient(ptid).getPatientIdentifier(opdNumber)
				        .getIdentifier();
			}
			
			ret.put(ptid, new SimpleResult(opdNumnberValue, this));
		}
		
		return ret;
	}
	
	private Date getAyearFromToday(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.YEAR, -1);
		return calendar.getTime();
		
	}
}
