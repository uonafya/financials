<%
    ui.includeJavascript("ehrconfigs", "jquery.dataTables.min.js")
    ui.includeCss("ehrconfigs", "datatables/dataTables_jui.css")
%>

<script type="text/javascript">
    jQuery(function() {
        jQuery("#details").DataTable({
            searching: true,
            lengthChange: false,
            pageLength: 10,
            jQueryUI: true,
            pagingType: 'full_numbers',
            sort: true,
            select: true,
            dom: 'Bfrtip',
            language: {
                zeroRecords: 'No Services billed.',
                paginate: {
                    first: 'First',
                    previous: 'Previous',
                    next: 'Next',
                    last: 'Last'
                }
            },
            buttons: [
                {
                    extend: 'collection',
                    text: 'Export',
                    buttons: [
                        'copy',
                        'excel',
                        'csv',
                        'pdf',
                        'print'
                    ]
                }
                ]
        });
        jQuery("#summaryDpt").DataTable({
            searching: true,
            lengthChange: false,
            pageLength: 10,
            jQueryUI: true,
            pagingType: 'full_numbers',
            select: true,
            sort: true,
            dom: 'Bfrtip',
            language: {
                zeroRecords: 'No Services billed.',
                paginate: {
                    first: 'First',
                    previous: 'Previous',
                    next: 'Next',
                    last: 'Last'
                }
            },
            buttons: [
                {
                    extend: 'collection',
                    text: 'Export',
                    buttons: [
                        'copy',
                        'excel',
                        'csv',
                        'pdf',
                        'print'
                    ]
                }
            ]
        });
    });
</script>
<style>
.format{
    text-align: center;
    background-color: lightgrey;
    font-size: 16px;
}
.formatp{
    text-align: center;
    background-color: lightgrey;
    font-size: 16px;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Financial Report</div>
    <div class="ke-panel-content">
        <table border="0" cellpadding="0" cellspacing="10">
            <tr>
                <td valign="top">
                    <table id="details" width="75%">
                        <thead>
                        <tr>
                            <th>Service bill #</th>
                            <th>Transaction Date</th>
                            <th>Patient Identifier</th>
                            <th>Patient Names</th>
                            <th>Patient Category</th>
                            <th>Patient SubCategory</th>
                            <th>Waiver Amount</th>
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
                            <td>${it.actualAmount}</td>
                            <td>${it.paidAmount}</td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </div>
<div class="ke-panel-content">
<table border="0" id="summaryDpt">
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