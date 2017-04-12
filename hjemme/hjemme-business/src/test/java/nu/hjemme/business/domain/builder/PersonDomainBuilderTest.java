package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.PersonDomain;
import nu.hjemme.business.rules.BuildValidations;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.rules.BuildValidations.Build.ADDRESS;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PersonDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Rule public BuildValidations buildValidations = BuildValidations.skipValidationOn(ADDRESS);

    @Test public void willNotBuildPersonDomainWithoutAnAddress() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersonDomainBuilder.AN_ADDRESS_MUST_BE_PRESENT);

        aPerson().withDescriptionAs("description field will not be validated").build();
    }

    @Test public void willBuildPersonDomainWhenAllRequiredFieldsAreSet() throws Exception {
        PersonDomain personDomain = aPerson().with(anAddress()).withDescriptionAs("description field will not be validated").build();

        assertThat(personDomain, notNullValue());
    }
}
