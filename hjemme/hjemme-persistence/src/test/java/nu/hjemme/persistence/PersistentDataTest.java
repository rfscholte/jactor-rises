package nu.hjemme.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Person;
import nu.hjemme.persistence.db.PersonEntityImpl;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Tor Egil Jacobsen
 */
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
        assertThat(PersistentData.getInstance().provideInstanceFor(PersonEntity.class), is(instanceOf(PersonEntityImpl.class), "personEntity"));
    }

    @Test public void willFindImplementationOfInterfaceUsingConstructorArguments() {
        assertThat(PersistentData.getInstance().provideInstanceFor(PersonEntity.class, mock(Person.class)), is(instanceOf(PersonEntityImpl.class), "personEntity"));
    }

    @After public void resetInstance() {
        PersistentData.reset();
    }
}
