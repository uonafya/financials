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
		if (obs == null) {
			return "N";
		}
		Concept concept = obs.getValueCoded();
		
		if (concept.equals(Context.getConceptService().getConceptByUuid("d5ea1533-7346-4e0b-8626-9bff6cd183b2"))) {
			return "R";
		} else {
			return "N";
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
