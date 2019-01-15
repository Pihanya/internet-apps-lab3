/*
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bepis.model.Request;
import ru.bepis.repository.HibernateRequestRepository;
import ru.bepis.repository.RequestRepository;

public class RequestRepositoryTests {
  private static RequestRepository repository;

  @BeforeAll
  public static void init() {
    repository = new HibernateRequestRepository();
  }

  @BeforeEach
  public void beforeTest() {
    repository.deleteAllRequests();
  }

  @Test
  public void addRequestTest() {
    Request request = new Request(0.5, 0.25, 5.0D, true);
    repository.addRequest(request);

    List<Request> requests = repository.getAllRequests();

    assertEquals(1, requests.size());
    assertEquals(request, requests.get(0));
  }

  @Test
  public void clearTableTest() {
    repository.addRequest(new Request(0.5, 0.25, 5.0D, true));
    repository.deleteAllRequests();

    List<Request> requests = repository.getAllRequests();
    assertEquals(0, requests.size());
  }
}
*/