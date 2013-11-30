package nu.hjemme.module.domain.base;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/** @author Tor Egil Jacobsen */
public class DomainBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void willFailIfTryingToBuildDomainWithMoreThanOneMutable() {
        expectedException.expect(IllegalArgumentException.class);

        TestDomainBuilder testDomainBuilder = new TestDomainBuilder();

        Object mockedObject = mock(Object.class);
        testDomainBuilder.build(mockedObject, mockedObject);
    }

    @Test
    public void willBuildWithoutArgumentWhenNoMutableDataIsProvided() {
        TestDomainBuilder testDomainBuilder = new TestDomainBuilder();
        testDomainBuilder.build();
        Spy spy = testDomainBuilder.getSpy();

        assertThat("The sub classes should not be called with an argument", spy,
                is(equalTo(Spy.INVOKED_WITHOUT_ARGUMENT)));
    }

    @Test
    public void willBuildInstancedWithArgumentWhenMutableDataIsProvided() {
        TestDomainBuilder testDomainBuilder = new TestDomainBuilder();
        testDomainBuilder.build(mock(Object.class));
        Spy spy = testDomainBuilder.getSpy();

        assertThat("The sub classes should not be called with an argument", spy,
                is(equalTo(Spy.INVOKED_WITH_ARGUMENT)));
    }

    @Test
    public void willBuildInstanceWithoutArgumentWhenMutableDataIsNotProvided() {
        TestDomainBuilder testDomainBuilder = new TestDomainBuilder();
        testDomainBuilder.build();
        Spy spy = testDomainBuilder.getSpy();

        assertThat("The sub classes should not be called with an argument", spy,
                is(equalTo(Spy.INVOKED_WITHOUT_ARGUMENT)));
    }

    private class TestDomainBuilder extends DomainBuilder<Object, Object> {
        private Spy spy = Spy.NOT_INVOKED;

        @Override
        protected Object buildInstance() {
            spy = Spy.INVOKED_WITHOUT_ARGUMENT;
            return new Object();
        }

        @Override
        protected Object buildInstance(Object o) {
            spy = Spy.INVOKED_WITH_ARGUMENT;
            return o;
        }

        @Override
        protected void validate(Object o) {
            spy = Spy.INVOKED_WITH_ARGUMENT;
        }

        @Override
        protected void validateMutableData() {
            spy = Spy.INVOKED_WITHOUT_ARGUMENT;
        }

        Spy getSpy() {
            return spy;
        }
    }

    private enum Spy {
        INVOKED_WITH_ARGUMENT, INVOKED_WITHOUT_ARGUMENT, NOT_INVOKED
    }
}
