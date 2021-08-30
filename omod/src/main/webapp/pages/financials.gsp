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
                    icon: "buttons/upline.png"
            ],
            [
                    label: "Cummulative patient Summaries",
                    href: ui.pageLink("financials", "cummulativePatientSummary"),
                    iconProvider: "financials",
                    icon: "buttons/Zoom-in.png"
            ],
            [
                    label: "Pharmacy Department Summaries",
                    href: ui.pageLink("financials", "financials", [section: "pharmacyRevenueSummaries"]),
                    active: (selection == "section-pharmacyRevenueSummaries"),
                    iconProvider: "financials",
                    icon: "buttons/pharmacy_summary.png"
            ],
            [
                    label: "Student Account Summaries",
                    href: ui.pageLink("financials", "financials", [section: "studentRevenueSummaries"]),
                    active: (selection == "section-studentRevenueSummaries"),
                    iconProvider: "financials",
                    icon: "buttons/student.png"
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
    <% } else  if (section == "pharmacyRevenueSummaries") { %>
    ${ ui.includeFragment("financials", "pharmacyRevenueSummaries") }
    <%} else if(section == "studentRevenueSummaries") { %>
    ${ui.includeFragment("financials","studentRevenueSummaries")}
    <% } %>
</div>
