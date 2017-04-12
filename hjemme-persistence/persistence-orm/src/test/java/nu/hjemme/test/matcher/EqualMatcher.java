package nu.hjemme.test.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.core.AllOf.allOf;

public class EqualMatcher<T> extends TypeSafeMatcher<T> {
    private final T equal;
    private final T notEqual;

    private EqualMatcher(T equal, T notEqual) {
        this.equal = equal;
        this.notEqual = notEqual;
    }

    @Override protected boolean matchesSafely(T item) {
        Matcher<T> equalMatches = allOf(
                equalTo(equal), not(equalTo(notEqual)), not(equalTo(null))
        );

        return equalMatches.matches(item) && sameInstance(item).matches(item);
    }

    @Override public void describeTo(Description description) {
        description.appendText("equals implementation according to java specification");
    }

    public static <V> Matcher<V> implementsWith(V equal, V notEqual) {
        return new EqualMatcher<>(equal, notEqual);
    }
}
