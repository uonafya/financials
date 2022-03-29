<%
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
    ui.includeCss("financials", "bootstrap.min.css")
    ui.includeCss("financials", "main.css")
%>

<link rel="stylesheet" type="text/css" href="./bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css" href="./main.css">

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/bootstrap-datetimepicker.js"></script>
<script src="https://cdn.anychart.com/releases/8.0.0/js/anychart-base.min.js"></script>
<style>
html, body, #container {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}
</style>
<div class="col-sm-9 col-md-9 col-lg-10" id="contemt-main" style="width: 100%; height: 100%">

    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-4" id="dashboard">

            <h1>Daily Revenue</h1>

        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-8">
            <div class="row">
                <div class='col-sm-12 form-inline datetimepickerwrapper'>
                    <div class="form-group">
                        <label>From</label>
                        <div class='input-group date' id='datetimepicker6'>

                            <input type='text' class="form-control" value="01/07/2016" data-provide="datepicker"  />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>To</label>
                        <div class='input-group date' id='datetimepicker7'>

                            <input type='text' class="form-control" value="1/08/2016" disabled="true" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <hr>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6 col-md-6 col-lg-3">
            <div class="well" style="background: #E0FFFF">
                <div class="row">
                    <div class="col-md-12 col-lg-6">

                        <h5>REGISTRATION</h5>
                        <h3>200</h3>

                    </div>

                </div>
            </div>

        </div>
        <div class="col-sm-6 col-md-6 col-lg-3">
            <div class="well" style="background: #E6E6FA">
                <div class="row">
                    <div class="col-md-12 col-lg-6">

                        <h5>PHARMACY</h5>
                        <h3>424</h3>
                    </div>


                </div>

            </div>
        </div>
        <div class="col-sm-6 col-md-6 col-lg-3">
            <div class="well" style="background: #FFA07A">
                <div class="row">
                    <div class="col-md-12 col-lg-6">

                        <h5>LABORATORY</h5>
                        <h3>100</h3>
                    </div>


                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-6 col-lg-3">


            <div class="well" style="background: #B0E0E6">
                <div class="row">
                    <div class="col-md-12 col-lg-6">

                        <h5>THEATRE</h5>
                        <h3>500</h3>
                    </div>


                </div>

            </div>
        </div>
    </div>
</div>
<div id="container"style="width: 100%; height: 50vh"></div>
<script>
    anychart.onDocumentReady(function() {

        // set the data
        var data = {
            header: ["Name", "TotalAmount"],
            rows: [
                ["Registration", 200],
                ["Pharm", 424],
                ["Lab", 100],
                ["Theatre", 500]
            ]};

        // create the chart
        var chart = anychart.column();

        // add data
        chart.data(data);

        // set the chart title
        chart.title("Daily Revenue Collection Per Department");


        // draw
        chart.container("container");
        chart.draw();
    });
</script>
