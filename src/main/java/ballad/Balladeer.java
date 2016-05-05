package ballad;

import static org.junit.runner.Description.createSuiteDescription;
import static org.junit.runner.Description.createTestDescription;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
  private final Map<Postcondition, Deque<BlockContext>> contexts;

  public Balladeer(Class<?> spec) throws InitializationError {
    this(new ArrayList<Postcondition>(), new BlockContext(spec), spec);
  }

  private Balladeer(List<Postcondition> acc, BlockContext context, Class<?> spec) throws InitializationError {
    this(new AccumulatingScribe(acc, context), acc, context, spec);
  }

  Balladeer(Scribe s, List<Postcondition> acc, BlockContext root, Class<?> spec) throws InitializationError {
    super(spec);
    this.accumulator = acc;
    this.spec = spec;
    this.description = createSuiteDescription(spec);
    this.cache = new HashMap<>();
    this.contexts = new HashMap<>();

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
      contextsFor(postcondition).filter(c -> !cache.containsKey(c))
      .forEachOrdered(c -> {
        Description desc = createSuiteDescription(c.description());
        cache.get(c.context()).addChild(desc);
        cache.put(c, desc);
      });

      String stanza = contextsFor(postcondition).map(BlockContext::description).collect(Collectors.joining(" "));
      result = createTestDescription(spec, String.format("%s (%h)",
          stanza,
          postcondition.hashCode()));
      cache.get(postcondition.context()).addChild(result);
      cache.put(postcondition, result);
    }
    return result;
  }

  @Override
  protected void runChild(Postcondition postcondition, RunNotifier notifier) {
    try {
      clausesFor(postcondition).filter(Clause::isPrecondition).forEachOrdered(Clause::run);
      clausesFor(postcondition).filter(Clause::isSpecification).forEachOrdered(Clause::run);
      postcondition.verify();
    } catch (AssertionError|SpecificationError e) {
      notifier.fireTestFailure(new Failure(cache.get(postcondition), e));
    }
  }

  private Stream<Clause> clausesFor(Postcondition postcondition) {
    return contextsFor(postcondition).map(BlockContext::clauses).flatMap(List::stream);
  }

  private Stream<BlockContext> contextsFor(Postcondition postcondition) {
    Deque<BlockContext> stack = contexts.get(postcondition);
    if (stack == null) {
      stack = new ArrayDeque<>();
      BlockContext context = postcondition.context();
      do {
        stack.push(context);
        context = context.context();
      } while (context != null);
      contexts.put(postcondition, stack);
    }
    return stack.stream();
  }
}
