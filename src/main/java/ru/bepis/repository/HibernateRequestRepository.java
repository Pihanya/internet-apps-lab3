package ru.bepis.repository;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.bepis.model.Request;
import ru.bepis.util.HibernateSessionFactory;

public class HibernateRequestRepository implements RequestRepository {

  private Transaction transactionObject;
  private static Session sessionObject;

  public HibernateRequestRepository() {
    if (Boolean.valueOf(System.getProperty("jsch.tunnel", "false"))) {
      String sshHost = System.getProperty("jsch.sshHost", null);
      String host = System.getProperty("jsch.host", null);

      int port = Integer.parseInt(System.getProperty("jsch.port", "22"));
      int lport = Integer.parseInt(System.getProperty("jsch.lport", "5432"));
      int rport = Integer.parseInt(System.getProperty("jsch.rport", "5432"));

      int timeout = Integer.parseInt(System.getProperty("jsch.timeout", "10000"));

      String user = System.getProperty("jsch.user", null);
      String password = System.getProperty("jsch.password", null);

      if (sshHost != null && host != null && user != null && password != null) {
        try {
          JSch jsch = new JSch();
          com.jcraft.jsch.Session session = jsch.getSession(user, sshHost, port);
          session.setPassword(password);

          Properties config = new Properties();
          config.put("StrictHostKeyChecking", "no");
          session.setConfig(config);

          session.connect(timeout);
          session.setPortForwardingL(lport, host, rport);
        } catch (JSchException ex) {
          ex.printStackTrace();
        }
      }
    }

    sessionObject = HibernateSessionFactory.getSession();
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

  @SuppressWarnings({"unchecked", "unused"})
  public List<Request> getAllRequests() {
    List<Request> requests;
    try {
      transactionObject = sessionObject.beginTransaction();
      Query query = sessionObject.createQuery("from Request");
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
      Query query = sessionObject.createQuery("delete from Request");
      query.executeUpdate();
    } finally {
      transactionObject.commit();
    }
  }
}
