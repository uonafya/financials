package org.openmrs.module.financials.reporting.converter;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.data.converter.DataConverter;

public class VisitTypeConverter implements DataConverter {
	
	public VisitTypeConverter() {
		
	}
	
	public Object convert(Object obj) {
		
		Obs obs = (Obs) obj;
		String results = "";
		if (obs == null) {
			return "";
		}
		Concept concept = obs.getValueCoded();
		
		if (concept.equals(Context.getConceptService().getConceptByUuid("164180AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))) {
			return "N";
		} else {
			return "R";
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
