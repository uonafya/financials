<%
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
    ui.includeJavascript("ehrconfigs", "knockout-3.4.0.js")
    ui.includeJavascript("ehrcashier", "moment.js")
    ui.includeJavascript("financials", "dataTables.buttons.min.js")
    ui.includeJavascript("financials", "pdfmake.min.js")
    ui.includeJavascript("financials", "vfs_fonts.js")
    ui.includeJavascript("financials", "buttons.html5.js")
    ui.includeJavascript("financials", "buttons.print.min.js")
    ui.includeCss("financials", "buttons.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
            var table =  jQuery("#dDetails").DataTable({
                dom: 'Bfrtip',
                buttons: ['copy', 'csv', 'excel',
                    {   extend: 'print',
                        messageTop: 'Departmental transactions list.',
                        customize: function ( win ) {
                            jq(win.document.body)
                                .prepend(`${ ui.includeFragment("patientdashboardapp", "printHeader") }`);
                        },
                        repeatingHead: {
                            logo: '${ui.resourceLink('ehrinventoryapp', 'images/kenya_logo.bmp')}',
                            logoPosition: 'center',
                            logoStyle: ''
                        },
                        title: ''
                    }
                ]

            });
            jQuery('#dDetails tbody').on( 'click', 'tr', function () {
                console.log( table.row( this ).data() );
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



table#dDetails.dataTable tbody tr:hover {
    background-color: #43fff8;
}

table#dDetails.dataTable tbody tr:hover > .sorting_1 {
    background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Departmental Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">

        <table id="dDetails">
            <thead>
            <tr>
                <td>Transaction date</td>
                <td>Department</td>
                <td>Service</td>
                <td>Amount collected</td>
            </tr>
            </thead>
            <tbody>
            <% if (summaryAccounts.empty) { %>
            <tr>
                <td colspan="3">
                    No records found
                </td>
            </tr>
            <% } %>
            <% summaryAccounts.each {%>
            <tr>
                <td>${it.transactionDate}</td>
                <td>${it.department}</td>
                <td>${it.servicePaidFor}</td>
                <td>${it.totalAmount}</td>

            </tr>
            <% } %>
            </tbody>
        </table>

    </div>
</div>
<div>
    ${ ui.includeFragment("financials", "cumulativeDepartmentalFinanceSummaries") }
</div>
