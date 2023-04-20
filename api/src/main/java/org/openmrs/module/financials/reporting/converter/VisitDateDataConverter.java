package org.openmrs.module.financials.reporting.converter;

import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.reporting.data.converter.DataConverter;

import java.sql.Timestamp;
import java.util.Date;

public class VisitDateDataConverter implements DataConverter {
	
	@Override
	public Object convert(Object obj) {
		
		Timestamp value = (Timestamp) obj;
		if (value == null) {
			return "";
		}
		Date date = new Date();
		date.setTime(value.getTime());
		return FinancialsUtils.formatDateWithTime((date));
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
