import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bepis.RequestRepository;
import ru.bepis.model.Request;

public class RequestRepositoryTests {
  private static RequestRepository repository;

  @BeforeAll
  public static void init() {
    repository = new RequestRepository();
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
}
