[#ftl]
[#assign hasForm = false]
[#assign hasCrud = false]
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
  xmlns:h="http://java.sun.com/jsf/html"
  xmlns:ui="http://java.sun.com/jsf/facelets">
  <h:body>
    <ui:composition template="/templates/default.xhtml">
      <ui:param name="title" value="${view.title}"/>
      <ui:define name="content">
        [#if view.actions??]
          [#list view.actions as action]
            [#if action.actionType == "BUTTON" || action.actionType == "LINK"]
              [#assign hasForm = true]
              [#break]
            [/#if]
          [/#list]
        [/#if]
        [#if hasForm]
          [#assign indent="  "]
          [#include "views/form.ftl"]
        [#else]
          [#include "views/components.ftl"]
        [/#if]
      </ui:define>
    </ui:composition>
  </h:body>
</html>
