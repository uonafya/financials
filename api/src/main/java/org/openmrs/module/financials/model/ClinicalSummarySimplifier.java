package org.openmrs.module.financials.model;

public class ClinicalSummarySimplifier {
	
	private Integer conceptId;
	
	public Integer getConceptId() {
		return conceptId;
	}
	
	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}
	
	public String getConceptName() {
		return conceptName;
	}
	
	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}
	
	public Integer getListSize() {
		return listSize;
	}
	
	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}
	
	private String conceptName;
	
	private Integer listSize;
}
