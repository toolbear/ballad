package ballad;

import static ballad.Ballad.var;
import static org.hamcrest.CoreMatchers.anything;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import info.solidsoft.mockito.java8.api.WithMockito;

public class BalladSpecTest implements WithMockito {
  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();
  @Mock
  private Scribe scribe;

  @Before
  public void recruitScribe() {
    Ballad.recruit(scribe);
  }

  @Test
  public void booleanPostcondition() {
    final Function<Boolean> postcondition = () -> 2 != 3;

    new BalladSpec() {
      {
        Then(postcondition);
      }
    };

    verify(scribe).recordPostcondition(postcondition);
  }

  @Test
  public void proceduralPostcondition() {
    final Procedure postcondition = () -> {
      assert (2 != 3);
    };

    new BalladSpec() {
      {
        Then(postcondition);
      }
    };

    verify(scribe).recordPostcondition(postcondition);
  }

  @Test
  public void valueAndBooleanPostcondition() {
    final int value = 42;
    final Function1<Boolean, Integer> postcondition = (v) -> v > 0;

    new BalladSpec() {
      {
        Then(value, postcondition);
      }
    };

    verify(scribe).recordPostcondition(value, postcondition);
  }

  @Test
  public void varAndBooleanPostcondition() {
    final Var<Integer> value = var(42);
    final Function1<Boolean, Integer> postcondition = (v) -> v > 0;

    new BalladSpec() {
      {
        Then(value, postcondition);
      }
    };

    verify(scribe).recordPostcondition(value, postcondition);
  }

  @Test
  public void valueAndProceduralPostcondition() {
    final int value = 42;
    final Procedure1<Integer> postcondition = (v) -> {
      assert (v > 0);
    };

    new BalladSpec() {
      {
        Then(value, postcondition);
      }
    };

    verify(scribe).recordPostcondition(value, postcondition);
  }

  @Test
  public void varAndProceduralPostcondition() {
    final Var<Integer> value = var(42);
    final Procedure1<Integer> postcondition = (v) -> {
      assert (v > 0);
    };

    new BalladSpec() {
      {
        Then(value, postcondition);
      }
    };

    verify(scribe).recordPostcondition(value, postcondition);
  }

  @Test
  public void valueAndMatcherPostcondition() {
    final String value = "whatever";
    final Matcher<Object> isAnything = anything("match anything");

    new BalladSpec() {
      {
        Then(value, isAnything);
      }
    };

    verify(scribe).recordPostcondition(value, isAnything);
  }

  @Test
  public void varAndMatcherPostcondition() {
    final Var<String> var = var("whatever");
    final Matcher<Object> isAnything = anything("match anything");

    new BalladSpec() {
      {
        Then(var, isAnything);
      }
    };

    verify(scribe).recordPostcondition(var, isAnything);
  }
}
