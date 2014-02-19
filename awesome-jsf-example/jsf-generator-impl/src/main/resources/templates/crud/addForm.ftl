[#ftl]
[#assign addMethod = r"#{" + bean + ".add" + capModel + "}"]
[#assign panelId = "newPanel"]
        <h:form id="addForm">
          <p:dialog header="New ${view.name?cap_first}" widgetVar="addDialog" modal="true">
            [#include "panelGrid.ftl"]
            <p:commandButton value="Add" actionListener="${addMethod}" update=":mainForm:${modelList}" oncomplete="addDialog.hide()" />
          </p:dialog>
        </h:form>
