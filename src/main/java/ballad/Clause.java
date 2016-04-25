package ballad;

@FunctionalInterface
public interface Clause {

  void run();

  default boolean isPrecondition() {
    return false;
  }

  default boolean isSpecification() {
    return false;
  }

  default boolean isPostcondition() {
    return false;
  }
}
