package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.ProfileDomain;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.builder.DomainBuilder.Build.ADDRESS;
import static nu.hjemme.business.domain.builder.DomainBuilder.aProfile;
import static nu.hjemme.business.domain.builder.DomainBuilder.anAddress;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ProfileDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Rule public DomainBuilderValidations domainBuilderValidations = DomainBuilderValidations.init().skipValidationOn(ADDRESS);

    @Test public void willNotBuildProfileDomainWithoutAnAddress() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileDomainBuilder.AN_ADDRESS_MUST_BE_PRESENT);

        aProfile().withDescriptionAs("description field will not be validated").get();
    }

    @Test public void willBuildProfileDomainWhenAllRequiredFieldsAreSet() throws Exception {
        ProfileDomain profileDomain = aProfile().with(anAddress()).withDescriptionAs("description field will not be validated").get();

        assertThat("ProfileEntity", profileDomain, is(notNullValue()));
    }
}
