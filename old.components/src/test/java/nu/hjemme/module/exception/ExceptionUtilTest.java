package nu.hjemme.module.exception;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides a test of ExceptionUtil.
 * @author Tor Egil Jacobsen
 */
public class ExceptionUtilTest {

    private static final String EXCEPTION_EXPECTED = "A java.lang.Exception is expected!";

    // Variables
    private transient Exception exception;
    private transient NonReferencedException nonReferencedException;
    private transient RuntimeException runtimeException;

    /**
     * {@inheritDoc}
     */
    @Before
    public void setUp() {
        exception = new Exception();
        runtimeException = new RuntimeException(exception);
        nonReferencedException = new OnlyStackException(runtimeException, "Test exception!");
    }

    // --- tests ---

    /**
     * Tests the root cause.
     */
    @Test
    public void testRootCause() {
        Throwable cause = ExceptionUtil.getRootCause(exception);
        assertThat("This should be an exception without a cause!", cause, is(nullValue()));
        cause = ExceptionUtil.getRootCause(runtimeException);
        assertThat("The exception should have a couse!", cause, is(notNullValue()));
        assertThat("The cause name which is expected is wrong!", cause.getClass().getName(), is(Exception.class.getName()));
        assertThat("The cause should not be referenced!", nonReferencedException.getCause(), is(nullValue()));
    }

    /**
     * Test the expected cause.
     */
    @Test
    public void testExpectedCause() {
        assertThat(EXCEPTION_EXPECTED, ExceptionUtil.isExpectedCause(runtimeException, Exception.class), is(true));
        assertThat(EXCEPTION_EXPECTED, ExceptionUtil.isExpectedCause(new Exception(runtimeException), Exception.class),
            is(true));
        assertThat(EXCEPTION_EXPECTED, ExceptionUtil.isExpectedCause(nonReferencedException, Exception.class), is(true));
        assertThat(EXCEPTION_EXPECTED, ExceptionUtil.isExpectedCause((Throwable) nonReferencedException, Exception.class),
            is(true));
        assertThat("A RuntimeException is not expected!", ExceptionUtil.isExpectedCause(exception, RuntimeException.class),
            is(false));
        assertThat("A RuntimeException is not expected!",
            ExceptionUtil.isExpectedCause(runtimeException, RuntimeException.class), is(false));
        assertThat("A null value is not expected!", ExceptionUtil.isExpectedCause(runtimeException, null), is(false));

        assertThat("NonReferencedException is not expected to have any cause!", nonReferencedException.getCause(),
            is(nullValue()));
        assertThat(EXCEPTION_EXPECTED,
            ExceptionUtil.isExpectedCause(new OnlyStackException(exception, "sexception"), Exception.class), is(true));
        assertThat("A OnlyStackException is expected!", //
            ExceptionUtil.isExpectedCause(new OnlyStackException(nonReferencedException, "sexception"),
                OnlyStackException.class), is(true));

        try {
            new OnlyStackException(null, "sexception");
            fail("An exception is supposed to be thrown if the cause is null!");
        } catch (IllegalArgumentException iae) {}

        assertThat("False when no cause!",
            ExceptionUtil.isExpectedCause((NonReferencedException) null, OnlyStackException.class), is(false));
        assertThat("False when nothing expected!",
            ExceptionUtil.isExpectedCause(new OnlyStackException(runtimeException, null), null), is(false));
        assertThat("Flase when wrong cause!", ExceptionUtil.isExpectedCause(nonReferencedException, RuntimeException.class),
            is(false));
        assertThat("False when no cause!", ExceptionUtil.isExpectedCause((Throwable) null, Exception.class), is(false));
    }

    @Test
    public void boostCoverage() {
        new ConfigurationException();
        new ConfigurationException("config error");

        assertNotNull(new CommonsException() {
            private static final long serialVersionUID = 1L;
        });

        new CommonsException("common error") {
            private static final long serialVersionUID = 1L;
        };
    }

    @Test
    public void notReferancedExceptionClassNotFound() {
        Mockery mockery = new Mockery();
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
        final OnlyStackException oseMock = mockery.mock(OnlyStackException.class);

        mockery.checking(new Expectations() {
            {
                one(oseMock).getRootCauseName();
                will(returnValue("class.not.referenced.SomeException"));
            }
        });

        assertFalse("Class not to be found!", ExceptionUtil.isExpectedCause(oseMock, RuntimeException.class));
        mockery.assertIsSatisfied();
    }

    @Test
    public void willThrowIllegalStateException() {
        try {
            ExceptionUtil.throwIllegalState(new RuntimeException());
        } catch (IllegalStateException ise) {
            String message = ise.getMessage();
            boolean isRuntime = message.indexOf(RuntimeException.class.getName()) != -1;
            assertThat("Exception class should be part of the message: " + message, isRuntime, is(true));

            boolean isFromTest = message.indexOf(ExceptionUtilTest.class.getName()) != -1;
            assertThat("ExceptionUtilTest class should be part of the message: " + message, isFromTest, is(true));
        }
    }
}
