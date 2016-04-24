package ballad;

public final class Var<T> {
  private T value;
  private boolean initialized = false;

  Var() {}

  boolean initialized() {
    return initialized;
  }

  public T get() {
    return value;
  }

  public void set(T value) {
    this.value = value;
    this.initialized = true;
  }

  @Override
  public String toString() {
    return initialized
        ? String.format("Var<%s>❪%s❫", value != null ? value.getClass().getSimpleName() : "", value)
            : "Var<>";
  }
}
