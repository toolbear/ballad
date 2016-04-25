package ballad.trait;

import org.hamcrest.Matcher;

public interface WithHamcrestCore {
  default <T> void assertThat(T actual, Matcher<? super T> matcher) {
    org.hamcrest.MatcherAssert.assertThat(actual, matcher);
  }

  default <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
    org.hamcrest.MatcherAssert.assertThat(reason, actual, matcher);
  }

  default void assertThat(String reason, boolean assertion) {
    org.hamcrest.MatcherAssert.assertThat(reason, assertion);
  }

  default <T> org.hamcrest.Matcher<T> allOf(java.lang.Iterable<org.hamcrest.Matcher<? super T>> matchers) {
    return org.hamcrest.core.AllOf.<T>allOf(matchers);
  }

  @SuppressWarnings("unchecked")
  default <T> org.hamcrest.Matcher<T> allOf(org.hamcrest.Matcher<? super T>... matchers) {
    return org.hamcrest.core.AllOf.<T>allOf(matchers);
  }

  default <T> org.hamcrest.Matcher<T> allOf(org.hamcrest.Matcher<? super T> first, org.hamcrest.Matcher<? super T> second) {
    return org.hamcrest.core.AllOf.<T>allOf(first, second);
  }

  default <T> org.hamcrest.Matcher<T> allOf(org.hamcrest.Matcher<? super T> first, org.hamcrest.Matcher<? super T> second, org.hamcrest.Matcher<? super T> third) {
    return org.hamcrest.core.AllOf.<T>allOf(first, second, third);
  }

  default <T> org.hamcrest.Matcher<T> allOf(org.hamcrest.Matcher<? super T> first, org.hamcrest.Matcher<? super T> second, org.hamcrest.Matcher<? super T> third, org.hamcrest.Matcher<? super T> fourth) {
    return org.hamcrest.core.AllOf.<T>allOf(first, second, third, fourth);
  }

  default <T> org.hamcrest.Matcher<T> allOf(org.hamcrest.Matcher<? super T> first, org.hamcrest.Matcher<? super T> second, org.hamcrest.Matcher<? super T> third, org.hamcrest.Matcher<? super T> fourth, org.hamcrest.Matcher<? super T> fifth) {
    return org.hamcrest.core.AllOf.<T>allOf(first, second, third, fourth, fifth);
  }

  default <T> org.hamcrest.Matcher<T> allOf(org.hamcrest.Matcher<? super T> first, org.hamcrest.Matcher<? super T> second, org.hamcrest.Matcher<? super T> third, org.hamcrest.Matcher<? super T> fourth, org.hamcrest.Matcher<? super T> fifth, org.hamcrest.Matcher<? super T> sixth) {
    return org.hamcrest.core.AllOf.<T>allOf(first, second, third, fourth, fifth, sixth);
  }

  default <T> org.hamcrest.core.AnyOf<T> anyOf(java.lang.Iterable<org.hamcrest.Matcher<? super T>> matchers) {
    return org.hamcrest.core.AnyOf.<T>anyOf(matchers);
  }

  default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first, org.hamcrest.Matcher<? super T> second, org.hamcrest.Matcher<? super T> third) {
    return org.hamcrest.core.AnyOf.<T>anyOf(first, second, third);
  }

  default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first, org.hamcrest.Matcher<? super T> second, org.hamcrest.Matcher<? super T> third, org.hamcrest.Matcher<? super T> fourth) {
    return org.hamcrest.core.AnyOf.<T>anyOf(first, second, third, fourth);
  }

  default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first, org.hamcrest.Matcher<? super T> second, org.hamcrest.Matcher<? super T> third, org.hamcrest.Matcher<? super T> fourth, org.hamcrest.Matcher<? super T> fifth) {
    return org.hamcrest.core.AnyOf.<T>anyOf(first, second, third, fourth, fifth);
  }

  default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first, org.hamcrest.Matcher<? super T> second, org.hamcrest.Matcher<? super T> third, org.hamcrest.Matcher<? super T> fourth, org.hamcrest.Matcher<? super T> fifth, org.hamcrest.Matcher<? super T> sixth) {
    return org.hamcrest.core.AnyOf.<T>anyOf(first, second, third, fourth, fifth, sixth);
  }

  default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first, org.hamcrest.Matcher<? super T> second) {
    return org.hamcrest.core.AnyOf.<T>anyOf(first, second);
  }

  @SuppressWarnings("unchecked")
  default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<? super T>... matchers) {
    return org.hamcrest.core.AnyOf.<T>anyOf(matchers);
  }

  default <LHS> org.hamcrest.core.CombinableMatcher.CombinableBothMatcher<LHS> both(org.hamcrest.Matcher<? super LHS> matcher) {
    return org.hamcrest.core.CombinableMatcher.<LHS>both(matcher);
  }

  default <LHS> org.hamcrest.core.CombinableMatcher.CombinableEitherMatcher<LHS> either(org.hamcrest.Matcher<? super LHS> matcher) {
    return org.hamcrest.core.CombinableMatcher.<LHS>either(matcher);
  }

  default <T> org.hamcrest.Matcher<T> describedAs(java.lang.String description, org.hamcrest.Matcher<T> matcher, java.lang.Object... values) {
    return org.hamcrest.core.DescribedAs.<T>describedAs(description, matcher, values);
  }

  default <U> org.hamcrest.Matcher<java.lang.Iterable<U>> everyItem(org.hamcrest.Matcher<U> itemMatcher) {
    return org.hamcrest.core.Every.<U>everyItem(itemMatcher);
  }

  default <T> org.hamcrest.Matcher<T> is(T value) {
    return org.hamcrest.core.Is.<T>is(value);
  }

  default <T> org.hamcrest.Matcher<T> is(org.hamcrest.Matcher<T> matcher) {
    return org.hamcrest.core.Is.<T>is(matcher);
  }

  default <T> org.hamcrest.Matcher<T> isA(java.lang.Class<T> type) {
    return org.hamcrest.core.Is.<T>isA(type);
  }

  default org.hamcrest.Matcher<java.lang.Object> anything() {
    return org.hamcrest.core.IsAnything.anything();
  }

  default org.hamcrest.Matcher<java.lang.Object> anything(java.lang.String description) {
    return org.hamcrest.core.IsAnything.anything(description);
  }

  default <T> org.hamcrest.Matcher<java.lang.Iterable<? super T>> hasItem(T item) {
    return org.hamcrest.core.IsCollectionContaining.<T>hasItem(item);
  }

  default <T> org.hamcrest.Matcher<java.lang.Iterable<? super T>> hasItem(org.hamcrest.Matcher<? super T> itemMatcher) {
    return org.hamcrest.core.IsCollectionContaining.<T>hasItem(itemMatcher);
  }

  @SuppressWarnings("unchecked")
  default <T> org.hamcrest.Matcher<java.lang.Iterable<T>> hasItems(T... items) {
    return org.hamcrest.core.IsCollectionContaining.<T>hasItems(items);
  }

  @SuppressWarnings("unchecked")
  default <T> org.hamcrest.Matcher<java.lang.Iterable<T>> hasItems(org.hamcrest.Matcher<? super T>... itemMatchers) {
    return org.hamcrest.core.IsCollectionContaining.<T>hasItems(itemMatchers);
  }

  default <T> org.hamcrest.Matcher<T> equalTo(T operand) {
    return org.hamcrest.core.IsEqual.<T>equalTo(operand);
  }

  default <T> org.hamcrest.Matcher<T> any(java.lang.Class<T> type) {
    return org.hamcrest.core.IsInstanceOf.<T>any(type);
  }

  default <T> org.hamcrest.Matcher<T> instanceOf(java.lang.Class<?> type) {
    return org.hamcrest.core.IsInstanceOf.<T>instanceOf(type);
  }

  default <T> org.hamcrest.Matcher<T> not(org.hamcrest.Matcher<T> matcher) {
    return org.hamcrest.core.IsNot.<T>not(matcher);
  }

  default <T> org.hamcrest.Matcher<T> not(T value) {
    return org.hamcrest.core.IsNot.<T>not(value);
  }

  default org.hamcrest.Matcher<java.lang.Object> nullValue() {
    return org.hamcrest.core.IsNull.nullValue();
  }

  default <T> org.hamcrest.Matcher<T> nullValue(java.lang.Class<T> type) {
    return org.hamcrest.core.IsNull.<T>nullValue(type);
  }

  default org.hamcrest.Matcher<java.lang.Object> notNullValue() {
    return org.hamcrest.core.IsNull.notNullValue();
  }

  default <T> org.hamcrest.Matcher<T> notNullValue(java.lang.Class<T> type) {
    return org.hamcrest.core.IsNull.<T>notNullValue(type);
  }

  default <T> org.hamcrest.Matcher<T> sameInstance(T target) {
    return org.hamcrest.core.IsSame.<T>sameInstance(target);
  }

  default <T> org.hamcrest.Matcher<T> theInstance(T target) {
    return org.hamcrest.core.IsSame.<T>theInstance(target);
  }

  default org.hamcrest.Matcher<java.lang.String> containsString(java.lang.String substring) {
    return org.hamcrest.core.StringContains.containsString(substring);
  }

  default org.hamcrest.Matcher<java.lang.String> startsWith(java.lang.String prefix) {
    return org.hamcrest.core.StringStartsWith.startsWith(prefix);
  }

  default org.hamcrest.Matcher<java.lang.String> endsWith(java.lang.String suffix) {
    return org.hamcrest.core.StringEndsWith.endsWith(suffix);
  }
}
