<script type="text/javascript">
  jQuery(function() {
    populateDentalDetails();
    jq("#filterDental").click(function () {
      populateDentalDetails();
    });
  });
  function populateDentalDetails() {
    fetchDentalSummariesByDateRange(jQuery("#summaryFromDate-field").val(), jQuery("#summaryToDate-field").val());

  }

  function fetchDentalSummariesByDateRange(fromDate, toDate) {
      var toReturn;
      jQuery.ajax({
          type: "GET",
          url: '${ui.actionLink("financials", "departmentFinanceSummaries", "fetchDentalSummariesByDateRange")}',
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
      return populateTableBodyForPatientDentalSummary(toReturn);
  }

  function populateTableBodyForPatientDentalSummary(data) {
      jQuery("#dental").DataTable().clear().destroy();

        var totalActualAmount = 0;
        var totalPaidAmount = 0;

      data.map((item) => {
            jQuery("#dentalTbody").append("<tr><td>" + item.transactionDate + "</td><td>" + item.serviceOffered + "</td><td>" + item.identifier + "</td><td>" + item.patient + "</td><td>" + item.category + "</td><td>" + item.subCategory + "</td><td>" + item.actualAmount + "</td><td>" + item.paidAmount + "</td></tr>");

            var actualAmount = parseFloat(item.actualAmount);
            var paidAmount = parseFloat(item.paidAmount);

            totalActualAmount += actualAmount;
            totalPaidAmount += paidAmount;
      });

        var footerRow = jQuery("<tr></tr>");

        footerRow.append("<td></td>");
        footerRow.append("<td></td>");
        footerRow.append("<td></td>");
        footerRow.append("<td></td>");
        footerRow.append("<td></td>");
        footerRow.append("<td></td>");
        footerRow.append("<td>" + totalActualAmount.toFixed(2) + "</td>");
        footerRow.append("<td>" + totalPaidAmount.toFixed(2) + "</td>");

        jQuery("#dental tfoot").html(footerRow);

      initDataTable();
  }

  function initDataTable() {
      var table = jQuery("#dental").DataTable({
          dom: 'Bfrtip',
          "oLanguage": {
              "oPaginate": {
                  "sNext": '<i class="fa fa-chevron-right py-1" ></i>',
                  "sPrevious": '<i class="fa fa-chevron-left py-1" ></i>'
              }
          },
          buttons: ['copy', 'csv', 'excel',
              {   extend: 'print',
                  messageTop: 'Dental revenue transactions.',
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
    <div class="ke-panel-heading">Patient Dental Summary</div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <div class="row">
            <div class="col-12">
                <div style="margin-top: -1px " class="onerow">
                    <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
                     <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    <button id="filterDental" type="button" class=" btn btn-primary right">${ui.message("Filter")}
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
        <table border="0" cellpadding="0" cellspacing="0" id="dental" width="100%">
            <thead>
            <tr>
                <th>Transaction Date</th>
                <th>Dental Service</th>
                <th>Patient Identifier</th>
                <th>Patient Names</th>
                <th>Category</th>
                <th>SubCategory</th>
                <th>Actual Amount</th>
                <th>Paid Amount</th>
            </tr>
            </thead>
            <tbody id="dentalTbody">
            </tbody>
            <tfoot>

            </tfoot>
        </table>
    </div>
</div>