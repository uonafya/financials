<%
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
%>

<script type="text/javascript">
    jQuery(function() {
        var table = jQuery("#details").DataTable();
        var table1 = Query("#summaryDpt").DataTable();
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



table#details.dataTable tbody tr:hover {
    background-color: #43fff8;
}

table#details.dataTable tbody tr:hover > .sorting_1 {
    background-color: #43fff8;
}
table1#summaryDpt.dataTable tbody tr:hover {
    background-color: #43fff8;
}

table1#summaryDpt.dataTable tbody tr:hover > .sorting_1 {
    background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Financial Report and Statements today</div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <h4>Patient Summary</h4>
        <table border="0" cellpadding="5" cellspacing="0" id="details" width="75%">
            <thead>
                <tr>
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
                    <td colspan="7">
                        No records found for today
                    </td>
                </tr>
            <% } %>
            <% bills.each {%>
                <tr>
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
        <h4>Departmental Summaries</h4>
        <table border="0" id="summaryDpt" width="75%" cellspacing="0" cellpadding="5">
            <thead>
            <tr>
                <td>Transaction date</td>
                <td>Department</td>
                <td>Amount collected</td>
            </tr>
            </thead>
            <tbody>
            <% if (summaryAccounts.empty) { %>
            <tr>
                <td colspan="3">
                    No records found for today
                </td>
            </tr>
            <% } %>
            <% summaryAccounts.each {%>
            <tr>
                <td>${it.transactionDate}</td>
                <td>${it.department}</td>
                <td>${it.totalAmount}</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>