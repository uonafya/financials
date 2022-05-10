<script type="text/javascript">
  jQuery(function() {
    var table = jQuery("#details").DataTable();
    var table1 = Query("#cummulative").DataTable();
  });
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
table1#cummulative.dataTable tbody tr:hover {
  background-color: #43fff8;
}

table1#cummulative.dataTable tbody tr:hover > .sorting_1 {
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
                                        <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                                    </div>
                                    <div class="col-4" style="margin-bottom: 10px">
                                        <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                                    </div>
                                    <div class="col-4" style="margin-bottom: 10px">
                                        <button id="filter" type="button" class=" btn btn-primary right">${ui.message("Filter")}
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
                                                        <div class="stat-digit">${registrationFees}</div>
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
                                                        <div class="stat-digit">${revisitFees}</div>
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
                                                        <div class="stat-digit">${specialClinicFees}</div>
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
                        <table border="0" cellpadding="0" cellspacing="0" id="details" width="100%">
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
                            <tbody>
                            <% if (bills.empty) { %>
                            <tr>
                                <td colspan="8">
                                    No records found for today
                                </td>
                            </tr>
                            <% } %>
                            <% bills.each {%>
                            <tr>
                                <td>${it.transactionDate}</td>
                                <td>${it.serviceOffered}</td>
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
            </td>
        </tr>
    </table>
</div>