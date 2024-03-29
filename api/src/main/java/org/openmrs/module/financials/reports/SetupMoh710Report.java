package org.openmrs.module.financials.reports;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.PatientIdentifierType;
import org.openmrs.module.financials.reporting.library.dimesions.EhrAddonDimesion;
import org.openmrs.module.financials.reporting.library.indicator.Moh710IndicatorDefinition;
import org.openmrs.module.kenyacore.report.ReportDescriptor;
import org.openmrs.module.kenyacore.report.ReportUtils;
import org.openmrs.module.kenyacore.report.builder.AbstractReportBuilder;
import org.openmrs.module.kenyacore.report.builder.Builds;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.kenyaemr.reporting.ColumnParameters;
import org.openmrs.module.kenyaemr.reporting.EmrReportingUtils;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.data.DataDefinition;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.reporting.data.converter.ObjectFormatter;
import org.openmrs.module.reporting.data.patient.definition.ConvertedPatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.ConvertedPersonDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Builds({ "ehraddons.common.report.moh710" })
public class SetupMoh710Report extends AbstractReportBuilder {
	
	protected static final Log log = LogFactory.getLog(SetupMoh710Report.class);
	
	private Moh710IndicatorDefinition moh710Indicators;
	
	private EhrAddonDimesion commonDimensions;
	
	@Autowired
	public SetupMoh710Report(Moh710IndicatorDefinition moh710Indicators, EhrAddonDimesion commonDimensions) {
		this.moh710Indicators = moh710Indicators;
		this.commonDimensions = commonDimensions;
	}
	
	/**
	 * @see org.openmrs.module.kenyacore.report.builder.AbstractReportBuilder#getParameters(org.openmrs.module.kenyacore.report.ReportDescriptor)
	 */
	@Override
	protected List<Parameter> getParameters(ReportDescriptor descriptor) {
		return Arrays.asList(new Parameter("startDate", "Start Date", Date.class), new Parameter("endDate", "End Date",
		        Date.class));
	}
	
	/**
	 * @see org.openmrs.module.kenyacore.report.builder.AbstractReportBuilder#buildDataSets(org.openmrs.module.kenyacore.report.ReportDescriptor,
	 *      org.openmrs.module.reporting.report.definition.ReportDefinition)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<Mapped<DataSetDefinition>> buildDataSets(ReportDescriptor descriptor, ReportDefinition report) {
		return Arrays.asList(ReportUtils.map(immunizationsDataSet(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	/**
	 * Creates the dataset for section #1: Immunizations
	 * 
	 * @return the dataset
	 */
	protected DataSetDefinition immunizationsDataSet() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		dsd.setName("Immunizations");
		dsd.setDescription("MOH 710 Immunizations");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		DataConverter nameFormatter = new ObjectFormatter("{familyName}, {givenName} {middleName}");
		DataDefinition nameDef = new ConvertedPersonDataDefinition("name", new PreferredNameDataDefinition(), nameFormatter);
		PatientIdentifierType upn = MetadataUtils.existing(PatientIdentifierType.class,
		    HivMetadata._PatientIdentifierType.UNIQUE_PATIENT_NUMBER);
		DataConverter identifierFormatter = new ObjectFormatter("{identifier}");
		DataDefinition identifierDef = new ConvertedPatientDataDefinition("identifier", new PatientIdentifierDataDefinition(
		        upn.getName(), upn), identifierFormatter);
		
		dsd.addDimension("age", ReportUtils.map(commonDimensions.moh710AgeGroups(), "onDate=${endDate}"));
		ColumnParameters infantLess_1 = new ColumnParameters(null, "<1", "age=<1");
		ColumnParameters infantAtleast_1 = new ColumnParameters(null, ">=1", "age=>=1");
		
		ColumnParameters childBtw18mAnd24m = new ColumnParameters(null, "18-24", "age=18-24");
		ColumnParameters childOver2y = new ColumnParameters(null, ">2", "age=>2");
		
		List<ColumnParameters> moh710Disaggregations = Arrays.asList(infantLess_1, infantAtleast_1);
		List<ColumnParameters> moh710DisaggregationsMR = Arrays.asList(childBtw18mAnd24m, childOver2y);
		
