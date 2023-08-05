<%  ui.decorateWith("kenyaemr", "standardPage")
    ui.includeCss("financials", "bootstrap.min.css")
    ui.includeCss("financials", "bootstrap-datetimepicker.min.css")
    ui.includeJavascript("ehrconfigs", "datetimepicker/bootstrap-datetimepicker.min.js")
    ui.includeJavascript("ehrconfigs", "bootstrap.min.js")
%>
<script type="text/javascript">
    jq(document).ready(function () {
        jq("#summaryTabs").tabs();
    });
</script>
<div class="ke-page-content">
    <div id="summaryTabs">
            <ul>
                <li id="load"><a href="#workLoad">Work Load</a></li>
                <li id="clinical"><a href="#clinical">Clinical Summaries</a></li>
                <li id="lab"><a href="#lab">Lab Summaries</a></li>
                <li id="pharm"><a href="#pharm">Pharmacy Summaries</a></li>
                <li id="revenue"><a href="#revenue">Revenue Summaries</a></li>
            </ul>
          <div id="workLoad">
                ${ ui.includeFragment("financials", "adminWorkLoadSummary")}
          </div>
          <div id="clinical">
                <p>Clinical summaries about diagnoses, procedures</p>
          </div>
          <div id="lab">
                <p>Lab summaries about freguent tests done, number of patients who pass</p>
          </div>
          <div id="pharm">
              <p>Pharmacy summaries
          </div>
          <div id="revenue">
              <p>Summarized revenue summaries</p>
          </div>

    </div>
</div>