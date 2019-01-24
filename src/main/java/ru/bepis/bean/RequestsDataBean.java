package ru.bepis.bean;

import java.util.Collections;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import lombok.Data;
import ru.bepis.model.Request;
import ru.bepis.repository.HibernateRequestRepository;
import ru.bepis.repository.RepositoryResponse;
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
  private List<Request> requests;

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

  public RequestsDataBean() {
    this(new HibernateRequestRepository());
  }

  public RequestsDataBean(RequestRepository repository) {
    this.repository = repository;

    RepositoryResponse<Void> response = repository.createTable();
    if(!response.isSuccess()) {
      throw new RuntimeException("Could not create table during creation of request data bean: " + response.getException().getMessage());
    }
  }

  public List<Request> getRequests() {
    RepositoryResponse<List<Request>> response = repository.getAllRequests();
    if (response.isSuccess()) {
      List<Request> requests = response.getResult();
      return requests.subList(Math.max(0, requests.size() - 10), requests.size()); // todo normalize
    } else {
      // todo implement behaviour
      return Collections.emptyList();
    }
  }

  public Request addRequest() {
    boolean res = Boolean.parseBoolean(result);
    Request request = new Request(x, y, r, res);

    RepositoryResponse<Void> response = repository.addRequest(request);
    if(response.isSuccess()) {
      System.out.println("Added " + request.toString());
      return request;
    } else {
      System.out.println("Could not add given request: " + response.getException().getMessage());
      return null;
    }
  }
}
