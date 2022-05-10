package org.openmrs.module.financials.utils;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsUtil;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public static List<Concept> registrationFeeConcepts() {
		return Arrays.asList(getConcept("19e1f7a9-52b4-4975-804d-5c74445be316"),
		    getConcept("caf177ab-8d96-45bb-8ab4-f66507f11b2b"), getConcept("cecc12d2-4308-4567-9bd1-92011b1648df"));
	}
	
	public static List<Concept> revisitFeeConcepts() {
		return Arrays.asList(getConcept("81f2e941-d724-4794-98bf-8764b593c838"),
		    getConcept("509a7862-de5a-47e4-8837-4851024dbccf"), getConcept("81cfb05a-998d-46f6-b0b0-8d26e27840ae"),
		    getConcept("a6deb3a8-e9a6-41fc-a221-469b8a364f9b"));
	}
	
	public static List<Concept> specialClinicFeeConcepts() {
		return Arrays.asList(getConcept("430fc46d-94bb-4fbc-b7bd-894b7cc98058"),
		    getConcept("7da33864-3183-4764-a1e8-d86bca84c9ef"));
	}
	
	/**
	 * @return the Concept that matches the passed uuid, name, source:code mapping, or primary key
	 *         id
	 */
	public static Concept getConcept(String lookup) {
		Concept c = Context.getConceptService().getConceptByUuid(lookup);
		if (c == null) {
			c = Context.getConceptService().getConceptByName(lookup);
		}
		if (c == null) {
			try {
				String[] split = lookup.split("\\:");
				if (split.length == 2) {
					c = Context.getConceptService().getConceptByMapping(split[1], split[0]);
				}
			}
			catch (Exception e) {}
		}
		if (c == null) {
			try {
				c = Context.getConceptService().getConcept(Integer.parseInt(lookup));
			}
			catch (Exception e) {
				throw new RuntimeException(e.toString());
			}
		}
		return c;
	}
}
