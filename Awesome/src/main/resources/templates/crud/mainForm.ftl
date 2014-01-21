[#ftl]
[#assign beanList = r"#{" + bean + "." + modelList + "}"]
[#assign beanModel = r"#{" + bean + "." + view.name + "}"]
[#assign viewName = r"#{" + view.name + "}"]
    <h:form id="mainForm">
      <p:dataTable id="${modelList}" value="${beanList}" var="${view.name}" style="width:20%">
        [#list view.fields as field]
        <p:column headerText="${field.name?cap_first}">
          ${r"#{" + view.name + "." + field.name + "}"}
       </p:column>
       [/#list]
        <p:column headerText="Actions">
          <p:commandButton value="Edit" update=":editForm:Edit" oncomplete="editDialog.show()" immediate="true">
            <f:setPropertyActionListener target="${beanModel}" value="${viewName}" />
          </p:commandButton>
          <p:commandButton value="Delete" update=":deleteForm:Delete" oncomplete="confirmation.show()" immediate="true">
            <f:setPropertyActionListener target="${beanModel}" value="${viewName}" />
          </p:commandButton>
        </p:column>
      </p:dataTable>
      <p:commandButton value="Add" id="create" oncomplete="addDialog.show()" />
    </h:form>
