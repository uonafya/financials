<%
    ui.decorateWith("kenyaemr", "standardPage", [ patient: currentPatient ])

    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
        var table = jQuery("#summary").DataTable();
    });
</script>
<style type="text/css">
.no-close .ui-dialog-titlebar-close {
    display: none;
}
body {
    font: 90%/1.45em "Helvetica Neue", HelveticaNeue, Verdana, Arial, Helvetica, sans-serif;
    margin: 0;
    padding: 0;
    color: #333;
}



table#summary.dataTable tbody tr:hover {
    background-color: #43fff8;
}

</style>
<table cellpadding="5" cellspacing="0">
    <tr>
        <td width="30%" valign="top">
            <div class="ke-panel-frame">
                <div class="ke-panel-heading">Other actions</div>
                <div class="ke-page-content">

                </div>
            </div>
        </td>
        <td width="100%">
            <div class="ke-panel-frame">
                <div class="ke-panel-heading">Patient payment history</div>
                <div class="ke-page-content" style="background-color: #F3F9FF;">
                    <table id="summary">
                        <thead>
                            <tr>
                                <th>Created Date</th>
                                <th>Created By</th>
                                <th>Patient Category</th>
                                <th>Patient sub Category</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <td>Unit Price</td>
                                <td>Amount</td>
                                <th>Recept Number</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% if (allItems.empty) { %>
                        <tr>
                            <td colspan="9">
                                No records found for this patirnt
                            </td>
                        </tr>
                        <% } %>
                        <% allItems.each {%>
                        <tr>
                            <td>${it.createdDate}</td>
                            <td>${it.patientServiceBill.creator.person.names.givenName}</td>
                            <td>${it.patientServiceBill.patientCategory}</td>
                            <td>${it.patientServiceBill.patientSubCategory}</td>
                            <td>${it.name}</td>
                            <td>${it.quantity}</td>
                            <td>${it.unitPrice}</td>
                            <td>${it.actualAmount}</td>
                            <td>${it.patientServiceBill.receipt.id}</td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
</td>
</tr>
</table>