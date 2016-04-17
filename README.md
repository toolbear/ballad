# Given/When/Then for JUnit

Ballad provides a specification DSL similar to [rspec-given](https://github.com/rspec-given/rspec-given).

```java
public class PostfixCalculatorSpec implements BalladSpec {
  private Calculator subject;

  describe(PostfixCalculator.class, () -> {

    Given(subject, new PostfixCalculator());

    context("no input", () -> {
      Then(() -> assertThat(subject.peek()).isEqualTo(0);
    });

    context("one input", () -> {
      When(() -> subject.input(5));

      Then(() -> assertThat(subject.peek()).isEqualTo(5));
    });

    context("two inputs", () -> {
      When(() -> {
        subject.input(5);
        subject.input(2);
      });

      Then(() -> assertThat(subject.peek()).isEqualTo(2));

      context("adding", () -> {
        When(() -> subject.add());

        Then(() -> assertThat(subject.peek()).isEqualTo(7));
      });
    });
  });
}
```

## Installation

Ballad requires Java 8 or later and JUnit 4.



## Differences from G/W/T in other languages (e.g. rspec-given, jasmine-given, mocha-gwt)

* Ballad is an extension to JUnit, not to an rspec-like framework. There are no
  `#before/#beforeEach/#after/#afterEach` hooks nor is there an `#it`. This omission is
  intentional

* Java 8 lambas aren't closures; variables in an outer scope must be "effectively final".
  Two workarounds — member variables and `Var` wrappers — are available for writing
  DRY specs.

## Example

TODO: write me

## TODO

* [ ] improve README
  * [ ] write more detailed example
  * [ ] installation instructions for Gradle, Maven, direct download
* [ ] DSL
  * `#Given(class|description)`
  * `#describe` and `#context`
  * `#Given(fn)`
  * `#Given(Var<T> var, T value)`
  * `#When(fn)`
  * `#When(Var<T> var, T value)`
  * `#Then(fn)`
  * `#Then(value, matcher)`
  * `#Then(Var<T> var, matcher)`
  * `#Invariant(fn|value, matcher|Var<T> var, matcher)`
* [ ] deploy
  * [ ] travis build
  * [ ] publish to maven
  * [ ] automatically reflect latest release in installation instructions
