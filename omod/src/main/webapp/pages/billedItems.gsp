<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])


    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")

    def menuItems = [
            [ label: "Back to home",
              iconProvider: "kenyaui",
              icon: "buttons/back.png",
              href: ui.pageLink("financials", "financials", [ section: "patientFinanceSummaries" ])
             ]
    ]

%>
<script type="text/javascript">
    jQuery(function() {

        var table =  jQuery("#tbl").DataTable();
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
<div class="ke-page-sidebar">
    ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Tasks", items: menuItems ]) }
</div>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Patient Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <table id="tbl">
            <thead>
                <tr>
                    <th>Created Date</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Unit Price</th>
                    <th>Actual Amount</th>
                    <th>Waiver Amount</th>
                    <th>Receipt Number</th>
                </tr>
            </thead>
            <tbody>
            <% if (billedItems.empty) { %>
            <tr>
                <td colspan="7">
                    No records found for this patirnt
                </td>
            </tr>
            <% } %>
            <% billedItems.each {%>
                <tr>
                <td>${it.createdDate}</td>
                <td>${it.name}</td>
                <td>${it.quantity}</td>
                <td>${it.unitPrice}</td>
                <td>${it.actualAmount}</td>
                <td>${it.patientServiceBill.waiverAmount}</td>
                <td>${it.patientServiceBill.receipt.id}</td>
                </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>