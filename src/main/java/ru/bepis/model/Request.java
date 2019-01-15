package ru.bepis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
public class Request {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "x")
  private double x;

  @Column(name = "y")
  private double y;

  @Column(name = "r")
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
