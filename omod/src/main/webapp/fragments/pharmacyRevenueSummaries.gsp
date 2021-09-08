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
    ui.includeJavascript("ehrconfigs", "underscore-min.js")

%>
<script type="text/javascript">
    jQuery(function() {
        var table =  jQuery("#dDetails").DataTable({
            dom: 'Bfrtip',
            buttons: ['copy', 'csv', 'excel',
                {   extend: 'print',
                    messageTop: 'Pharmacy revenue transactions.',
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
                ],
            initComplete: function (){

                jq(this.api().table().container()).find('input[type="search"]').parent().wrap('<form>').parent().attr('autocomplete','off').css('overflow','hidden').css('margin','auto');

            }

        });
        jQuery('#dDetails tbody').on( 'click', 'tr', function () {
            console.log( table.row( this ).data() );
        } );
    });
``
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

    function updateTable() {
        summaryData=""
        summaryData=fetchPharmacySummariesByDateRange(moment(jq("#summaryFromDate-field").val()).format('YYYY-MM-DD'), moment(jq('#summaryToDate-field').val()).format('YYYY-MM-DD'));
        list.departmentSummaries(summaryData);
        console.log(list.departmentSummaries);

    }

    jq('#summaryFromDate').on('change',function(){
        console.log("update table fromdate");
        updateTable();
    });

    jq('#summaryToDate').on('change',function(){
        console.log("update table toDate");
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
        console.log("Return data",fromDate,toDate,"value###"+toReturn)
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
                <td>Transaction date</td>
                <td>Drug Name</td>
                <td>Formulation</td>
                <td>Issued Quantity</td>
                <td>Total Price</td>
            </tr>

            </thead>
            <tbody>
            <% if (departmentSummaries.empty) { %>
            <tr>
                <td colspan="3">
                    No records found
                </td>
            </tr>
            <% } %>

            <% departmentSummaries.each { %>
            <tr>
                <td>${it.createdOn}</td>
                <td >${it.drugName}</td>
                <td>${it.formulationName}</td>
                <td>${it.issueQuantity}</td>
                <td>${it.totalPrice}</td>
            </tr>
            <% } %>
            </tbody>
        </table>

    </div>
</div>