<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
  xmlns:h="http://java.sun.com/jsf/html"
  xmlns:ui="http://java.sun.com/jsf/facelets">
  <h:body>
    <ui:composition template="/templates/default.xhtml">
      <ui:param name="title" value="${r"#{title}"}"/>
      <ui:define name="content">
        <div id="main">
          <ui:insert name="mainForm" />
        </div>
        <div id="add">
          <ui:insert name="addForm" />
        </div>
        <div id="edit">
          <ui:insert name="editForm" />
        </div>
        <div id="delete">
          <ui:insert name="deleteForm" />
        </div>
      </ui:define>
    </ui:composition>
  </h:body>
</html>
