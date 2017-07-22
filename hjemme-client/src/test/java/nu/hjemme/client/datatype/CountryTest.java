package nu.hjemme.client.datatype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("A Country")
class CountryTest {

    @DisplayName("should not initialize when country code is not configured as ISO 3166")
    @Test void skalFeileNarInstansieringInneholderAndreLandkoderEnnFraISO3166() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Country("gb", "illegal"));
        assertThat(illegalArgumentException.getMessage()).contains(Country.NOT_A_VALID_COUNTRY_CODE_ACCORDING_TO_ISO_3166);
    }

    @DisplayName("should initialize when country code is configured as ISO 3166")
    @Test void skalIkkeFeileNarInstansieringInneholderLandkoderEnnFraISO3166() {
        assertThat(new Country("no", "NO")).isNotNull();
    }

    @DisplayName("should have an implemention of the hashCode method")
    @Test void whenInvokingHashCodeItShouldBeImplementedCorrect() {
        Country base = new Country("no", "NO");
        Country equal = new Country("no", "NO");
        Country notEqual = new Country("se", "SE");

        assertAll(
                () -> assertThat(base.hashCode()).as("%s.hashCode() is equal to %s.hashCode()", base, equal).isEqualTo(equal.hashCode()),
                () -> assertThat(base.hashCode()).as("%s.hashCode() is not equal to %s.hashCode()", base, notEqual).isNotEqualTo(notEqual.hashCode()),
                () -> assertThat(base.hashCode()).as("%s.hashCode() is a number with different value").isNotEqualTo(0)
        );
    }

    @DisplayName("should have an implementation of the equals method")
    @Test void whenInvokingEqualsItShouldBeImplementedCorrect() {
        Country base = new Country("no", "NO");
        Country equal = new Country("no", "NO");
        Country notEqual = new Country("se", "SE");

        assertAll(
                () -> assertThat(base).as("%s is equal to %s", base, equal).isEqualTo(equal),
                () -> assertThat(base).as("%s is not equal to %s", base, notEqual).isNotEqualTo(notEqual),
                () -> assertThat(base).as("%s is not equal to %s").isNotEqualTo(null)
        );
    }

    @DisplayName("should implement toString")
    @Test void whenInvokingToStringOnTheDataTypeItShouldBeImplementedOnTheDataTypeClass() {
        assertThat(new Country("no", "NO").toString())
                .as("A toString should be implemented")
                .isEqualTo("Country[no_NO]");
    }

    @DisplayName("should not initialize when country code is null")
    @Test void skalFeileNarCountryCodeErNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Country("NO", null));
        assertThat(illegalArgumentException.getMessage()).isEqualTo(Country.THE_COUNTRY_CODE_CANNOT_BE_EMPTY);
    }

    @DisplayName("should not initialize when couyntry code is empty")
    @Test void skalFeileNarCountryCodeErTom() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Country("NO", ""));
        assertThat(illegalArgumentException.getMessage()).isEqualTo(Country.THE_COUNTRY_CODE_CANNOT_BE_EMPTY);
    }

    @DisplayName("should not initialize when there is no language code")
    @Test void skalFeileNarLocaleCodeErNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Country(null, "NO"));
        assertThat(illegalArgumentException.getMessage()).isEqualTo(Country.THE_LANGUAGE_CODE_MUST_BE_PROVIDED);
    }

    @DisplayName("thould not initialize when there is an empty language code")
    @Test void skalFeileNarLocaleCodeErTom() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Country("", "NO"));
        assertThat(illegalArgumentException.getMessage()).isEqualTo(Country.THE_LANGUAGE_CODE_MUST_BE_PROVIDED);
    }

    @DisplayName("should convert the language and country code to a java.util.Locale")
    @Test void whenCreatingAnInstanceWillConvertCountryAndLanguageCodeToJavaUtilLocale() {
        Country country = new Country("no", "NO");

        assertThat(country.getLocale()).isEqualTo(new Locale("no", "NO"));
    }

    @DisplayName("should be converted to a string")
    @Test
    void shouldConvertToString() {
        assertThat(new Country("no", "NO").asString()).isEqualTo("no_NO");
    }
}
