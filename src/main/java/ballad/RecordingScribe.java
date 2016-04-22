package ballad;

import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class RecordingScribe implements Scribe {
  private final Collection<Postcondition> accumulator;

  RecordingScribe(Collection<Postcondition> acc) {
    this.accumulator = acc;
  }

  @Override
  public void chroniclePostcondition(Function<Boolean> expression, PostconditionError eager) {
    accumulator.add(() -> {
      if (!expression.invoke()) throw eager;
    });
  }

  @Override
  public void chroniclePostcondition(Procedure proc, PostconditionError eager) {
    accumulator.add(() -> {
      try {
        proc.invoke();
      } catch (final AssertionError cause) {
        throw new PostconditionError(eager, cause);
      }
    });
  }

  @Override
  public <T> void chroniclePostcondition(T value, Function1<Boolean, T> expression, PostconditionError eager) {
    accumulator.add(() -> {
      if (!expression.invoke(value)) throw eager;
    });
  }

  @Override
  public <T> void chroniclePostcondition(Var<T> var, Function1<Boolean, T> expression, PostconditionError eager) {
    accumulator.add(() -> {
      if (!expression.invoke(var.get())) throw eager;
    });
  }

  @Override
  public <T> void chroniclePostcondition(T value, Procedure1<T> proc, PostconditionError eager) {
    accumulator.add(() -> {
      try {
        proc.invoke(value);
      } catch (final AssertionError cause) {
        throw new PostconditionError(eager, cause);
      }
    });
  }

  @Override
  public <T> void chroniclePostcondition(Var<T> var, Procedure1<T> proc, PostconditionError eager) {
    accumulator.add(() -> {
      try {
        proc.invoke(var.get());
      } catch (final AssertionError cause) {
        throw new PostconditionError(eager, cause);
      }
    });
  }

  @Override
  public <T> void chroniclePostcondition(T value, Matcher<? super T> matchExpression, PostconditionError eager) {
    accumulator.add(() -> {
      if (!matchExpression.matches(value)) {
        final Description description = new StringDescription();
        description.appendText("").appendText("\nExpected: ").appendDescriptionOf(matchExpression)
            .appendText("\n     but: ");
        matchExpression.describeMismatch(value, description);
        throw new PostconditionError(eager, description);
      }
    });
  }

  @Override
  public <T> void chroniclePostcondition(Var<T> var, Matcher<? super T> matchExpression, PostconditionError eager) {
    accumulator.add(() -> {
      if (!matchExpression.matches(var.get())) {
        final Description description = new StringDescription();
        description.appendText("").appendText("\nExpected: ").appendDescriptionOf(matchExpression)
            .appendText("\n     but: ");
        matchExpression.describeMismatch(var.get(), description);
        throw new PostconditionError(eager, description);
      }
    });
  }
}
