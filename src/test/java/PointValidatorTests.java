import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import ru.bepis.bean.AreaCheckBean;

public class PointValidatorTests {

  private static final double R = 1.0D;

  private Predicate<Point> checker = (point) -> AreaCheckBean.validate(point.x, point.y, R);

  @Test
  public void upperRightNegativeTest() {
    assertFalse(checker.test(new Point(1, 0.75)));
    assertFalse(checker.test(new Point(0.51, 0.75)));
    assertFalse(checker.test(new Point(0.50001, 0.75)));

    assertFalse(checker.test(new Point(0.25, 1.25)));
    assertFalse(checker.test(new Point(0.25, 1.1)));
    assertFalse(checker.test(new Point(0.25, 1.0001)));

    assertFalse(checker.test(new Point(0.51, 1.1)));
    assertFalse(checker.test(new Point(0.50001, 1.0001)));
  }

  @Test
  public void belowRightNegativeTest() {
    assertFalse(checker.test(new Point(-1.25, -0.75)));
    assertFalse(checker.test(new Point(-1.1, -0.75)));
    assertFalse(checker.test(new Point(-1.0001, -0.75)));

    assertFalse(checker.test(new Point(-0.25, -1.25)));
    assertFalse(checker.test(new Point(-0.25, -1.1)));
    assertFalse(checker.test(new Point(-0.25, -1.0001)));
  }

  @Test
  public void upperLeftNegativeTest() {
    assertFalse(checker.test(new Point(-1.5, 0.5)));
    assertFalse(checker.test(new Point(-1.1, 0.5)));
    assertFalse(checker.test(new Point(0.50001, 0.75)));

    assertFalse(checker.test(new Point(-0.5, 1.5)));
    assertFalse(checker.test(new Point(-0.5, 1.1)));
    assertFalse(checker.test(new Point(0.25, 1.0001)));

    assertFalse(checker.test(new Point(-1.5, 1.5)));
    assertFalse(checker.test(new Point(-1.1, 1.1)));
    assertFalse(checker.test(new Point(0.50001, 1.0001)));
  }

  @Test
  public void belowLeftNegativeTest() {
    assertFalse(checker.test(new Point(-0.5, -0.5)));
    assertFalse(checker.test(new Point(-0.1, -0.1)));
    assertFalse(checker.test(new Point(-0.0001, -0.00001)));
  }

  @Test
  public void upperRightPositiveTest() {
    assertTrue(checker.test(new Point(0.5, 0)));
    assertTrue(checker.test(new Point(0.49999999, 0)));

    assertTrue(checker.test(new Point(0, 1)));
    assertTrue(checker.test(new Point(0, 0.9999999)));

    assertTrue(checker.test(new Point(0.25, 0.5)));
  }

  @Test
  public void belowRightPositiveTest() {
    assertTrue(checker.test(new Point(0.25, -0.25)));
  }

  @Test
  public void upperLeftPositiveTest() {
    assertTrue(checker.test(new Point(-0.5, 0.5)));

    assertTrue(checker.test(new Point(-1, 0.5)));
    assertTrue(checker.test(new Point(-0.5, 1)));
  }

  private class Point {

    double x, y;

    public Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }
}
