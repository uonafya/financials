<script type="text/javascript">
  jQuery(function() {
      populateAdminDashboard();
      jq("#lfilter").click(function () {
        populateAdminDashboard();
      });
    });
function populateAdminDashboard() {
  const summaryFromDate = jq('#summaryFromDate-field').val(),
      summaryToDate = jq('#summaryToDate-field').val();
      jq.getJSON('${ui.actionLink("financials", "dashboard", "getAdminSummariesOnDateRange")}',
                {
                  "fromDate" : summaryFromDate,
                  "toDate" : summaryToDate,
                }
            ).success(function(data) {
              jq('.stat-digit').eq(0).html(data.allVisits)
              jq('.stat-digit').eq(1).html(data.opdVisits)
              jq('.stat-digit').eq(2).html(data.allWalkIn)
              jq('.stat-digit').eq(3).html(data.revisitPatients)
              jq('.stat-digit').eq(4).html(data.newPatients)
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
 .card-counter{
    box-shadow: 2px 2px 10px #DADADA;
    margin: 5px;
    padding: 20px 10px;
    background-color: #fff;
    height: 100px;
    border-radius: 5px;
    transition: .3s linear all;
  }

  .card-counter:hover{
    box-shadow: 4px 4px 20px #DADADA;
    transition: .3s linear all;
  }

  .card-counter.primary{
    background-color: #B0E0E6;
    color: black;
  }

  .card-counter.danger{
    background-color: #E6E6FA;
    color: black;
  }

   .card-counter.pham{
    background-color: #E0FFFF;
    color: black;
  }

  .card-counter.success{
    background-color: #A9FF96;
    color: black;
  }

  .card-counter.info{
    background-color: #FFA07A;
    color: black;
  }

  .card-counter i{
    font-size: 2.5em;
    opacity: 0.2;
  }

  .card-counter .count-numbers{
    position: absolute;
    right: 35px;
    top: 20px;
    font-size: 20px;
    display: block;
  }

  .card-counter .count-name{
    position: absolute;
    right: 35px;
    top: 65px;
    font-style: italic;
    text-transform: capitalize;
    opacity: 0.5;
    display: block;
    font-size: 15px;
  }
</style>
<div class="row">
    <div class="col-md-12">
        <hr />
    </div>
</div>
<div class="row">
    <div class="col-12">
        <div class="row">
            <div class="col-md-4">
              <div class="card-counter danger">
                  <i class="fa fa-users"></i>
                <span class="count-name stat-text">ALL VISITS</span>
                <span class="count-numbers stat-digit"></span>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-counter danger">
                  <i class="fa fa-users"></i>
                <span class="count-name stat-text">OPD VISITS</span>
                <span class="count-numbers stat-digit"></span>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-counter danger">
                  <i class="fa fa-users"></i>
                <span class="count-name stat-text">WALK IN</span>
                <span class="count-numbers stat-digit"></span>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-counter danger">
                  <i class="fa fa-users"></i>
                <span class="count-name stat-text">RE-VISITS</span>
                <span class="count-numbers stat-digit"></span>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-counter danger">
                  <i class="fa fa-users"></i>
                <span class="count-name stat-text">NEW VISITS</span>
                <span class="count-numbers stat-digit"></span>
              </div>
            </div>
        </div>
    </div>
</div>