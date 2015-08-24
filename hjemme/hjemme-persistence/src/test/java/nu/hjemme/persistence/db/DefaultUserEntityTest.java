package nu.hjemme.persistence.db;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class DefaultUserEntityTest {
    private DefaultUserEntity defaultUserEntityToTest;

    @Before public void initClassToTest() {
        defaultUserEntityToTest = new DefaultUserEntity();
    }

    @Test
    public void willHaveCorrectImplementedHashCode() {
        defaultUserEntityToTest.setUserName("some user");
        defaultUserEntityToTest.setProfileEntity(new DefaultProfileEntity());
        defaultUserEntityToTest.setEmailAddress("some@where");
        defaultUserEntityToTest.setUserNameAsEmailAddress();

        DefaultUserEntity equal = new DefaultUserEntity(defaultUserEntityToTest);

        DefaultUserEntity notEqual = new DefaultUserEntity();
        notEqual.setEmailAddress("any@where");
        notEqual.setProfileEntity(new DefaultProfileEntity());
        notEqual.setUserName("some other user");

        assertThat(defaultUserEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test
    public void willHaveCorrectImplementedEquals() {
        defaultUserEntityToTest.setUserName("some user");
        defaultUserEntityToTest.setProfileEntity(new DefaultProfileEntity());
        defaultUserEntityToTest.setEmailAddress("some@where");
        defaultUserEntityToTest.setUserNameAsEmailAddress();

        DefaultUserEntity equal = new DefaultUserEntity(defaultUserEntityToTest);

        DefaultUserEntity notEqual = new DefaultUserEntity();
        notEqual.setEmailAddress("any@where");
        notEqual.setProfileEntity(new DefaultProfileEntity());
        notEqual.setUserName("some other user");

        assertThat(defaultUserEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}
