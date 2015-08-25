package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.AddressEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.db.DefaultUserEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class DefaultPersistentEntityTest {
    private TestPersistentEntity testPersistentEntity;

    @Rule public ExpectedException expectedException = ExpectedException.none();

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
        assertThat(testPersistentEntity.convertTo("jacobsen", Name.class), is(equalTo(new Name("jacobsen")), "String->Name"));
    }

    @Test public void willConvertEmailAddress() {
        assertThat(testPersistentEntity.convertTo("some@where.com", EmailAddress.class), is(equalTo(new EmailAddress("some", "where.com")), "String->EmailAddress"));
    }

    @Test public void willConvertUserName() {
        assertThat(testPersistentEntity.convertTo("jactor", UserName.class), is(equalTo(new UserName("jactor")), "String->UserName"));
    }

    @Test public void willConvertDescription() {
        assertThat(testPersistentEntity.convertTo("description", Description.class), is(equalTo(new Description("description")), "String->Description"));
    }

    @Test public void willNotCastOrInitializeKnownImplementation() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Unable to cast or initialize");

        testPersistentEntity.castOrInitializeCopyWith(new DefaultUserEntity(), AddressEntity.class);
    }

    @Test public void willCastKnownImplementation() {
        DefaultUserEntity defaultUserEntity = new DefaultUserEntity();

        assertThat(defaultUserEntity, is(sameInstance(testPersistentEntity.castOrInitializeCopyWith(defaultUserEntity, DefaultUserEntity.class)), "casting"));
    }

    @Test public void willInitializeKnownImplementation() {
        assertThat(
                new DefaultUserEntity(),
                is(allOf(notNullValue(), not(sameInstance(testPersistentEntity.castOrInitializeCopyWith(mock(UserEntity.class), DefaultUserEntity.class)))), "initialized")
        );
    }

    private class TestPersistentEntity extends DefaultPersistentEntity {

        public TestPersistentEntity setId(Long id) {
            super.id = id;
            return this;
        }
    }
}
