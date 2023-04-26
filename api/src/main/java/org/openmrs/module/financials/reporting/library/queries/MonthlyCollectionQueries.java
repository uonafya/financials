package org.openmrs.module.financials.reporting.library.queries;

public class MonthlyCollectionQueries {
	
	public static String getMonthlySummaryQuery() {
		String sql = "SELECT *  FROM monthly_summary_report";
		return sql;
	}
}
