
import static ballad.Ballad.var;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.runner.RunWith;
import ballad.BalladSpec;
import ballad.Balladeer;
import ballad.Var;

@RunWith(Balladeer.class)
public class SampleSpec implements BalladSpec {{
  final Var<Integer> intVar = var(2);
  final Var<String> stringVar = var("eep");

  Then(() -> false); // FAIL

  Then(() -> {
    fail("boom");
  }); // FAIL

  Then(intVar, (Integer i) -> i > 2); // FAIL

  Then(intVar, i -> {
    assertThat(i, equalTo(42));
  }); // FAIL

  Then(stringVar, (String s) -> {
    assertThat(s, startsWith("sl"));
  }); // FAIL

  Then(stringVar, startsWith("sl")); // FAIL
  Then(stringVar, equalTo("eep"));   // PASS
}}
