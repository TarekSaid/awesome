[#ftl]
            <p:panelGrid id="${panelId}" columns="2">
            [#list view.fields as field]
              <h:outputText value="${field.name?cap_first}:" />
              <p:inputText value="${r"#{" + bean + "." + view.name + "." + field.name + "}"}" />
            [/#list]
            </p:panelGrid>
