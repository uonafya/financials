<%
    ui.decorateWith("kenyaemr", "standardPage", [ patient: currentPatient])
    ui.includeCss("financials", "jquery.dataTables.min.css")
    ui.includeCss("ehrconfigs", "referenceapplication.css")
%>
<div class="ke-page-content">
${ ui.includeFragment("patientdashboardapp", "visitSummary", [patientId: patientId]) }
</div>