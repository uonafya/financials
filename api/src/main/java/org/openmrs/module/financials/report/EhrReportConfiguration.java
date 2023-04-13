/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.financials.report;

import org.openmrs.module.kenyacore.AbstractContentConfiguration;
import org.openmrs.module.kenyacore.program.ProgramDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;

import java.util.Map;
import java.util.Set;

public class EhrReportConfiguration extends AbstractContentConfiguration {
	
	private Set<ReportDescriptor> ehrCommonReports;
	
	private Map<ProgramDescriptor, Set<ReportDescriptor>> ehrProgramReports;
	
	/**
	 * Gets the common reports
	 * 
	 * @return the report descriptors
	 */
	public Set<ReportDescriptor> getEhrCommonReports() {
		return ehrCommonReports;
	}
	
	/**
	 * Sets the common reports
	 * 
	 * @param commonReports the report descriptors
	 */
	public void setEhrCommonReports(Set<ReportDescriptor> commonReports) {
		this.ehrCommonReports = commonReports;
	}
	
	/**
	 * Gets the program specific reports
	 * 
	 * @return the map of program and report descriptors
	 */
	public Map<ProgramDescriptor, Set<ReportDescriptor>> getEhrProgramReports() {
		return ehrProgramReports;
	}
	
	/**
	 * Sets the program specific reports
	 * 
	 * @param programReports the map of program and report descriptors
	 */
	public void setEhrProgramReports(Map<ProgramDescriptor, Set<ReportDescriptor>> programReports) {
		this.ehrProgramReports = programReports;
	}
}
