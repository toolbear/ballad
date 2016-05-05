package ballad;

class Ballad {
  private static final ThreadLocal<Scribe> scribe = new ThreadLocal<>();

  static final <T> Var<T> var() {
    return new Var<>();
  }

  static final void recruit(Scribe s) {
    scribe.set(s);
  }

  static final Scribe scribe() {
    return scribe.get();
  }

  static final void retire(Scribe s) {
    if (scribe.get() == s) scribe.remove();
  }
}
