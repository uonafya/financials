package org.openmrs.module.financials.reporting.converter;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.data.converter.DataConverter;

public class ReferralFromConverter implements DataConverter {
	
	public ReferralFromConverter() {
		
	}
	
	public Object convert(Object obj) {
		
		Obs obs = (Obs) obj;
		
		if (obs == null) {
			return "";
		}
		Concept concept = obs.getValueCoded();
		
		if (concept == null) {
			return "";
		} else if (concept.equals(Context.getConceptService().getConceptByUuid("1537AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))) {
			return "1";
		} else {
			return "2";
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
