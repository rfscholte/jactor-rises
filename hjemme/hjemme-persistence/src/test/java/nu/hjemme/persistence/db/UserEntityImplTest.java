package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.db.ProfileEntityImpl;
import nu.hjemme.persistence.db.UserEntityImpl;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class UserEntityImplTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        UserEntityImpl base = new UserEntityImpl();
        base.setUserName(new UserName("some user"));
        base.setProfileEntity(new ProfileEntityImpl());
        base.setPassword("some password");

        UserEntityImpl equal = new UserEntityImpl(base);

        UserEntityImpl notEqual = new UserEntityImpl();
        notEqual.setUserName(new UserName("some other user"));
        notEqual.setProfileEntity(new ProfileEntityImpl());
        notEqual.setPassword("some other password");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        UserEntityImpl base = new UserEntityImpl();
        base.setUserName(new UserName("some user"));
        base.setProfileEntity(new ProfileEntityImpl());
        base.setPassword("some password");

        UserEntityImpl equal = new UserEntityImpl(base);

        UserEntityImpl notEqual = new UserEntityImpl();
        notEqual.setUserName(new UserName("some other user"));
        notEqual.setProfileEntity(new ProfileEntityImpl());
        notEqual.setPassword("some other password");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}
