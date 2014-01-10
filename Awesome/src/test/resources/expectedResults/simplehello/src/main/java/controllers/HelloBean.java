package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class HelloBean {
  private String hello;

  public String getHello() {
    return "Hello World!";
  }
}
