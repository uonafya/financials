<%
    ui.decorateWith("kenyaemr", "standardPage", [patient: currentPatient])
    ui.includeCss("financials", "jquery.dataTables.min.css")
    ui.includeCss("financials", "bootstrap.min.css")

    ui.includeJavascript("ehrconfigs", "bootstrap.min.js")
    ui.includeJavascript("financials", "jquery.dataTables.min.js")

%>

<script type="text/javascript">
    jq(document).ready(function () {
        var jq = jQuery;
        jq('#invoice-items').DataTable({
            searchPanes: false,
            searching: false,
            'dom': ''
        });
    });

</script>

<div class="p-20">
    <div id="invoice-detail">
        <div id="person-detail">
            <img width="60" height="60" align="center"
                 src="${ui.resourceLink('ehrinventoryapp', 'images/kenya_logo.bmp')}">

            <div style="text-align: center;">
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
</div>

