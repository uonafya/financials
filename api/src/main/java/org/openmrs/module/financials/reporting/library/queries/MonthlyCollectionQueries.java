package org.openmrs.module.financials.reporting.library.queries;

public class MonthlyCollectionQueries {
	
	public static String getMonthlySummaryQuery() {
		String sql = "SELECT *  FROM monthly_summary_report WHERE transaction_date BETWEEN :starDate AND :endDate";
		return sql;
	}
}
