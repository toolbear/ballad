package ballad;

import org.hamcrest.Matcher;

public interface BalladSpec {
  default <T> Var<T> var() {
    return Ballad.var();
  }

  default void describe(Class<?> c, Procedure proc) {
    Ballad.scribe().chronicleContext(c, proc);
  }

  default void context(String desc, Procedure proc) {
    Ballad.scribe().chronicleContext(desc, proc);
  }

  default void describe(String desc, Procedure proc) {
    Ballad.scribe().chronicleContext(desc, proc);
  }

  default void Given(Procedure proc) {
    Ballad.scribe().chroniclePrecondition(proc);
  }

  default <S, T extends S> void Given(Var<S> var, Function<T> expression) {
    Ballad.scribe().chroniclePrecondition(var, expression);
  }

  default void When(Procedure proc) {
    Ballad.scribe().chronicleSpecification(proc);
  }

  default <S, T extends S> void When(Var<S> var, Function<T> expression) {
    Ballad.scribe().chronicleSpecification(var, expression);
  }

  default <S, T extends S> void When(Var<S> var, Function1<T, S> expression) {
    Ballad.scribe().chronicleSpecification(var, expression);
  }

  default <S, T extends S> void When(Var<T> var, Procedure1<S> proc) {
    Ballad.scribe().chronicleSpecification(var, proc);
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
