package ru.bepis.bean;

import static ru.bepis.util.ConstantValues.DOUBLE_MACHINE_EPSILON;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import lombok.Data;

@ManagedBean(name = "areaCheck", eager = true)
@RequestScoped
@Data
public class AreaCheckBean {
  private static final double EPS = DOUBLE_MACHINE_EPSILON;

  private List<Double> xOptions = new ArrayList<>();
  // todo: захуячить сюда возможные иксы
  private double selectedX;

  public double getSelectedX() {
    return selectedX;
  }
  public void setSelectedX(double selectedX) {
    this.selectedX = selectedX;
  }
  public List<Double> getxOptions() {
    return xOptions;
  }

  @ManagedProperty(value = "#{x}")
  private double x;

  @ManagedProperty(value = "#{y}")
  private double y;

  @ManagedProperty(value = "#{r}")
  private double r;

  public boolean isValidPoint(double x, double y, double r) {
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
