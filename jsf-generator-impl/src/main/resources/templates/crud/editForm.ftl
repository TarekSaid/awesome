[#ftl]
[#assign editMethod = r"#{" + bean + ".edit" + capModel + "}"]
[#assign panelId = "editPanel"]
  <h:form id="editForm">
    <p:dialog header="Edit" widgetVar="editDialog" modal="true" id="Edit">
[#include "panelGrid.ftl"]
      <p:commandButton value="Edit" actionListener="${editMethod}" update=":mainForm:${modelList}" oncomplete="editDialog.hide()" />
    </p:dialog>
  </h:form>
