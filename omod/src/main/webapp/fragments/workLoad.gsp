<script type="text/javascript">
  jQuery(function() {
    populateWorkLoadDetailsParameters();
    jq("#filterWorkLoad").click(function () {
      populateWorkLoadDetailsParameters();
    });
  });
  function populateWorkLoadDetails(fromDate, toDate) {
    var toReturn;
    jQuery.ajax({
      type: "GET",
      url: '${ui.actionLink("financials", "workLoad", "fetchWorkLoadSummariesByDateRange")}',
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
    return populateWorkLoadSummary(toReturn);
  }
  function populateWorkLoadSummary(data) {
    jQuery("#opdData").append(data.opd);
    jQuery("#spc").append(data.spc);
    jQuery("#ipd").append(data.ipd);
    jQuery("#mopc").append(data.mopc);
    jQuery("#lab").append(data.lab);
    jQuery("#procedure").append(data.procedure);
    jQuery("#radiology").append(data.radiology);

  }
  function populateWorkLoadDetailsParameters() {
    populateWorkLoadDetails(jQuery("#workLoadFromDate-field").val(), jQuery("#workLoadToDate-field").val());

  }
</script>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Facility Workload Summary</div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <div class="row">
            <div class="col-12">
                <div class="row">
                    <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'workLoadFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'workLoadToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    <button id="filterWorkLoad" type="button" class=" btn btn-primary right">${ui.message("Filter")}</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <hr />
        </div>
    </div>
    <div class="ke-panel-content">
        <table>
            <thead>
                <tr>
                    <th>Department</th>
                    <th>Work Load</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>OPD</td>
                    <td id="opdData"></td>
                </tr>
                <tr>
                    <td>IPD</td>
                    <td id="ipd"></td>
                </tr>
                <tr>
                    <td>Special Clinic</td>
                    <td id="spc"></td>
                </tr>
                <tr>
                    <td>MOPC</td>
                    <td id="mopc"></td>
                </tr>
            <tr>
                <td>Lab Investigation</td>
                <td id="lab"></td>
            </tr>
            <tr>
                <td>Procedures</td>
                <td id="procedure"></td>
            </tr>
            <tr>
                <td>Radiology</td>
                <td id="radiology"></td>
            </tr>
            </tbody>

        </table>
    </div>
</div>