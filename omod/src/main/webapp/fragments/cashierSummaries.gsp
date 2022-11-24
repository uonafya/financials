<div class="ke-panel-frame">
    <div class="ke-panel-heading">Cashier's Summaries</div>
        <div class="ke-panel-content">
        <br />
        <div class="row">
                <label>&nbsp;&nbsp;From&nbsp;</label>${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'fromDate', id: 'summaryFromDate', label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                <label>&nbsp;&nbsp;To&nbsp;</label  >${ui.includeFragment("uicommons", "field/datetimepicker", [formFieldName: 'toDate',    id: 'summaryToDate',   label: '', useTime: false, defaultToday: false, class: ['newdtp']])}
                <label>Cashier</label>
                <select name="cashier" id="cashier">
                    <% cashier.each {%>
                        <option value="${it.userId}">${it.username}</option>
                    <% } %>
                </select>
                <button id="filter" type="button" class=" btn btn-primary right">${ui.message("Filter")}</button>

        </div>
        </div>
</div>