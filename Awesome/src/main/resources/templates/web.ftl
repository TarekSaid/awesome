<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
  xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
  http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
  id="WebApp_ID" version="3.0">

  <display-name>${app.name}</display-name>

  <!-- Change to "Production" when you are ready to deploy -->
  <context-param>
    <param-name>javax.faces.PROJECT_STAGE</param-name>
    <param-value>Development</param-value>
  </context-param>

<#assign h2=false>
<#if app.models??>
  <#list app.models as model>
    <#if model.persisted>
      <#assign h2=true>
    </#if>
    <#if model.mainPage>
      <#if model.persisted>
        <#assign welcomePage = "${model.name?lower_case}s">
      <#else>
        <#assign welcomePage = "${model.name?lower_case}">
      </#if>
    </#if>
  </#list>
</#if>
  <#if h2>
  <!-- Configuring H2 database -->
  <listener>
    <listener-class>org.h2.server.web.DbStarter</listener-class>
  </listener>

  <context-param>
    <param-name>db.url</param-name>
    <param-value>jdbc:h2:~/db/${app.name}</param-value>
  </context-param>
  <context-param>
    <param-name>db.user</param-name>
    <param-value>sa</param-value>
  </context-param>

  </#if>
  <!-- Welcome page -->
  <welcome-file-list>
    <welcome-file>faces/${welcomePage}.xhtml</welcome-file>
  </welcome-file-list>

  <!-- JSF mapping -->
  <servlet>
    <servlet-name>Faces Servlet</servlet-name>
    <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>

  <!-- Map these files with JSF -->
  <servlet-mapping>
    <servlet-name>Faces Servlet</servlet-name>
    <url-pattern>/faces/*</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>Faces Servlet</servlet-name>
    <url-pattern>*.jsf</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>Faces Servlet</servlet-name>
    <url-pattern>*.faces</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>Faces Servlet</servlet-name>
    <url-pattern>*.xhtml</url-pattern>
  </servlet-mapping>
</web-app>