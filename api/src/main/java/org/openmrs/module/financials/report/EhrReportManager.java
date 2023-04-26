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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Program;
import org.openmrs.api.context.Context;
import org.openmrs.module.appframework.domain.AppDescriptor;
import org.openmrs.module.kenyacore.ContentManager;
import org.openmrs.module.kenyacore.program.ProgramDescriptor;
import org.openmrs.module.kenyacore.program.ProgramManager;
import org.openmrs.module.kenyacore.report.CalculationReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyacore.report.builder.CalculationReportBuilder;
import org.openmrs.module.kenyacore.report.builder.ReportBuilder;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class EhrReportManager implements ContentManager {
	
	protected static final Log log = LogFactory.getLog(EhrReportManager.class);
	
	private Map<String, ReportDescriptor> ehrReports = new LinkedHashMap<String, ReportDescriptor>();
	
	private List<ReportDescriptor> ehrCommonReports = new ArrayList<ReportDescriptor>();
	
	private Map<String, ReportBuilder> builders = new HashMap<String, ReportBuilder>();
	
	@Autowired
	private ProgramManager programManager;
	
	@Autowired
	@Qualifier("kenyacore.genericCalcReportBuilder")
	private CalculationReportBuilder calculationReportBuilder;
	
	/**
	 * @see org.openmrs.module.kenyacore.ContentManager#getPriority()
	 */
	@Override
	public int getPriority() {
		return 1001;
	}
	
	/**
	 * @see org.openmrs.module.kenyacore.ContentManager#refresh()
	 */
	@Override
	public synchronized void refresh() {
		ehrReports.clear();
		
		// Process ehr report descriptor components
		for (ReportDescriptor descriptor : Context.getRegisteredComponents(ReportDescriptor.class)) {
			for (AppDescriptor appDescriptor : descriptor.getApps()) {
				if (appDescriptor.getId().equals("financials.ehr.reports")) {
					if (ehrReports.containsKey(descriptor.getTargetUuid())) {
						throw new RuntimeException("Report " + descriptor.getTargetUuid() + " already registered");
					}
					
					ehrReports.put(descriptor.getTargetUuid(), descriptor);
					
					log.debug("Registered report descriptor: " + descriptor.getId());
				}
			}
		}
		
		// Process form configuration beans
		for (EhrReportConfiguration configuration : Context.getRegisteredComponents(EhrReportConfiguration.class)) {
			// Register ehr common reports
			if (configuration.getEhrCommonReports() != null) {
				ehrCommonReports.addAll(configuration.getEhrCommonReports());
			}
			
			// Register additional program specific reports
			if (configuration.getEhrProgramReports() != null) {
				Map<ProgramDescriptor, Set<ReportDescriptor>> programReports = configuration.getEhrProgramReports();
				for (ProgramDescriptor programDescriptor : programReports.keySet()) {
					for (ReportDescriptor report : programReports.get(programDescriptor)) {
						programDescriptor.addReport(report);
					}
				}
			}
		}
		
		// Process report builder components
		for (ReportBuilder builder : Context.getRegisteredComponents(ReportBuilder.class)) {
			Builds builds = builder.getClass().getAnnotation(Builds.class);
			if (builds != null) {
				for (String reportId : builds.value()) {
					String[] reportIdValueSplit = reportId.split("\\.");
					if (reportIdValueSplit[0].equals("financials")) {
						builders.put(reportId, builder);
					}
				}
			}
		}
		
		// Build definitions if builder available
		for (ReportDescriptor report : getAllEhrReportDescriptors()) {
			// We don't use usual load mechanism because we don't want to de-serialise the definition
			System.out.println("Report discriptors in the build>>" + report.getId());
			ReportDefinition existingDefinition = getReportDefinitionStub(report.getTargetUuid());
			
			ReportBuilder builder = getEhrReportBuilder(report);
			
			if (builder != null) {
				ReportDefinition definition = builder.build(report);
				// Steal id of existing definition
				if (existingDefinition != null) {
					definition.setId(existingDefinition.getId());
				}
				
				definition.setUuid(report.getTargetUuid());
				
				Context.getService(ReportDefinitionService.class).saveDefinition(definition);
			} else if (existingDefinition == null) {
				throw new RuntimeException("Report " + report.getId() + " has no builder or existing definition");
			}
		}
	}
	
	/**
	 * Gets a report descriptor for the given report definition
	 * 
	 * @param definition the report definition
	 * @return the report descriptor
	 */
	public ReportDescriptor getEhrReportDescriptor(ReportDefinition definition) {
		return ehrReports.get(definition.getUuid());
	}
	
	/**
	 * Gets all report descriptors
	 * 
	 * @@return the list of report descriptors
	 */
	public List<ReportDescriptor> getAllEhrReportDescriptors() {
		return new ArrayList<ReportDescriptor>(ehrReports.values());
	}
	
	/**
	 * Gets all common (non program specific) reports
	 * 
	 * @@return the list of reports
	 */
	public List<ReportDescriptor> getEhrCommonReports(AppDescriptor app) {
		return filterReports(ehrCommonReports, app);
	}
	
	/**
	 * Gets program specific reports
	 * 
	 * @@return the list of reports
	 */
	public List<ReportDescriptor> getEhrProgramReports(AppDescriptor app, Program program) {
		ProgramDescriptor programDescriptor = programManager.getProgramDescriptor(program);
		System.out.println("The program discriptor is >>" + programDescriptor.getId());
		if (programDescriptor.getReports() != null) {
			return filterReports(programDescriptor.getReports(), app);
		}
		return Collections.emptyList();
	}
	
	/**
	 * Gets a report builder for the given report
	 * 
	 * @param report the report
	 * @return the report builder
	 */
	protected ReportBuilder getEhrReportBuilder(ReportDescriptor report) {
		// Look for specific builder
		ReportBuilder builder = builders.get(report.getId());
		
		// Can we use the generic calculation report builder?
		if (builder == null && report instanceof CalculationReportDescriptor) {
			builder = calculationReportBuilder;
		}
		
		return builder;
	}
	
	/**
	 * Filters the given collection of reports to those applicable for the given application
	 * 
	 * @param app the application
	 * @return the filtered reports
	 */
	protected List<ReportDescriptor> filterReports(Collection<ReportDescriptor> descriptors, AppDescriptor app) {
		List<ReportDescriptor> filtered = new ArrayList<ReportDescriptor>();
		for (ReportDescriptor descriptor : descriptors) {
			System.out.println("The report discriptors are as follows>>" + descriptor.getName());
			
			// Filter by app id
			if (app != null && descriptor.getApps() != null && !descriptor.getApps().contains(app)) {
				continue;
			}
			
			filtered.add(descriptor);
		}
		
		return filtered;
	}
	
	/**
	 * Gets a "stub" of a report definition from its UUID. We use this to avoid unnecessarily
	 * de-serializing definitions
	 * 
	 * @param uuid the UUID
	 * @return the stub
	 */
	protected ReportDefinition getReportDefinitionStub(String uuid) {
		String query = "SELECT serialized_object_id, uuid FROM serialized_object WHERE uuid = '" + uuid + "'";
		List<List<Object>> result = Context.getAdministrationService().executeSQL(query, true);
		if (result.size() > 0) {
			ReportDefinition rd = new ReportDefinition();
			rd.setId((Integer) result.get(0).get(0));
			rd.setUuid((String) result.get(0).get(1));
			return rd;
		}
		return null;
	}
	
}
