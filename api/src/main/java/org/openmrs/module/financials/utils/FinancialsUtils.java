package org.openmrs.module.financials.utils;

import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PersonName;
import org.openmrs.util.OpenmrsUtil;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FinancialsUtils {
	
	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Date getStartOfDay(Date date) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
		return calendar.getTime();
	}
	
	public static Date getEndOfDay(Date date) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
		return calendar.getTime();
	}
	
	/**
	 * Formats a person name
	 * 
	 * @param name the name
	 * @return the string value
	 * @should format voided person as empty string
	 */
	public static String formatPersonName(PersonName name) {
		if (name != null) {
			List<String> items = new ArrayList<String>();
			
			if (name.getFamilyName() != null) {
				items.add(name.getFamilyName() + " ");
			}
			if (name.getGivenName() != null) {
				items.add(name.getGivenName());
			}
			if (name.getMiddleName() != null) {
				items.add(name.getMiddleName());
			}
			return OpenmrsUtil.join(items, " ");
		}
		return "";
	}
	
	public static String formatPatientIdentifier(Patient patient) {
		String identifier = "";
		if (patient != null && patient.getIdentifiers() != null) {
			for (PatientIdentifier patientIdentifier : patient.getIdentifiers()) {
				identifier = patientIdentifier.getIdentifier();
			}
		}
		return identifier;
	}
	
	public static String formatDateWithTime(Date date) {
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return formatter.format(date);
	}
}
