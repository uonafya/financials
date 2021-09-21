package org.openmrs.module.financials.reporting.converter;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.module.hospitalcore.model.OpdDrugOrder;
import org.openmrs.module.reporting.data.converter.DataConverter;

public class OutcomeConverter implements DataConverter {
	
	public OutcomeConverter() {
		
	}
	
	public Object convert(Object obj) {
		
		Obs obs = (Obs) obj;
		String results = "";
		if (obs == null) {
			return "";
		}
		Concept concept = obs.getValueCoded();
		
		if (concept == null) {
			return "";
		} else {
			return "D";
		}
	}
	
	@Override
	public Class<?> getInputDataType() {
		return String.class;
	}
	
	@Override
	public Class<?> getDataType() {
		return String.class;
	}
}
