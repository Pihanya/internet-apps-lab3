/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import javax.persistence.PersistenceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.bepis.model.Request;
import ru.bepis.repository.HibernateRequestRepository;
import ru.bepis.repository.RequestRepository;

public class RequestRepositoryTests {

  private static RequestRepository repository;

  @BeforeAll
  public static void beforeAllTests() {
    repository = new HibernateRequestRepository();
  }

  @BeforeEach
  public void beforeTest() {
    repository.createTable();
  }

  @AfterEach
  public void afterTest() {
    repository.dropTable();
  }

  @Test
  public void addRequestTest() {
    Request request = new Request(0.5, 0.25, 5.0D, true);
    repository.addRequest(request);

    List<Request> requests = repository.getAllRequests().getResult();
    assertEquals(1, requests.size());
    assertEquals(request, requests.get(0));
  }

  @Test
  public void clearTableTest() {
    repository.addRequest(new Request(0.5, 0.25, 5.0D, true));
    repository.addRequest(new Request(0.75, 0.1, 2.0D, true));
    repository.deleteAllRequests();

    List<Request> requests = repository.getAllRequests().getResult();
    assertEquals(0, requests.size());
  }

  @Test
  public void dropTableTest() {
    Executable closureContainingCodeToTest = () -> {
      repository.dropTable();
      List<Request> requests = repository.getAllRequests().getResult();
      assertEquals(0, requests.size());
    };

    assertThrows(PersistenceException.class, closureContainingCodeToTest);
  }
}
*/
