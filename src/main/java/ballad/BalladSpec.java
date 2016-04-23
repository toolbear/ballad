package ballad;

import org.hamcrest.Matcher;

public interface BalladSpec {
  default void describe(Class<?> c, Procedure proc) {
    Ballad.scribe().chronicleDescription(c, proc);
  }

  default void describe(String desc, Procedure proc) {
    Ballad.scribe().chronicleDescription(desc, proc);
  }

  default void Then(Function<Boolean> expression) {
    Ballad.scribe().chroniclePostcondition(expression, PostconditionError.eager());
  }

  default void Then(Procedure proc) {
    Ballad.scribe().chroniclePostcondition(proc, PostconditionError.eager());
  }

  default <S, T extends S> void Then(Var<T> var, Function1<Boolean, S> expression) {
    Ballad.scribe().chroniclePostcondition(var, expression, PostconditionError.eager());
  }

  default <S, T extends S> void Then(Var<T> var, Procedure1<S> proc) {
    Ballad.scribe().chroniclePostcondition(var, proc, PostconditionError.eager());
  }

  default <S, T extends S> void Then(Var<T> var, Matcher<S> matchExpression) {
    Ballad.scribe().chroniclePostcondition(var, matchExpression, PostconditionError.eager());
  }
}
