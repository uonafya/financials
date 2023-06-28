<script type="text/javascript">
  jQuery(function() {
    updateTable();
    jq("#filterNhif").click(function () {
      updateTable();
    });

  });
  function updateTable() {
    fetchNhifSummariesByDateRange(moment(jq("#summaryFromDate-field").val()).format('DD/MM/YYYY'), moment(jq('#summaryToDate-field').val()).format('DD/MM/YYYY'));
  }
  function fetchNhifSummariesByDateRange(fromDate, toDate) {
    console.log(fromDate, toDate);
    var toReturn;
    jQuery.ajax({
      type: "GET",
      url: '${ui.actionLink("financials", "nhifSummaries", "fetchNhifPatientsPerDateRange")}',
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
    return populateTableBodyForPatientNhifSummary(toReturn);
  }
  function populateTableBodyForPatientNhifSummary(data) {
    jQuery("#nhifDetails").DataTable().clear().destroy();
    data.map((item) => {
      jQuery("#nhifPatientSummaryItems").append("<tr><td>" + item.patient.id + "</td><td>" + item.visitDate + "</td><td>" + item.identifierValue + "</td><td>" + item.names + "</td><td>" + item.visitType +"</td></tr>");
    });

      var table = jQuery("#nhifDetails").DataTable({
          dom: 'Bfrtip',
          buttons: ['copy', 'csv', 'excel',
              {
                  extend: 'print',
                  messageTop: 'Pharmacy revenue transactions.',
                  customize: function (win) {
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

      jQuery('#nhifDetails tbody').on( 'click', 'tr', function () {
          var table =  jq("#nhifDetails").DataTable();

          var billId = table.row(this).data();
          ui.navigate('financials', 'patientNhifSummary', {patientId: billId[0], whichDate:billId[1] });
      } );

  }
</script>
<style>
table#nhifDetails.dataTable tbody tr:hover {
  background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">NHIF Summaries </div>
    <div class="ke-panel-content">
        <div style="margin-top: -1px " class="onerow">
            <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
            <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
            <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
            <button id="filterNhif" type="button" class=" btn btn-primary right">${ui.message("Filter")}</button>
        </div>
        <table border="0" cellpadding="0" cellspacing="0" id="nhifDetails" width="100%">
            <thead>
            <tr>
                <th>Visit Date</th>
                <th>Patient Identifier</th>
                <th>Patient Names</th>
                <th>Visit type</th>
            </tr>
            </thead>
            <tbody id="nhifPatientSummaryItems"></tbody>
        </table>
    </div>
</div>
