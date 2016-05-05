package ballad;

public interface Postcondition {
  BlockContext context();
  void verify();
}
