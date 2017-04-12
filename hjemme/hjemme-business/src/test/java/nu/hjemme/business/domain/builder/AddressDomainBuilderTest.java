package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.AddressDomain;
import nu.hjemme.client.datatype.Country;
import org.junit.jupiter.api.Test;

import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressDomainBuilderTest {

    @Test void willNotBuildDomainWithoutAddressLine1() {
        assertThat(assertThrows(
                IllegalArgumentException.class, () -> anAddress().withZipCodeAs(1234).withCountryAs("NO", "no").build()
        ).getMessage(), is(equalTo(AddressDomainBuilder.ADDRESS_LINE_1_CANNOT_BE_EMPTY)));
    }

    @Test void willNotBuildDomainWithAnEmptyAddressLine1() {
        assertThat(assertThrows(
                IllegalArgumentException.class, () -> anAddress().withAddressLine1As("").withZipCodeAs(1234).withCountryAs("NO", "no").build()
        ).getMessage(), is(equalTo(AddressDomainBuilder.ADDRESS_LINE_1_CANNOT_BE_EMPTY)));
    }

    @Test void willNotBuildDomainWithoutZipCode() {
        assertThat(assertThrows(
                IllegalArgumentException.class, () -> anAddress().withAddressLine1As("somewhere").withCountryAs("NO", "no").build()
        ).getMessage(), is(equalTo(AddressDomainBuilder.ZIP_CODE_CANNOT_BE_NULL)));
    }

    @Test void willNotBuildDomainWithoutCountry() {
        assertThat(assertThrows(
                IllegalArgumentException.class, () -> anAddress().withAddressLine1As("somewhere").withZipCodeAs(1234).build()
        ).getMessage(), is(equalTo(AddressDomainBuilder.COUNTRY_CANNOT_BE_NULL)));
    }

    @Test void willBuildValidatedDomain() {
        AddressDomain addressDomain = anAddress()
                .withAddressLine1As("somewhere")
                .withZipCodeAs(1234)
                .withCountryAs("NO", "no")
                .build();

        assertThat("Address", addressDomain, is(notNullValue()));
    }

    @Test void whenBuildingAnAddressAllAddressLinesAndItsCityCanAlsoBeAppended() {
        AddressDomain addressDomain = anAddress()
                .withAddressLine1As("somewhere")
                .appendAddressLine2("somewhere else")
                .appendAddressLine3("way out there")
                .withCityAs("some city")
                .withCountryAs("NO", "no")
                .withZipCodeAs(1234)
                .build();

        assertAll("A domain with all properties set",
                () -> assertThat("Address line 1", addressDomain.getAddressLine1(), equalTo("somewhere")),
                () -> assertThat("Address line 2", addressDomain.getAddressLine2(), equalTo("somewhere else")),
                () -> assertThat("Address line 3", addressDomain.getAddressLine3(), equalTo("way out there")),
                () -> assertThat("City", addressDomain.getCity(), equalTo("some city")),
                () -> assertThat("Country", addressDomain.getCountry(), equalTo(new Country("NO", "no"))),
                () -> assertThat("Zip Code", addressDomain.getZipCode(), equalTo(1234))
        );
    }
}
