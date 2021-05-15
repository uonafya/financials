<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to home",
              iconProvider: "kenyaui",
              icon: "buttons/back.png",
              href: ui.pageLink("kenyaemr", "userHome")
            ],
            [
                    label: "Overview",
                    href: ui.pageLink("financials", "financials", [ section: "overview" ]),
                    active: (selection == "section-overview"),
                    iconProvider: "financials",
                    icon: "buttons/overview.png"
            ],
            [
                    label: "Patient Summaries",
                    href: ui.pageLink("financials", "financials", [ section: "patientFinanceSummaries" ]),
                    active: (selection == "section-patientFinanceSummaries"),
                    iconProvider: "financials",
                    icon: "buttons/patients.png"
            ],
            [
                    label: "Departmental Summaries",
                    href: ui.pageLink("financials", "financials", [ section: "departmentFinanceSummaries" ]),
                    active: (selection == "section-departmentFinanceSummaries"),
                    iconProvider: "financials",
                    icon: "forms/upline.png"
            ]
    ]
%>
<div class="ke-page-sidebar">
    ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Tasks", items: menuItems ]) }
</div>
<div class="ke-page-content">

    <% if (section == "overview") { %>
    ${ ui.includeFragment("financials", "summary") }
    <% } else if (section == "patientFinanceSummaries") { %>
    ${ ui.includeFragment("financials", "patientFinanceSummaries") }
    <% } else  if (section == "departmentFinanceSummaries") { %>
    ${ ui.includeFragment("financials", "departmentFinanceSummaries") }
    <% } %>
</div>
