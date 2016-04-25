import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import ballad.*;

/**
 * Every postcondition in <code>FailingSpec</code> should fail. Passes likely represents an
 * internal bug in Ballad.
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
    Then(() -> fail("boom"));

    Var<Failing> subject = var();
    Var<Integer> v1 = var();
    Var<String> v2 = var();
    Var<Runnable> v4 = var();
    Var<Double> result = var();

    Given(subject, () -> new Failing());
    Given(v1, () -> 3);
    Given(v2, () -> "sleep");
    Given(v4, () -> mock(Runnable.class));

    When(v1, i -> i - 1);
    When(() -> subject.get().doIt(v4.get()));
    When(result, () -> Math.PI);

    Then(v1, i -> i > 2);
    Then(v1, equalTo(3));
    Then(v2, s -> assertThat(s, startsWith("leep")));
    Then(v2, endsWith("slee"));
    Then(v2, equalTo("ee"));
    Then(v4, mock -> {
      verify(mock).run();
    });
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
