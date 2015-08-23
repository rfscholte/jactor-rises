package nu.hjemme.persistence.base;

import nu.hjemme.client.datatype.Description;
import nu.hjemme.client.datatype.EmailAddress;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.test.matcher.MatchBuilder;
import nu.hjemme.test.matcher.TypeSafeBuildMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PersistentEntityImplTest {
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
        expectedException.expectMessage(PersistentEntityImpl.class.toString());

        testPersistentEntity.convert(new TestPersistentEntity(), PersistentEntityImpl.class);
    }

    @Test public void willConvertName() {
        assertThat(testPersistentEntity.convert("jacobsen", Name.class), is(equalTo(new Name("jacobsen")), "String->Name"));
    }

    @Test public void willConvertEmailAddress() {
        assertThat(testPersistentEntity.convert("some@where.com", EmailAddress.class), is(equalTo(new EmailAddress("some", "where.com")), "String->EmailAddress"));
    }

    @Test public void willConvertUserName() {
        assertThat(testPersistentEntity.convert("jactor", UserName.class), is(equalTo(new UserName("jactor")), "String->UserName"));
    }

    @Test public void willConvertDescription() {
        assertThat(testPersistentEntity.convert("description", Description.class), is(equalTo(new Description("description")), "String->Description"));
    }

    @Test public void willUpdateEntityWithCreatedByAndCreationTime() {
        testPersistentEntity.createInstanceWith("jactor");

         assertThat(testPersistentEntity, new TypeSafeBuildMatcher<TestPersistentEntity>("create persistent instance") {
            @Override public MatchBuilder matches(TestPersistentEntity typeToTest, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(typeToTest.getCreatedBy(), is(equalTo(new Name("jactor")), "created by"))
                        .matches(typeToTest.getCreationTime().toLocalDate(), is(equalTo(LocalDate.now()), "creation time"));
            }
        });
    }

    private class TestPersistentEntity extends PersistentEntityImpl {
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
