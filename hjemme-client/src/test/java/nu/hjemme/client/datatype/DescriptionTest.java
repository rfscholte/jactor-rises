package nu.hjemme.client.datatype;


import org.junit.jupiter.api.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class DescriptionTest {

    @Test void whenInvokingHashCodeTheImplementationShouldBeCorrect() {
        Description base = new Description(new Name("some item"), "some description");
        Description equal = new Description(new Name("some item"), "some description");
        Description notEqual = new Description(new Name("some other item"), "some other description");

        assertThat(base.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test void whenChecksForEqualityTheImplementationShouldBeCorrect() {
        Description base = new Description(new Name("some item"), "some description");
        Description equal = new Description(new Name("some item"), "some description");
        Description notEqual = new Description(new Name("some other item"), "some other description");

        assertThat(base, implementsWith(equal, notEqual));
    }

    @Test void willSpellDescriptionInToString() {
        assertThat(new Description("some description").toString(), is(equalTo("some description")));
    }
}
