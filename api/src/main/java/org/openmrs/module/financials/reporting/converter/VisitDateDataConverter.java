package org.openmrs.module.financials.reporting.converter;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.module.financials.utils.FinancialsUtils;
import org.openmrs.module.reporting.data.converter.DataConverter;

import java.text.ParseException;

public class VisitDateDataConverter implements DataConverter {
	
	@Override
	public Object convert(Object obj) {
		
		String value = (String) obj;
		if (StringUtils.isBlank(value)) {
			return "";
		}
		try {
			return FinancialsUtils.formatDateFromString((String) obj);
		}
		catch (ParseException e) {
			throw new RuntimeException(e);
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
