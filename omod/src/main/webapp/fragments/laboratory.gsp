<script type="text/javascript">
  jQuery(function() {
    jQuery("#laboratory").DataTable();
  });
</script>

<div class="ke-panel-frame">
    <div class="ke-panel-heading">Patient Summary</div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <div class="row">
            <div class="col-12">
                <div class="row">
                    <div class="col-4" style="margin-bottom: 10px">
                        <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'labFromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    </div>
                    <div class="col-4" style="margin-bottom: 10px">
                        <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'labToDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                    </div>
                    <div class="col-4" style="margin-bottom: 10px">
                        <button id="filter" type="button" class=" btn btn-primary right">${ui.message("Filter")}
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
            <% if (labBills.empty) { %>
            <tr>
                <td colspan="8">
                    No records found for today
                </td>
            </tr>
            <% } %>
            <% labBills.each {%>
            <tr>
                <td>${it.transactionDate}</td>
                <td>${it.serviceOffered}</td>
                <td>${it.identifier}</td>
                <td>${it.patient}</td>
                <td>${it.category}</td>
                <td>${it.subCategory}</td>
                <td>${it.waiver}</td>
                <td>${it.actualAmount}</td>
                <td>${it.paidAmount}</td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>