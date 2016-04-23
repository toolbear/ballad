package ballad;

import static ballad.Ballad.var;
import org.hamcrest.CoreMatchers;
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

    verify(scribe).chroniclePostcondition(eq(postcondition), anyPostconditionError());
  }

  @Test
  public void proceduralPostcondition() {
    final Procedure postcondition = () -> {
      assert 2 != 3;
    };

    new BalladSpec() {
      {
        Then(postcondition);
      }
    };

    verify(scribe).chroniclePostcondition(eq(postcondition), anyPostconditionError());
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

    verify(scribe).chroniclePostcondition(eq(value), eq(postcondition), anyPostconditionError());
  }

  @Test
  public void varAndProceduralPostcondition() {
    final Var<Integer> value = var(42);
    final Procedure1<Integer> postcondition = (v) -> {
      assert v > 0;
    };

    new BalladSpec() {
      {
        Then(value, postcondition);
      }
    };

    verify(scribe).chroniclePostcondition(eq(value), eq(postcondition), anyPostconditionError());
  }

  @Test
  public void varAndMatcherPostcondition() {
    final Var<String> var = var("whatever");
    final Matcher<String> isAnything = CoreMatchers.any(String.class);

    new BalladSpec() {
      {
        Then(var, isAnything);
      }
    };

    verify(scribe).chroniclePostcondition(eq(var), eq(isAnything), anyPostconditionError());
  }

  private PostconditionError anyPostconditionError() {
    return any(PostconditionError.class);
  }
}
