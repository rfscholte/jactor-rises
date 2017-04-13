package nu.hjemme.client.datatype;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryTest {

    @Test void skalFeileNarInstansieringInneholderAndreLandkoderEnnFraISO3166() {
        assertThat(
                assertThrows(IllegalArgumentException.class, () -> new Country("illegal", "gb")).getMessage(),
                containsString(Country.NOT_A_VALID_COUNTRY_CODE_ACCORDING_TO_ISO_3166)
        );
    }

    @Test void skalIkkeFeileNarInstansieringInneholderLandkoderEnnFraISO3166() {
        assertThat("Country initialized", new Country("NO", "no"), is(notNullValue()));
    }

    @Test void whenInvokingHashCodeItShouldBeImplementedCorrect() {
        Country base = new Country("NO", "no");
        Country equal = new Country("NO", "no");
        Country notEqual = new Country("SE", "se");

        assertThat(base.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test void whenInvokingEqualsItShouldBeImplementedCorrect() {
        Country base = new Country("NO", "no");
        Country equal = new Country("NO", "no");
        Country notEqual = new Country("SE", "se");

        assertThat(base, implementsWith(equal, notEqual));
    }

    @Test void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat("ToString", new Country("NO", "no").toString(), is(equalTo("Country[NO,no]")));
    }

    @Test void skalFeileNarCountryCodeErNull() {
        assertThat(
                assertThrows(IllegalArgumentException.class, () -> new Country(null, "NO")).getMessage(),
                is(equalTo(Country.THE_COUNTRY_CODE_CANNOT_BE_EMPTY))
        );
    }

    @Test void skalFeileNarCountryCodeErTom() {
        assertThat(
                assertThrows(IllegalArgumentException.class, () -> new Country("", "NO")).getMessage(),
                is(equalTo(Country.THE_COUNTRY_CODE_CANNOT_BE_EMPTY))
        );
    }

    @Test void skalFeileNarLocaleCodeErNull() {
        assertThat(
                assertThrows(IllegalArgumentException.class, () -> new Country("NO", null)).getMessage(),
                is(equalTo(Country.CODE_FOR_JAVA_UTIL_LOCALE_MUST_BE_PROVIDED))
        );
    }

    @Test void skalFeileNarLocaleCodeErTom() {
        assertThat(
                assertThrows(IllegalArgumentException.class, () -> new Country("NO", "")).getMessage(),
                is(equalTo(Country.CODE_FOR_JAVA_UTIL_LOCALE_MUST_BE_PROVIDED))
        );
    }

    @Test void whenCreatingAnInstanceTheLocaleCodeWillBeConvertedToJavaUtilLocale() {
        Country country = new Country("NO", "no");

        assertThat("Code", country.getCountryCode(), is(equalTo("NO")));
        assertThat("Locale", country.getLocale(), is(equalTo(new Locale("no"))));
    }

    @Test void whenCallingToStringTheStringShouldBeExcepted() {
        assertThat("Country.toString", new Country("NO", "no").toString(), is(equalTo("Country[NO,no]")));
    }
}
