<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeJavascript("ehrconfigs", "datetimepicker/bootstrap-datetimepicker.min.js")
    ui.includeJavascript("ehrconfigs", "bootstrap.min.js")
    ui.includeJavascript("ehrconfigs", "popper.min.js")
    ui.includeCss("financials", "bootstrap.min.css")
    ui.includeCss("financials", "bootstrap-datetimepicker.min.css")
    ui.includeCss("ehrconfigs", "referenceapplication.css")
%>
<script type="text/javascript">
    jq(document).ready(function () {
        jq("#summaryTabs").tabs();
        populateTableBodyForClinicalDiagnosisSummary("8d4918b0-c2cc-11de-8d13-0010c6dffd0f", "ba45c278-f290-11ea-9666-1b3e6e848887");
        populateTableBodyForClinicalProceduresSummary("8d490bf4-c2cc-11de-8d13-0010c6dffd0f", "ba45c278-f290-11ea-9666-1b3e6e848887");
        populateTableBodyForClinicalLabTestsSummary("8d4907b2-c2cc-11de-8d13-0010c6dffd0f", "11d3f37a-f282-11ea-a825-1b5b1ff1b854");
        populateTableBodyForClinicalRadiologyOrdersSummary("8caa332c-efe4-4025-8b18-3398328e1323", "012bb9f4-f282-11ea-a6d6-3b4fa4aefb5a");
        jq("#lfilter").click(function () {
          populateTableBodyForClinicalDiagnosisSummary("8d4918b0-c2cc-11de-8d13-0010c6dffd0f", "ba45c278-f290-11ea-9666-1b3e6e848887");
          populateTableBodyForClinicalProceduresSummary("8d490bf4-c2cc-11de-8d13-0010c6dffd0f", "ba45c278-f290-11ea-9666-1b3e6e848887");
          populateTableBodyForClinicalLabTestsSummary("8d4907b2-c2cc-11de-8d13-0010c6dffd0f", "11d3f37a-f282-11ea-a825-1b5b1ff1b854");
          populateTableBodyForClinicalRadiologyOrdersSummary("8caa332c-efe4-4025-8b18-3398328e1323", "012bb9f4-f282-11ea-a6d6-3b4fa4aefb5a");
        });
    });
    function fetchClinicalSummariesByDateRange(uuid, encounterType) {
        var toReturn;
        jQuery.ajax({
          type: "GET",
          url: '${ui.actionLink("financials", "generalSummaries", "fetchClinicalSummariesByDateRange")}',
          dataType: "json",
          global: false,
          async: false,
          data: {
            fromDate: jq("#summaryFromDate-field").val(),
            toDate: jq('#summaryToDate-field').val(),
            uuid: uuid,
            enType: encounterType
          },
          success: function (data) {
            toReturn = data;
          }
        });
        return toReturn;
      }
      function populateTableBodyForClinicalDiagnosisSummary(uuid, enType) {
          jQuery("#diagnosisTbody").empty();
          fetchClinicalSummariesByDateRange(uuid, enType).map((item) => {
          jQuery("#diagnosisTbody").append("<li>" + item.conceptName +" "+ item.listSize + "</li>");
        });
      }
      function populateTableBodyForClinicalProceduresSummary(uuid, enType) {
          jQuery("#proceduresTbody").empty();
          fetchClinicalSummariesByDateRange(uuid, enType).map((item) => {
          jQuery("#proceduresTbody").append("<li>" + item.conceptName +" "+ item.listSize + "</li>");
        });
      }
      function populateTableBodyForClinicalLabTestsSummary(uuid, enType) {
          jQuery("#laboratoryTbody").empty();
          fetchClinicalSummariesByDateRange(uuid, enType).map((item) => {
          jQuery("#laboratoryTbody").append("<li>" + item.conceptName +" "+ item.listSize + "</li>");
        });
      }
      function populateTableBodyForClinicalRadiologyOrdersSummary(uuid, enType) {
          jQuery("#radiologyTbody").empty();
          fetchClinicalSummariesByDateRange(uuid, enType).map((item) => {
          jQuery("#radiologyTbody").append("<li>" + item.conceptName +" "+ item.listSize + "</li>");
        });
      }
</script>
<div class="ke-page-content">
  <div class="ke-panel-frame">
      <div class="ke-panel-heading">Administrator Facility Overview</div>
          <div class="ke-panel-content">
              <div class="row">
                  <div class="col-6">
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
                          <li id="revenue"><a href="#revenueSummary">Revenue Summaries</a></li>
                      </ul>
                    <div id="workLoad">
                          ${ ui.includeFragment("financials", "adminWorkLoadSummary")}
                    </div>
                    <div id="clinicalSummary">
                          ${ ui.includeFragment("financials", "adminClinicalSummary")}
                    </div>
                    <div id="revenueSummary">
                        ${ ui.includeFragment("financials", "adminRevenueSummary")}
                    </div>
              </div>
          </div>
      </div>
</div>