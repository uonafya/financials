<script type="text/javascript">
  jQuery(function() {
    jq("#regDetails").DataTable();
    populateRegistrationDetails();
    jq("#filterRegistration").click(function () {
      populateRegistrationDetails();
    });
  });
  function populateRegistrationDetails() {
    const fromDate = jq('#regFromDate-field').val(),
        toDate = jq('#regFromDate-field').val();
    jq.getJSON('${ui.actionLink("financials", "Summary", "getPatientServiceBillByDepartmentTotals")}',
        {
          "fromDate" : fromDate,
          "toDate" : toDate,
        }
    ).success(function(data) {
      jq('#regFees').html(data.regFees)
      jq('#revFees').html(data.revFees)
      jq('#specialFees').html(data.specialFees)
  });
    jq('#regDetails').DataTable().clear().destroy();
    jq.getJSON('${ui.actionLink("financials", "Summary", "getPatientServiceBillByDepartmentTable")}',
        {
          "fromDate" : fromDate,
          "toDate" : toDate,
        }
    ).success(function(regData) {
      jq("#regDetails").DataTable();
      regData.map((item) => {
        jq('#regTbody').append("<tr><td>" + item.transactionDate + "</td><td>" + item.serviceOffered + "</td><td>" + item.identifier + "</td> <td>" + item.patient + "</td><td>" + item.category + "</td> <td>" + item.subCategory + "</td><td>" + item.waiver + "</td>><td>" + item.actualAmount + "</td>><td>" + item.paidAmount + "</td> </tr>");
      });

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



table#details.dataTable tbody tr:hover {
  background-color: #43fff8;
}

table#details.dataTable tbody tr:hover > .sorting_1 {
  background-color: #43fff8;
}
</style>
<div class="ke-panel-frame" style="background-color: #ffffff">
    <table cellspacing="0" cellpadding="5" width="100%">
        <tr>
            <td width="40%" valign="top">
                <div class="ke-panel-frame">
                    <div class="ke-panel-heading">Cumulative Summaries</div>
                    <div class="ke-panel-content">
                        <br />
                        <div class="row">
                            <div class="col-12">
                                <div class="row">
                                    <div class="col-4" style="margin-bottom: 10px">
                                        <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'regFromDate', id: 'regFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                                    </div>
                                    <div class="col-4" style="margin-bottom: 10px">
                                        <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'regToDate',    id: 'regToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                                    </div>
                                    <div class="col-4" style="margin-bottom: 10px">
                                        <button id="filterRegistration" type="button" class=" btn btn-primary right">${ui.message("Filter")}
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
                        <div class="row">
                            <div class="col-12">
                                <div class="row">
                                    <div class="col-4" style="margin-bottom: 10px;">
                                        <div class="card" style="height: 100%; background: #43fff8">
                                            <div class="stat-widget-one">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="stat-text">General Registration Fees</div>
                                                        <div class="stat-digit" id="regFees"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-4" style="margin-bottom: 10px;">
                                        <div class="card" style="height: 100%; background: #E6E6FA">
                                            <div class="stat-widget-one">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="stat-text">Revisit fees</div>
                                                        <div class="stat-digit" id="revFees"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-4" style="margin-bottom: 10px;">
                                        <div class="card" style="height: 100%; background: #DAF7A1">
                                            <div class="stat-widget-one">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="stat-text">Special Clinic fees</div>
                                                        <div class="stat-digit" id="specialFees"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="ke-panel-frame">
                    <div class="ke-panel-heading">Patient Summary</div>
                    <div class="ke-panel-content" style="background-color: #F3F9FF;">
                        <table border="0" cellpadding="0" cellspacing="0" id="regDetails" width="100%">
                            <thead>
                            <tr>
                                <th>Transaction Date</th>
                                <th>Service Offered</th>
                                <th>Patient Identifier</th>
                                <th>Patient Names</th>
                                <th>Category</th>
                                <th>SubCategory</th>
                                <th>Waiver Amount</th>
                                <th>Actual Amount</th>
                                <th>Paid Amount</th>
                            </tr>
                            </thead>
                            <tbody id="regTbody">
                            </tbody>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>