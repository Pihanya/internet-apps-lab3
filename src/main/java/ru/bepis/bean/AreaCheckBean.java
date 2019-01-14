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

  private double hiddenX;
  private double hiddenR;
  private double hiddenY;
  private String hiddenResult;

  public double getHiddenX() {
    return hiddenX;
  }

  public void setHiddenX(double hiddenX) {
    this.hiddenX = hiddenX;
  }

  public double getHiddenR() {
    return hiddenR;
  }

  public void setHiddenR(double hiddenR) {
    this.hiddenR = hiddenR;
  }

  public double getHiddenY() {
    return hiddenY;
  }

  public void setHiddenY(double hiddenY) {
    this.hiddenY = hiddenY;
  }

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

  public String getHiddenResult() {
    return hiddenResult;
  }

  public void setHiddenResult(String hiddenResult) {
    this.hiddenResult = hiddenResult;
  }

  public void validateFromGraph() {
    hiddenResult = validate(hiddenX, hiddenY, selectedR) ? "true" : "false";
  }
}
