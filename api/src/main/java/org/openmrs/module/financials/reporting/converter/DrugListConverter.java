package org.openmrs.module.financials.reporting.converter;

import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.hospitalcore.model.OpdDrugOrder;
import org.openmrs.module.reporting.data.converter.DataConverter;

public class DrugListConverter implements DataConverter {
	
	public DrugListConverter() {
		
	}
	
	public Object convert(Object obj) {
		
		SimpleResult simpleResult = (SimpleResult) obj;
		
		return simpleResult;
	}
	
	@Override
	public Class<?> getInputDataType() {
		return OpdDrugOrder.class;
	}
	
	@Override
	public Class<?> getDataType() {
		return OpdDrugOrder.class;
	}
}
