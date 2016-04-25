package ballad.trait;

import org.mockito.*;
import org.mockito.stubbing.*;
import org.mockito.verification.*;

public interface WithMockito {

  default <T> T mock(Class<T> classToMock) {
    return org.mockito.Mockito.mock(classToMock);
  }

  default <T> T mock(Class<T> classToMock, String name) {
    return org.mockito.Mockito.mock(classToMock, name);
  }

  default MockingDetails mockingDetails(Object toInspect) {
    return org.mockito.Mockito.mockingDetails(toInspect);
  }

  default <T> T mock(Class<T> classToMock, Answer defaultAnswer) {
    return org.mockito.Mockito.mock(classToMock, defaultAnswer);
  }

  default <T> T mock(Class<T> classToMock, MockSettings mockSettings) {
    return org.mockito.Mockito.mock(classToMock, mockSettings);
  }

  default <T> T spy(T object) {
    return org.mockito.Mockito.spy(object);
  }

  @Incubating
  default <T> T spy(Class<T> classToSpy) {
    return org.mockito.Mockito.spy(classToSpy);
  }

  default <T> OngoingStubbing<T> when(T methodCall) {
    return org.mockito.Mockito.when(methodCall);
  }

  default <T> T verify(T mock) {
    return org.mockito.Mockito.verify(mock);
  }

  default <T> T verify(T mock, VerificationMode mode) {
    return org.mockito.Mockito.verify(mock, mode);
  }

  default void verifyNoMoreInteractions(Object... mocks) {
    org.mockito.Mockito.verifyNoMoreInteractions(mocks);
  }

  default void verifyZeroInteractions(Object... mocks) {
    org.mockito.Mockito.verifyZeroInteractions(mocks);
  }

  default Stubber doThrow(Throwable... toBeThrown) {
    return org.mockito.Mockito.doThrow(toBeThrown);
  }

  default Stubber doThrow(Class<? extends Throwable> toBeThrown) {
    return org.mockito.Mockito.doThrow(toBeThrown);
  }

  // Additional method helps users of JDK7+ to hide heap pollution / unchecked generics array creation
  @SuppressWarnings ({"unchecked", "varargs"})
  default Stubber doThrow(Class<? extends Throwable> toBeThrown, Class<? extends Throwable>... toBeThrownNext) {
    return org.mockito.Mockito.doThrow(toBeThrown, toBeThrownNext);
  }

  default Stubber doCallRealMethod() {
    return org.mockito.Mockito.doCallRealMethod();
  }

  default Stubber doAnswer(Answer answer) {
    return org.mockito.Mockito.doAnswer(answer);
  }

  default Stubber doNothing() {
    return org.mockito.Mockito.doNothing();
  }

  default Stubber doReturn(Object toBeReturned) {
    return org.mockito.Mockito.doReturn(toBeReturned);
  }

  @SuppressWarnings({"unchecked", "varargs"})
  default Stubber doReturn(Object toBeReturned, Object... toBeReturnedNext) {
    return org.mockito.Mockito.doReturn(toBeReturned, toBeReturnedNext);
  }

  default InOrder inOrder(Object... mocks) {
    return org.mockito.Mockito.inOrder(mocks);
  }

  default Object[] ignoreStubs(Object... mocks) {
    return org.mockito.Mockito.ignoreStubs(mocks);
  }

  default VerificationMode times(int wantedNumberOfInvocations) {
    return org.mockito.Mockito.times(wantedNumberOfInvocations);
  }

  default VerificationMode never() {
    return org.mockito.Mockito.never();
  }

  default VerificationMode atLeastOnce() {
    return org.mockito.Mockito.atLeastOnce();
  }

  default VerificationMode atLeast(int minNumberOfInvocations) {
    return org.mockito.Mockito.atLeast(minNumberOfInvocations);
  }

  default VerificationMode atMost(int maxNumberOfInvocations) {
    return org.mockito.Mockito.atMost(maxNumberOfInvocations);
  }

  default VerificationMode calls( int wantedNumberOfInvocations ){
    return org.mockito.Mockito.calls(wantedNumberOfInvocations);
  }

  default VerificationMode only() {
    return org.mockito.Mockito.only();
  }

  default VerificationWithTimeout timeout(long millis) {
    return org.mockito.Mockito.timeout(millis);
  }

  default VerificationAfterDelay after(long millis) {
    return org.mockito.Mockito.after(millis);
  }

  default MockSettings withSettings() {
    return org.mockito.Mockito.withSettings();
  }

  default VerificationMode description(String description) {
    return org.mockito.Mockito.description(description);
  }
}
