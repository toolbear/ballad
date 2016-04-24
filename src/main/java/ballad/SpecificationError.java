package ballad;

/** indicates an error on the test writer's part in specifying the tests */
class SpecificationError extends Error {
  private static final long serialVersionUID = 6659195506386880422L;

  SpecificationError(String message, PostconditionError eager) {
    super(message);
    this.setStackTrace(eager.getStackTrace());
  }
}
