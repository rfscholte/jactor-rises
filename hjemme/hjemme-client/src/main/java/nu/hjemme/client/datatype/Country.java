package nu.hjemme.client.datatype;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Locale;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Objects.hash;

/**
 * Representing a country according to the ISO 3166 standard. In addition to the country, you must add a specific {@link Locale} representing the language of the country.
 */
public class Country {
    private static final String VALID_COUNTRY_CODES = ", valid codes: ";
    static final String CODE_FOR_JAVA_UTIL_LOCALE_MUST_BE_PROVIDED = "The code for a java.util.Locale must be provided.";
    static final String NOT_A_VALID_COUNTRY_CODE_ACCORDING_TO_ISO_3166 = " is not a valid countryCode according to ISO 3166";
    static final String THE_COUNTRY_CODE_CANNOT_BE_EMPTY = "The country code cannot be empty";

    private final String countryCode;
    private final Locale locale;

    public Country(String countryCode, String localeCode) {
        validate(countryCode, localeCode);
        this.countryCode = countryCode.toUpperCase();
        locale = new Locale(localeCode);
    }

    private void validate(String countryCode, String localeCode) {
        Validate.notEmpty(countryCode, THE_COUNTRY_CODE_CANNOT_BE_EMPTY);
        Validate.notEmpty(localeCode, CODE_FOR_JAVA_UTIL_LOCALE_MUST_BE_PROVIDED);

        String[] isoCountries = Locale.getISOCountries();

        for (String country : isoCountries) {
            if (countryCode.equals(country)) {
                return;
            }
        }

        throw new IllegalArgumentException(countryCode + NOT_A_VALID_COUNTRY_CODE_ACCORDING_TO_ISO_3166 + VALID_COUNTRY_CODES + asList(isoCountries));
    }

    @Override public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() &&
                Objects.equals(getCountryCode(), ((Country) obj).getCountryCode()) && Objects.equals(getLocale(), ((Country) obj).getLocale());
    }

    @Override public int hashCode() {
        return hash(countryCode, locale);
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(countryCode)
                .append(locale)
                .toString();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Locale getLocale() {
        return locale;
    }
}
