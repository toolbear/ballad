package ballad;

import org.hamcrest.Matcher;

public interface BalladSpec {
  default void Then(Function<Boolean> expression) {
    Ballad.scribe().chroniclePostcondition(expression, PostconditionError.eager());
  }

  default void Then(Procedure proc) {
    Ballad.scribe().chroniclePostcondition(proc, PostconditionError.eager());
  }

  default <T> void Then(T value, Function1<Boolean, T> expression) {
    Ballad.scribe().chroniclePostcondition(value, expression, PostconditionError.eager());
  }

  default <T extends Object> void Then(Var<T> var, Function1<Boolean, T> expression) {
    Ballad.scribe().chroniclePostcondition(var, expression, PostconditionError.eager());
  }

  default <T> void Then(T value, Procedure1<T> proc) {
    Ballad.scribe().chroniclePostcondition(value, proc, PostconditionError.eager());
  }

  default <T> void Then(Var<T> var, Procedure1<T> proc) {
    Ballad.scribe().chroniclePostcondition(var, proc, PostconditionError.eager());
  }

  default <T extends Object> void Then(T value, Matcher<? super T> matchExpression) {
    Ballad.scribe().chroniclePostcondition(value, matchExpression, PostconditionError.eager());
  }

  default <T extends Object> void Then(Var<T> var, Matcher<? super T> matchExpression) {
    Ballad.scribe().chroniclePostcondition(var, matchExpression, PostconditionError.eager());
  }
}
