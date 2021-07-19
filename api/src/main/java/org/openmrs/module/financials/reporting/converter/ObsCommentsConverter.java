package org.openmrs.module.financials.reporting.converter;

import org.openmrs.Obs;
import org.openmrs.module.reporting.data.converter.DataConverter;

public class ObsCommentsConverter implements DataConverter {
	
	public Object convert(Object original) {
		Obs o = (Obs) original;
		
		if (o == null) {
			return null;
		} else if (o.getComment() != null) {
			return o.getComment();
		}
		return null;
	}
	
	public Class<?> getInputDataType() {
		return Obs.class;
	}
	
	public Class<?> getDataType() {
		return Object.class;
	}
}
