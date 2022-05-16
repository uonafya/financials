package org.openmrs.module.financials.reporting.library.indicator;

import org.openmrs.module.financials.reporting.library.cohorts.Moh706CohortDefinition;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.openmrs.module.financials.utils.EhrReportingUtils.cohortIndicator;
import static org.openmrs.module.kenyacore.report.ReportUtils.map;

@Component
public class Moh706IndicatorDefinitions {
	
	private Moh706CohortDefinition moh706CohortDefinition;
	
	@Autowired
	public Moh706IndicatorDefinitions(Moh706CohortDefinition moh706CohortDefinition) {
		this.moh706CohortDefinition = moh706CohortDefinition;
	}
	
	public CohortIndicator getAllUrineAnalysisGlucoseTestsPositives() {
		return cohortIndicator(
		    "All patients who have urinalysis glucose",
		    map(moh706CohortDefinition.getAllUrineAnalysisGlucoseTestsPositives(),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllUrineAnalysisKetonesTestsPositives() {
		return cohortIndicator(
		    "All patients who have urinalysis ketones",
		    map(moh706CohortDefinition.getAllUrineAnalysisKetonesTestsPositives(),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllUrineAnalysisProteinsTestsPositives() {
		return cohortIndicator(
		    "All patients who have urinalysis Proteins",
		    map(moh706CohortDefinition.getAllUrineAnalysisProteinsTestsPositives(),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllMalariaTests() {
		return cohortIndicator("All patients who have malaria test done",
		    map(moh706CohortDefinition.getAllMalariaTests(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllRapidMalariaTests() {
		return cohortIndicator("All patients who have rapid malaria test done",
		    map(moh706CohortDefinition.getAllRapidMalariaTests(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllMalariaTestsPositiveCases() {
		return cohortIndicator("All patients who have malaria test done and are positive",
		    map(moh706CohortDefinition.getAllMalariaTestsPositiveCases(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getAllRapidMalariaTestsPositiveCases() {
		return cohortIndicator("All patients who have rapid malaria test done and are positive",
		    map(moh706CohortDefinition.getAllRapidMalariaTestsPositiveCases(), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getResponsesBasedOnAnswerIndicator(int q, List<Integer> ans) {
		return cohortIndicator("All patients who have tests done based on a question and answers",
		    map(moh706CohortDefinition.getResponsesBasedOnAnswer(q, ans), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getPatientsWithObsIndicator(int q) {
		return cohortIndicator("All patients who have Obs recorded",
		    map(moh706CohortDefinition.getPatientsWithObs(q), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(List<Integer> q, List<Integer> ans) {
		return cohortIndicator(
		    "All patients who have tests done based on a question and answers based on list of questions and answers",
		    map(moh706CohortDefinition.getResponsesBasedOnAlistOfQuestionsAndListOfAnswers(q, ans),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getResponsesBasedOnAlistOfQuestions(List<Integer> q) {
		return cohortIndicator("All patients who have tests done based on a question list",
		    map(moh706CohortDefinition.getResponsesBasedOnAlistOfQuestions(q), "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getResponsesBasedOnValueNumericQuestion(int q) {
		return cohortIndicator(
		    "Get patients with obs recorded based on value numeric concept id indicators",
		    map(moh706CohortDefinition.getResponsesBasedOnValueNumericQuestion(q),
		        "startDate=${startDate},endDate=${endDate}"));
	}
	
	public CohortIndicator getResponsesBasedOnValueNumericQuestionBetweenLimits(int question, double lower, double upper) {
		return cohortIndicator(
		    "Get patients with obs recorded based on value numeric concept id within limits indicators",
		    map(moh706CohortDefinition.getResponsesBasedOnValueNumericQuestionBetweenLimits(question, lower, upper),
		        "startDate=${startDate},endDate=${endDate}"));
	}
}
