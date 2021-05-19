<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to revenue summary",
              iconProvider: "kenyaui",
              icon: "buttons/back.png",
              href: ui.pageLink("financials", "financials")
            ]
    ]
%>

<div class="ke-page-sidebar">
    ${ ui.includeFragment("kenyaemr", "patient/patientSearchForm", [ defaultWhich: "all" ]) }
</div>
<div class="ke-page-sidebar">
    ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Tasks", items: menuItems ]) }
</div>

<div class="ke-page-content">
    ${ ui.includeFragment("kenyaemr", "patient/patientSearchResults", [ pageProvider: "financials", page: "cummulativePatientDetails" ]) }
</div>
<script type="text/javascript">
    jQuery(function() {
        jQuery('input[name="query"]').focus();
    });
</script>