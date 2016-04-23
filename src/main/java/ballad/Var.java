package ballad;

public final class Var<T> {
  private T value;

  Var(T value) {
    this.value = value;
  }

  public T get() {
    return value;
  }

  public void set(T value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("Var❪%s❫", value);
  }
}
