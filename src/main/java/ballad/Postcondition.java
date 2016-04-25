package ballad;

import org.hamcrest.Description;

public interface Postcondition {
  default void describeTo(Description description) {
    description.appendText(String.format("%h",  hashCode()));
  }

  Context context();
  void verify();
}
