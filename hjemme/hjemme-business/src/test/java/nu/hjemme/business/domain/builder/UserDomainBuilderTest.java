package nu.hjemme.business.domain.builder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.business.domain.builder.DomainBuilder.Build.PROFILE;
import static nu.hjemme.business.domain.builder.DomainBuilder.aProfile;
import static nu.hjemme.business.domain.builder.DomainBuilder.aUser;
import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class UserDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Rule public DomainBuilderValidations domainBuilderValidations = DomainBuilderValidations.init().skipValidationOn(PROFILE);

    @Test public void willNotBuildUserDomainWithoutUserName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_USER_NAME_CANNOT_BE_NULL);

        aUser().appendProfile(aProfile()).appendPassword("password").get();

    }

    @Test public void willNotBuildUserDomainWithoutProfile() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_USER_MUST_HAVE_A_PROFILE);

        aUser().appendUserName("some user").appendPassword("password").get();
    }

    @Test public void willNotBuildUserDomainWithoutPassword() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);

        aUser().appendUserName("some user").appendProfile(aProfile()).get();
    }

    @Test public void willNotBuildUserDomainWithAnEmptyPassword() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);

        aUser().appendUserName("some user").appendProfile(aProfile()).appendPassword("").get();
    }

    @Test public void willBuildUserDomainWithAllRequiredProperties() {
        assertThat(aUser().appendUserName("some user").appendProfile(aProfile()).appendPassword("password").get(), is(notNullValue(), "build of a user domain"));
    }
}
