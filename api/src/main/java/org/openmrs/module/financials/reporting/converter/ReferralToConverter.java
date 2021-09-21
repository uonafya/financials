package org.openmrs.module.financials.reporting.converter;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.data.converter.DataConverter;

public class ReferralToConverter implements DataConverter {
	
	public ReferralToConverter() {
		
	}
	
	public Object convert(Object obj) {
		
		Obs obs = (Obs) obj;
		
		if (obs == null) {
			return "";
		}
		Concept concept = obs.getValueCoded();
		
		if (concept == null) {
			return "";
		} else if (concept.equals(Context.getConceptService().getConceptByUuid("4fcf003e-71cf-47a5-a967-47d24aa61092"))) {
			return "4";
		} else {
			return "3";
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
