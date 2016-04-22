package ballad;

import java.util.Arrays;
import org.hamcrest.Description;

class PostconditionError extends AssertionError {
  private static final long serialVersionUID = -3383182470908582913L;

  static final PostconditionError eager() {
    final PostconditionError e = new PostconditionError();
    final StackTraceElement[] a = e.getStackTrace();
    e.setStackTrace(Arrays.copyOfRange(a, 2, a.length, a.getClass()));
    return e;
  }

  private PostconditionError() {
    this("postcondition not met");
  }

  private PostconditionError(String message, AssertionError cause) {
    super(message, cause);
  }

  PostconditionError(PostconditionError eager, AssertionError cause) {
    this(eager.getMessage(), cause);
    setStackTrace(eager.getStackTrace());
  }

  PostconditionError(String message) {
    super(message);
  }

  PostconditionError(PostconditionError eager, Description description) {
    this(String.format("postcondition not met%s", description.toString()));
    setStackTrace(eager.getStackTrace());
  }
}
