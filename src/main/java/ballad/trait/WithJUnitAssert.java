package ballad.trait;

import org.junit.internal.ArrayComparisonFailure;

public interface WithJUnitAssert {

  default void assertTrue(String message, boolean condition) {
    org.junit.Assert.assertTrue(message, condition);
  }

  default void assertTrue(boolean condition) {
    org.junit.Assert.assertTrue(condition);
  }

  default void assertFalse(String message, boolean condition) {
    org.junit.Assert.assertFalse(message, condition);
  }

  default void assertFalse(boolean condition) {
    org.junit.Assert.assertFalse(condition);
  }

  default void fail(String message) {
    org.junit.Assert.fail(message);
  }

  default void fail() {
    org.junit.Assert.fail();
  }

  default void assertEquals(String message, Object expected, Object actual) {
    org.junit.Assert.assertEquals(message, expected, actual);
  }

  default void assertEquals(Object expected, Object actual) {
    org.junit.Assert.assertEquals(expected, actual);
  }

  default void assertNotEquals(String message, Object unexpected, Object actual) {
    org.junit.Assert.assertNotEquals(message, unexpected, actual);
  }

  default void assertNotEquals(Object unexpected, Object actual) {
    org.junit.Assert.assertNotEquals(unexpected, actual);
  }

  default void assertNotEquals(String message, long unexpected, long actual) {
    org.junit.Assert.assertNotEquals(message, unexpected, actual);
  }

  default void assertNotEquals(long unexpected, long actual) {
    org.junit.Assert.assertNotEquals(unexpected, actual);
  }

  default void assertNotEquals(String message, double unexpected, double actual, double delta) {
    org.junit.Assert.assertNotEquals(message, unexpected, actual, delta);
  }

  default void assertNotEquals(double unexpected, double actual, double delta) {
    org.junit.Assert.assertNotEquals(unexpected, actual, delta);
  }

  default void assertNotEquals(float unexpected, float actual, float delta) {
    org.junit.Assert.assertNotEquals(unexpected, actual, delta);
  }

  default void assertArrayEquals(String message, Object[] expecteds, Object[] actuals) throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
  }

  default void assertArrayEquals(Object[] expecteds, Object[] actuals) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals);
  }

  default void assertArrayEquals(String message, boolean[] expecteds, boolean[] actuals) throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
  }

  default void assertArrayEquals(boolean[] expecteds, boolean[] actuals) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals);
  }

  default void assertArrayEquals(String message, byte[] expecteds, byte[] actuals) throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
  }

  default void assertArrayEquals(byte[] expecteds, byte[] actuals) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals);
  }

  default void assertArrayEquals(String message, char[] expecteds, char[] actuals) throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
  }

  default void assertArrayEquals(char[] expecteds, char[] actuals) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals);
  }

  default void assertArrayEquals(String message, short[] expecteds, short[] actuals) throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
  }

  default void assertArrayEquals(short[] expecteds, short[] actuals) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals);
  }

  default void assertArrayEquals(String message, int[] expecteds, int[] actuals) throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
  }

  default void assertArrayEquals(int[] expecteds, int[] actuals) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals);
  }

  default void assertArrayEquals(String message, long[] expecteds, long[] actuals) throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
  }

  default void assertArrayEquals(long[] expecteds, long[] actuals) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals);
  }

  default void assertArrayEquals(String message, double[] expecteds, double[] actuals, double delta)
      throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals, delta);
  }

  default void assertArrayEquals(double[] expecteds, double[] actuals, double delta) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals, delta);
  }

  default void assertArrayEquals(String message, float[] expecteds, float[] actuals, float delta)
      throws ArrayComparisonFailure {
    org.junit.Assert.assertArrayEquals(message, expecteds, actuals, delta);
  }

  default void assertArrayEquals(float[] expecteds, float[] actuals, float delta) {
    org.junit.Assert.assertArrayEquals(expecteds, actuals, delta);
  }

  default void assertEquals(String message, double expected, double actual, double delta) {
    org.junit.Assert.assertEquals(message, expected, actual, delta);
  }

  default void assertEquals(String message, float expected, float actual, float delta) {
    org.junit.Assert.assertEquals(message, expected, actual, delta);
  }

  default void assertNotEquals(String message, float unexpected, float actual, float delta) {
    org.junit.Assert.assertNotEquals(message, unexpected, actual, delta);
  }

  default void assertEquals(long expected, long actual) {
    org.junit.Assert.assertEquals(expected, actual);
  }

  default void assertEquals(String message, long expected, long actual) {
    org.junit.Assert.assertEquals(message, expected, actual);
  }

  default void assertEquals(double expected, double actual, double delta) {
    org.junit.Assert.assertEquals(expected, actual, delta);
  }

  default void assertEquals(float expected, float actual, float delta) {
    org.junit.Assert.assertEquals(expected, actual, delta);
  }

  default void assertNotNull(String message, Object object) {
    org.junit.Assert.assertNotNull(message, object);
  }

  default void assertNotNull(Object object) {
    org.junit.Assert.assertNotNull(object);
  }

  default void assertNull(String message, Object object) {
    org.junit.Assert.assertNull(message, object);
  }

  default void assertNull(Object object) {
    org.junit.Assert.assertNull(object);
  }

  default void assertSame(String message, Object expected, Object actual) {
    org.junit.Assert.assertSame(message, expected, actual);
  }

  default void assertSame(Object expected, Object actual) {
    org.junit.Assert.assertSame(expected, actual);
  }

  default void assertNotSame(String message, Object unexpected, Object actual) {
    org.junit.Assert.assertNotSame(message, unexpected, actual);
  }

  default void assertNotSame(Object unexpected, Object actual) {
    org.junit.Assert.assertNotSame(unexpected, actual);
  }
}
