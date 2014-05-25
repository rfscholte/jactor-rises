package nu.hjemme.business.domain.persistence;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.test.EqualsMatching;
import nu.hjemme.test.HashCodeMatching;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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

        assertTrue(new HashCodeMatching(base)
                        .hasImplementionForEquality(equal)
                        .hasImplementationForUniqeness(notEqual)
                        .isMatch()
        );
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

        assertTrue(new EqualsMatching(base)
                        .isEqualTo(equal)
                        .isNotEqualTo(notEqual)
                        .isMatch()
        );
    }
}
