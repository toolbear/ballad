package ballad;

import org.hamcrest.Matcher;

interface Scribe {
  void chronicleDescription(Class<?> c, Procedure proc);

  void chronicleDescription(String desc, Procedure proc);

  void chroniclePostcondition(Function<Boolean> expression, PostconditionError eager);

  void chroniclePostcondition(Procedure proc, PostconditionError eager);

  <S, T extends S> void chroniclePostcondition(Var<T> var, Function1<Boolean, S> expression, PostconditionError eager);

  <S, T extends S> void chroniclePostcondition(Var<T> var, Procedure1<S> proc, PostconditionError eager);

  <S, T extends S> void chroniclePostcondition(Var<T> var, Matcher<S> matchExpression, PostconditionError eager);
}
