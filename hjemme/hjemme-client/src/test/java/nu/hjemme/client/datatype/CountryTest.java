package nu.hjemme.client.datatype;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Locale;

import static com.github.jactorrises.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static com.github.jactorrises.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CountryTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void skalFeileNarInstansieringInneholderAndreLandkoderEnnFraISO3166() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Country.NOT_A_VALID_COUNTRY_CODE_ACCORDING_TO_ISO_3166);

        new Country("illegal", "gb");
    }

    @Test public void skalIkkeFeileNarInstansieringInneholderLandkoderEnnFraISO3166() {
        Country country = new Country("NO", "no");

        assertThat("Country initialized", country, is(notNullValue()));
    }

    @Test public void whenInvokingHashCodeItShouldBeImplementedCorrect() {
        Country base = new Country("NO", "no");
        Country equal = new Country("NO", "no");
        Country notEqual = new Country("SE", "se");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void whenInvokingEqualsItShouldBeImplementedCorrect() {
        Country base = new Country("NO", "no");
        Country equal = new Country("NO", "no");
        Country notEqual = new Country("SE", "se");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("ToString", new Country("NO", "no").toString(), is(equalTo("Country[NO,no]")));
    }

    @Test public void skalFeileNarCountryCodeErNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Country.THE_COUNTRY_CODE_CANNOT_BE_EMPTY);

        new Country(null, "NO");
    }

    @Test public void skalFeileNarCountryCodeErTom() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Country.THE_COUNTRY_CODE_CANNOT_BE_EMPTY);

        new Country("", "NO");
    }

    @Test public void skalFeileNarLocaleCodeErNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Country.CODE_FOR_JAVA_UTIL_LOCALE_MUST_BE_PROVIDED);

        new Country("NO", null);
    }

    @Test public void skalFeileNarLocaleCodeErTom() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Country.CODE_FOR_JAVA_UTIL_LOCALE_MUST_BE_PROVIDED);

        new Country("NO", "");
    }

    @Test public void whenCreatingAnInstanceTheLocaleCodeWillBeConvertedToJavaUtilLocale() {
        Country country = new Country("NO", "no");

        assertThat("Code", country.getCountryCode(), is(equalTo("NO")));
        assertThat("Locale", country.getLocale(), is(equalTo(new Locale("no"))));
    }

    @Test public void whenCallingToStringTheStringShouldBeExcepted() {
        assertThat("Country.toString", new Country("NO", "no").toString(), is(equalTo("Country[NO,no]")));
    }
}
