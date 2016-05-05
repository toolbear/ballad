package ballad;

public interface Assertion1<S> extends Procedure1<S> {
  @Override
  void invoke(S t) throws AssertionError;
}
