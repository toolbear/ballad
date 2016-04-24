
import static ballad.Ballad.var;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import ballad.BalladSpec;
import ballad.Balladeer;
import ballad.Var;

class Passing {}

@RunWith(Balladeer.class)
public class PassingSpec implements BalladSpec {{

  Then(() -> true);

  describe(Passing.class, () -> {
    Then(() -> true);

    Then(() -> {});

    Var<Integer> v1 = var();
    Var<String>  v2 = var();

    Given(v1, () -> 2);
    Given(v2, () -> "eep");

    Then(v1, i -> i <= 2);
    Then(v1, equalTo(2));

    Then(v2, s -> {
      assertThat(s, startsWith("ee"));
    });

    Then(v2, startsWith("ee"));
    Then(v2, equalTo("eep"));

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
