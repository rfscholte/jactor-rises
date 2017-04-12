package nu.hjemme.client.datatype;

import org.junit.jupiter.api.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class UserNameTest {

    @Test void willBeEqualIfOnlyDifferenceIsTheCharacterCase() {
        assertThat(new UserName("jactor"), equalTo(new UserName("JACTOR")));
    }

    @Test void willProduceEqualHashCodeIfOnlyDifferenceIsTheCharacterCase() {
        assertThat(new UserName("jactor").hashCode(), equalTo(new UserName("JACTOR").hashCode()));
    }

    @Test void willImplementHashCodeAccordingToTheJavaSpecifications() {
        UserName base = new UserName("someone");
        UserName equal = new UserName("SOMEONE");
        UserName notEqual = new UserName("SOMEONE else");

        assertThat(base.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test void willImplementEqualsAccordingToTheJavaSpecifications() {
        UserName base = new UserName("someone");
        UserName equal = new UserName("SOMEONE");
        UserName notEqual = new UserName("SOMEONE else");

        assertThat(base, implementsWith(equal, notEqual));
    }
}
