<script type="text/javascript">
  jQuery(function() {
    jQuery('.datepicker').datepicker();
  })
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
                <div class="col-md-4">
                    <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                </div>
                <div class="col-md-4">
                    <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                </div>
                <div class="col-md-4">
                    <button id="filter" type="button" class=" btn btn-primary right">${ui.message("Filter")}
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <hr />
                </div>
            </div>

            <div class="row">
                <div class="col-sm-6 col-md-6 col-lg-3">
                    <div class="well" style="background: #E0FFFF">
                        <div class="row">
                            <div class="col-md-12 col-lg-6">
                                <h4>REGISTRATION</h4>
                                <h2>${registration}</h2>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-3">
                    <div class="well" style="background: #E6E6FA">
                        <div class="row">
                            <div class="col-md-12 col-lg-6">

                                <h4>PHARMACY</h4>
                                <h2>${pharmacy}</h2>
                            </div>


                        </div>

                    </div>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-3">
                    <div class="well" style="background: #FFA07A">
                        <div class="row">
                            <div class="col-md-12 col-lg-6">

                                <h4>LABORATORY</h4>
                                <h2>${laboratory}</h2>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <br />
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-md-6 col-lg-3">


                    <div class="well" style="background: #B0E0E6">
                        <div class="row">
                            <div class="col-md-12 col-lg-6">

                                <h4>Radiology</h4>
                                <h2>${radiology}</h2>
                            </div>


                        </div>

                    </div>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-3">


                    <div class="well" style="background: #B0E0E6">
                        <div class="row">
                            <div class="col-md-12 col-lg-6">

                                <h4>Procedures</h4>
                                <h2>${procedure}</h2>
                            </div>


                        </div>

                    </div>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-3">


                    <div class="well" style="background: #B0E0E6">
                        <div class="row">
                            <div class="col-md-12 col-lg-6">

                                <h4>NHIF</h4>
                                <h2>${nhif}</h2>
                            </div>


                        </div>

                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <hr />
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <br />
                </div>
            </div>
            <div id="graph-container" style="min-width: 100%; height:100px; margin: 0; padding-bottom: 5px;"></div>
        </div>
        <script type="text/javascript">
          jQuery(function () {
            jQuery("#graph-container").highcharts({
              credits: {
                enabled: false
              },
              chart: {
                type: 'column'
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
                  y:${registration},
                }, {
                  name: 'Pharmacy',
                  y: ${pharmacy},
                }, {
                  name: 'Laboratory',
                  y: ${laboratory},
                }, {
                  name: 'Radiology',
                  y: ${radiology},
                }, {
                  name: 'Procedures',
                  y: ${procedure},
                },
                  {
                    name: 'NHIF',
                    y: ${nhif},
                  }]
              }],
            });
          });
        </script>
    </div>
</div>