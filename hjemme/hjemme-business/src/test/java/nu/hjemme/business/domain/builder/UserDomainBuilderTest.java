package nu.hjemme.business.domain.builder;

import nu.hjemme.business.rules.BuildValidations;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static nu.hjemme.business.rules.BuildValidations.Build.PERSON;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class UserDomainBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Rule public BuildValidations buildValidations = BuildValidations.skipValidationOn(PERSON);

    @Test public void willNotBuildUserDomainWithoutUserName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_USER_NAME_CANNOT_BE_NULL);

        aUser().with(aPerson()).withPasswordAs("password").build();

    }

    @Test public void willNotBuildUserDomainWithoutPerson() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_USER_MUST_BE_A_PERSON);

        aUser().withUserNameAs("some user").withPasswordAs("password").build();
    }

    @Test public void willNotBuildUserDomainWithoutPassword() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);

        aUser().withUserNameAs("some user").with(aPerson()).build();
    }

    @Test public void willNotBuildUserDomainWithAnEmptyPassword() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);

        aUser().withUserNameAs("some user").with(aPerson()).withPasswordAs("").build();
    }

    @Test public void willBuildUserDomainWithAllRequiredProperties() {
        assertThat(aUser().withUserNameAs("some user").with(aPerson()).withPasswordAs("password").build(), is(notNullValue(), "build of a user domain"));
    }
}
