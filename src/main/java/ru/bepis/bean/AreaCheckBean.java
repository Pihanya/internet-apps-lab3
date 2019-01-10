package ru.bepis.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import lombok.Data;
import ru.bepis.model.Request;

@ManagedBean(name = "areaCheck", eager = true)
@RequestScoped
@Data
public class AreaCheckBean {
  @ManagedProperty(value = "#{userRequest}")
  private Request request;

  public AreaCheckBean(double x, double y, double r) {
    this.request = new Request(x, y, r, isValidPoint(x, y, r));
  }

  // todo implement
  public static boolean isValidPoint(double x, double y, double r) {
    return false;
  }
}
