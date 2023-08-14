package org.openmrs.module.financials.reporting.converter;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Obs;
import org.openmrs.module.reporting.data.converter.DataConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ObsValueListConverter implements DataConverter {
	
	public Object convert(Object original) {
		List<Obs> o = new ArrayList<Obs>((Collection<? extends Obs>) original);
		List<String> conceptNames = new ArrayList<String>();
		if (o.isEmpty()) {
			return null;
		}
		for (Obs obs : o) {
			conceptNames.add(obs.getConcept().getDisplayString());
		}
		return StringUtils.join(",", conceptNames);
	}
	
	@Override
	public Class<?> getInputDataType() {
		return Obs.class;
	}
	
	@Override
	public Class<?> getDataType() {
		return Object.class;
	}
}