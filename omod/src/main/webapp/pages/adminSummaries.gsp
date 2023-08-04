<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeCss("financials", "bootstrap.min.css")
    ui.includeCss("financials", "bootstrap-datetimepicker.min.css")
    ui.includeJavascript("ehrconfigs", "datetimepicker/bootstrap-datetimepicker.min.js")
    ui.includeJavascript("ehrconfigs", "bootstrap.min.js")
%>
<script type="text/javascript">
jQuery(function() {
    populateAdminDashboard();
    jq("#filter").click(function () {
      populateAdminDashboard();
    });
  });
function populateAdminDashboard() {
  const summaryFromDate = jq('#summaryFromDate-field').val(),
      summaryToDate = jq('#summaryToDate-field').val();
      jq.getJSON('${ui.actionLink("financials", "dashboard", "getAdminSummariesOnDateRange")}',
                {
                  "fromDate" : summaryFromDate,
                  "toDate" : summaryToDate,
                }
            ).success(function(data) {
              jq('.stat-digit').eq(0).html(data.opdVisits)
              jq('.stat-digit').eq(1).html(data.allVisits)
              jq('.stat-digit').eq(2).html(data.revisitPatients)
              jq('.stat-digit').eq(3).html(data.newPatients)
            });
}

</script>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Administrator Facility Overview</div>
        <div class="ke-panel-content">
            <br />
            <div class="row">
                <div class="col-12">
                    <div style="margin-top: -1px " class="onerow">
                        <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
                        <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                        <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                        <button id="filter" type="button" class=" btn btn-primary right">${ui.message("Filter")}</button>
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
                        <div class="col-md-4">
                          <div class="card-counter danger">
                              <i class="fa fa-users"></i>
                            <span class="count-name stat-text">OPD VISITS</span>
                            <span class="count-numbers stat-digit"></span>
                          </div>
                        </div>
                        <div class="col-md-4">
                          <div class="card-counter danger">
                              <i class="fa fa-users"></i>
                            <span class="count-name stat-text">ALL VISITS</span>
                            <span class="count-numbers stat-digit"></span>
                          </div>
                        </div>
                        <div class="col-md-4">
                          <div class="card-counter danger">
                              <i class="fa fa-users"></i>
                            <span class="count-name stat-text">RE-VISITS</span>
                            <span class="count-numbers stat-digit"></span>
                          </div>
                        </div>
                        <div class="col-md-4">
                          <div class="card-counter danger">
                              <i class="fa fa-users"></i>
                            <span class="count-name stat-text">NEW VISITS</span>
                            <span class="count-numbers stat-digit"></span>
                          </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<div>