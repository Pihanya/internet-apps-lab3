package ru.bepis.repository;

import java.util.List;
import ru.bepis.model.Request;

public interface RequestRepository {

  void addRequest(Request request);

  List<Request> getAllRequests();

  void deleteAllRequests();

  void createTable();

  void dropTable();
}
