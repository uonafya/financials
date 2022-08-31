<script type="text/javascript">
    jQuery(function() {
        var table =  jQuery("#dDetails").DataTable({
            dom: 'rtp',
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
      updateTable();
      jq("#filterPharmacy").click(function () {
        updateTable();
      });
    });

    function updateTable() {
        summaryData=""
        summaryData=fetchPharmacySummariesByDateRange(moment(jq("#summaryFromDate-field").val()).format('DD/MM/YYYY'), moment(jq('#summaryToDate-field').val()).format('DD/MM/YYYY'));
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
      jQuery("#patientPharmacySummaryItems").empty();
      data.map((item) => {
        jQuery("#patientPharmacySummaryItems").append("<tr><td>" + item.createdOn + "</td><td>" + item.patientIdentifier + "</td><td>" + item.patientNames + "</td><td>" + item.waiverAmount+ "</td><td>"+ item.totalAMount+"</td></tr>");
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
            <div class="col-4" style="margin-bottom: 10px">
                <button id="filterPharmacy" type="button" class=" btn btn-primary right">${ui.message("Filter")}
                </button>
            </div>
        </div>

        <table id="dDetails">
            <thead>
            <tr>
                <td>Transaction date</td>
                <td>Identifier</td>
                <td>Patient Names</td>
                <td>Total Waiver Amount</td>
                <td>Total Amount Charged</td>
            </tr>
            </thead>
            <tbody id="patientPharmacySummaryItems"></tbody>
        </table>

    </div>
</div>