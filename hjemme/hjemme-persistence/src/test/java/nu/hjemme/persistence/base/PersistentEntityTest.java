package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.test.matcher.MatchBuilder;
import nu.hjemme.test.matcher.TypeSafeBuildMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PersistentEntityTest {
    private TestPersistentEntity testPersistentEntity;

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Before public void initForTesting() {
        testPersistentEntity = new TestPersistentEntity();
    }

    @Test public void willHaveClassNameAndIdOnToStringMethod() {
        String string = testPersistentEntity.setId(101L).toString();

        assertThat(string, allOf(containsString("TestPersistentEntity"), containsString("101")));
    }

    @Test public void willTellWhenIdIsPresentAndTheSameOnBothInstances() {
        assertThat(testPersistentEntity.setId(101L), new TypeSafeBuildMatcher<TestPersistentEntity>("checking of id is the same") {
            @Override public MatchBuilder matches(TestPersistentEntity typeToTest, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(typeToTest.isIdPresentAndEqualTo(new TestPersistentEntity().setId(null)), is(equalTo(false), "other id is null"))
                        .matches(typeToTest.isIdPresentAndEqualTo(new TestPersistentEntity().setId(100L)), is(equalTo(false), "other id is 100"))
                        .matches(typeToTest.isIdPresentAndEqualTo(new TestPersistentEntity().setId(101L)), is(equalTo(true), "other id is 101"));
            }
        });
    }

    @Test public void willThrowIllegalArgumentExceptionWhenDataTypeIsUnknown() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersistentEntity.class.toString());

        testPersistentEntity.convertTo(new TestPersistentEntity(), PersistentEntity.class);
    }

    @Test public void willThrowIllegalArgumentExceptionWhenConvertFromClassTypeIsUnknown() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersistentEntity.class.toString());

        testPersistentEntity.convertFrom(new TestPersistentEntity());
    }

    @Test public void willConvertFromName() {
        assertThat(testPersistentEntity.convertFrom(new Name("jacobsen")), is(equalTo("jacobsen"), "Name->String"));
    }

    @Test public void willConvertToName() {
        assertThat(testPersistentEntity.convertTo("jacobsen", Name.class), is(equalTo(new Name("jacobsen")), "String->Name"));
    }

    @Test public void willConvertToEmailAddress() {
        assertThat(testPersistentEntity.convertTo("some@where.com", EmailAddress.class), is(equalTo(new EmailAddress("some", "where.com")), "String->EmailAddress"));
    }

    @Test public void willConvertFromEmailAddress() {
        assertThat(testPersistentEntity.convertFrom(new EmailAddress("some@where.com")), is(equalTo("some@where.com"), "EmailAddress->String"));
    }

    @Test public void willConvertToUserName() {
        assertThat(testPersistentEntity.convertTo("jactor", UserName.class), is(equalTo(new UserName("jactor")), "String->UserName"));
    }

    @Test public void willConvertFromUserName() {
        assertThat(testPersistentEntity.convertFrom(new UserName("jactor")), is(equalTo("jactor"), "UserName->String"));
    }

    private class TestPersistentEntity extends PersistentEntity<Long> {

        private Long id;

        @Override public Long getId() {
            return id;
        }

        public TestPersistentEntity setId(Long id) {
            this.id = id;
            return this;
        }
    }
}
