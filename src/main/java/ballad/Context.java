package ballad;

import java.util.ArrayList;
import java.util.List;

final class Context {
  private final Context outer;
  private final String description;
  private final List<Precondition> preconditions;

  Context(Class<?> spec) {
    this(null, spec.getSimpleName(), true);
  }

  Context(Context outer, Class<?> subject) {
    this(outer, subject.getSimpleName(), false);
  }

  Context(Context outer, String desc) {
    this(outer, desc, false);
  }

  private Context(Context outer, String desc, boolean isRoot) {
    this.outer = outer;
    this.description = desc;
    this.preconditions = new ArrayList<>();
  }

  public Context context() {
    return outer;
  }

  public String description() {
    return description;
  }

  public Iterable<Precondition> preconditions() {
    return preconditions;
  }

  @Override
  public String toString() {
    return String.format("Context<%s>", description);
  }

  public boolean isRoot() {
    return outer == null;
  }

  public void addPrecondition(Precondition pre) {
    this.preconditions.add(pre);
  }
}
