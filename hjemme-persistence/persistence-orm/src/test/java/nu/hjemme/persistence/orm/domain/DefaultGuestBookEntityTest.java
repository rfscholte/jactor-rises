package nu.hjemme.persistence.orm.domain;

import org.junit.Before;
import org.junit.Test;

import static nu.hjemme.test.matcher.EqualMatcher.implementsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

        assertThat(defaultGuestBookEntityToTest.hashCode(), implementsWith(equal.hashCode(), notEqual.hashCode()));
    }

    @Test public void willHaveCorrectImplementedEquals() {
        defaultGuestBookEntityToTest.setTitle("title");
        defaultGuestBookEntityToTest.setUser(new DefaultUserEntity());

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity(defaultGuestBookEntityToTest);

        DefaultGuestBookEntity notEqual = new DefaultGuestBookEntity();
        notEqual.setTitle("another title");
        notEqual.setUser(new DefaultUserEntity());

        assertThat(defaultGuestBookEntityToTest, implementsWith(equal, notEqual));
    }

    @Test public void willBeEqualAnIdenticalEntity() {
        DefaultUserEntity userEntity = new DefaultUserEntity();

        defaultGuestBookEntityToTest.setTitle("title");
        defaultGuestBookEntityToTest.setUser(userEntity);

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity();
        equal.setTitle("title");
        equal.setUser(userEntity);

        assertThat(defaultGuestBookEntityToTest, equalTo(equal));
    }

    @Test public void willBeEqualAnIdenticalEntityUsingConstructor() {
        defaultGuestBookEntityToTest.setTitle("title");
        defaultGuestBookEntityToTest.setUser(new DefaultUserEntity());

        DefaultGuestBookEntity equal = new DefaultGuestBookEntity(defaultGuestBookEntityToTest);

        assertThat(defaultGuestBookEntityToTest, equalTo(equal));
    }
}                             
