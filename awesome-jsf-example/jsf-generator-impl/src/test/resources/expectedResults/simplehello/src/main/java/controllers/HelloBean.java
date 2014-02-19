package controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class HelloBean implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String HELLO = "Hello World!";

  public String getHello() {
    return HELLO;
  }
}
