package nu.hjemme.persistence.converter;

import nu.hjemme.client.datatype.Country;

public class CountryConverter implements TypeConverter<Country, String> {
    private static final String SPLITTER = "\\$";

    @Override public Country convertTo(String country) {
        if (country != null && country.contains(SPLITTER)) {
            throw new IllegalArgumentException("Missing splitter: <country code>" + SPLITTER + "<locale code>, got: " + country);
        }

        return country != null ? new Country(country.split(SPLITTER)[0], country.split(SPLITTER)[1]) : null;
    }

    @Override public String convertFrom(Country country) {
        return country != null ? country.getCountryCode() + SPLITTER + country.getLocale() : null;
    }
}
