<script type="text/javascript">
  jQuery(function() {
    populateDashboard();
    jq("#filter").click(function () {
      populateDashboard();
    });
  })
  function populateDashboard() {
      const summaryFromDate = jq('#summaryFromDate-field').val(),
          summaryToDate = jq('#summaryToDate-field').val();

      jq.getJSON('${ui.actionLink("financials", "Dashboard", "getDepartmentTotalsOnDateRange")}',
          {
            "fromDate" : summaryFromDate,
            "toDate" : summaryToDate,
          }
      ).success(function(data) {
        jq('.stat-digit').eq(0).html(data.registration)
        jq('.stat-digit').eq(1).html(data.pharmacy)
        jq('.stat-digit').eq(2).html(data.laboratory)
        jq('.stat-digit').eq(3).html(data.radiology)
        jq('.stat-digit').eq(4).html(data.procedure)
        jQuery("#graph-container").highcharts({
          credits: {
            enabled: false
          },
          chart: {
            type: 'column',
             height: '400px'
          },
          title: {
            text: ''
          },
          subtitle: {
            text: ''
          },
          xAxis: {
            type: 'category'
          },
          yAxis: {
            title: {
              text: 'Daily workload'
            }
          },
          legend: {
            enabled: false
          },
          plotOptions: {
            series: {
              borderWidth: 0,
              dataLabels: {
                enabled: true,
                format: '{point.y:.0f}'
              }
            }
          },
          tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.0f}</b><br/>'
          },
          series: [{
            name: 'Statistics',
            colorByPoint: true,
            data: [{
              name: 'Registration',
              y:data.registration,
            }, {
              name: 'Pharmacy',
              y: data.pharmacy,
            }, {
              name: 'Laboratory',
              y: data.laboratory,
            }, {
              name: 'Radiology',
              y: data.radiology,
            }, {
              name: 'Procedures',
              y: data.procedure,
            }]
          }],
        });
      });
  }
</script>
<style>
html, body, #graph-container {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>
<div class="ke-panel-frame">
    <div class="ke-panel-heading">Revenue Summaries</div>
        <div class="ke-panel-content">
            <br />
            <div class="row">
                <div class="col-12">
                    <div class="row">
                            <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                            <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                            <button id="filter" type="button" class=" btn btn-primary right">${ui.message("Filter")}</button>

                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <hr />
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="row">
                      </div>
                        <div class="col-4" style="margin-bottom: 10px;">
                            <div class="card" style="height: 100%; background: #E0FFFF; border-radius: 10px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
                                <div class="stat-widget-one">
                                    <div class="row">
                                        <div class="col-12" style="padding-left: 5px;">
                                            <div class="stat-text">REGISTRATION</div>
                                            <div class="stat-digit"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4" style="margin-bottom: 10px;">
                            <div class="card" style="height: 100%; background: #E6E6FA; border-radius: 10px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
                                <div class="stat-widget-one">
                                    <div class="row">
                                        <div class="col-12" style="padding-left: 5px;">
                                            <div class="stat-text">PHARMACY</div>
                                            <div class="stat-digit"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4" style="margin-bottom: 10px;">
                            <div class="card" style="height: 100%; background: #FFA07A; border-radius: 10px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
                                <div class="stat-widget-one">
                                    <div class="row">
                                        <div class="col-12" style="padding-left: 5px;">
                                            <div class="stat-text">LABORATORY</div>
                                            <div class="stat-digit"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4" style="margin-bottom: 10px;">
                            <div class="card" style="height: 100%; background: #B0E0E6; border-radius: 10px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
                                <div class="stat-widget-one">
                                    <div class="row">
                                        <div class="col-12" style="padding-left: 5px;">
                                            <div class="stat-text">RADIOLOGY</div>
                                            <div class="stat-digit"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-4" style="margin-bottom: 10px;">
                            <div class="card" style="height: 100%; background: #DAF7A1; border-radius: 10px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
                                <div class="stat-widget-one">
                                    <div class="row">
                                        <div class="col-12" style="padding-left: 5px;">
                                            <div class="stat-text">PROCEDURES</div>
                                            <div class="stat-digit"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="graph-container" style="min-width: 100%; height:400px; margin: 0; padding-bottom: 5px;">

            </div>
        </div>
</div>

<script type="text/javascript">

</script>