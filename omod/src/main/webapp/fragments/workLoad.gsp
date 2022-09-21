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
        console.log(data);
      }
    });
    return populateWorkLoadSummary(toReturn);
  }
  function populateWorkLoadSummary(data) {
    jQuery("#opdData").empty().append(data.opd);
    jQuery("#spc").empty().append(data.spc);
    jQuery("#ipd").empty().append(data.ipd);
    jQuery("#mopc").empty().append(data.mopc);
    jQuery("#lab").empty().append(data.lab);
    jQuery("#procedure").empty().append(data.procedure);
    jQuery("#radiology").empty().append(data.radiology);
    jQuery("#dental").empty().append(data.dental);
    jQuery("#eye").empty().append(data.eye);
    jQuery("#ent").empty().append(data.ent);
    jQuery("#mchm").empty().append(data.mchm);
    jQuery("#mchc").empty().append(data.mchc);
    jQuery("#deliveries").empty().append(data.deliveries);
    jQuery("#immunization").empty().append(data.immunization);
    jQuery("#anc").empty().append(data.anc);
    jQuery("#pnc").empty().append(data.pnc);
    jQuery("#preventiveServices").empty().append(data.preventiveServices);

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

            <tr>
                <td>Dental Department</td>
                <td id="dental"></td>
            </tr><tr>
                <td>Eye Department</td>
                <td id="eye"></td>
            </tr></tr>
            <tr>
                <td>ENT Department</td>
                <td id="ent"></td>
            </tr>
            <tr>
                <td>MCH Mothers</td>
                <td id="mchm"></td>
            </tr>
            <tr>
                <td>MCH Children</td>
                <td id="mchc"></td>
            </tr>
            <tr>
                <td>Deliveries</td>
                <td id="deliveries"></td>
            </tr>
            <tr>
                <td>Immunizations</td>
                <td id="immunization"></td>
            </tr>
            <tr>
                <td>ANC</td>
                <td id="anc"></td>
            </tr>
            <tr>
                <td>PNC</td>
                <td id="pnc"></td>
            </tr>
            <tr>
                <td>Mothers Preventive Services</td>
                <td id="preventiveServices"></td>
            </tr>
            </tbody>

        </table>
    </div>
</div>