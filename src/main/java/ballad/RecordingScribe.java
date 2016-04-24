package ballad;

import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class RecordingScribe implements Scribe {
  private final Collection<Postcondition> accumulator;
  private Context context;

  private static abstract class ContextualPostcondition implements Postcondition {
    private final Context context;

    ContextualPostcondition(Context context) {
      this.context = context;
    }

    @Override
    public final Context context() {
      return context;
    }
  }

  RecordingScribe(Collection<Postcondition> acc, Context initial) {
    this.accumulator = acc;
    this.context = initial;
  }

  @Override
  public void chronicleContext(Class<?> c, Procedure proc) {
    context = new Context(context, c);
    try {
      proc.invoke();
    } finally {
      context = context.context();
    }
  }

  @Override
  public void chronicleContext(String desc, Procedure proc) {
    context = new Context(context, desc);
    try {
      proc.invoke();
    } finally {
      context = context.context();
    }
  }

  @Override
  public void chroniclePrecondition(Procedure proc) {
    context.addPrecondition(() -> {
      proc.invoke();
    });
  }

  @Override
  public <S, T extends S> void chroniclePrecondition(Var<S> var, Function<T> expression) {
    context.addPrecondition(() -> {
      var.set(expression.invoke());
    });
  }

  @Override
  public void chroniclePostcondition(Function<Boolean> expression, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        if (!expression.invoke()) throw eager;
      }
    });
  }

  @Override
  public void chroniclePostcondition(Procedure proc, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        try {
          proc.invoke();
        } catch (final AssertionError cause) {
          throw new PostconditionError(eager, cause);
        }
      }
    });
  }

  @Override
  public <S, T extends S> void chroniclePostcondition(Var<T> var, Function1<Boolean, S> expression, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        if (!var.initialized()) throw new UninitializedVarError(eager);
        if (!expression.invoke(var.get())) throw eager;
      }
    });
  }

  @Override
  public <S, T extends S> void chroniclePostcondition(Var<T> var, Procedure1<S> proc, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        if (!var.initialized()) throw new UninitializedVarError(eager);
        try {
          proc.invoke(var.get());
        } catch (final AssertionError cause) {
          throw new PostconditionError(eager, cause);
        }
      }
    });
  }

  @Override
  public <S, T extends S> void chroniclePostcondition(Var<T> var, Matcher<S> matchExpression, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        if (!var.initialized()) throw new UninitializedVarError(eager);
        T val = var.get();
        if (!matchExpression.matches(val)) {
          final Description description = new StringDescription();
          description.appendText("").appendText("\nExpected: ").appendDescriptionOf(matchExpression)
          .appendText("\n     but: ");
          matchExpression.describeMismatch(val, description);
          throw new PostconditionError(eager, description);
        }
      }
    });
  }
}
