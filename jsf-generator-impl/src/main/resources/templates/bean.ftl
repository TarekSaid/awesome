[#ftl]
package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.${bean.scope.description};

@ManagedBean
@${bean.scope.description}
public class ${bean.name}Bean {
[#list bean.fields as field]
  private ${field.type} ${field.name};
[/#list]

[#list bean.fields as field]
  [#if field.value??]
    [#assign result = field.value]
  [#else]
    [#assign result = field.name]
  [/#if]
  public ${field.type} get${field.name?cap_first}() {
    return ${result};
  }
[#if !field.value??]

  public void set${field.name?cap_first}(${field.type} ${field.name}) {
    this.${field.name} = ${field.name};
  }
[/#if]
[/#list]
}
