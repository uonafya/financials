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
    ui.includeJavascript("financials", "logo.js")
%>
<script type="text/javascript">
    jQuery(function() {
        jQuery("#dataIn").DataTable();
        var table =  jQuery("#pDetails").DataTable({
            dom: 'Bfrtip',
            buttons: [
                'copy',
                'csv',
                'excel',
                {   extend: 'print',
                    customize: function ( win ) {
                        jq(win.document.body)
                            .prepend(`${ ui.includeFragment("patientdashboardapp", "printHeader") }`);
                    }
                },
                {
                    extend: 'pdfHtml5',
                    customize: function ( doc ) {
                        jq(doc.content.body).prepend(`${ ui.includeFragment("patientdashboardapp", "printHeader") }`);
                    }
                }
            ],
            initComplete: function (){
            jq(this.api().table().container()).find('input[type="search"]').parent().wrap('<form>').parent().attr('autocomplete','off').css('overflow','hidden').css('margin','auto');
        }
        });
        jQuery('#pDetails tbody').on( 'click', 'tr', function () {
            var billId = table.row( this ).data();
            ui.navigate('financials', 'billedItems', {billedId: billId[0], patientId: billId[1]});
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



table#pDetails.dataTable tbody tr:hover {
    background-color: #43fff8;
}

table#pDetails.dataTable tbody tr:hover > .sorting_1 {
    background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Student Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <table id="pDetails">
            <thead>
            <tr>
                <td>Service Id</td>
                <td>Patient Id</td>
                <th>Transaction Date</th>
                <th>Patient Identifier</th>
                <th>Patient Names</th>
                <th>Category</th>
                <th>SubCategory</th>
                <th>Institution</th>
                <th>Waiver Amount</th>
                <th>Actual Amount</th>
                <th>Paid Amount</th>
            </tr>
            </thead>
            <tbody>
            <% if (bills.empty) { %>
            <tr>
                <td colspan="9">
                    No records found
                </td>
            </tr>
            <% } %>
            <% bills.each {%>
            <% if (it.subCategory == "Student") { %>
            <tr>
                <td>${it.billId}</td>
                <td>${it.patientId}</td>
                <td>${it.transactionDate}</td>
                <td>${it.identifier}</td>
                <td>${it.patient}</td>
                <td>${it.category}</td>
                <td>${it.subCategory}</td>
                <td>${it.studentAttributeName}</td>
                <td>${it.waiver}</td>
                <td>${it.actualAmount}</td>
                <td>${it.paidAmount}</td>
            </tr>
            <%}%>
            <%}%>
            </tbody>
        </table>
    </div>
</div>

