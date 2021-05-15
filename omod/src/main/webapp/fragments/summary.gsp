<%
    ui.includeJavascript("ehrconfigs", "jquery.dataTables.min.js")
    ui.includeCss("ehrconfigs", "datatables/dataTables_jui.css")
%>

<script type="text/javascript">
    jQuery(function() {
        jQuery("#details").DataTable({
            "processing": true,
            "pagingType": "full_numbers",
            searching: true,
            lengthChange: false,
            pageLength: 15,
            jQueryUI: true,
            sort: true,
            select: true,
            dom: 't<"fg-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"ip>',
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
                'colvis',
                'excel',
                'print'
            ]
        });
        jQuery("#summaryDpt").DataTable({
            "processing": true,
            "pagingType": "full_numbers",
            searching: true,
            lengthChange: false,
            pageLength: 15,
            jQueryUI: true,
            select: true,
            sort: true,
            dom: 't<"fg-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"ip>',
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
                'colvis',
                'excel',
                'print'
            ]
        });
    });
</script>
<style type="text/css">
#report-search-form input {
    display: inline;
    margin-top: 5px;
}
#summaryDpt {
    margin-top: 1em;
}
.paging_full_numbers .fg-button {
    margin: 1px;
}
th:last-child{
    width: 75px!important;
}
#details {
    margin-top: 1em;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Financial Report and Statements</div>
    <div class="ke-panel-content">
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
        <br />
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