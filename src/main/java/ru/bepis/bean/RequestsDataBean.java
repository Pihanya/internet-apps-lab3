package ru.bepis.bean;

import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Data;
import ru.bepis.model.Request;

@ManagedBean(name = "requestsData", eager = true)
@SessionScoped
@Data
public class RequestsDataBean {
  private List<Request> requests;

  public Request addRequest(Request request) {
    requests.add(request);
    return request;
  }

  public Collection<Request> saveRequests() {
    // todo implement
    return null;
  }
}
