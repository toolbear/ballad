package ballad;

import org.hamcrest.Matcher;

interface Scribe {
  void chronicleContext(Class<?> c, Block block);
  void chronicleContext(String desc, Block block);

  void chroniclePrecondition(Procedure proc);
  <S, T extends S> void chroniclePrecondition(Var<S> var, Function<T> expression);

  void chronicleSpecification(Action action);
  <S, T extends S> void chronicleSpecification(Var<S> var, Function<T> expression);
  <S, T extends S> void chronicleSpecification(Var<S> var, Function1<T, S> expression);
  <S, T extends S> void chronicleSpecification(Var<T> var, Action1<S> action);

  void chroniclePostcondition(BooleanExpression expression, PostconditionError eager);
  void chroniclePostcondition(Assertion assertion, PostconditionError eager);
  <S, T extends S> void chroniclePostcondition(Var<T> var, BooleanExpression1<S> expression, PostconditionError eager);
  <S, T extends S> void chroniclePostcondition(Var<T> var, Assertion1<S> proc, PostconditionError eager);
  <S, T extends S> void chroniclePostcondition(Var<T> var, Matcher<S> matchExpression, PostconditionError eager);
}
