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

    jq(document).ready(function () {
        jq('#invoice-items').DataTable({
            searchPanes: false,
            searching: false,
            'dom': ''
        });
    });

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

<div class="p-20">
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
    <br/>
    <div id="invoice-detail">
        <div id="person-detail">
            <div style="text-align: center;">
                <center>
                    <img src="/openmrs/ms/uiframework/resource/ehrinventoryapp/images/kenya_logo.bmp" width="60" height="60" align="middle">
                </center>
                ${ui.includeFragment("patientdashboardapp", "printHeader")}
            </div>

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
        <table id="invoice-items">
            <thead>
            <tr>
                <th>Receipt ID</th>
                <th>Transaction Date</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Unit price</th>
                <th>Actual Amount</th>
                <th>Waiver Amount</th>
            </tr>
            </thead>
            <tbody>
            <% if (billedItems.empty) { %>
            <tr>
                <td colspan="7">
                    No records found for this patirnt
                </td>
            </tr>
            <% } %>

            <% billedItems.each { %>
            <tr>
                <td>${it.patientServiceBill.receipt.id}</td>
                <td>${it.createdDate}</td>
                <td>${it.name}</td>
                <td>${it.quantity}</td>
                <td>${it.unitPrice}</td>
                <td>${it.actualAmount}</td>
                <td>${it.patientServiceBill.waiverAmount}</td>

            </tr>
            <% } %>
            </tbody>
        </table>

    </div>
    <div>
        <button id="printInvoiceBtn"  onclick="printInvoice()" class="info" style="float: right; margin: 50px;">
            <i class="btn-info"></i>
            Print Invoice
        </button>
    </div>
</div>

