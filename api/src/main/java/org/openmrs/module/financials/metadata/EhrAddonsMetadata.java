package org.openmrs.module.financials.metadata;

import org.openmrs.ConceptClass;
import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.openmrs.module.financials.EhrAddonsConstants;
import org.openmrs.module.financials.Moh717Constants;
import org.openmrs.module.financials.metadata.ConfigurableMetadataLookupException;

public class EhrAddonsMetadata {
	
	/**
	 * Diagnosis class ID 4
	 * 
	 * @return ConceptClass
	 */
	public static ConceptClass getDiagnosisConceptClass() {
		return Context.getConceptService().getConceptClassByUuid(EhrAddonsConstants._EhrAddOnConceptClasses.DIAGNOSIS_CLASS);
	}
	
	public static EncounterType getAdultsInitialEncounterType() {
		return getEncounterType(Moh717Constants.ADULTINITIAL_OUTPATIENT);
	}
	
	public static EncounterType getAdultReturnEncounterType() {
		return getEncounterType(Moh717Constants.ADULTRETURN_OUTPATIENT);
	}
	
	// encounter type 3
	public static EncounterType getPedsInitialEncounterType() {
		return getEncounterType(Moh717Constants.PEDSINITIAL_OUTPATIENT);
	}
	
	public static EncounterType getPedsReturnEncounterType() {
		return getEncounterType(Moh717Constants.PEDSRETURN_OUTPATIENT);
	}
	
	public static EncounterType getCheckInEncounterType() {
		return getEncounterType(Moh717Constants.CHECKIN_ENCOUNTER);
	}
	
	public static EncounterType getAncEncounterType() {
		return getEncounterType(Moh717Constants.ANC_ENCOUNTER);
	}
	
	public static EncounterType getRegReturnEncounterType() {
		return getEncounterType(Moh717Constants.REGREVISIT_OUTPATIENT);
	}
	
	public static EncounterType getOpdEncounterType() {
		return getEncounterType(Moh717Constants.OPDENCOUNTER_OUTPATIENT);
	}
	
	/**
	 * @return the EncounterType that matches the passed uuid, name, or primary key id
	 */
	public static EncounterType getEncounterType(String lookup) {
		EncounterType et = Context.getEncounterService().getEncounterTypeByUuid(lookup);
		if (et == null) {
			et = Context.getEncounterService().getEncounterType(lookup);
		}
		if (et == null) {
			try {
				et = Context.getEncounterService().getEncounterType(Integer.parseInt(lookup));
			}
			catch (Exception e) {}
		}
		if (et == null) {
			throw new ConfigurableMetadataLookupException("Unable to find EncounterType using key: " + lookup);
		}
		
		return et;
	}
}
