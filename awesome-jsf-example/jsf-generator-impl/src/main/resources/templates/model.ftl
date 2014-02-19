[#ftl]
package models.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import models.Identifiable;

@Entity
@Table(name = "${model.name?lower_case}s")
public class ${model.name} implements Serializable, Identifiable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;
  [#list model.fields as field]
  @Column[#if field.property??](${field.property.description})[/#if]
  private ${field.type} ${field.name};
  [/#list]

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  [#list model.fields as field]
  [#assign method = field.name?cap_first]
  public ${field.type} get${method}() {
    return ${field.name};
  }

  public void set${method}(${field.type} ${field.name}) {
    this.${field.name} = ${field.name};
  }
  [/#list]

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    [#list model.fields as field]
    [#if field.property == "REQUIRED"]
    result = prime * result + ((${field.name} == null) ? 0 : ${field.name}.hashCode());
    [/#if]
    [/#list]
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    [#list model.fields as field]
    [#if field.property == "REQUIRED"]
    if (${field.name} == null) {
      if (other.${field.name} != null)
        return false;
    } else if (!${field.name}.equals(other.${field.name}))
      return false;
    [/#if]
    [/#list]
    return true;
  }
}
