<%
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
        var table =  jQuery("#pDetails").DataTable();
        jQuery('#pDetails tbody').on( 'click', 'tr', function () {
            var billId = table.row( this ).data();
            jq.getJSON('${ ui.actionLink("financials", "patientFinanceSummaries" ,"getBilledItemsPerServiceBill") }',
                { 'billId' : billId[0] }
            ).success(function (data) {
                if (data.items.length > 0) {
                    jQuery('#items-detail').dialog({
                        dialogClass: "no-close",
                        autoOpen: true,
                        height: 400,
                        modal: true,
                        title: "Patient Billed Items Summary",
                        width: 500,
                        buttons: {
                            Close: function () {
                                jq(this).dialog("close");
                            },
                            Print: function () {

                            }
                        },
                        open: function(event, ui) {
                            $("#results tr").remove();
                            createTable(data)
                        }
                    });
                }

            })

        } );
    });


    function createTable(data) {
        resultstable = jq('<table><thead><tr><th>Index</th><th>Created On</th><th>Name</th><th>Quantity</th><th>Unit price</th><th>Amount</th></tr><thead></table>').attr({ id: "results" });
        jq(data.items).each(function(index, dt){
            alert(dt);
          jq('<tr><td>{{index + 1}}</td><td>{{dt.createdDate}}</td><td>dt.name</td><td>dt.quantity</td><td>dt.unitPrice</td>dt.actualAmount<td></td></tr>').appendTo(resultstable);
        });
        resultstable.appendTo("#items-detail");
    }
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
    <div class="ke-panel-heading">Patient Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <table id="pDetails">
            <thead>
            <tr>
                <td>Service Id</td>
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
        </div>
</div>
<div class="ke-panel-content" id="items-detail"></div>
