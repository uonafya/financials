<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeCss("financials", "bootstrap.min.css")
    ui.includeCss("financials", "bootstrap-datetimepicker.min.css")
    ui.includeJavascript("ehrconfigs", "datetimepicker/bootstrap-datetimepicker.min.js")
    ui.includeJavascript("ehrconfigs", "bootstrap.min.js")
    ui.includeCss("ehrconfigs", "referenceapplication.css")
%>
<script type="text/javascript">
    jq(document).ready(function () {
        jq("#summaryTabs").tabs();
    });
</script>
<div class="ke-page-content">
  <div class="ke-panel-frame">
      <div class="ke-panel-heading">Administrator Facility Overview</div>
          <div class="ke-panel-content">
              <div class="row">
                  <div class="col-9">
                      <div style="margin-top: -1px " class="onerow">
                          <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
                          <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                          <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                          <button id="lfilter" type="button" class=" btn btn-primary right">${ui.message("Filter")}</button>
                      </div>
                  </div>
              </div>
              <div id="summaryTabs">
                      <ul>
                          <li id="load"><a href="#workLoad">Work Load</a></li>
                          <li id="clinical"><a href="#clinicalSummary">Clinical Summaries</a></li>
                          <li id="lab"><a href="#labSummary">Lab Summaries</a></li>
                          <li id="pharm"><a href="#pharmSummary">Pharmacy Summaries</a></li>
                          <li id="revenue"><a href="#revenueSummary">Revenue Summaries</a></li>
                      </ul>
                    <div id="workLoad">
                          ${ ui.includeFragment("financials", "adminWorkLoadSummary")}
                    </div>
                    <div id="clinicalSummary">
                          <p>Clinical summaries about diagnoses, procedures</p>
                    </div>
                    <div id="labSummary">
                          <p>Lab summaries about freguent tests done, number of patients who pass</p>
                    </div>
                    <div id="pharmSummary">
                        <p>Pharmacy summaries
                    </div>
                    <div id="revenueSummary">
                        <p>Summarized revenue summaries</p>
                    </div>
              </div>
          </div>
      </div>
</div>