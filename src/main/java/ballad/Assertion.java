package ballad;

public interface Assertion extends Procedure {
  @Override
  void invoke() throws AssertionError;
}
