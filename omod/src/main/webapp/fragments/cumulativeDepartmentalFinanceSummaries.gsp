<script type="text/javascript">
    jq(function() {

        var summaryData =  getCumulativeSummariesByDateRange();
        var table =  jQuery("#summaryDetails").DataTable(
                {
                    dom: 'Bfrtip',
                    buttons: ['copy', 'csv', 'excel',
                        {   extend: 'print',
                            messageTop: 'Cumulative revenue.',
                            customize: function ( win ) {
                                jq(win.document.body)
                                    .prepend(`${ ui.includeFragment("patientdashboardapp", "printHeader") }`);
                            },
                            repeatingHead: {
                                logo: '${ui.resourceLink('ehrinventoryapp', 'images/kenya_logo.bmp')}',
                                logoPosition: 'center',
                                logoStyle: ''
                            },
                            title: ''
                        }
                        ],
                    initComplete: function (){

                        jq(this.api().table().container()).find('input[type="search"]').parent().wrap('<form>').parent().attr('autocomplete','off').css('overflow','hidden').css('margin','auto');

                    }
                }

            );


        jq('#summaryDetails tbody').on( 'click', 'tr', function () {
            console.log( table.row( this ).data() );
        } );

        function SummariesDataListView() {
            var self = this;
            self.departmentSummaries = ko.observableArray([]);
            self.departmentSummaries(summaryData);
        }

        function updateTable() {
            summaryData="";
             summaryData=getCumulativeSummariesByDateRange(jq("#cashier").val().trim(),moment(jq("#summaryFromDate-field").val()).format('YYYY-MM-DD'), moment(jq('#summaryToDate-field').val()).format('YYYY-MM-DD'));
             list.departmentSummaries(summaryData);

        }

        jq('#summaryFromDate').on('change',function(){
            updateTable();
        });

        jq('#summaryToDate').on('change',function(){
            updateTable();
        });

        var list = new SummariesDataListView();
        ko.applyBindings(list, jq("#summaryDetails")[0]);

    });


    function getCumulativeSummariesByDateRange(cashier, fromDate, toDate) {
        var toReturn;
        jQuery.ajax({
            type: "GET",
            url: '${ui.actionLink("financials", "cumulativeDepartmentalFinanceSummaries", "fetchCumulativeSummariesByDateRange")}',
            dataType: "json",
            global: false,
            async: false,
            data: {
                cashier: cashier,
                fromDate: fromDate,
                toDate: toDate
            },
            success: function (data) {
                toReturn = data;
            }
        });
        return toReturn;
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



table#summaryDetails.dataTable tbody tr:hover {
    background-color: #43fff8;
}

table#summaryDetails.dataTable tbody tr:hover > .sorting_1 {
    background-color: #43fff8;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">DEPARTMENT WISE REVENUE BREAKDOWN SUMMARY</div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">
        <div style="margin-top: -1px " class="onerow">
            <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
            <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
            <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}

            <label for="cashier">&nbsp;&nbsp;Cashier:&nbsp; </label>
            <select name="cashier" id="cashier" style="width: 200px;">
                <option value="0">ALL</option>
                <% cashiers.each { cashier -> %>
                <option value="${cashier.userId}">${cashier.username}</option>
                <% } %>
            </select>

        </div>
        <table id="summaryDetails" >
            <thead>
            <tr>
                <td>#</td>
                <td>Department</td>
                <td>Amount collected</td>
            </tr>
            </thead>
            <tbody data-bind="foreach: departmentSummaries">
            <tr>
                <td data-bind="text: \$index() + 1"></td>
                <td data-bind="text: department"></td>
                <td data-bind="text: totalAmount"></td>
            </tr>
            </tbody>
        </table>

    </div>
</div>