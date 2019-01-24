package ru.bepis.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.bepis.model.Request;
import ru.bepis.repository.session.SessionSupplier;
import ru.bepis.repository.session.SessionSupplierFactoryMethods;

public class HibernateRequestRepository implements RequestRepository {

  private Transaction transactionObject;
  private static Session sessionObject;

  public HibernateRequestRepository() {
    SessionSupplier supplier;
    if (Boolean.valueOf(getSystemProperty("jsch.tunnel", "false"))) {
      supplier = SessionSupplierFactoryMethods.getJSCHForwaredHibernateSessionSupplier(
          getSystemProperty("jsch.sshHost", null),
          getSystemProperty("jsch.host", null),
          Integer.parseInt(getSystemProperty("jsch.port", "22")),
          Integer.parseInt(getSystemProperty("jsch.lport", "5432")),
          Integer.parseInt(getSystemProperty("jsch.rport", "5432")),
          Integer.parseInt(getSystemProperty("jsch.timeout", "10000")),
          getSystemProperty("jsch.user", null),
          getSystemProperty("jsch.password", null)
      );
    } else {
      supplier = SessionSupplierFactoryMethods.getHibernateSessionSupplier();
    }

    sessionObject = supplier.supplySession();
  }

  @Override
  public RepositoryResponse<Void> addRequest(Request request) {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.save(request);

      return RepositoryResponse.getSuccessResponseWith(null);
    } catch (Exception ex) {
      return RepositoryResponse.getFailResponseWith(ex);
    } finally {
      transactionObject.commit();
    }
  }

  @Override
  public RepositoryResponse<List<Request>> getAllRequests() {
    List<Request> requests;

    try {
      transactionObject = sessionObject.beginTransaction();
      requests = sessionObject.createSQLQuery("SELECT * FROM request;").list();
      return RepositoryResponse.getSuccessResponseWith(requests);
    } catch (Exception ex) {
      return RepositoryResponse.getFailResponseWith(ex);
    } finally {
      transactionObject.commit();
    }
  }

  @Override
  public RepositoryResponse<Void> deleteAllRequests() {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.createSQLQuery("DELETE FROM request");
      return RepositoryResponse.getSuccessResponseWith(null);
    } catch (Exception ex) {
      return RepositoryResponse.getFailResponseWith(ex);
    } finally {
      transactionObject.commit();
    }
  }

  @Override
  public RepositoryResponse<Void> createTable() {
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
      return RepositoryResponse.getSuccessResponseWith(null);
    } catch (Exception ex) {
      return RepositoryResponse.getFailResponseWith(ex);
    } finally {
      if (transactionObject != null) {
        transactionObject.commit();
      }
    }
  }

  @Override
  public RepositoryResponse<Void> dropTable() {
    try {
      transactionObject = sessionObject.beginTransaction();
      sessionObject.createSQLQuery("drop table if exists REQUEST").executeUpdate();
      return RepositoryResponse.getSuccessResponseWith(null);
    } catch (Exception ex) {
      return RepositoryResponse.getFailResponseWith(ex);
    } finally {
      transactionObject.commit();
    }
  }

  private static String getSystemProperty(String propName, String defaultValue) {
    return System.getProperty(propName, defaultValue);
  }
}
