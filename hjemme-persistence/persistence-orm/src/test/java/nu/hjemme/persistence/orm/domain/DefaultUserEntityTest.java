package nu.hjemme.persistence.orm.domain;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultUserEntityTest {
    private DefaultUserEntity defaultUserEntityToTest;

    @Before public void initClassToTest() {
        defaultUserEntityToTest = new DefaultUserEntity();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        defaultUserEntityToTest.setUserName("some user");
        defaultUserEntityToTest.setPersonEntity(new DefaultPersonEntity());
        defaultUserEntityToTest.setEmailAddress("some@where");
        defaultUserEntityToTest.setUserNameAsEmailAddress();

        DefaultUserEntity equal = new DefaultUserEntity(defaultUserEntityToTest);

        DefaultUserEntity notEqual = new DefaultUserEntity();
        notEqual.setEmailAddress("any@where");
        notEqual.setPersonEntity(new DefaultPersonEntity());
        notEqual.setUserName("some other user");

        assertThat(defaultUserEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        defaultUserEntityToTest.setPersonEntity(new DefaultPersonEntity());
        defaultUserEntityToTest.setUserName("some user");
        defaultUserEntityToTest.setEmailAddress("some@where");
        defaultUserEntityToTest.setUserNameAsEmailAddress();

        DefaultUserEntity equal = new DefaultUserEntity(defaultUserEntityToTest);

        DefaultUserEntity notEqual = new DefaultUserEntity();
        notEqual.setPersonEntity(new DefaultPersonEntity());
        notEqual.setEmailAddress("any@where");
        notEqual.setUserName("some other user");

        assertThat(defaultUserEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willBeEqualToAnIdenticalDefaultUserEntity() {
        defaultUserEntityToTest.setPersonEntity(new DefaultPersonEntity());
        defaultUserEntityToTest.setUserName("aUser");
        defaultUserEntityToTest.setEmailAddress("far@away.com");
        defaultUserEntityToTest.setUserNameAsEmailAddress();

        DefaultUserEntity defaultUserEntity = new DefaultUserEntity();
        defaultUserEntity.setPersonEntity(new DefaultPersonEntity());
        defaultUserEntity.setUserName("aUser");
        defaultUserEntity.setEmailAddress("far@away.com");
        defaultUserEntity.setUserNameAsEmailAddress();

        assertThat(defaultUserEntityToTest, is(equalTo(defaultUserEntity), "equal to"));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        DefaultPersonEntity defaultPersonEntity = new DefaultPersonEntity();

        defaultUserEntityToTest.setPersonEntity(defaultPersonEntity);
        defaultUserEntityToTest.setUserName("aUser");
        defaultUserEntityToTest.setEmailAddress("far@away.com");
        defaultUserEntityToTest.setUserNameAsEmailAddress();

        DefaultUserEntity equal = new DefaultUserEntity();
        equal.setPersonEntity(defaultPersonEntity);
        equal.setUserName("aUser");
        equal.setEmailAddress("far@away.com");
        equal.setUserNameAsEmailAddress();

        assertThat(defaultUserEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        defaultUserEntityToTest.setPersonEntity(new DefaultPersonEntity());
        defaultUserEntityToTest.setUserName("aUser");
        defaultUserEntityToTest.setEmailAddress("far@away.com");
        defaultUserEntityToTest.setUserNameAsEmailAddress();

        DefaultUserEntity equal = new DefaultUserEntity(defaultUserEntityToTest);

        assertThat(defaultUserEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}
