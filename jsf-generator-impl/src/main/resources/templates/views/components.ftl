[#ftl]
[#if view.actions??]
[#list view.actions as action]
  [#if action.actionType == "DISPLAY"]
    ${indent!}${action.value}
  [#elseif action.actionType == "ASSIGN"]
    ${indent!}<h:inputText value="${action.value}" />
  [#elseif action.actionType == "BUTTON"]
    ${indent!}<h:commandButton value="ok" action="${action.value}" />
  [/#if]
[/#list]
[/#if]