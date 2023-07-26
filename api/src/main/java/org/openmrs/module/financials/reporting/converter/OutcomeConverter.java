package org.openmrs.module.financials.reporting.converter;

import org.openmrs.module.reporting.data.converter.DataConverter;

public class OutcomeConverter implements DataConverter {
	
	public OutcomeConverter() {
		
	}
	
	public Object convert(Object obj) {
		
		//String data = (String) obj;
		
		if (obj == null) {
			return "A";
		}
		
		else {
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
