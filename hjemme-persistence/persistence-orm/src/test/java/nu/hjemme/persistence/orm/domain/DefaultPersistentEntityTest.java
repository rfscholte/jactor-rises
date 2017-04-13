package nu.hjemme.persistence.orm.domain;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.client.AddressEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class DefaultPersistentEntityTest {
    private TestPersistentEntity testPersistentEntity;

    @Rule public final ExpectedException expectedException = ExpectedException.none();

    @Before public void initForTesting() {
        testPersistentEntity = new TestPersistentEntity();
    }

    @Test public void willHaveClassNameAndIdOnToStringMethod() {
        String string = testPersistentEntity.setId(101L).toString();

        assertThat(string, allOf(containsString("TestPersistentEntity"), containsString("101")));
    }

    @Test public void willThrowIllegalArgumentExceptionWhenDataTypeIsUnknown() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(DefaultPersistentEntity.class.toString());

        testPersistentEntity.convertTo(new TestPersistentEntity(), DefaultPersistentEntity.class);
    }

    @Test public void willConvertName() {
        assertThat(testPersistentEntity.convertTo("jacobsen", Name.class), equalTo(new Name("jacobsen")));
    }

    @Test public void willConvertEmailAddress() {
        assertThat(testPersistentEntity.convertTo("some@where.com", EmailAddress.class), equalTo(new EmailAddress("some", "where.com")));
    }

    @Test public void willConvertUserName() {
        assertThat(testPersistentEntity.convertTo("jactor", UserName.class), equalTo(new UserName("jactor")));
    }

    @Test public void willConvertDescription() {
        assertThat(testPersistentEntity.convertTo("description", Description.class), equalTo(new Description("description")));
    }

    @Test public void willNotCastOrInitializeKnownImplementation() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Unable to cast or initialize");

        testPersistentEntity.castOrInitializeCopyWith(new DefaultUserEntity(), AddressEntity.class);
    }

    @Test public void willCastKnownImplementation() {
        DefaultUserEntity defaultUserEntity = new DefaultUserEntity();

        assertThat(defaultUserEntity, sameInstance(testPersistentEntity.castOrInitializeCopyWith(defaultUserEntity, DefaultUserEntity.class)));
    }

    @Test public void willInitializeKnownImplementation() {
        assertThat(testPersistentEntity.castOrInitializeCopyWith(mock(DefaultUserEntity.class), DefaultUserEntity.class), allOf(notNullValue(), not(instanceOf(Mockito.class))));
    }

    private class TestPersistentEntity extends DefaultPersistentEntity {

        TestPersistentEntity setId(@SuppressWarnings("SameParameterValue") Long id) {
            super.id = id;
            return this;
        }
    }
}
