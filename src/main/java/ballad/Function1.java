package ballad;

@FunctionalInterface
public interface Function1<T, U> {
  public T invoke(U u);
}
