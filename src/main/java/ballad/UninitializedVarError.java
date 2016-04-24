package ballad;

class UninitializedVarError extends SpecificationError {
  private static final long serialVersionUID = 2763547938534888511L;
  private static final String MESSAGE = "Var<> never initialized.\n\n"
      + "A Var<> must be initialized before use with one of:\n"
      + "  • Given(v, () -> VALUE | EXPRESSION )\n"
      + "  • Given(() -> { v.set( VALUE | EXPRESSION ); })\n";

  UninitializedVarError(PostconditionError eager) {
    super(MESSAGE, eager);
  }
}
