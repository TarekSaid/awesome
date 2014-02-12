<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
  xmlns:h="http://java.sun.com/jsf/html"
  xmlns:ui="http://java.sun.com/jsf/facelets">
  <h:head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
      <ui:insert name="title">${r"#{title}"}</ui:insert>
    </title>
  </h:head>

  <h:body>
    <div id="page">
      <div id="header">
        <ui:insert name="header">
          <ui:include src="/templates/default/header.xhtml">
            <param name="title" value="${r"#{title}"}" />
          </ui:include>
        </ui:insert>
      </div>
  
      <div id="content">
        <ui:insert name="content" />
      </div>
    </div>
  </h:body>
</html>