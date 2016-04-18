package ballad;

public class Ballad {
  private static final ThreadLocal<Scribe> scribe = new ThreadLocal<>();

  public static final <T> Var<T> var(T value) {
    return new Var<>(value);
  }

  static final void recruit(Scribe scribe) {
    Ballad.scribe.set(scribe);
  }

  static final Scribe scribe() {
    return Ballad.scribe.get();
  }
}
