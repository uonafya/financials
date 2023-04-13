package org.openmrs.module.financials.page.controller.reports;

import org.openmrs.Program;
import org.openmrs.module.financials.report.EhrReportManager;
import org.openmrs.module.kenyacore.program.ProgramDescriptor;
import org.openmrs.module.appframework.domain.AppDescriptor;
import org.openmrs.module.kenyacore.program.ProgramManager;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportManager;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.openmrs.ui.framework.page.PageRequest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@AppPage("financials.ehr.reports")
public class EhrReportsHomePageController {
	
	public void controller(PageModel model, UiUtils ui, @SpringBean EhrReportManager reportManager,
	        @SpringBean ProgramManager programManager, @SpringBean KenyaUiUtils kenyaUi, PageRequest pageRequest) {
		
		AppDescriptor currentApp = kenyaUi.getCurrentApp(pageRequest);
		
		Map<String, List<SimpleObject>> reportsByProgram = new LinkedHashMap<String, List<SimpleObject>>();
		
		List<SimpleObject> common = new ArrayList<SimpleObject>();
		for (ReportDescriptor report : reportManager.getEhrCommonReports(currentApp)) {
			common.add(ui.simplifyObject(report));
		}
		reportsByProgram.put("Facility Summaries", common);
		
		for (ProgramDescriptor programDescriptor : programManager.getAllProgramDescriptors()) {
			Program program = programDescriptor.getTarget();
			List<ReportDescriptor> reports = reportManager.getEhrProgramReports(currentApp, program);
			
			if (reports.size() > 0) {
				List<SimpleObject> forProgram = new ArrayList<SimpleObject>();
				
				// We're not calling ui.simplifyCollection because it doesn't play well with subclasses
				for (ReportDescriptor report : reports) {
					forProgram.add(ui.simplifyObject(report));
				}
				
				reportsByProgram.put(program.getName(), forProgram);
			}
		}
		
		model.addAttribute("reportsByProgram", reportsByProgram);
	}
}
