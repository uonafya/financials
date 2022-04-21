<script type="text/javascript">
  jQuery(function() {
    var table = jQuery("#details").DataTable();
    var table1 = Query("#cummulative").DataTable();
  });
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



table#details.dataTable tbody tr:hover {
  background-color: #43fff8;
}

table#details.dataTable tbody tr:hover > .sorting_1 {
  background-color: #43fff8;
}
table1#cummulative.dataTable tbody tr:hover {
  background-color: #43fff8;
}

table1#cummulative.dataTable tbody tr:hover > .sorting_1 {
  background-color: #43fff8;
}
</style>
<div class="ke-panel-frame" style="background-color: #ffffff">
    <div class="ke-panel-heading">Financial Report and Statements today</div>
    <br />
    <table cellspacing="0" cellpadding="5" width="100%">
        <tr>
            <td width="40%" valign="top">
                <div class="ke-panel-frame">
                    <div class="ke-panel-heading">Cummulative Summaries - today</div>
                    <div class="ke-panel-content">
                        <table border="0" cellpadding="0" cellspacing="0" id="cummulative" width="40%" style="background-color: #F3F9FF;">
                            <thead>
                            <tr>
                                <th>Department</th>
                                <th>Commulative total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% if(totalSumPerDepartiment.empty) { %>
                            <tr>
                                <td colspan="2">
                                    No records to display
                                </td>
                            </tr>
                            <%}%>
                            </tbody>
                            <% totalSumPerDepartiment.each { name, value -> %>
                            <tr>
                                <td>${name} </td>
                                <td>${value}</td>
                            </tr>
                            <%}%>
                        </table>

                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="ke-panel-frame">
                    <div class="ke-panel-heading">Patient Summary - today</div>
                    <div class="ke-panel-content" style="background-color: #F3F9FF;">
                        <table border="0" cellpadding="0" cellspacing="0" id="details" width="100%">
                            <thead>
                            <tr>
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
                            <tbody>
                            <% if (bills.empty) { %>
                            <tr>
                                <td colspan="7">
                                    No records found for today
                                </td>
                            </tr>
                            <% } %>
                            <% bills.each {%>
                            <tr>
                                <td>${it.transactionDate}</td>
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
            </td>
        </tr>
    </table>
</div>