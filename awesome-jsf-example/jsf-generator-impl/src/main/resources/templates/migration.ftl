[#ftl]
[#list app.models as model]
create table ${model.name?lower_case}s (
  id int PRIMARY KEY auto_increment,
  [#list model.fields as field]
    [#if field.type == "String"]
  ${field.name} varchar(255)[#if field.property??] ${field.property.sql}[/#if][#if field_has_next],[/#if]
    [/#if]
  [/#list]
[/#list]
);