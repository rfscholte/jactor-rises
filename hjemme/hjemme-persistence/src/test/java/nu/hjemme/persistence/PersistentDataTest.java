package nu.hjemme.persistence;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Person;
import nu.hjemme.persistence.client.PersonEntity;
import nu.hjemme.persistence.db.PersonEntityImpl;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Tor Egil Jacobsen
 */
public class PersistentDataTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willProvideStaticInstance() {
        assertThat(PersistentData.getInstance(), is(notNullValue(), "instance"));
    }

    @Test
    public void willOverrideStaticInstance() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("overridden");

        new TestPersistentData();
        PersistentData.getInstance().provideFor(null);
    }

    @Test
    public void willNotFindImplementationOfInterfaceIfNoImplementingFilesInPersistentDataPackage() {
        assertThat(PersistentData.getInstance().provideFor(Name.class), is(nullValue(), "name"));
    }

    @Test
    public void willFindImplementationOfInterface() {
        assertThat(PersistentData.getInstance().provideFor(PersonEntity.class), is(instanceOf(PersonEntityImpl.class), "personEntity"));
    }

    @After
    public void resetInstance() {
        PersistentData.reset();
    }

    private class TestPersistentData extends PersistentData {
        protected TestPersistentData() {
            super();
        }

        @Override
        public <T> T provideFor(Class<T> persistentInterface) {
            throw new IllegalStateException("overridden");
        }
    }
}
