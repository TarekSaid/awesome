[#ftl]
[#assign model = bean.name?lower_case]
[#assign DaoClass = bean.name + "Dao"]
[#assign dao = model + "Dao"]
[#assign modelList = model + "s"]
package controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import models.daos.impl.${DaoClass};
import models.impl.${bean.name};

@ManagedBean
@ViewScoped
public class ${bean.name}Bean implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<${bean.name}> ${modelList};
  private ${bean.name} ${model};
  private ${DaoClass} ${dao};

  public ${bean.name}Bean() {
    ${dao} = new ${DaoClass}();
    init();
  }

  public void add${bean.name}(ActionEvent event) {
    ${dao}.create(${model});
    init();
  }

  public void edit${bean.name}(ActionEvent event) {
    ${dao}.update(${model});
    init();
  }

  public void delete${bean.name}(ActionEvent event) {
    ${dao}.delete(${model});
    init();
  }

  public List<${bean.name}> get${bean.name}s() {
    return ${modelList};
  }

  public void set${bean.name}s(List<${bean.name}> ${modelList}) {
    this.${modelList} = ${modelList};
  }

  public ${bean.name} get${bean.name}() {
    return ${model};
  }

  public void set${bean.name}(${bean.name} ${model}) {
    this.${model} = ${model};
  }

  public ${DaoClass} get${DaoClass}() {
    return ${dao};
  }

  public void set${DaoClass}(${DaoClass} ${dao}) {
    this.${dao} = ${dao};
  }

  private void init() {
    ${model} = new ${bean.name}();
    ${modelList} = ${dao}.findAll();
  }
}
