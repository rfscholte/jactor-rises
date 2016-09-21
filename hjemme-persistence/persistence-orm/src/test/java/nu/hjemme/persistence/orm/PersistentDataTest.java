package nu.hjemme.persistence.orm;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.orm.domain.DefaultUserEntity;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class PersistentDataTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void willProvideStaticInstance() {
        assertThat(PersistentData.getInstance(), is(notNullValue(), "instance"));
    }

    @Test public void willThrowExceptionIfInterfaceIsNotConfiguredForNewInstances() {
        expectedException.expect(IllegalArgumentException.class);

        PersistentData.getInstance().provideInstanceFor(Name.class);
    }

    @Test public void willFindImplementationOfInterface() {
        MatcherAssert.assertThat(PersistentData.getInstance().provideInstanceFor(UserEntity.class), is(instanceOf(DefaultUserEntity.class), "defaultUserEntity"));
    }

    @Test public void willFindImplementationOfInterfaceUsingConstructorArguments() {
        assertThat(PersistentData.getInstance().provideInstanceFor(UserEntity.class, mock(User.class)), is(instanceOf(DefaultUserEntity.class), "defaultUserEntity"));
    }

    @After public void resetInstance() {
        PersistentData.reset();
    }
}
