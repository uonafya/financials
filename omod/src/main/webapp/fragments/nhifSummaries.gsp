<script type="text/javascript">
  jQuery(function() {
    var table = jQuery("#nhifDetails").DataTable();

  });
</script>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">NHIF Summaries </div>
    <div class="ke-panel-content">
        <table border="0" cellpadding="0" cellspacing="0" id="nhifDetails" width="100%">
            <thead>
            <tr>
                <th>Visit Date</th>
                <th>Patient Identifier</th>
                <th>Patient Names</th>
                <th>Visit type</th>
                <th>Amount</th>
            </tr>
            </thead>
            <tbody>
            <% if (nhifPatients.empty) { %>
            <tr>
                <td colspan="5">
                    No records found for specified period
                </td>
            </tr>
            <% } %>
            <% nhifPatients.each {%>
            <tr>
                <td>${it.visitDate}</td>
                <td>${it.identifierValue}</td>
                <td>${it.names}</td>
                <td>${it.visitType}</td>
                <td>${it.totals}</td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
