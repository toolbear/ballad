package ballad;

import org.hamcrest.Matcher;

public interface BalladSpec {
  default <T> Var<T> var() {
    return Ballad.var();
  }

  default void describe(Class<?> c, Block block) {
    Ballad.scribe().chronicleContext(c, block);
  }

  default void describe(String desc, Block block) {
    Ballad.scribe().chronicleContext(desc, block);
  }

  default void context(String desc, Block block) {
    Ballad.scribe().chronicleContext(desc, block);
  }

  default void Given(Procedure proc) {
    Ballad.scribe().chroniclePrecondition(proc);
  }

  default <S, T extends S> void Given(Var<S> var, Function<T> expression) {
    Ballad.scribe().chroniclePrecondition(var, expression);
  }

  default void When(Action action) {
    Ballad.scribe().chronicleSpecification(action);
  }

  default <S, T extends S> void When(Var<S> var, Function<T> expression) {
    Ballad.scribe().chronicleSpecification(var, expression);
  }

  default <S, T extends S> void When(Var<S> var, Function1<T, S> expression) {
    Ballad.scribe().chronicleSpecification(var, expression);
  }

  default <S, T extends S> void When(Var<T> var, Action1<S> action) {
    Ballad.scribe().chronicleSpecification(var, action);
  }

  default void Then(BooleanExpression expression) {
    Ballad.scribe().chroniclePostcondition(expression, PostconditionError.eager());
  }

  default void Then(Assertion assertion) {
    Ballad.scribe().chroniclePostcondition(assertion, PostconditionError.eager());
  }

  default <S, T extends S> void Then(Var<T> var,  BooleanExpression1<S> expression) {
    Ballad.scribe().chroniclePostcondition(var, expression, PostconditionError.eager());
  }

  default <S, T extends S> void Then(Var<T> var, Assertion1<S> assertion) {
    Ballad.scribe().chroniclePostcondition(var, assertion, PostconditionError.eager());
  }

  default <S, T extends S> void Then(Var<T> var, Matcher<S> matchExpression) {
    Ballad.scribe().chroniclePostcondition(var, matchExpression, PostconditionError.eager());
  }
}
