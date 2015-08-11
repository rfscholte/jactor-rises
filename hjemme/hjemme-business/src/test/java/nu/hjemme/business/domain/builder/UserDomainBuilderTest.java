package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.UserDomain;
import nu.hjemme.persistence.ProfileEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class UserDomainBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willNotBuildUserDomainWithoutUserName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_USER_NAME_CANNOT_BE_NULL);

        UserDomainBuilder.init().build();
    }

    @Test
    public void willNotBuildUserDomainWithoutProfile() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_USER_MUST_HAVE_A_PROFILE);

        UserDomainBuilder.init().appendUserName("some user").build();
    }

    @Test
    public void willNotBuildUserDomainWithoutPassword() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);

        UserDomainBuilder.init().appendUserName("some user").appendProfile(new ProfileEntity()).build();
    }

    @Test
    public void willNotBuildUserDomainWithAnEmptyPassword() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(UserDomainBuilder.THE_PASSWORD_FIELD_CANNOT_BE_EMPTY);

        UserDomainBuilder.init().appendUserName("some user").appendProfile(new ProfileEntity()).appendPassword("").build();
    }

    @Test
    public void willBuildUserDomainWithAllRequiredProperties() {
        UserDomain userEntity = UserDomainBuilder.init()
                .appendUserName("some user")
                .appendProfile(new ProfileEntity())
                .appendPassword("password")
                .build();

        assertThat("User", userEntity, is(notNullValue()));
    }
}
