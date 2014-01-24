[#ftl]
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1"
  xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
  <persistence-unit name="${app.name}">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
[#list app.models as model]
  [#if model.persisted]
    <class>models.impl.${model.name}</class>
  [/#if]
[/#list]
    <properties>
      <property name="javax.persistence.jdbc.driver" value="org.h2.Driver" />
      <property name="javax.persistence.jdbc.url" value="jdbc:h2:~/db/${app.name}" />
      <property name="javax.persistence.jdbc.user" value="sa" />
    </properties>
  </persistence-unit>
</persistence>