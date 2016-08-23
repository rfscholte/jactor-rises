package nu.hjemme.persistence.client.converter;

import nu.hjemme.client.datatype.Country;

import java.util.regex.Pattern;

public class CountryConverter implements TypeConverter<Country, String> {
    public static final String SPLITTER = "+";
    private static final String REGEX_SPLITTER = Pattern.quote(SPLITTER);

    @Override public Country convertTo(String country) {
        if (country != null && !country.contains(SPLITTER)) {
            throw new IllegalArgumentException("Missing splitter: <country code>" + SPLITTER + "<locale code>, got: " + country);
        }

        return country != null ? new Country(country.split(REGEX_SPLITTER)[0], country.split(REGEX_SPLITTER)[1]) : null;
    }

    @Override public String convertFrom(Country country) {
        return country != null ? country.getCountryCode() + SPLITTER + country.getLocale() : null;
    }
}
