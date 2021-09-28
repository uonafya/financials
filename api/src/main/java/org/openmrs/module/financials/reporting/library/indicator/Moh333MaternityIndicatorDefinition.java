package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.AdolescentsNewHIVPositiveAtMaternityCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.AssistedVaginalDeliveriesCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.BreechDeliveriesCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.CaesareanSectionsCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.HivPositiveDeliveriesCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.InfantARVProphylaxisAtMaternityCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.InitialTestAtMaternityCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.LiveBirthsCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.MaternityAliveCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.MaternityClientsWithAPHCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.MaternityClientsWithEclampsiaCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.MaternityClientsWithObstructedLabourCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.MaternityClientsWithPPHCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.MaternityClientsWithRapturedUterusCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.MaternityClientsWithSepsisCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.MaternityDeathsCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.NormalDeliveriesCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.PositiveResultsAtMaternityCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.PreTermBabiesCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.StartedHAARTAtMaternityCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.StillBirthsCohortDefinition;
import org.openmrs.module.kenyaemr.reporting.cohort.definition.pmtct.maternity.UnderWeightBabiesCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.stereotype.Component;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;

@Component
public class Moh333MaternityIndicatorDefinition {
	
	public CohortIndicator clientsWithAPH() {
		return cohortIndicator("Clients with APH",
		    ReportUtils.<CohortDefinition> map(new MaternityClientsWithAPHCohortDefinition(), ""));
	}
	
	public CohortIndicator clientsWithPPH() {
		return cohortIndicator("Clients with PPH",
		    ReportUtils.<CohortDefinition> map(new MaternityClientsWithPPHCohortDefinition(), ""));
	}
	
	public CohortIndicator clientsWithEclampsia() {
		return cohortIndicator("Clients with Eclampsia",
		    ReportUtils.<CohortDefinition> map(new MaternityClientsWithEclampsiaCohortDefinition(), ""));
	}
	
	public CohortIndicator clientsWithRapturedUterus() {
		return cohortIndicator("Clients with raptured uterus",
		    ReportUtils.<CohortDefinition> map(new MaternityClientsWithRapturedUterusCohortDefinition(), ""));
	}
	
	public CohortIndicator clientsWithObstructedLabour() {
		return cohortIndicator("Clients with obstructed Labour",
		    ReportUtils.<CohortDefinition> map(new MaternityClientsWithObstructedLabourCohortDefinition(), ""));
	}
	
	public CohortIndicator clientsWithSepsis() {
		return cohortIndicator("Clients with Sepsis",
		    ReportUtils.<CohortDefinition> map(new MaternityClientsWithSepsisCohortDefinition(), ""));
	}
	
	public CohortIndicator clientsAlive() {
		return cohortIndicator("Clients Alive", ReportUtils.<CohortDefinition> map(new MaternityAliveCohortDefinition(), ""));
	}
	
	public CohortIndicator clientsDead() {
		return cohortIndicator("Clients Dead", ReportUtils.<CohortDefinition> map(new MaternityDeathsCohortDefinition(), ""));
	}
	
	public CohortIndicator preTermBabies() {
		return cohortIndicator("Pre-Term Babies",
		    ReportUtils.<CohortDefinition> map(new PreTermBabiesCohortDefinition(), ""));
	}
	
	public CohortIndicator underWeightBabies() {
		return cohortIndicator("Under Weight Babies",
		    ReportUtils.<CohortDefinition> map(new UnderWeightBabiesCohortDefinition(), ""));
	}
	
	public CohortIndicator liveBirths() {
		return cohortIndicator("Live Births", ReportUtils.<CohortDefinition> map(new LiveBirthsCohortDefinition(), ""));
	}
	
	public CohortIndicator stillBirths() {
		return cohortIndicator("Still Births", ReportUtils.<CohortDefinition> map(new StillBirthsCohortDefinition(), ""));
	}
	
	public CohortIndicator initialTestAtMaternity() {
		return cohortIndicator("Initial Test at Maternity",
		    ReportUtils.<CohortDefinition> map(new InitialTestAtMaternityCohortDefinition(), ""));
	}
	
	public CohortIndicator positiveResultsAtMaternity() {
		return cohortIndicator("Positive Results At Maternity",
		    ReportUtils.<CohortDefinition> map(new PositiveResultsAtMaternityCohortDefinition(), ""));
	}
	
	public CohortIndicator hivPositiveDeliveries() {
		return cohortIndicator("HIV Positive Deliveries",
		    ReportUtils.<CohortDefinition> map(new HivPositiveDeliveriesCohortDefinition(), ""));
	}
	
	public CohortIndicator adolescentsNewHivPositiveAtMaternity() {
		return cohortIndicator("Adolescents (10-19 Years) New HIV+ Maternity ",
		    ReportUtils.<CohortDefinition> map(new AdolescentsNewHIVPositiveAtMaternityCohortDefinition(), ""));
	}
	
	public CohortIndicator startedHAARTMaternity() {
		return cohortIndicator("Started on HAART at Maternity",
		    ReportUtils.<CohortDefinition> map(new StartedHAARTAtMaternityCohortDefinition(), ""));
	}
	
	public CohortIndicator infantARVProphylaxisMaternity() {
		return cohortIndicator("Infant ARV Prophylaxis Maternity",
		    ReportUtils.<CohortDefinition> map(new InfantARVProphylaxisAtMaternityCohortDefinition(), ""));
	}
	
	public CohortIndicator normalDeliveries() {
		return cohortIndicator("Normal Deliveries",
		    ReportUtils.<CohortDefinition> map(new NormalDeliveriesCohortDefinition(), ""));
	}
	
	public CohortIndicator caesareanSections() {
		return cohortIndicator("Caesarean Sections",
		    ReportUtils.<CohortDefinition> map(new CaesareanSectionsCohortDefinition(), ""));
	}
	
	public CohortIndicator breechDeliveries() {
		return cohortIndicator("Breech Deliveries",
		    ReportUtils.<CohortDefinition> map(new BreechDeliveriesCohortDefinition(), ""));
	}
	
	public CohortIndicator assistedVaginalDeliveries() {
		return cohortIndicator("Assisted Vaginal Deliveries",
		    ReportUtils.<CohortDefinition> map(new AssistedVaginalDeliveriesCohortDefinition(), ""));
	}
}
