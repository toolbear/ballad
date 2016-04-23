package ballad;

public interface Postcondition {
  Context context();
  void verify();
}
