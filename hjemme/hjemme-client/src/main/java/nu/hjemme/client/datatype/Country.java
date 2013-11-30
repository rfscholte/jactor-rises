package nu.hjemme.client.datatype;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Arrays;
import java.util.Locale;

/**
 * Representing a country according to the ISO 3166 standard. In addition to the country, you must add a specific {@link
 * Locale} representing the language of the country.
 * @author Tor Egil Jacobsen
 */
public class Country {

    private final String countryCode;
    private final Locale locale;

    public Country(String countryCode, String localeCode) {
        validate(countryCode, localeCode);
        this.countryCode = countryCode.toUpperCase();
        locale = new Locale(localeCode);
    }

    private void validate(String countryCode, String localeCode) {
        Validate.notEmpty(countryCode, "The country code cannot be empty");
        Validate.notEmpty(localeCode, "A countryCode for a java.util.Locale must be provided.");

        String[] isoCountries = Locale.getISOCountries();

        for (String country : isoCountries) {
            if (countryCode.equals(country)) {
                return;
            }
        }

        String errorMessage = countryCode +
                " is not a valid countryCode for a country according to ISO 3166, valid codes: " +
                Arrays.asList(isoCountries);

        throw new IllegalArgumentException(errorMessage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Country country = (Country) obj;

        return new EqualsBuilder()
                .append(getCountryCode(), country.getCountryCode())
                .append(getLocale(), country.getLocale())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return getCountryCode().hashCode() + getLocale().hashCode();
    }

    @Override
    public String toString() {
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
