<%
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
        jQuery("#pDetails").DataTable({

        });
    });
</script>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Patient Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF">
        <table id="pDetails">
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
        </div>
</div>