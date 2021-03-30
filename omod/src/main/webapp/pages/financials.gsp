<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeJavascript("ehrconfigs", "jquery.dataTables.min.js")
    ui.includeCss("ehrconfigs", "jquery.dataTables.min.css")
%>
<script type="text/javascript">
    jQuery(function() {
        jQuery("#startDate").datepicker();
        jQuery("#endDate").datepicker();

    });
</script>

<div class="ke-page-content">
    <fieldset>
        <legend>Financial Report</legend>
        <form method="post">
                <table>
                    <tr>
                        <td>
                            Start Date:<input type="text" id="startDate" name="startDate"/>
                        </td>
                        <td>
                            End Date:<input type="text" id="endDate" name="endDate"/>
                        </td>
                        <td>
                            <input type="submit" value="Filter" >
                        </td>
                    </tr>

                </table>
        </form>

    </fieldset>

</div>
