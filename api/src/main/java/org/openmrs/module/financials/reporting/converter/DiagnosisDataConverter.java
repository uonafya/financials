package org.openmrs.module.financials.reporting.converter;

import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.data.converter.DataConverter;

public class DiagnosisDataConverter implements DataConverter {
	
	public DiagnosisDataConverter() {
		
	}
	
	public Object convert(Object obj) {
		
		Obs obs = (Obs) obj;
		if (obs == null) {
			return "N";
		}
		
		if (obs.getConcept().equals(Context.getConceptService().getConceptByUuid("160250AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))) {
			return "Final";
		} else if (obs.getConcept().equals(
		    Context.getConceptService().getConceptByUuid("160249AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))) {
			return "Provisional";
		} else {
			//do nothing
		}
		return null;
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
