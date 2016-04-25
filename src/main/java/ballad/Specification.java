package ballad;

public interface Specification extends Clause {
  @Override
  default boolean isSpecification() {
    return true;
  }
}
