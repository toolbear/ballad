package ballad;

import org.hamcrest.Matcher;

interface Scribe {
  void chroniclePostcondition(Function<Boolean> expression, PostconditionError eager);

  void chroniclePostcondition(Procedure proc, PostconditionError eager);

  <T> void chroniclePostcondition(T value, Function1<Boolean, T> expression, PostconditionError eager);

  <T> void chroniclePostcondition(Var<T> var, Function1<Boolean, T> expression, PostconditionError eager);

  <T> void chroniclePostcondition(T value, Procedure1<T> proc, PostconditionError eager);

  <T> void chroniclePostcondition(Var<T> var, Procedure1<T> proc, PostconditionError eager);

  <T extends Object> void chroniclePostcondition(T value, Matcher<? super T> matchExpression, PostconditionError eager);

  <T extends Object> void chroniclePostcondition(Var<T> var, Matcher<? super T> matchExpression,
      PostconditionError eager);
}
