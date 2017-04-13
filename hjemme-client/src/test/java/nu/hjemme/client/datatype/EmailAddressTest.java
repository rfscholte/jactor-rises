package nu.hjemme.client.datatype;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class EmailAddressTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void whenInvokingHashCodeTheImplementationShouldBeCorrect() {
        EmailAddress base = new EmailAddress("someone", "somewhere");
        EmailAddress equal = new EmailAddress("someone", "somewhere");
        EmailAddress notEqual = new EmailAddress("anyone", "anywhere");

        assertThat(base.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test public void whenChecksForEqualityTheImplementationShouldBeCorrect() {
        EmailAddress base = new EmailAddress("someone", "somewhere");
        EmailAddress equal = new EmailAddress("someone", "somewhere");
        EmailAddress notEqual = new EmailAddress("anyone", "anywhere");

        assertThat(base, implementsWith(equal, notEqual));
    }

    @Test public void willNotInitializeWhenPrefixIsNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("prefix cannot be null");

        new EmailAddress(null, "somewhere");
    }

    @Test public void willNotInitializeWhenSuffixIsNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("suffix cannot be null");

        new EmailAddress("someone", null);
    }

    @Test public void willInitializeWithFullEmailAddress() {
        assertThat("full email address constructor", new EmailAddress("someone@somewhere"), is(notNullValue()));
    }

    @Test public void willNotInitializeWhenFullEmailAddressIsNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("full email address is required");

        new EmailAddress(null);
    }

    @Test public void willNotInitializeWithoutAtSignInConstructor() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("An email address requires a @-sign");

        new EmailAddress("someoneAreSomewhere");
    }
}
