[#ftl]
[#assign deleteMethod = r"#{" + bean + ".delete" + capModel + "}"]
[#list view.fields as field]
  [#if field.property == "REQUIRED"]
    [#assign reqField = r"#{" + bean + "." + view.name + "." + field.name + "}"]
    [#break]
  [/#if]
[/#list]
  <h:form id="deleteForm">
    <p:confirmDialog id="Delete" message="Delete ${reqField}?" header="Delete" severity="alert" widgetVar="confirmation">
      <p:commandButton id="confirm" value="Yes" oncomplete="confirmation.hide()" actionListener="${deleteMethod}" update=":mainForm:${modelList}" />
      <p:commandButton id="decline" value="No" onclick="confirmation.hide()" type="button" />
    </p:confirmDialog>
  </h:form>
