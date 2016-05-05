package ballad;

import java.util.ArrayList;
import java.util.List;

final class BlockContext {
  private final BlockContext outer;
  private final String description;
  private final List<Clause> clauses;

  BlockContext(Class<?> spec) {
    this(null, spec.getSimpleName(), true);
  }

  BlockContext(BlockContext outer, Class<?> subject) {
    this(outer, subject.getSimpleName(), false);
  }

  BlockContext(BlockContext outer, String desc) {
    this(outer, desc, false);
  }

  private BlockContext(BlockContext outer, String desc, boolean isRoot) {
    this.outer = outer;
    this.description = desc;
    this.clauses = new ArrayList<>();
  }

  BlockContext context() {
    return outer;
  }

  String description() {
    return description;
  }

  List<Clause> clauses() {
    return clauses;
  }

  @Override
  public String toString() {
    return String.format("Context<%s>", description);
  }

  boolean isRoot() {
    return outer == null;
  }

  void addPrecondition(Precondition p) {
    clauses.add(p);
  }

  void addSpecification(Specification s) {
    clauses.add(s);
  }
}
