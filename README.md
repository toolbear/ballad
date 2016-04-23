# Given/When/Then for JUnit

Ballad provides a specification DSL similar to [rspec-given](https://github.com/rspec-given/rspec-given).

```java
describe(PostfixCalculator.class, () -> {
  Given(subject, new PostfixCalculator());

  When(() -> {
    subject.push(5);
    subject.push(2);
  });

  Then(() -> assertEquals(2, subject.peek()));

  context("adding", () -> {
    When(() -> subject.plus());

    Then(() -> assertEquals(7, subject.peek()));
  });
});
```

## Installation

Ballad requires Java 8 or later and JUnit 4.

## Differences from G/W/T in other languages

Given/When/Then implementations in other languages (e.g. rspec-given, jasmine-given, mocha-gwt)
have historically been written as extensions to rspec or an equivalent framework. They also tend
to be written in languages that have closures.

With Ballad:

* There are no `#before/#beforeEach/#after/#afterEach` hooks. You should prefer setting up
  preconditions with `#Given`. You can use JUnit's `@Before`; there are no ordering
  guarantees between `#Given` and `@Before` (the ordering of nested Givens is well defined, however).

* There are no `#it` blocks; specifications/assertions must be written with `#Then`.

* Java 8 lambas aren't closures; variables in an outer scope must be "effectively final". Part
  of what makes Given/When/Then so expressive and consise is altering initial conditions in
  nested scopes while reusing `#Given` and `#When` clauses from outer scopes. Two workarounds
  — member variables and `Var` wrappers — can be used to approximate closures.

## Example

This example uses [AssertJ fluent assertions](http://joel-costigliola.github.io/assertj/index.html), but any assertion library can be used.

```java
public class PostfixCalculatorSpec implements BalladSpec {
  private Calculator subject;

  {
    describe(PostfixCalculator.class, () -> {

      Given(subject, new PostfixCalculator());

      context("no input", () -> {
        Then(() -> assertThat(subject.peek()).isEqualTo(0);
      });

      context("one input", () -> {
        When(() -> subject.push(5));

        Then(() -> assertThat(subject.peek()).isEqualTo(5));
      });

      context("two inputs", () -> {
        When(() -> {
          subject.push(5);
          subject.push(2);
        });

        Then(() -> assertThat(subject.peek()).isEqualTo(2));

        context("adding", () -> {
          When(() -> subject.plus());

          Then(() -> assertThat(subject.peek()).isEqualTo(7));
        });
      });
    });
  }
}
```

## TODO

* [ ] improve README
  * [ ] write more detailed example
  * [ ] installation instructions for Gradle, Maven, direct download
* [ ] DSL
  * [x] `#describe(class|description)`
  * [x] `#context(description)`
  * [ ] `#Given(description)`
  * [ ] `#Given(fn)`
  * [ ] `#Given(Var<T> var, T value)`
  * [ ] `#When(fn)`
  * [ ] `#When(Var<T> var, T value)`
  * [x] `#Then(fn)`
  * [x] `#Then(value, fn)`
  * [x] `#Then(Var<T>, fn)`
  * [x] `#Then(value, matcher)`
  * [x] `#Then(Var<T> var, matcher)`
  * [ ] `#Invariant(fn|value, matcher|Var<T> var, matcher)`
* [ ] deploy
  * [ ] travis build
  * [ ] publish to maven
  * [ ] automatically reflect latest release in installation instructions
