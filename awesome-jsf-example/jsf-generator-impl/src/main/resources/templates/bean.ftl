[#ftl]
[#import "macros/utils.ftl" as m]
package controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.${bean.scope.description};

@ManagedBean
@${bean.scope.description}
public class ${bean.name}Bean implements Serializable {
  private static final long serialVersionUID = 1L;
[#list bean.fields as field]
  [#if field.value??]
  private static final ${field.type} [@m.under_score field = field.name/] = ${field.value};
  [#else]
  private ${field.type} ${field.name};
  [/#if]
[/#list]

[#list bean.fields as field]
  [#if field.value??]
    [#assign result = field.value]
  [#else]
    [#assign result = field.name]
  [/#if]
  public ${field.type} get${field.name?cap_first}() {
    [#if field.value??]
    return [@m.under_score field = field.name/];
    [#else]
    return ${field.name};
    [/#if]
  }
[#if !field.value??]

  public void set${field.name?cap_first}(${field.type} ${field.name}) {
    this.${field.name} = ${field.name};
  }
[/#if]
[/#list]
}
