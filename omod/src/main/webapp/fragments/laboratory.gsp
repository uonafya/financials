<script type="text/javascript">
  jQuery(function() {
    populateLaboratoryDetails();
    jq("#filterLaboratory").click(function () {
      populateLaboratoryDetails();
    });
  });
  function populateLaboratoryDetails() {
    fetchLaboratorySummariesByDateRange(jQuery("#summaryFromDate-field").val(), jQuery("#summaryToDate-field").val());

  }

  function fetchLaboratorySummariesByDateRange(fromDate, toDate) {
      var toReturn;
      jQuery.ajax({
          type: "GET",
          url: '${ui.actionLink("financials", "laboratory", "fetchLaboratorySummariesByDateRange")}',
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
      jQuery("#laboratory").DataTable().clear().destroy();
      data.map((item) => {
          jQuery("#labTbody").append("<tr><td>" + item.transactionDate + "</td><td>" + item.serviceOffered + "</td><td>" + item.identifier + "</td><td>" + item.patient + "</td><td>" + item.category + "</td><td>" + item.subCategory + "</td><td>" + item.actualAmount + "</td><td>" + item.paidAmount + "</td></tr>");
      });
      initDataTable();
  }

  function initDataTable() {
      var table = jQuery("#laboratory").DataTable({
          dom: 'Bfrtip',
          "oLanguage": {
              "oPaginate": {
                  "sNext": '<i class="fa fa-chevron-right py-1" ></i>',
                  "sPrevious": '<i class="fa fa-chevron-left py-1" ></i>'
              }
          },
          buttons: ['copy', 'csv', 'excel',
              {   extend: 'print',
                  messageTop: 'Laboratory revenue transactions.',
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

<div class="ke-panel-frame">
    <div class="ke-panel-heading">Patient Laboratory Summary</div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <div class="row">
            <div class="col-12">
                <div style="margin-top: -1px " class="onerow">
                    <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
                     <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    <button id="filterLaboratory" type="button" class=" btn btn-primary right">${ui.message("Filter")}
                    </button>
                </div>
            </div>
        </div>
    </div>
        <div class="row">
            <div class="col-md-12">
                <hr />
            </div>
        </div>
        <table border="0" cellpadding="0" cellspacing="0" id="laboratory" width="100%">
            <thead>
            <tr>
                <th>Transaction Date</th>
                <th>Lab test</th>
                <th>Patient Identifier</th>
                <th>Patient Names</th>
                <th>Category</th>
                <th>SubCategory</th>
                <th>Actual Amount</th>
                <th>Paid Amount</th>
            </tr>
            </thead>
            <tbody id="labTbody">
            </tbody>
        </table>
    </div>
</div>