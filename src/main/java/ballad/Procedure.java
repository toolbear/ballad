package ballad;

@FunctionalInterface
public interface Procedure {
  void invoke() throws AssertionError;
}
