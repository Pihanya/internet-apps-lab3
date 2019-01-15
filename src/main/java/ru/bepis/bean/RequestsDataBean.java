package ru.bepis.bean;

import com.jcraft.jsch.JSchException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import lombok.Data;
import ru.bepis.repository.HibernateRequestRepository;
import ru.bepis.model.Request;
import ru.bepis.repository.RequestRepository;

@ManagedBean(name = "requestsData", eager = true)
@ApplicationScoped
@Data
public class RequestsDataBean {
  private RequestRepository repository;
  private double x;
  private double y;
  private double r;
  private String result;

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getR() {
    return r;
  }

  public void setR(double r) {
    this.r = r;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public RequestsDataBean() throws JSchException {
    this(new HibernateRequestRepository());
  }

  public RequestsDataBean(RequestRepository repository) {
    this.repository = repository;
  }

  public List<Request> getRequests() {
    try {
      return repository.getAllRequests();
    } catch (Exception ex) {
      return Collections.emptyList();
    }
  }

  public Request addRequest() {

    boolean res = Boolean.parseBoolean(result);
    Request request = new Request(x, y, r, res);
    try {
      repository.addRequest(request);
      return request;
    } catch (Exception ex) {
      return null;
    }
  }
}
