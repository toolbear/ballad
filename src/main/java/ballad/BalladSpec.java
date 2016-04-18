package ballad;

import org.hamcrest.Matcher;

public interface BalladSpec {
  default void Then(Function<Boolean> postcondition) {
    Ballad.scribe().recordPostcondition(postcondition);
  }

  default void Then(Procedure postcondition) {
    Ballad.scribe().recordPostcondition(postcondition);
  }

  default <T> void Then(T value, Function1<Boolean, T> postcondition) {
    Ballad.scribe().recordPostcondition(value, postcondition);
  }

  default <T extends Object> void Then(Var<T> var, Function1<Boolean, T> postcondition) {
    Ballad.scribe().recordPostcondition(var, postcondition);
  }

  default <T> void Then(T value, Procedure1<T> postcondition) {
    Ballad.scribe().recordPostcondition(value, postcondition);
  }

  default <T> void Then(Var<T> var, Procedure1<T> postcondition) {
    Ballad.scribe().recordPostcondition(var, postcondition);
  }

  default <T extends Object> void Then(T value, Matcher<Object> matcher) {
    Ballad.scribe().recordPostcondition(value, matcher);
  }

  default <T extends Object> void Then(Var<T> var, Matcher<Object> matcher) {
    Ballad.scribe().recordPostcondition(var, matcher);
  }
}
