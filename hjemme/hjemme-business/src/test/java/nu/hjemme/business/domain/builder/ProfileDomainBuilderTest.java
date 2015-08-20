package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.ProfileDomain;
import nu.hjemme.client.datatype.Name;
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

    @Test public void willNotBuildProfileDomainWithoutFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileDomainBuilder.THE_FIRST_NAME_CANNOT_BE_NULL);

        aProfile()
                .withLastNameAs("some last name")
                .with(anAddress())
                .withDescriptionAs("description field will not be validated")
                .get();
    }

    @Test public void willNotBuildProfileDomainWithAnEmptyFirstName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        aProfile()
                .withFirstNameAs("")
                .withLastNameAs("some last name")
                .with(anAddress())
                .withDescriptionAs("description field will not be validated")
                .get();
    }

    public String hentFeilmeldingFraName() {
        String nameErrorMessage = null;

        try {
            new Name("");
        } catch (IllegalArgumentException iae) {
            nameErrorMessage = iae.getMessage();
        }

        return nameErrorMessage;
    }

    @Test public void willNotBuildProfileDomainWithoutLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileDomainBuilder.THE_LAST_NAME_CANNOT_BE_NULL);

        aProfile()
                .withFirstNameAs("some first name")
                .with(anAddress())
                .withDescriptionAs("description field will not be validated")
                .get();
    }

    @Test public void willNotBuildProfileDomainWithAnEmptyLastName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(hentFeilmeldingFraName());

        aProfile()
                .withFirstNameAs("some first name")
                .withLastNameAs("")
                .with(anAddress())
                .withDescriptionAs("description field will not be validated")
                .get();
    }

    @Test public void willNotBuildProfileDomainWithoutAnAddress() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ProfileDomainBuilder.AN_ADDRESS_MUST_BE_PRESENT);

        aProfile()
                .withFirstNameAs("some first name")
                .withLastNameAs("some last name")
                .withDescriptionAs("description field will not be validated")
                .get();
    }

    @Test public void willBuildProfileDomainWhenAllRequiredFieldsAreSet() throws Exception {
        ProfileDomain profileDomain = aProfile()
                .withFirstNameAs("some first name")
                .withLastNameAs("some last name")
                .with(anAddress())
                .withDescriptionAs("description field will not be validated")
                .get();

        assertThat("ProfileEntity", profileDomain, is(notNullValue()));
    }
}
