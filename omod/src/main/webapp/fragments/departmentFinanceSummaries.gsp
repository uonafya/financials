<%
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
            var table =  jQuery("#dDetails").DataTable();
            jQuery('#dDetails tbody').on( 'click', 'tr', function () {
                console.log( table.row( this ).data() );
            } );
    });
</script>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Departmental Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF">
        <table id="dDetails">
            <thead>
            <tr>
                <td>Transaction date</td>
                <td>Department</td>
                <td>Amount collected</td>
            </tr>
            </thead>
            <tbody>
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