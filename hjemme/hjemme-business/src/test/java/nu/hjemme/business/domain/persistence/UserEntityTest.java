package nu.hjemme.business.domain.persistence;

import nu.hjemme.client.datatype.UserName;
import org.junit.Test;

import static nu.hjemme.test.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class UserEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        UserEntity base = new UserEntity();
        base.setUserName(new UserName("some user"));
        base.setProfileEntity(new ProfileEntity());
        base.setPassword("some password");

        UserEntity equal = new UserEntity(base);

        UserEntity notEqual = new UserEntity();
        notEqual.setUserName(new UserName("some other user"));
        notEqual.setProfileEntity(new ProfileEntity());
        notEqual.setPassword("some other password");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        UserEntity base = new UserEntity();
        base.setUserName(new UserName("some user"));
        base.setProfileEntity(new ProfileEntity());
        base.setPassword("some password");

        UserEntity equal = new UserEntity(base);

        UserEntity notEqual = new UserEntity();
        notEqual.setUserName(new UserName("some other user"));
        notEqual.setProfileEntity(new ProfileEntity());
        notEqual.setPassword("some other password");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}
