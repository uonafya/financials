<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])
    ui.includeJavascript("financials", "jquery.dataTables.min.js")
    ui.includeCss("financials", "jquery.dataTables.min.css")
    ui.includeCss("financials", "bootstrap.min.css")
    ui.includeCss("financials", "main.css")
    ui.includeCss("financials", "bootstrap-datetimepicker.min.css")
    ui.includeJavascript("ehrconfigs", "datetimepicker/bootstrap-datetimepicker.min.js")
    ui.includeJavascript("ehrconfigs", "bootstrap.min.js")
    ui.includeJavascript("kenyaemr", "highcharts.js")
    ui.includeJavascript("kenyaemr", "highcharts-grouped-categories.js")
    ui.includeJavascript("ehrconfigs", "knockout-3.4.0.js")
    ui.includeJavascript("ehrcashier", "moment.js")
    ui.includeJavascript("financials", "dataTables.buttons.min.js")
    ui.includeJavascript("financials", "pdfmake.min.js")
    ui.includeJavascript("financials", "vfs_fonts.js")
    ui.includeJavascript("financials", "buttons.html5.js")
    ui.includeJavascript("financials", "buttons.print.min.js")
    ui.includeCss("financials", "buttons.dataTables.min.css")
    ui.includeJavascript("ehrconfigs", "underscore-min.js")
    def menuItems = [
            [ label: "Back to home",
              iconProvider: "kenyaui",
              icon: "buttons/back.png",
              href: ui.pageLink("kenyaemr", "userHome")
            ],
            [
                    label: "Dashboard",
                    href: ui.pageLink("financials", "financials", [ section: "dashboard" ]),
                    active: (selection == "section-dashboard"),
                    iconProvider: "financials",
                    icon: "buttons/dashboard.jpeg"
            ],
            [
                    label: "Work Load",
                    href: ui.pageLink("financials", "financials", [ section: "workLoad" ]),
                    active: (selection == "section-workLoad"),
                    iconProvider: "financials",
                    icon: "buttons/247.png"
            ],
            [
                    label: "Registration",
                    href: ui.pageLink("financials", "financials", [ section: "registration" ]),
                    active: (selection == "section-registration"),
                    iconProvider: "financials",
                    icon: "buttons/printer.png"
            ],
            [
                    label: "Laboratory",
                    href: ui.pageLink("financials", "financials", [ section: "laboratory" ]),
                    active: (selection == "section-laboratory"),
                    iconProvider: "financials",
                    icon: "buttons/lab.png"
            ],
            [
                    label: "Procedures",
                    href: ui.pageLink("financials", "financials", [ section: "procedure" ]),
                    active: (selection == "section-procedure"),
                    iconProvider: "financials",
                    icon: "buttons/scissors.png"
            ],
            [
                    label: "Radiology",
                    href: ui.pageLink("financials", "financials", [ section: "radiology" ]),
                    active: (selection == "section-radiology"),
                    iconProvider: "financials",
                    icon: "buttons/radiology.jpg"
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
                    label: "Patient Invoices",
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
                    label: "NHIF Summaries",
                    href: ui.pageLink("financials", "financials", [section: "nhifSummaries"]),
                    active: (selection == "section-nhifSummaries"),
                    iconProvider: "financials",
                    icon: "buttons/nhif.jpeg"
            ],
            [
                    label: "Cashier Summaries",
                    href: ui.pageLink("financials", "financials", [section: "cashierSummaries"]),
                    active: (selection == "section-cashierSummaries"),
                    iconProvider: "financials",
                    icon: "buttons/Book.png"
            ]
    ]
%>
<div class="ke-page-sidebar">
    ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Tasks", items: menuItems ]) }
</div>
<div class="ke-page-content">
    <% if (section == "dashboard") { %>
    ${ ui.includeFragment("financials", "dashboard") }
    <%} else if (section == "workLoad") { %>
    ${ ui.includeFragment("financials", "workLoad") }
    <%} else if (section == "registration") { %>
    ${ ui.includeFragment("financials", "summary") }
    <% } else if (section == "patientFinanceSummaries") { %>
    ${ ui.includeFragment("financials", "patientFinanceSummaries") }
    <% } else  if (section == "departmentFinanceSummaries") { %>
    ${ ui.includeFragment("financials", "departmentFinanceSummaries") }
    <% } else  if (section == "pharmacyRevenueSummaries") { %>
    ${ ui.includeFragment("financials", "pharmacyRevenueSummaries") }
    <%} else if(section == "nhifSummaries") { %>
    ${ui.includeFragment("financials","nhifSummaries")}
    <% } else if(section == "laboratory") { %>
    ${ui.includeFragment("financials","laboratory")}
    <%} else if(section == "procedure") {%>
    ${ui.includeFragment("financials","procedure")}
    <%} else if(section == "radiology") {%>
    ${ui.includeFragment("financials","radiology")}
    <%} else if(section == "cashierSummaries") {%>
    ${ui.includeFragment("financials","cashierSummaries")}
    <%}%>
</div>
