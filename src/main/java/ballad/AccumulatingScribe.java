package ballad;

import java.util.Collection;
import org.hamcrest.*;

class AccumulatingScribe implements Scribe {
  private final Collection<Postcondition> accumulator;
  private BlockContext context;

  AccumulatingScribe(Collection<Postcondition> acc, BlockContext initial) {
    this.accumulator = acc;
    this.context = initial;
  }

  @Override
  public void chronicleContext(Class<?> c, Block block) {
    context = new BlockContext(context, c);
    try {
      block.invoke();
    } finally {
      context = context.context();
    }
  }

  @Override
  public void chronicleContext(String desc, Block block) {
    context = new BlockContext(context, desc);
    try {
      block.invoke();
    } finally {
      context = context.context();
    }
  }

  @Override
  public void chroniclePrecondition(Procedure proc) {
    context.addPrecondition(() -> proc.invoke());
  }

  @Override
  public <S, T extends S> void chroniclePrecondition(Var<S> var, Function<T> expression) {
    context.addPrecondition(() -> {
      var.set(expression.invoke());
    });
  }

  @Override
  public void chronicleSpecification(Action action) {
    context.addSpecification(() -> action.invoke());
  }

  @Override
  public <S, T extends S> void chronicleSpecification(Var<S> var, Function<T> expression) {
    context.addPrecondition(() -> {
      var.set(expression.invoke());
    });
  }

  @Override
  public <S, T extends S> void chronicleSpecification(Var<T> var, Action1<S> action) {
    context.addPrecondition(() -> {
      action.invoke(var.get());
    });
  }

  @Override
  public <S, T extends S> void chronicleSpecification(Var<S> var, Function1<T, S> expression) {
    context.addSpecification(() -> {
      var.set(expression.invoke(var.get()));
    });
  }

  @Override
  public void chroniclePostcondition(BooleanExpression expression, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        if (!expression.invoke()) throw eager;
      }
    });
  }

  @Override
  public void chroniclePostcondition(Assertion assertion, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        try {
          assertion.invoke();
        } catch (final AssertionError cause) {
          throw new PostconditionError(eager, cause);
        }
      }
    });
  }

  @Override
  public <S, T extends S> void chroniclePostcondition(Var<T> var, BooleanExpression1<S> expression, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        if (!var.initialized()) throw new UninitializedVarError(eager);
        if (!expression.invoke(var.get())) throw eager;
      }
    });
  }

  @Override
  public <S, T extends S> void chroniclePostcondition(Var<T> var, Assertion1<S> assertion, PostconditionError eager) {
    accumulator.add(new ContextualPostcondition(context) {
      @Override
      public void verify() {
        if (!var.initialized()) throw new UninitializedVarError(eager);
        try {
          assertion.invoke(var.get());
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

  private static abstract class ContextualPostcondition implements Postcondition {
    private final BlockContext context;

    ContextualPostcondition(BlockContext context) {
      this.context = context;
    }

    @Override
    public final BlockContext context() {
      return context;
    }
  }
}
