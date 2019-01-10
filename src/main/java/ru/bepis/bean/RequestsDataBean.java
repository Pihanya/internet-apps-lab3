package ru.bepis.bean;

import java.util.Collections;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import lombok.Data;
import ru.bepis.RequestRepository;
import ru.bepis.model.Request;

@ManagedBean(name = "requestsData", eager = true)
@ApplicationScoped
@Data
public class RequestsDataBean {
  private RequestRepository repository;

  public RequestsDataBean() {
    this(new RequestRepository());
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

  public Request addRequest(Request request) {
    try {
      repository.addRequest(request);
      return request;
    } catch (Exception ex) {
      return null;
    }
  }
}
