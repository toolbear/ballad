package ballad;

@FunctionalInterface
public interface Procedure1<T> {
  void invoke(T t) throws AssertionError;
}
