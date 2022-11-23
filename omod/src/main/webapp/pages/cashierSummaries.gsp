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
    Start Date:
    <br />
    End Date:
    <br />
    Cashier:
</div>

<div class="ke-page-content">

</div>