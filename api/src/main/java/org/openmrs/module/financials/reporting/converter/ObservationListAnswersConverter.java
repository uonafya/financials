package org.openmrs.module.financials.reporting.converter;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Obs;
import org.openmrs.module.reporting.data.converter.DataConverter;

import java.util.*;

public class ObservationListAnswersConverter implements DataConverter {
	
	public ObservationListAnswersConverter() {
	}
	
	public Object convert(Object original) {
		Set<Obs> allObs = new HashSet<Obs>();
		if (original != null) {
			allObs.addAll((Collection<? extends Obs>) original);
		}
		
		List<String> allDiagnosis = new ArrayList<String>();
		if (allObs.isEmpty()) {
			return null;
		}
		for (Obs obs : allObs) {
			allDiagnosis.add(obs.getValueCoded().getDisplayString());
		}
		
		return StringUtils.join(allDiagnosis, "|");
	}
	
	public Class<?> getInputDataType() {
		return Obs.class;
	}
	
	public Class<?> getDataType() {
		return Object.class;
	}
}
