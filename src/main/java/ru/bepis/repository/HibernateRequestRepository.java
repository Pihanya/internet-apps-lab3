package ru.bepis.repository;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import java.util.List;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.bepis.model.Request;
import ru.bepis.util.HibernateSessionFactory;

public class HibernateRequestRepository implements RequestRepository {

  private Transaction transactionObject;
  private static Session sessionObject;

  public HibernateRequestRepository() {
    if (Boolean.valueOf(System.getProperty("jsch.tunnel", "false"))) {
      /*tryTunnel(
          System.getProperty("jsch.sshHost", null), System.getProperty("jsch.host", null),
          Integer.parseInt(System.getProperty("jsch.port", "22")),
          Integer.parseInt(System.getProperty("jsch.lport", "5432")),
          Integer.parseInt(System.getProperty("jsch.rport", "5432")),
          Integer.parseInt(System.getProperty("jsch.timeout", "10000")),
          System.getProperty("jsch.user", null), System.getProperty("jsch.password", null)
      );*/
    }

    sessionObject = HibernateSessionFactory.getSession();

    createTable();
  }

  @Override
  public void addRequest(Request request) {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.save(request);
    } finally {
      transactionObject.commit();
    }
  }

  @Override
  public List<Request> getAllRequests() {
    List<Request> requests;

    try {
      transactionObject = sessionObject.beginTransaction();
      requests = sessionObject.createSQLQuery("SELECT * FROM request;").list();
//      Query query = sessionObject.createQuery("from Request");
//      requests = (List<Request>) query.list();
    } finally {
      transactionObject.commit();
    }

    return requests;
  }

  @Override
  public void deleteAllRequests() {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.createSQLQuery("DELETE FROM request");
//      Query query = sessionObject.createQuery("delete from Request");
//      query.executeUpdate();
    } finally {
      transactionObject.commit();
    }
  }

  @Override
  public void createTable() {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.createSQLQuery("create table if not exists REQUEST (\n"
          + "   id     SERIAL NOT NULL,\n"
          + "   x      REAL   NOT NULL,\n"
          + "   y      REAL   NOT NULL,\n"
          + "   r      REAL   NOT NULL,\n"
          + "   result BOOLEAN NOT NULL,\n"
          + "   PRIMARY KEY (id)\n"
          + ");").executeUpdate();
    } finally {
      if (transactionObject != null) {
        transactionObject.commit();
      }
    }
  }

  @Override
  public void dropTable() {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.createSQLQuery("drop table if exists REQUEST").executeUpdate();
    } finally {
      transactionObject.commit();
    }
  }

  private static void tryTunnel(String sshHost, String host, int port, int lport, int rport,
      int timeout, String user, String password) {
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
}
