import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import ballad.*;

/**
 * Every postcondition in <code>PassingSpec</code> should pass. Failures likely represents an
 * internal bug in Ballad.
 * <p>
 * <code>PassingSpec</code> exhaustively exercises the Ballad DSL; it isn't necessarily a good
 * example of testing style.
 *
 * @see FailingSpec
 */
@org.junit.runner.RunWith(Balladeer.class)
public class PassingSpec implements BalladSpec {{

  Then(() -> true);

  describe(Passing.class, () -> {
    Then(() -> true);

    Then(() -> {});

    Var<Integer> v1 = var();
    Var<String>  v2 = var();
    Var<Double> result = var();

    Given(v1, () -> 2);
    Given(v2, () -> "eep");

    When(() -> {
      v1.set(1);
    });

    When(result, () -> Math.E);

    Then(v1, i -> i < 2);
    Then(v1, equalTo(2));

    Then(v2, s -> {
      assertThat(s, startsWith("ee"));
    });

    Then(v2, startsWith("ee"));
    Then(v2, equalTo("eep"));

    Then(result, r -> r < Math.E);

    describe("when nested", () -> {
      Given(v1, () -> 1);

      Then(v1, i -> i < 2);

      describe("deeply", () -> {
        Then(() -> true);
      });

      context("is contextual", () -> {
        Then(() -> true);
      });
    });

    describe("awesomeness", () -> {
      Var<Character> v3 = var();

      Given(v3, () -> '*');

      context("addition", () -> {
        Given(v3, () -> '+');
        Then(v3, equalTo('+'));
      });

      context("subtraction", () -> {
        Given(v3, () -> '-');
        Then(v3, equalTo('-'));
      });
    });
  });
}}

class Passing {
  void doIt(Runnable r) {
    r.run();
  }
}
