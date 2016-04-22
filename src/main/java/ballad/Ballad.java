package ballad;

public class Ballad {
  private static final ThreadLocal<Scribe> scribe = new ThreadLocal<>();

  public static final <T> Var<T> var(T value) {
    return new Var<>(value);
  }

  static final void recruit(Scribe s) {
    scribe.set(s);
  }

  static final Scribe scribe() {
    return scribe.get();
  }

  static final void retire(Scribe s) {
    if (scribe.get() == s) {
      scribe.remove();
    }
  }
}
