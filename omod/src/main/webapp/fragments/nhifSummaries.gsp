<%
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
    ui.includeJavascript("ehrconfigs", "knockout-3.4.0.js")
    ui.includeJavascript("ehrcashier", "moment.js")
    ui.includeJavascript("financials", "dataTables.buttons.min.js")
    ui.includeJavascript("financials", "pdfmake.min.js")
    ui.includeJavascript("financials", "vfs_fonts.js")
    ui.includeJavascript("financials", "buttons.html5.js")
    ui.includeJavascript("financials", "buttons.print.min.js")
    ui.includeCss("financials", "buttons.dataTables.min.css")
    ui.includeJavascript("ehrconfigs", "underscore-min.js")

%>
<script type="text/javascript">
jQuery(function() {
        var table =  jQuery("#dDetails").DataTable({
            dom: 'Bfrtip',
            buttons: ['copy', 'csv', 'excel',
                {   extend: 'print',
                    messageTop: 'Pharmacy revenue transactions.',
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

        });
        jQuery('#dDetails tbody').on( 'click', 'tr', function () {
            console.log( table.row( this ).data() );
        } );
        jQuery('#filter').click(function () {
            jQuery('#summaryDiv').show();
        });
    });
</script>

<div style="margin-top: -1px " class="onerow">
    <i class="icon-filter" style="font-size: 26px!important; color: #5b57a6"></i>
    <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
    <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
    <button id="filter" class="confirm">Filter</button>

</div>

<br />
<div id="summaryDiv" class="ke-panel-frame" style="display: none">
    <div class="ke-panel-heading">NHIF Finance Summaries </div>
    <div class="ke-panel-content" style="background-color: #F3F9FF;">

        <table id="dDetails">
            <thead>
            <tr>
                <td>Transaction date</td>
                <td>Patient Identifier</td>
                <td>Service</td>
                <td>Category</td>
                <td>Subcategory</td>
            </tr>

            </thead>
            <tbody>
            <tr>
                <td>Transaction date</td>
                <td>Transaction date</td>
                <td>Transaction date</td>
                <td>Transaction date</td>
                <td>Transaction date</td>
            </tr>
            </tbody>
        </table>

    </div>
</div>