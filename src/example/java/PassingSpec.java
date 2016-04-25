import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

    Var<Passing> subject = var();
    Var<Integer> v1 = var();
    Var<String> v2 = var();
    Var<Runnable> v4 = var();
    Var<Double> result = var();

    Given(subject, () -> new Passing());
    Given(v1, () -> 2);
    Given(v2, () -> "eep");
    Given(v4, () -> mock(Runnable.class));

    When(v1, i -> i - 1);
    When(result, () -> Math.E);
    When(() -> {
      subject.get().doIt(v4.get());
    });

    Then(v1, i -> i < 2);
    Then(v1, equalTo(1));
    Then(v2, s -> {
      assertThat(s, startsWith("ee"));
    });
    Then(v2, startsWith("ee"));
    Then(v2, equalTo("eep"));
    Then(v4, mock -> {
      verify(mock).run();
    });
    Then(result, r -> r < Math.PI);

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
