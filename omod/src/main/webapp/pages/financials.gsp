<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeJavascript("ehrconfigs", "jquery.dataTables.min.js")
    ui.includeCss("ehrconfigs", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
        jQuery("#startDate").datepicker();
        jQuery("#endDate").datepicker();
        jQuery("#details").DataTable();

    });
</script>

<div class="ke-page-content">
    <fieldset>
        <legend>Financial Report</legend>
        <form method="post">
                <table>
                    <tr>
                        <td>
                            Start Date:<input type="text" id="startDate" name="startDate"/>
                        </td>
                        <td>
                            End Date:<input type="text" id="endDate" name="endDate"/>
                        </td>
                        <td>
                            <input type="submit" value="Filter" >
                        </td>
                    </tr>

                </table>
        </form>

    </fieldset>
    <table id="details">
        <thead>
            <th>Service bill #</th>
            <th>Patient name</th>
            <th>Amount</th>
            <th>Actual Amount</th>
            <th>Rebate Amount</th>
            <th>CategoryNumber</th>
            <th>Patient Category</th>
            <th>Admitted Days</th>
            <th>Patient SubCategory</th>
        </thead>
        <tbody>
        <% bills.each {%>
            <tr>
                <td>${it.patientServiceBillId}</td>
                <td>${it.patient}</td>
                <td>${it.amount}</td>
                <td>${it.actualAmount}</td>
                <td>${it.rebateAmount}</td>
                <td>${it.categoryNumber}</td>
                <td>${it.patientCategory}</td>
                <td>${it.admittedDays}</td>
                <td>${it.patientSubCategory}</td>
            </tr>
        <%}%>
        </tbody>
    </table>

</div>
