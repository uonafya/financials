<%
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
        var table =  jQuery("#pDetails").DataTable();
        jQuery('#pDetails tbody').on( 'click', 'tr', function () {
            var billId = table.row( this ).data();
            jq.getJSON('${ ui.actionLink("financials", "patientFinanceSummaries" ,"getBilledItemsPerServiceBill") }',
                { 'billId' : billId[0] }
            ).success(function (data) {
                ui.navigate('financials', 'billedItems', {billedId: data.service.serviceId});
            })
        } );
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



table#pDetails.dataTable tbody tr:hover {
    background-color: #43fff8;
}

table#pDetails.dataTable tbody tr:hover > .sorting_1 {
    background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Patient Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <table id="pDetails">
            <thead>
            <tr>
                <td>Service Id</td>
                <th>Transaction Date</th>
                <th>Patient Identifier</th>
                <th>Patient Names</th>
                <th>Category</th>
                <th>SubCategory</th>
                <th>Waiver Amount</th>
                <th>Actual Amount</th>
                <th>Paid Amount</th>
            </tr>
            </thead>
            <tbody>
            <% if (bills.empty) { %>
            <tr>
                <td colspan="9">
                    No records found for this patirnt
                </td>
            </tr>
            <% } %>
            <% bills.each {%>
            <tr>
                <td>${it.billId}</td>
                <td>${it.transactionDate}</td>
                <td>${it.identifier}</td>
                <td>${it.patient}</td>
                <td>${it.category}</td>
                <td>${it.subCategory}</td>
                <td>${it.waiver}</td>
                <td>${it.actualAmount}</td>
                <td>${it.paidAmount}</td>
            </tr>
            <%}%>
            </tbody>
        </table>
        </div>
</div>
