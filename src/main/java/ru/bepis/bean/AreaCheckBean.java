package ru.bepis.bean;

import static ru.bepis.util.ConstantValues.DOUBLE_MACHINE_EPSILON;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import lombok.Data;
import ru.bepis.model.Request;

@ManagedBean(name = "areaCheck", eager = true)
@RequestScoped
@Data
public class AreaCheckBean {

  private static final double EPS = DOUBLE_MACHINE_EPSILON;

  @ManagedProperty(value = "#{userRequest}")
  private Request request;

  public AreaCheckBean(double x, double y, double r) {
    this.request = new Request(x, y, r, isValidPoint(x, y, r));
  }

  public static boolean isValidPoint(double x, double y, double r) {
    if (Math.abs(y) <= EPS) {
      return r - Math.abs(x) >= 0;
    }

    if (Math.abs(x) <= EPS) {
      return r - Math.abs(y) >= 0;
    }

    if ((x > 0) && (y > 0)) {
      return (y + 2 * x <= r);
    } else if ((x > 0) && (y < 0)) {
      return (Math.sqrt(x * x + y * y) <= r);
    } else if ((x < 0) && (y > 0)) {
      return (x >= -r) && (y <= r);
    } else {
      return false;
    }
  }
}
