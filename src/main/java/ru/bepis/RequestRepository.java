package ru.bepis;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.bepis.model.Request;
import ru.bepis.util.HibernateUtils;

public class RequestRepository {
  private Transaction transactionObject;
  private static Session sessionObject;

  public RequestRepository() {
    sessionObject = HibernateUtils.getSessionFactory().openSession();
  }

  public void addRequest(Request request) {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.save(request);
      System.out.println("Request " + request + " was successfully added to database");

      // XHTML Response Text
//      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdStudentId",  studentObj.getId());
    } finally {
      transactionObject.commit();
    }
  }

  @SuppressWarnings({ "unchecked", "unused" })
  public List<Request> getAllRequests() {
    List<Request> requests;
    try {
      transactionObject = sessionObject.beginTransaction();
      Query query = sessionObject.createQuery("from REQUEST");
      requests = (List<Request>) query.list();

      System.out.println("Request in amount of " + requests.size() + " were fetched from database");

      // XHTML Response Text
//      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findStudentById",  studentId);
    } finally {
      transactionObject.commit();
    }
    return requests;
  }

  public void deleteAllRequests() {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.clear();

      // XHTML Response Text
//      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findStudentById",  studentId);
    } finally {
      transactionObject.commit();
    }
  }
}
