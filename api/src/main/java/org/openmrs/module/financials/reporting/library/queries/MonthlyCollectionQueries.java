package org.openmrs.module.financials.reporting.library.queries;

public class MonthlyCollectionQueries {
	
	public static String getMonthlySummaryQueryM1() {
		String sql = "SELECT m1.transaction_date,m1.total_sales,m1.ip_cash,m1.maternity,m1.xray,m1.lab,m1.theatre,m1.mortuary,m1.op_treatment,m1.pharmacy,m1.cash_receipts_cash_from_daily_services,m1.cash_receipt_nhif_receipt,m1.cash_receipt_other_debtors_receipt,m1.revenue_not_collected_patient_not_yet_paid_nhif_patients,m1.revenue_not_collected_patient_not_yet_paid_other_debtors,m1.revenue_not_collected_patient_not_yet_paid_waivers,m1.revenue_not_collected_write_offs_exemptions,m1.revenue_not_collected_write_offs_absconders  "
		        + " FROM monthly_summary_report m1 WHERE m1.transaction_date BETWEEN :startDate AND :endDate";
		return sql;
	}
	
	public static String getMonthlySummaryQueryM2() {
		String sql = "SELECT m2.transaction_date,m2.medical_exam,m2.medical_reports_including_P3,m2.dental,m2.physio_therapy,m2.occupational_therapy,m2.medical_records_cards_and_files,m2.booking_fees,m2.rental_services,m2.ambulance,m2.public_health_services,m2.ent_and_other_clinics,m2.other FROM monthly_summary_report m2 WHERE m2.transaction_date BETWEEN :startDate AND :endDate";
		return sql;
	}
	
	public static String getDailySummaryPerDepartment() {
		return "SELECT * FROM patient";
	}
	
	public static String getHptSummary() {
		return "SELECT * FROM inventory_drug";
	}
}
