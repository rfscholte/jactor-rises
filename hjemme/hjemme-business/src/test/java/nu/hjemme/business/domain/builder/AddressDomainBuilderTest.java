package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.AddressDomain;
import nu.hjemme.client.datatype.Country;
import nu.hjemme.test.matcher.MatchBuilder;
import nu.hjemme.test.matcher.TypeSafeBuildMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class AddressDomainBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildDomainWithoutAddressLine1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressDomainBuilder.ADDRESS_LINE_1_CANNOT_BE_EMPTY);

        AddressDomainBuilder.init().appendZipCode(1234).appendCountry("NO", "no").build();
    }

    @Test
    public void willNotBuildDomainWithAnEmptyAddressLine1() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressDomainBuilder.ADDRESS_LINE_1_CANNOT_BE_EMPTY);

        AddressDomainBuilder.init().appendAddressLine1("").appendZipCode(1234).appendCountry("NO", "no").build();
    }

    @Test
    public void willNotBuildDomainWithoutZipCode() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressDomainBuilder.ZIP_CODE_CANNOT_BE_NULL);

        AddressDomainBuilder.init().appendAddressLine1("somewhere").appendCountry("NO", "no").build();
    }

    @Test
    public void willNotBuildDomainWithoutCountry() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(AddressDomainBuilder.COUNTRY_CANNOT_BE_NULL);

        AddressDomainBuilder.init().appendAddressLine1("somewhere").appendZipCode(1234).build();
    }

    @Test
    public void willBuildValidatedDomain() {
        AddressDomain addressDomain = AddressDomainBuilder.init()
                .appendAddressLine1("somewhere")
                .appendZipCode(1234)
                .appendCountry("NO", "no")
                .build();

        assertThat("Address", addressDomain, is(notNullValue()));
    }

    @Test
    public void whenBuildingAnAddressAllAddressLinesAndItsCityCanAlsoBeAppended() {
        AddressDomain addressDomain = AddressDomainBuilder.init()
                .appendAddressLine1("somewhere")
                .appendAddressLine2("somewhere else")
                .appendAddressLine3("way out there")
                .appendCity("some city")
                .appendCountry("NO", "no")
                .appendZipCode(1234)
                .build();

        assertThat(addressDomain, new TypeSafeBuildMatcher<AddressDomain>("A domain with all properties set") {
            @Override
            public MatchBuilder matches(AddressDomain addressDomain, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(addressDomain.getAddressLine1(), is(equalTo("somewhere"), "Address line 1"))
                        .matches(addressDomain.getAddressLine2(), is(equalTo("somewhere else"), "Address line 2"))
                        .matches(addressDomain.getAddressLine3(), is(equalTo("way out there"), "Address line 3"))
                        .matches(addressDomain.getCity(), is(equalTo("some city"), "city"))
                        .matches(addressDomain.getCountry(), is(equalTo(new Country("NO", "no")), "Country"))
                        .matches(addressDomain.getZipCode(), is(equalTo(1234), "Zip code"));
            }
        });
    }
}
