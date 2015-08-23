package nu.hjemme.persistence.db;

import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class DefaultUserEntityTest {

    @Test
    public void willHaveCorrectImplementedHashCode() {
        DefaultUserEntity base = new DefaultUserEntity();
        base.setUserName("some user");
        base.setProfileEntity(new DefaultProfileEntity());
        base.setPassword("some password");

        DefaultUserEntity equal = new DefaultUserEntity(base);

        DefaultUserEntity notEqual = new DefaultUserEntity();
        notEqual.setUserName("some other user");
        notEqual.setProfileEntity(new DefaultProfileEntity());
        notEqual.setPassword("some other password");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        DefaultUserEntity base = new DefaultUserEntity();
        base.setUserName("some user");
        base.setProfileEntity(new DefaultProfileEntity());
        base.setPassword("some password");

        DefaultUserEntity equal = new DefaultUserEntity(base);

        DefaultUserEntity notEqual = new DefaultUserEntity();
        notEqual.setUserName("some other user");
        notEqual.setProfileEntity(new DefaultProfileEntity());
        notEqual.setPassword("some other password");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}
