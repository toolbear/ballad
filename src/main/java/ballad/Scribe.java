package ballad;

import org.hamcrest.Matcher;

public interface Scribe {
  void recordPostcondition(Function<Boolean> postcondition);

  void recordPostcondition(Procedure postcondition);

  <T> void recordPostcondition(T value, Function1<Boolean, T> postcondition);

  <T> void recordPostcondition(Var<T> var, Function1<Boolean, T> postcondition);

  <T> void recordPostcondition(T value, Procedure1<T> postcondition);

  <T> void recordPostcondition(Var<T> value, Procedure1<T> postcondition);

  <T extends Object> void recordPostcondition(T value, Matcher<Object> matcher);

  <T extends Object> void recordPostcondition(Var<T> var, Matcher<Object> matcher);
}
