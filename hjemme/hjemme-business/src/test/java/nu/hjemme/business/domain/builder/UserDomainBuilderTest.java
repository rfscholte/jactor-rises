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

        aUser().with(aProfile()).withPasswordAs("password").get();

    }

    @Test public void willNotBuildUserDomainWithoutProfile() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_USER_MUST_HAVE_A_PROFILE);

        aUser().withUserNameAs("some user").withPasswordAs("password").get();
    }

    @Test public void willNotBuildUserDomainWithoutPassword() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);

        aUser().withUserNameAs("some user").with(aProfile()).get();
    }

    @Test public void willNotBuildUserDomainWithAnEmptyPassword() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);

        aUser().withUserNameAs("some user").with(aProfile()).withPasswordAs("").get();
    }

    @Test public void willBuildUserDomainWithAllRequiredProperties() {
        assertThat(aUser().withUserNameAs("some user").with(aProfile()).withPasswordAs("password").get(), is(notNullValue(), "build of a user domain"));
    }
}
