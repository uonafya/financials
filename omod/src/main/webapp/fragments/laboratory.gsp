<script type="text/javascript">
  jQuery(function() {
    jQuery("#laboratory").DataTable();
    populateLaboratoryDetails();
    jq("#filterLaboratory").click(function () {
      populateLaboratoryDetails();
    });
  });
  function populateLaboratoryDetails() {
    const fromDate = jq('#labFromDate-field').val(),
        toDate = jq('#labToDate-field').val();
  }
</script>

<div class="ke-panel-frame">
    <div class="ke-panel-heading">Patient Laboratory Summary</div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <div class="row">
            <div class="col-12">
                <div class="row">
                    <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'labFromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'labToDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    <button id="filterLaboratory" type="button" class=" btn btn-primary right">${ui.message("Filter")}
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
        <table border="0" cellpadding="0" cellspacing="0" id="laboratory" width="100%">
            <thead>
            <tr>
                <th>Transaction Date</th>
                <th>Lab test</th>
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
            <tbody id="labTbody">
            </tbody>
        </table>
    </div>
</div>