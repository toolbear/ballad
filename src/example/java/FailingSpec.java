import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import ballad.*;

/**
 * Every postcondition (i.e. <code>Then(…)</code> clause) in <code>FailingSpec</code> should fail.
 * Passing postconditions likely represents an internal bug in Ballad.
 * <p>
 * <code>FailingSpec</code> exhaustively exercises the Ballad DSL; it isn't necessarily a good
 * example of testing style.
 *
 * @see PassingSpec
 */
@org.junit.runner.RunWith(Balladeer.class)
public class FailingSpec implements BalladSpec {{

  Then(() -> false);

  describe(Failing.class, () -> {
    Then(() -> false);

    Then(() -> {
      fail("boom");
    });

    Var<Integer> v1 = var();
    Var<String>  v2 = var();
    Var<Double> result = var();

    Given(v1, () -> 3);
    Given(v2, () -> "sleep");

    When(() -> {
      v1.set(2);
    });

    When(result, () -> Math.PI);

    Then(v1, i -> i > 2);
    Then(v1, equalTo(3));

    Then(v2, s -> {
      assertThat(s, startsWith("sl"));
    });

    Then(v2, startsWith("sl"));
    Then(v2, equalTo("beep"));

    Then(result, r -> r < Math.E);

    describe("when nested", () -> {
      Given(v1, () -> 1);

      Then(v1, i -> i > 2);

      describe("deeply", () -> {
        Then(() -> false);
      });

      context("is contextual", () -> {
        Then(() -> false);
      });
    });

    describe("awesomeness", () -> {
      Var<Character> v3 = var();

      Given(v3, () -> '*');

      context("addition", () -> {
        Given(v3, () -> '+');
        Then(v3, equalTo('*'));
      });

      context("subtraction", () -> {
        Given(v3, () -> '-');
        Then(v3, equalTo('*'));
      });
    });
  });

}}

class Failing {
  void doIt(Runnable r) {}
}
