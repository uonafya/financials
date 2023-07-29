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

    jq("#workloadDetails").DataTable();

  }
  function populateWorkLoadDetailsParameters() {
    populateWorkLoadDetails(jQuery("#workLoadFromDate-field").val(), jQuery("#workLoadToDate-field").val());
  }
</script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<style type="text/css">
table#pDetails.dataTable tbody tr:hover {
  background-color: #43fff8;
}
.card-counter{
    box-shadow: 2px 2px 10px #DADADA;
    margin: 5px;
    padding: 20px 10px;
    background-color: #fff;
    height: 100px;
    border-radius: 5px;
    transition: .3s linear all;
}

.card-counter:hover{
    box-shadow: 4px 4px 20px #DADADA;
    transition: .3s linear all;
}

.card-counter.primary{
    background-color: #B0E0E6;
    color: black;
}

.card-counter.danger{
    background-color: #E6E6FA;
    color: black;
}

.card-counter.pham{
    background-color: #E0FFFF;
    color: black;
}

.card-counter.success{
    background-color: #A9FF96;
    color: black;
}

.card-counter.info{
    background-color: #FFA07A;
    color: black;
}

.card-counter i{
    font-size: 2.5em;
    opacity: 0.2;
}

.card-counter .count-numbers{
    position: absolute;
    right: 35px;
    top: 20px;
    font-size: 20px;
    display: block;
}

.card-counter .count-name{
    position: absolute;
    right: 35px;
    top: 65px;
    font-style: italic;
    text-transform: capitalize;
    opacity: 0.5;
    display: block;
    font-size: 15px;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Facility Workload Summary</div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <div class="row">
            <div class="col-12">
                <div style="margin-top: -1px " class="onerow">
                    <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
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
    <div class="row" style="background-color: #fff;">
        <div class="col-12">
            <div class="row">

                <div class="col-md-4">
                    <div class="card-counter danger">
                        <i class="fa fa-users"></i>
                        <span class="count-name stat-text">TOTAL PATIENTS</span>
                        <span class="count-numbers stat-digit"></span>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card-counter pham">
                        <i class="fa fa-user-plus"></i>
                        <span class="count-name stat-text">TOTAL NEW PATIENTS</span>
                        <span class="count-numbers stat-digit"></span>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card-counter success">
                        <i class="fa fa-user-circle-o"></i>
                        <span class="count-name stat-text">TOTAL REVISIT PATIENTS</span>
                        <span class="count-numbers stat-digit"></span>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card-counter info">
                        <i class="fa fa fa-male"></i>
                        <span class="count-name stat-text">TOTAL MALE PATIENT</span>
                        <span class="count-numbers stat-digit"></span>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card-counter primary">
                        <i class="fa fa-female"></i>
                        <span class="count-name stat-text">TOTAL FEMALE PATIENT</span>
                        <span class="count-numbers stat-digit"></span>
                    </div>
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
        <table border="0" cellpadding="0" cellspacing="0" id="workloadDetails" width="100%">
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