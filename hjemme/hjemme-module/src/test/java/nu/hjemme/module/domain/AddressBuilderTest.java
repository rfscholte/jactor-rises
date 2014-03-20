package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class AddressBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildDomainWithoutAddressLine1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressBuilder.ADDRESS_LINE_1_CANNOT_BE_EMPTY);

        AddressBuilder.init().appendZipCode(1234).appendCountry("NO", "no").build();
    }

    @Test
    public void willNotBuildDomainWithAnEmptyAddressLine1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressBuilder.ADDRESS_LINE_1_CANNOT_BE_EMPTY);

        AddressBuilder.init().appendAddressLine1("").appendZipCode(1234).appendCountry("NO", "no").build();
    }

    @Test
    public void willNotBuildDomainWithoutZipCode() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressBuilder.ZIP_CODE_CANNOT_BE_NULL);

        AddressBuilder.init().appendAddressLine1("somewhere").appendCountry("NO", "no").build();
    }

    @Test
    public void willNotBuildDomainWithoutCountry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressBuilder.COUNTRY_CANNOT_BE_NULL);

        AddressBuilder.init().appendAddressLine1("somewhere").appendZipCode(1234).build();
    }

    @Test
    public void willBuildValidatedDomain() {
        Address address = AddressBuilder.init()
                .appendAddressLine1("somewhere")
                .appendZipCode(1234)
                .appendCountry("NO", "no")
                .build();

        assertThat("Address", address, is(notNullValue()));
    }

    @Test
    public void whenBuildingAnAddressAllAddressLinesAndItsCityCanAlsoBeAppended() {
        Address address = AddressBuilder.init()
                .appendAddressLine1("somewhere")
                .appendAddressLine2("somewhere else")
                .appendAddressLine3("way out there")
                .appendCity("some city")
                .appendCountry("NO", "no")
                .appendZipCode(1234)
                .build();

        assertThat(address, new NotNullBuildMatching<Address>("A domain with all properties set") {
            @Override
            public MatchBuilder matches(Address address, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(address.getAddressLine1(), is(equalTo("somewhere")), "Address line 1")
                        .matches(address.getAddressLine2(), is(equalTo("somewhere else")), "Address line 2")
                        .matches(address.getAddressLine3(), is(equalTo("way out there")), "Address line 3")
                        .matches(address.getCity(), is(equalTo("some city")), "city")
                        .matches(address.getCountry(), is(equalTo(new Country("NO", "no"))), "Country")
                        .matches(address.getZipCode(), is(equalTo(1234)), "Zip code");
            }
        });
    }
}
