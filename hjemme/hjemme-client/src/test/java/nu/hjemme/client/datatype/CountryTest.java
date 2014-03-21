package nu.hjemme.client.datatype;

import nu.hjemme.test.EqualsMatching;
import org.junit.Test;

import java.util.Locale;

import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/** @author Tor Egil Jacobsen */
public class CountryTest {

    @Test
    public void whenCreatingAnInstanceAnExceptionIsThrownWhenUsingValuesNotAccordingToISO3166() {
        Country country;

        try {
            country = new Country("illegal", "gb");
            fail(country + " should not represent a valid country code");
        } catch (IllegalArgumentException iae) {
            assertThat("The error message should not be null", iae.getMessage(), is(notNullValue()));
        }

        country = new Country("NO", "no");

        assertThat("Country initialized", country, is(notNullValue()));
    }

    @Test
    public void whenInvokingHashCodeItShouldBeImplementedCorrect() {
        Country base = new Country("NO", "no");
        Country equal = new Country("NO", "no");
        Country notEqual = new Country("SE", "se");

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
    }

    @Test
    public void whenInvokingEqualsItShouldBeImplementedCorrect() {
        Country base = new Country("NO", "no");
        Country equal = new Country("NO", "no");
        Country notEqual = new Country("SE", "se");

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }

    @Test
    public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("ToString", new Country("NO", "no").toString(), is(equalTo("Country[NO,no]")));
    }

    @Test
    public void whenCreatingAnInstanceTheNameOfTheCountryCannotBeEmpty() {

        try {
            new Country("NO", null);
            fail("The code for the locale cannot be null!");
        } catch (IllegalArgumentException iae) {
            assertThat("The error message should not be null", iae.getMessage(), is(notNullValue()));
        }

        try {
            new Country("NO", "");
            fail("The the code for the lovale cannot be empty!");
        } catch (IllegalArgumentException iae) {
            assertThat("The error message should not be null", iae.getMessage(), is(notNullValue()));
        }

        assertThat("Country initialized", new Country("NO", "no"), is(notNullValue()));
    }

    @Test
    public void whenCreatingAnInstanceTheLocaleCodeWillBeConvertedTaALocale() {
        Country country = new Country("NO", "no");

        assertThat("Code", country.getCountryCode(), is(equalTo("NO")));
        assertThat("Locale", country.getLocale(), is(equalTo(new Locale("no"))));
    }

    @Test
    public void whenCallingToStringTheStringShouldBeExcepted() {
        assertThat("Country.toString", new Country("NO", "no").toString(), is(equalTo("Country[NO,no]")));
    }
}
