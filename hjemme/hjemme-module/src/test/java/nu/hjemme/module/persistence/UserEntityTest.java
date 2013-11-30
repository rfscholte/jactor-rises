package nu.hjemme.module.persistence;

import nu.hjemme.client.datatype.UserName;
import org.junit.Test;

import static nu.hjemme.test.CollectionTests.assertThatEqualsIsImplementedCorrect;
import static nu.hjemme.test.CollectionTests.assertThatHashCodeIsImplementedCorrect;

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
        notEqual.setUserId(1L);

        assertThatHashCodeIsImplementedCorrect(base, equal, notEqual);
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
        notEqual.setUserId(1L);

        assertThatEqualsIsImplementedCorrect(base, equal, notEqual);
    }
}
