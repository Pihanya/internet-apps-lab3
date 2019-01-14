package ru.bepis.bean;

import static ru.bepis.util.ConstantValues.DOUBLE_MACHINE_EPSILON;

import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import lombok.Data;

@ManagedBean(name = "areaCheck", eager = true)
@RequestScoped
@Data
public class AreaCheckBean {

  public List<Double> getxOptions() {
    return xOptions;
  }

  public void setxOptions(List<Double> xOptions) {
    this.xOptions = xOptions;
  }

  public double getSelectedX() {
    return selectedX;
  }

  public void setSelectedX(double selectedX) {
    this.selectedX = selectedX;
  }

  public double getyValue() {
    return yValue;
  }

  public void setyValue(double yValue) {
    this.yValue = yValue;
  }

  public List<Double> getrOptions() {
    return rOptions;
  }

  public void setrOptions(List<Double> rOptions) {
    this.rOptions = rOptions;
  }

  public double getSelectedR() {
    return selectedR;
  }

  public void setSelectedR(double selectedR) {
    this.selectedR = selectedR;
  }

  private static final double EPS = DOUBLE_MACHINE_EPSILON;

  private List<Double> xOptions = Arrays.asList(-2D, -1.5, -1D, -0.5, 0D, 0.5, 1D, 1D);
  private double selectedX;

  private double yValue;

  private List<Double> rOptions = Arrays.asList(1D, 1.5, 2D, 2.5, 3D);
  private double selectedR;

  public static boolean validate(double x, double y, double r) {
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

  public boolean isValidPoint() {
    return validate(selectedX, yValue, selectedR);
  }
}
