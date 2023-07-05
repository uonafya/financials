<script type="text/javascript">
    jQuery(function() {
      updateTable();
      jq("#filterPharmacy").click(function () {
        updateTable();
      });
      jQuery('#pharmacySummaryDetails tbody').on( 'click', 'tr', function () {
        var table =  jq("#pharmacySummaryDetails").DataTable();

        var billId = table.row(this).data();
        ui.navigate('financials', 'patientPharmacySummary', {identifier: billId[1], whichDate:billId[0] });
    } );
    });

    function updateTable() {
        fetchPharmacySummariesByDateRange(jQuery("#summaryFromDate-field").val(), jQuery("#summaryToDate-field").val());
    }

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
        return populateTableBodyForPatientPharmacySummary(toReturn);
    }
    function populateTableBodyForPatientPharmacySummary(data) {
      jQuery("#pharmacySummaryDetails").DataTable().clear().destroy();
      data.map((item) => {
        jQuery("#patientPharmacySummaryItemsTblBody").append("<tr><td>" + item.createdOn + "</td><td>" + item.patientIdentifier + "</td><td>" + item.patientNames + "</td><td>" + item.waiverAmount+ "</td><td>"+ item.totalAMount+"</td></tr>");
      });
      initPharmacyDataTable();
    }
    function initPharmacyDataTable() {
                var table = jQuery("#pharmacySummaryDetails").DataTable({
                    dom: 'Bfrtip',
                    "oLanguage": {
                        "oPaginate": {
                            "sNext": '<i class="fa fa-chevron-right py-1" ></i>',
                            "sPrevious": '<i class="fa fa-chevron-left py-1" ></i>'
                        }
                    },
                    buttons: ['copy', 'csv', 'excel',
                        {   extend: 'print',
                            messageTop: 'Pharmacy revenue transactions.',
                            customize: function ( win ) {
                                jQuery(win.document.body)
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
    <div class="ke-panel-heading">Departmental Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">

        <div style="margin-top: -1px " class="onerow">
            <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
            <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
            <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
            <button id="filterPharmacy" type="button" class=" btn btn-primary right">${ui.message("Filter")}</button>
        </div>

        <table id="pharmacySummaryDetails">
            <thead>
            <tr>
                <td>Transaction date</td>
                <td>Identifier</td>
                <td>Patient Names</td>
                <td>Total Waiver Amount</td>
                <td>Total Amount Charged</td>
            </tr>
            </thead>
            <tbody id="patientPharmacySummaryItemsTblBody"></tbody>
        </table>

    </div>
</div>