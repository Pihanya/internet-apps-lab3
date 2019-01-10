package ru.bepis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Request {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "x")
  private double x;

  @Column(name = "x")
  private double y;

  @Column(name = "x")
  private double r;

  @Column(name = "result")
  private boolean result;

  public Request(double x, double y, double r, boolean result) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.result = result;
  }
}
