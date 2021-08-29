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
            buttons: ['copy', 'csv', 'excel', 'pdf', 'print']

        });
        jQuery('#dDetails tbody').on( 'click', 'tr', function () {
            console.log( table.row( this ).data() );
        } );
    });

    var summaryData = fetchPharmacySummariesByDateRange();

    function SummariesDataListView() {
        var self = this;
        self.departmentSummaries = ko.observableArray([]);
        var mappedDepartmentSummaries = jQuery.map(summaryData, function (item) {
            return item;
        });
        self.departmentSummaries(mappedDepartmentSummaries);
    }

    var list = new SummariesDataListView();
    ko.applyBindings(list, jq("#dDetails")[0]);
    console.log(list);

    function updateTable() {
        summaryData=" "
        summaryData=fetchPharmacySummariesByDateRange(jq("#cashier").val().trim(),moment(jq("#summaryFromDate-field").val()).format('DD/MM/YYYY'), moment(jq('#summaryToDate-field').val()).format('DD/MM/YYYY'));
        list.departmentSummaries(summaryData);

    }

    jq('#summaryFromDate').on('change',function(){
        updateTable();
    });

    jq('#summaryToDate').on('change',function(){
        updateTable();
    });


    function fetchPharmacySummariesByDateRange(fromDate, toDate) {
        var toReturn;
        jQuery.ajax({
            type: "GET",
            url: '${ui.actionLink("financials", "pharmacyRevenueSummaries", "fetchPharmacySummariesByDateRange")}',
            dataType: "json",
            global: false,
            async: false,
            data: {
                fromDate: fromDate,
                toDate: toDate
            },
            success: function (data) {
                toReturn = data;
            }
        });
        return toReturn;
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

        <div style="margin-top: -1px " class="onerow">
            <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
            <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
            <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
        </div>

        <table id="dDetails">
            <thead>
            <tr>
                <td>#</td>
                <td>Transaction date</td>
                <td>Drug Name</td>
                <td>Formulation</td>
                <td>Issued Quantity</td>
                <td>Total Price</td>
            </tr>
            </thead>
            <tbody data-bind="foreach: departmentSummaries">
            <tr>
                <td data-bind="text: \$index() + 1"></td>
                <td data-bind="text: (createdOn).substring(0, 11).replaceAt(2, ',').replaceAt(6, ' ').insertAt(3, 0, ' ') "></td>
                <td data-bind="text: drug.name"></td>
                <td data-bind="text: formulation.name"></td>
                <td data-bind="text: issueQuantity"></td>
                <td data-bind="text: totalPrice"></td>
            </tr>
            </tbody>
        </table>

    </div>
</div>