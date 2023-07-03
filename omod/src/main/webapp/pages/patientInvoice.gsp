<%
    ui.decorateWith("kenyaemr", "standardPage", [patient: currentPatient])
    ui.includeCss("financials", "jquery.dataTables.min.css")
    ui.includeCss("financials", "bootstrap.min.css")
    ui.includeCss("financials", "bootstrap-print.css")


    ui.includeJavascript("ehrconfigs", "bootstrap.min.js")
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeJavascript("patientdashboardapp", "jq.print.js")


%>
<script type="text/javascript">

    var jq = jQuery;

    jq = jQuery
    jq(document).ready(function() {
        getBills();
    });

    function calculateTotals() {
        var table = document.getElementById("invoice-items");
        var tbody = table.getElementsByTagName("tbody")[0];
        var rows = tbody.getElementsByTagName("tr");

        var totalActualAmount = 0;

        for (var i = 0; i < rows.length; i++) {
            var actualAmountCell = rows[i].querySelector("td[id='actual-amount']");
            var actualAmount = parseFloat(actualAmountCell.textContent.trim());
            
            if (!isNaN(actualAmount)) {
            totalActualAmount += actualAmount;
            }
        }

        var totalActualAmountCell = document.getElementById("total-actual-amount");
        totalActualAmountCell.textContent = totalActualAmount.toFixed(2);
    }

    function getBills(){
        jq.getJSON('${ ui.actionLink("financials", "patientFinanceSummaries", "getItemizedPatientBillsByDateTimeRange") }', {
            patientId: jq("#patientId").val(),
            fromDate:jq("#summaryFromDate-field").val(),
            toDate: jq("#summaryToDate-field").val()
        }).success(function(data) {
            populateTable(data);
            calculateTotals();
        });

    }

    function populateTable(data) {
        jq('#invoice-items').DataTable().clear().destroy();
        if (data) {
            data.map((it) => {
                jq('#tbody').append("<tr><td>" + it.patientServiceBill.patientServiceBillId + "</td><td>" + it.createdDate + "</td><td>" + it.name + "</td> <td>" + it.quantity + "</td><td>" + it.unitPrice + "</td> <td>" + it.actualAmount + "</td><td>" + it.patientServiceBill.waiverAmount  + "</td> </tr>");
            });
            jq('#invoice-items').DataTable({
                searchPanes: false,
                searching: false,
                'dom': ''
            });
        } else {
            jq('#tbody').append("<tr><td colspan='10'>" + "No records found for this patient" + "</td></tr>");
            jq('#invoice-items').DataTable({
                searchPanes: false,
                searching: false,
                'dom': ''
            });

        }
    }

    function printInvoice() {
        jq("#invoice-detail").print({
            globalStyles: false,
            mediaPrint: false,
            iframe: false,
            width: 600,
            height: 700
        });
    }

</script>
<br />
<div>
    <table cellpadding="0" cellspacing="0" border="0" align="center">
        <tr>
            <td>
                <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                &nbsp;&nbsp;&nbsp;&nbsp;
                <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button id="filter" type="button"  onclick="getBills()"  class=" btn btn-primary right">${ui.message("Filter")}
                </button>
            </td>
            <input id="patientId" value="${patient.getPatientId()}" style="display: none">
        <tr>
    </table>
</div>
<br/>
<div id="invoice-detail">
    <div id="person-detail">
        <div style="text-align: center;">
            ${ui.includeFragment("patientdashboardapp", "printHeader")}
        </div>
        <div style="margin-left: 13%">
            <h3>PATIENT SUMMARY INFORMATION</h3>

            <label>
                <span class='status active'></span>
                Identifier:
            </label>
            <span>${patient.getPatientIdentifier()}</span>
            <br/>

            <label>
                <span class='status active'></span>
                Full Names:
            </label>
            <span>${patient.givenName} ${patient.familyName} ${patient.middleName ? patient.middleName : ''}</span>
            <br/>

            <label>
                <span class='status active'></span>
                Age:
            </label>
            <span>${patient.age} (${ui.formatDatePretty(patient.birthdate)})</span>
            <br/>

            <label>
                <span class='status active'></span>
                Gender:
            </label>
            <span>${patient.gender}</span>
            <br/>
        </div>
    </div>
    <table id="invoice-items" cellpadding="0" cellspacing="0" width=75%>
        <thead>
        <tr>
            <th>Receipt ID</th>
            <th>Transaction Date</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Unit price</th>
            <th id="actual-amount">Actual Amount</th>
            <th>Waiver Amount</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
         <tfoot>
            <tr>
                <td><b>Totals</b></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td id="total-actual-amount"></td>
                <td id="total-waiver-amount"></td>
            </tr>
         </tfoot>
    </table>

</div>
<div>
    <button id="printInvoiceBtn"  onclick="printInvoice()" class="info" style="float: right; margin: 50px;">
        <i class="btn-info"></i>
        Print Invoice
    </button>
</div>
</div>

