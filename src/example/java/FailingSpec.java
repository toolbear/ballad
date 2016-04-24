
import static ballad.Ballad.var;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.runner.RunWith;
import ballad.BalladSpec;
import ballad.Balladeer;
import ballad.Var;

class Failing {}

@RunWith(Balladeer.class)
public class FailingSpec implements BalladSpec {{

  Then(() -> false);

  describe(Failing.class, () -> {
    Then(() -> false);

    Then(() -> {
      fail("boom");
    });

    Var<Integer> v1 = var();
    Var<String>  v2 = var();

    Given(v1, () -> 2);
    Given(v2, () -> "eep");

    Then(v1, i -> i > 2);
    Then(v1, equalTo(3));

    Then(v2, s -> {
      assertThat(s, startsWith("sl"));
    });

    Then(v2, startsWith("sl"));
    Then(v2, equalTo("beep"));

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
