<script type="text/javascript">
  jQuery(function() {
    updateTable();
    jq("#filterCashier").click(function () {
      updateTable();
    });

  });
  function updateTable() {
    fetchCashierSummariesByDateRange(jq("#summaryFromDate-field").val(),jq('#summaryToDate-field').val(), jq('#cashier').val());
  }
  function fetchCashierSummariesByDateRange(fromDate, toDate, cashierUser) {
    var toReturn;
    jQuery.ajax({
      type: "GET",
      url: '${ui.actionLink("financials", "cashierSummaries", "fetchPaymentSummariesByDateRangeAndUser")}',
      dataType: "json",
      global: false,
      async: false,
      data: {
        fromDate: fromDate,
        toDate: toDate,
        cashier: cashierUser
      },
      success: function (data) {
        toReturn = data;
      }
    });
    return populateTableBodyForCashierSummary(toReturn);
  }
  
  function populateTableBodyForCashierSummary(data) {
    console.log(data);
    jQuery("#cashierDetails").DataTable().clear().destroy();

    var totalAmountPaid = 0;

    jQuery("#cashierPatientSummaryItems").empty();

    data.map((item) => {
      totalAmountPaid += item.amount;

      jQuery("#cashierPatientSummaryItems").append("<tr><td>" + item.patientName + "</td><td>" + item.category + "</td><td>" + item.subCategory + "</td><td>" + item.transactionDateTime + "</td><td>" + item.paymentMode + "</td><td>" + item.amount + "</td><td>" + item.receiptNumber + "</td></tr>");
    });

    // Update the total amount paid in the table footer
    jQuery("#totalAmountPaid").text(totalAmountPaid);

    var table = jQuery("#cashierDetails").DataTable({
      dom: 'Bfrtip',
      buttons: [
        'copy', 'csv', 'excel',
        {
          extend: 'print',
          messageTop: 'Cashier revenue transactions.',
          customize: function (win) {
            jq(win.document.body).prepend(`${ui.includeFragment("patientdashboardapp", "printHeader")}`);
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

    jQuery('#cashierDetails tbody').on('click', 'tr', function () {
      console.log(table.row(this).data());
    });
  }
</script>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Cashier's Summaries</div>
        <div class="ke-panel-content">
        <br />
        <div class="row">
                <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                <label>Cashier</label>
                <select name="cashier" id="cashier">
                <option value="0">Please select a cashier</option>
                    <% cashier.each {%>
                        <option value="${it.userId}">${it.username}</option>
                    <% } %>
                </select>
                <label>&nbsp;&nbsp;&nbsp;</label><button id="filterCashier" type="button" class=" btn btn-primary right">${ui.message("Filter")}</button>
        </div>
        <br />
        <hr />
        <table border="0" cellpadding="0" cellspacing="0" id="cashierDetails" width="100%">
                    <thead>
                    <tr>
                        <th>Patient Name</th>
                        <th>Patient Category</th>
                        <th>Patient Sub category</th>
                        <th>Transaction Date and Time</th>
                        <th>Payment Mode</th>
                        <th>Amount Paid</th>
                        <th>Receipt Number</th>
                    </tr>
                    </thead>
                    <tbody id="cashierPatientSummaryItems"></tbody>
                     <tfoot>
                      <tr>
                        <td colspan="5">Totals:</td>
                        <td id="totalAmountPaid"></td>
                        <td></td>
                      </tr>
                    </tfoot>
                </table>
     </div>
</div>