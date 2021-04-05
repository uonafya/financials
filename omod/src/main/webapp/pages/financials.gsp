<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeJavascript("ehrconfigs", "jquery.dataTables.min.js")
    ui.includeCss("ehrconfigs", "datatables/dataTables_jui.css")
%>
<script type="text/javascript">
    jQuery(function() {
        jQuery("#details").DataTable({
            ordering: true
        });
        jQuery("#servicePoint").DataTable({
            ordering: true
        });

    });
</script>
<style>
    .format{
         text-align: center;
         background-color: lightgrey;
         font-size: 16px;
    }
</style>

<div class="ke-page-content">
    <div>
        <p align="center">
            <b>Financial Report</b>
        </p>
    </div>
        <table>
            <tr>
                <td valign="top">
                    <span class="format">Cummulative Revenue</span>
                    <table id="details">
                        <thead>
                        <tr>
                            <th>Service bill #</th>
                            <th>Transaction Date</th>
                            <th>Patient Identifier</th>
                            <th>Patient Names</th>
                            <th>Patient Category</th>
                            <th>Patient SubCategory</th>
                            <th>Waiver Amount</th>
                            <th>Rebate Amount</th>
                            <th>Actual Amount</th>
                            <th>Paid Amount</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% bills.each {%>
                        <tr>
                            <td>${it.billId}</td>
                            <td>${it.transactionDate}</td>
                            <td>${it.identifier}</td>
                            <td>${it.patient}</td>
                            <td>${it.category}</td>
                            <td>${it.subCategory}</td>
                            <td>${it.waiver}</td>
                            <td>${it.rebate}</td>
                            <td>${it.actualAmount}</td>
                            <td>${it.paidAmount}</td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                </td>
                <td>
                    <table id="servicePoint" valign="top">
                        <span class="format">Revenue per departiments</span>
                        <thead>
                            <tr>
                                <th>Depatiment</th>
                                <th>Amount</th>
                            </tr>
                        </thead>
                    </table>

                </td>
            </tr>
        </table>

</div>
