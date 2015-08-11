package nu.hjemme.client.datatype;

import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class UserNameTest {

    @Test
    public void willBeEqualIfOnlyDifferenceIsTheCharacterCase() {
        UserName jactorLowerCase = new UserName("jactor");
        UserName jactorUpperCase = new UserName("JACTOR");

        assertThat("UserName", jactorLowerCase, is(equalTo(jactorUpperCase)));

        UserName tip = new UserName("tip");

        assertThat("UserName", jactorLowerCase, is(not(equalTo(tip))));
    }

    @Test
    public void willProduceEqualHashCodeIfOnlyDifferenceIsTheCharacterCase() {
        UserName jactorLowerCase = new UserName("jactor");
        UserName jactorUpperCase = new UserName("JACTOR");
        UserName tip = new UserName("tip");

        assertThat("UserName", jactorLowerCase.hashCode(), is(equalTo(jactorUpperCase.hashCode())));
        assertThat("UserName", jactorLowerCase.hashCode(), is(not(equalTo(tip.hashCode()))));
    }

    @Test
    public void willImplementHashCodeAccordingToTheJavaSpecifications() {
        UserName base = new UserName("someone");
        UserName equal = new UserName("SOMEONE");
        UserName notEqual = new UserName("SOMEONE else");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willImplementEqualsAccordingToTheJavaSpecifications() {
        UserName base = new UserName("someone");
        UserName equal = new UserName("SOMEONE");
        UserName notEqual = new UserName("SOMEONE else");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}
