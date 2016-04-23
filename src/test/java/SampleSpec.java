
import static ballad.Ballad.var;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.runner.RunWith;
import ballad.Ballad;
import ballad.BalladSpec;
import ballad.Balladeer;
import ballad.Var;

@RunWith(Balladeer.class)
public class SampleSpec implements BalladSpec {{

  Then(() -> false);

  describe(Ballad.class, () -> {
    Var<Integer> intVar = var(2);
    Var<String> stringVar = var("eep");

    Then(() -> false);

    Then(() -> {
      fail("boom");
    });

    Then(intVar, (Integer i) -> i > 2);

    Then(stringVar, (String s) -> {
      assertThat(s, startsWith("sl"));
    });

    Then(stringVar, startsWith("sl"));

    describe("when nested", () -> {
      Then(() -> true);

      describe("deeply", () -> {
        Then(() -> false);
      });

      context("is contextualized", () -> {
        Then(() -> true);
        Then(() -> false);
      });
    });
  });

}}