		String indParams = "startDate=${startDate},endDate=${endDate}";
		
		EmrReportingUtils.addRow(dsd, "BCG", "Given BCG", ReportUtils.map(moh710Indicators.givenBCGVaccine(), indParams),
		    moh710Disaggregations, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "OPV-0", "Given OPV at birth",
		    ReportUtils.map(moh710Indicators.givenOPV(), indParams), moh710Disaggregations, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "OPV-1", "Given OPV 1", ReportUtils.map(moh710Indicators.givenOPV1(), indParams),
		    moh710Disaggregations, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "OPV-2", "Given OPV 2", ReportUtils.map(moh710Indicators.givenOPV2(), indParams),
		    moh710Disaggregations, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "OPV-3", "Given OPV 3", ReportUtils.map(moh710Indicators.givenOPV3(), indParams),
		    moh710Disaggregations, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "IPV", "Given IPV", ReportUtils.map(moh710Indicators.givenIpv(), indParams),
		    moh710Disaggregations, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "DHH-1", "Given Dpt-Hep-Hib 1",
		    ReportUtils.map(moh710Indicators.givenDptHepHibVaccine1(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "DHH-2", "Given Dpt-Hep-Hib 2",
		    ReportUtils.map(moh710Indicators.givenDptHepHibVaccine2(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "DHH-3", "Given Dpt-Hep-Hib 3",
		    ReportUtils.map(moh710Indicators.givenDptHepHibVaccine3(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "PCV-1", "Given Pneumococcal 1",
		    ReportUtils.map(moh710Indicators.givenPneumococcal1Vaccine(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "PCV-2", "Given Pneumococcal 2",
		    ReportUtils.map(moh710Indicators.givenPneumococcal2Vaccine(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "PCV-3", "Given Pneumococcal 3",
		    ReportUtils.map(moh710Indicators.givenPneumococcal3Vaccine(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "ROTA-1", "Given Rota 1",
		    ReportUtils.map(moh710Indicators.givenRota1VirusVaccine(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "ROTA-2", "Given Rota 2",
		    ReportUtils.map(moh710Indicators.givenRota2VirusVaccine(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "VA6M", "Given Vitamin A at 6 Months",
		    ReportUtils.map(moh710Indicators.givenVitAAt6MAge(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "YF", "Given Yellow Fever",
		    ReportUtils.map(moh710Indicators.givenYellowFeverVaccine(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "MR-1", "Given Measles-Rubella 1 vaccine",
		    ReportUtils.map(moh710Indicators.givenMeaslesRubella1Vaccine(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "FIC", "Fully immunized children",
		    ReportUtils.map(moh710Indicators.fullyImmunized(), indParams), moh710Disaggregations, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "VA-1Y", "Vitamin A at 1 years (200,000IU)",
		    ReportUtils.map(moh710Indicators.givenVitAAt12Months(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "VA-1.5Y", "Vitamin A at 1 1/2 years(200,000 IU)",
		    ReportUtils.map(moh710Indicators.givenVitAAt18Months(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "VA-2Y-5Y", "Vitamin A at 2 years to 5 years (200,000IU)",
		    ReportUtils.map(moh710Indicators.givenVitAAt2To5Years(), indParams), moh710Disaggregations,
		    Arrays.asList("01", "02"));
		//EmrReportingUtils.addRow(dsd,"VAS", "Vitamin A Supplemental Lactating Mothers(200,000 IU)", ReportUtils.map(moh710Indicators.givenVitASupplemental(), indParams),moh710Disaggregations, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "MR-2", "Measles - Rubella 2 (in Months)",
		    ReportUtils.map(moh710Indicators.givenMeaslesRubella2VaccineAge18To24Months(), indParams),
		    moh710DisaggregationsMR, Arrays.asList("01", "02"));
		EmrReportingUtils.addRow(dsd, "MR-2", "Measles - Rubella 2 (Years)",
		    ReportUtils.map(moh710Indicators.givenMeaslesRubellaVaccine2AndAgedOver2Years(), indParams),
		    moh710DisaggregationsMR, Arrays.asList("01", "02"));
		return dsd;
	}
}
