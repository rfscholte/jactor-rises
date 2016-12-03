package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.AddressDomain;
import nu.hjemme.client.datatype.Country;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.jactorrises.matcher.LambdaBuildMatcher.verify;
import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AddressDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void willNotBuildDomainWithoutAddressLine1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressDomainBuilder.ADDRESS_LINE_1_CANNOT_BE_EMPTY);

        anAddress().withZipCodeAs(1234).withCountryAs("NO", "no").build();
    }

    @Test public void willNotBuildDomainWithAnEmptyAddressLine1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressDomainBuilder.ADDRESS_LINE_1_CANNOT_BE_EMPTY);

        anAddress().withAddressLine1As("").withZipCodeAs(1234).withCountryAs("NO", "no").build();
    }

    @Test public void willNotBuildDomainWithoutZipCode() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressDomainBuilder.ZIP_CODE_CANNOT_BE_NULL);

        anAddress().withAddressLine1As("somewhere").withCountryAs("NO", "no").build();
    }

    @Test public void willNotBuildDomainWithoutCountry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressDomainBuilder.COUNTRY_CANNOT_BE_NULL);

        anAddress().withAddressLine1As("somewhere").withZipCodeAs(1234).build();
    }

    @Test public void willBuildValidatedDomain() {
        AddressDomain addressDomain = anAddress()
                .withAddressLine1As("somewhere")
                .withZipCodeAs(1234)
                .withCountryAs("NO", "no")
                .build();

        assertThat("Address", addressDomain, is(notNullValue()));
    }

    @Test public void whenBuildingAnAddressAllAddressLinesAndItsCityCanAlsoBeAppended() {
        AddressDomain addressDomain = anAddress()
                .withAddressLine1As("somewhere")
                .appendAddressLine2("somewhere else")
                .appendAddressLine3("way out there")
                .withCityAs("some city")
                .withCountryAs("NO", "no")
                .withZipCodeAs(1234)
                .build();

        assertThat(addressDomain, verify("A domain with all properties set", (address, buildMatcher) -> buildMatcher
                .matches(address.getAddressLine1(), equalTo("somewhere"), "Address line 1")
                .matches(address.getAddressLine2(), equalTo("somewhere else"), "Address line 2")
                .matches(address.getAddressLine3(), equalTo("way out there"), "Address line 3")
                .matches(address.getCity(), equalTo("some city"), "city")
                .matches(address.getCountry(), equalTo(new Country("NO", "no")), "Country")
                .matches(address.getZipCode(), equalTo(1234), "Zip code")
        ));
    }
}
