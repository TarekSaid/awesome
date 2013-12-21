package ${pom.groupId}.${pom.artifactId?lower_case}.models;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.${bean.scope.description};

@ManagedBean
@${bean.scope.description}
public class ${bean.name}Bean implements Serializable {
  private static final long serialVersionUID = 1L;

<#list bean.fields as field>
  private ${field.type} ${field.name};
</#list>
<#list bean.fields as field>

  public ${field.type} get${field.name?cap_first}() {
    return ${field.name};
  }

  public void set${field.name?cap_first}(${field.type} ${field.name}) {
    this.${field.name} = ${field.name};
  }
</#list>
}
