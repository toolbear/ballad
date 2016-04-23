package ballad;

import static org.junit.runner.Description.createSuiteDescription;
import static org.junit.runner.Description.createTestDescription;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

public class Balladeer extends ParentRunner<Postcondition> {
  private final List<Postcondition> accumulator;
  private final Class<?> spec;
  private final Description description;
  private final Map<Object, Description> cache;

  public Balladeer(Class<?> spec) throws InitializationError {
    this(new ArrayList<Postcondition>(), new Context(spec), spec);
  }

  private Balladeer(List<Postcondition> acc, Context context, Class<?> spec) throws InitializationError {
    this(new RecordingScribe(acc, context), acc, context, spec);
  }

  Balladeer(Scribe s, List<Postcondition> acc, Context root, Class<?> spec) throws InitializationError {
    super(spec);
    this.accumulator = acc;
    this.spec = spec;
    this.description = createSuiteDescription(spec);
    this.cache = new HashMap<>();

    cache.put(root, description);
    Ballad.recruit(s);
    try {
      spec.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new InitializationError(e);
    }
    Ballad.retire(s);

    // prime all the descriptions
    acc.stream().forEachOrdered(p -> { this.asJUnitDescription(p); });
  }

  @Override
  protected List<Postcondition> getChildren() {
    return accumulator;
  }

  @Override
  public Description getDescription() {
    return description;
  }

  @Override
  protected Description describeChild(Postcondition postcondition) {
    return cache.get(postcondition);
  }

  private Description asJUnitDescription(Postcondition postcondition) {
    Description result = cache.get(postcondition);
    if (result == null) {
      Deque<Context> stack = new ArrayDeque<>();
      Context context = postcondition.context();
      while (!context.isRoot()) {
        stack.push(context);
        context = context.context();
      }
      stack.stream()
      .filter(c -> !cache.containsKey(c))
      .forEachOrdered(c -> {
        Description desc = createSuiteDescription(c.description());
        cache.get(c.context()).addChild(desc);
        cache.put(c, desc);
      });

      String stanza = stack.stream()
          .map(Context::description)
          .collect(Collectors.joining(" "));
      result = createTestDescription(spec, String.format("%s (%h)", stanza, postcondition.hashCode()));
      cache.get(postcondition.context()).addChild(result);
      cache.put(postcondition, result);
    }
    return result;
  }

  @Override
  protected void runChild(Postcondition postcondition, RunNotifier notifier) {
    notifier.fireTestStarted(cache.get(postcondition));
    try {
      postcondition.verify();
    } catch (final AssertionError e) {
      notifier.fireTestFailure(new Failure(cache.get(postcondition), e));
    }
  }
}
