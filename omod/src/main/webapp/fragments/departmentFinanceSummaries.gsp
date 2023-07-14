<script type="text/javascript">
    jQuery(function() {
            populateDepartmentDetails();
            jQuery('#dDetails tbody').on( 'click', 'tr', function () {
                console.log( table.row( this ).data() );
            } );
            jQuery('#dept').on('change', function() {
                populateDepartmentDetails();
            });
    });
    function populateDepartmentDetails() {
        fetchDepartmentSummariesByDateRangeAndDepartment(jQuery("#summaryFromDate-field").val(), jQuery("#summaryToDate-field").val(), jQuery("#dept").val());

      }
      function fetchDepartmentSummariesByDateRangeAndDepartment(fromDate, toDate, dept) {
            var toReturn;
            jQuery.ajax({
                type: "GET",
                url: '${ui.actionLink("financials", "departmentFinanceSummaries", "getDepartmentsSummaries")}',
                dataType: "json",
                global: false,
                async: false,
                data: {
                    fromDate: fromDate,
                    toDate: toDate,
                    dept: dept
                },
                success: function (data) {
                    toReturn = data;
                }
            });
            return populateTableBodyDepartmentSummary(toReturn);
        }
        
        function populateTableBodyDepartmentSummary(data) {
            jQuery("#departmentSummaryDetails").DataTable().clear().destroy();

            var amountCollectedTotal = 0;

            jQuery("#departmentTblBody").empty();

            data.map((item) => {
                amountCollectedTotal += item.totalAmount;

                jQuery("#departmentTblBody").append("<tr><td>" + item.transactionDate + "</td><td>" + item.department + "</td><td>" + item.servicePaidFor + "</td><td>" + item.totalAmount + "</td></tr>");
            });

            // Update the total cell in the table footer
            jQuery("#amountCollectedTotal").text(amountCollectedTotal);

            initDepartmentDataTable();
        }

      function initDepartmentDataTable() {
            var table = jQuery("#departmentSummaryDetails").DataTable({
                dom: 'Bfrtip',
                "oLanguage": {
                    "oPaginate": {
                        "sNext": '<i class="fa fa-chevron-right py-1" ></i>',
                        "sPrevious": '<i class="fa fa-chevron-left py-1" ></i>'
                    }
                },
                buttons: ['copy', 'csv', 'excel',
                    {   extend: 'print',
                        messageTop: 'Departmental revenue transactions.',
                        customize: function ( win ) {
                            jQuery(win.document.body)
                                .prepend(`${ ui.includeFragment("patientdashboardapp", "printHeader") }`);
                        },
                        repeatingHead: {
                            logo: '${ui.resourceLink('ehrinventoryapp', 'images/kenya_logo.bmp')}',
                            logoPosition: 'center',
                            logoStyle: ''
                        },
                        title: ''
                    }
                ]
            });
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



table#dDetails.dataTable tbody tr:hover {
    background-color: #43fff8;
}

table#dDetails.dataTable tbody tr:hover > .sorting_1 {
    background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Departmental Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <div style="margin-top: -1px " class="onerow">
            <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
            <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
            <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
            <label for="dept">Department</label>
            <select name="dept" id="dept" style="width: 200px;">
                <option value="0">All</option>
                <% departments.each { dept -> %>
                <option value="${dept.id}">${dept.name}</option>
                <% } %>
            </select>
        </div>
        <table id="departmentSummaryDetails">
            <thead>doctorsSummaries
            <tr>
                <td>Transaction date</td>
                <td>Department</td>
                <td>Service</td>
                <td>Amount collected</td>
            </tr>
            </thead>
            <tbody id="departmentTblBody">
            </tbody>
            <tfoot>
                <tr>
                <td colspan="3">Totals:</td>
                <td id="amountCollectedTotal"></td>
                </tr>
            </tfoot>
        </table>

    </div>
</div>
