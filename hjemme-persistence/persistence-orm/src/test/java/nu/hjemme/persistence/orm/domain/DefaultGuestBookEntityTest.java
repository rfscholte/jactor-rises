package nu.hjemme.persistence.orm.domain;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static nu.hjemme.test.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static nu.hjemme.test.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultGuestBookEntityTest {

    private DefaultGuestBookEntity defaultGuestBookEntityToTest;

    @Before public void initClassToTest() {
        defaultGuestBookEntityToTest = new DefaultGuestBookEntity();
    }

    @Test public void willHaveCorrectImplementedHashCode() {
        defaultGuestBookEntityToTest.setTitle("title");
        defaultGuestBookEntityToTest.setUser(new DefaultUserEntity());

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity(defaultGuestBookEntityToTest);

        DefaultGuestBookEntity notEqual = new DefaultGuestBookEntity();
        notEqual.setTitle("another title");
        notEqual.setUser(new DefaultUserEntity());

        assertThat(defaultGuestBookEntityToTest, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        defaultGuestBookEntityToTest.setTitle("title");
        defaultGuestBookEntityToTest.setUser(new DefaultUserEntity());

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity(defaultGuestBookEntityToTest);

        DefaultGuestBookEntity notEqual = new DefaultGuestBookEntity();
        notEqual.setTitle("another title");
        notEqual.setUser(new DefaultUserEntity());

        assertThat(defaultGuestBookEntityToTest, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        DefaultUserEntity userEntity = new DefaultUserEntity();

        defaultGuestBookEntityToTest.setTitle("title");
        defaultGuestBookEntityToTest.setUser(userEntity);

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity();
        equal.setTitle("title");
        equal.setUser(userEntity);

        assertThat(defaultGuestBookEntityToTest, is(equalTo(equal), "Equal Entity"));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        defaultGuestBookEntityToTest.setTitle("title");
        defaultGuestBookEntityToTest.setUser(new DefaultUserEntity());

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity(defaultGuestBookEntityToTest);

        assertThat(defaultGuestBookEntityToTest, is(equalTo(equal), "Equal Entity"));
    }
}                             
