[#ftl]
[#assign modelList = view.name + "s"]
[#assign bean = view.name + "Bean"]
[#assign capModel = view.name?cap_first]
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
  xmlns:h="http://java.sun.com/jsf/html"
  xmlns:ui="http://java.sun.com/jsf/facelets"
  xmlns:f="http://java.sun.com/jsf/core"
  xmlns:p="http://primefaces.org/ui">
  <h:body>
    <ui:composition template="/templates/crud.xhtml">
      <ui:param name="title" value="${view.title}"/>
      <ui:define name="mainForm">
[#include "crud/mainForm.ftl"]
      </ui:define>
      <ui:define name="addForm">
[#include "crud/addForm.ftl"]
      </ui:define>
      <ui:define name="editForm">
[#include "crud/editForm.ftl"]
      </ui:define>
      <ui:define name="deleteForm">
[#include "crud/deleteForm.ftl"]
      </ui:define>
    </ui:composition>
  </h:body>
</html>