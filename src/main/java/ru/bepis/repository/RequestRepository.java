package ru.bepis.repository;

import java.util.List;
import ru.bepis.model.Request;

public interface RequestRepository {

  RepositoryResponse<Void> addRequest(Request request);

  RepositoryResponse<List<Request>> getAllRequests();

  RepositoryResponse<Void> deleteAllRequests();

  RepositoryResponse<Void> createTable();

  RepositoryResponse<Void> dropTable();
}
