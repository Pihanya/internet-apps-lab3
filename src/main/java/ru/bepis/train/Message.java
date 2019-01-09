package ru.bepis.train;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

// todo hatch
@ManagedBean(name = "message", eager = true)
@RequestScoped
public class Message {
  private String message = "Hello World!";

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
}
