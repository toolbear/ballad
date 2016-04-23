package ballad;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

public class Balladeer extends ParentRunner<Postcondition> {
  private final List<Postcondition> accumulator;

  public Balladeer(Class<?> testClass) throws InitializationError {
    this(new ArrayList<Postcondition>(), testClass);
  }

  private Balladeer(List<Postcondition> acc, Class<?> testClass) throws InitializationError {
    this(new RecordingScribe(acc), acc, testClass);
  }

  Balladeer(Scribe s, List<Postcondition> acc, Class<?> testClass) throws InitializationError {
    super(testClass);
    Ballad.recruit(s);
    try {
      testClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new InitializationError(e);
    }
    this.accumulator = acc;
    Ballad.retire(s);
  }

  @Override
  protected List<Postcondition> getChildren() {
    return accumulator;
  }

  @Override
  protected Description describeChild(Postcondition postcondition) {
    return Description.createTestDescription(getTestClass().getJavaClass(),
        String.format("Then… (%d)", postcondition.hashCode()));
  }

  @Override
  protected void runChild(Postcondition postcondition, RunNotifier notifier) {
    final Description desc = describeChild(postcondition);
    notifier.fireTestStarted(desc);
    try {
      postcondition.verify();
    } catch (final AssertionError e) {
      notifier.fireTestFailure(new Failure(desc, e));
    }
  }
}
