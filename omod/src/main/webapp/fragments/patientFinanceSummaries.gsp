<script type="text/javascript">
    jq = jQuery
    jq(document).ready(function() {
        getBills();
    });

    function getBills(){
        jq.getJSON('${ ui.actionLink("financials", "patientFinanceSummaries", "getPatientBillsByDateTimeRange") }', {
            startDate:jq("#summaryFromDate").val(),
            endDate: jq("#summaryToDate").val()
        }).success(function(data) {
            populateTable(data);
        });

    }
    function populateTable(data) {
        jq('#pDetails').DataTable().clear().destroy();
        if(data) {
            data.map((item) => {
                jq('#tbody').append("<tr><td>" + item.billId + "</td><td>" + item.patientId + "</td><td>" + item.transactionDate + "</td> <td>" + item.identifier + "</td><td>" + item.patient + "</td> <td>" + item.category + "</td><td>" + item.subCategory + "</td><td>" + item.waiver + "</td>><td>" + item.actualAmount + "</td>><td>" + item.paidAmount + "</td> </tr>");
            });

            var table =  jq("#pDetails").DataTable();
            jq('#pDetails tbody').on('click', 'tr', function () {
                var billId = table.row(this).data();
                ui.navigate('financials', 'billedItems', {billedId: billId[0], patientId: billId[1]});
            });

        }else {
            jq('#tbody').append("<tr><td colspan='10'>" +  "No records found for this patient" +  "</td></tr>");
            jq("#pDetails").DataTable();

        }
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



table#pDetails.dataTable tbody tr:hover {
    background-color: #43fff8;
}

table#pDetails.dataTable tbody tr:hover > .sorting_1 {
    background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Patient Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
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
                        <button id="filter" type="button"  onclick="getBills()"  class=" btn btn-primary right">${ui.message("Filter")}
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
        <table id="pDetails">
            <thead>
            <tr>
                <td>Service Id</td>
                <td>Patient Id</td>
                <th>Transaction Date</th>
                <th>Patient Identifier</th>
                <th>Patient Names</th>
                <th>Category</th>
                <th>SubCategory</th>
                <th>Waiver Amount</th>
                <th>Actual Amount</th>
                <th>Paid Amount</th>
            </tr>
            </thead>
            <tbody id="tbody">
            </tbody>
        </table>
        </div>
</div>
