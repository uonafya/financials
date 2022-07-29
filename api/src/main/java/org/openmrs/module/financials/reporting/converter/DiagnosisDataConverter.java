package org.openmrs.module.financials.reporting.converter;

import org.openmrs.module.reporting.data.converter.DataConverter;

public class DiagnosisDataConverter implements DataConverter {
	
	public DiagnosisDataConverter() {
		
	}
	
	public Object convert(Object obj) {
		
		Integer results = (Integer) obj;
		if (results == null) {
			return "";
		}
		
		if (results.equals(160250)) {
			return "Final";
		} else if (results.equals(160249)) {
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
