package ballad.trait;

public interface WithMockitoMatchers {

  default void any() {
    org.mockito.Matchers.any();
  }
}
