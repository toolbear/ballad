package ballad;

public interface Precondition extends Clause {
  @Override
  default boolean isPrecondition() {
    return true;
  }
}
