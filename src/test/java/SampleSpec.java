
import static ballad.Ballad.var;
import static org.hamcrest.CoreMatchers.equalTo;
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

  Then(() -> false); // FAIL

  describe(Ballad.class, () -> {
    Var<Integer> intVar = var(2);
    Var<String> stringVar = var("eep");

    Then(() -> false); // FAIL

    Then(() -> {
      fail("boom");
    });

    Then(intVar, (Integer i) -> i > 2);

    Then(stringVar, (String s) -> {
      assertThat(s, startsWith("sl"));
    }); // FAIL

    Then(stringVar, startsWith("sl")); // FAIL
    Then(stringVar, equalTo("eep"));   // PASS

    describe("when nested", () -> {
      Then(() -> true); // PASS

      describe("deeply", () -> {
        Then(() -> false);
      }); // FAIL

      context("is contextualized", () -> {
        Then(() -> true);  // PASS
        Then(() -> false); // FAIL
      });
    });

    describe("awesomeness", () -> {
      Var<Character> charVar = var();

      context("addition", () -> {
        Given(() -> { charVar.set('+'); });
        Then(charVar, equalTo('+'));
      });

      context("multiplication", () -> {
        Given(charVar, () -> '*');
        Then(charVar, equalTo('+'));
      });
    });
  });
}}
