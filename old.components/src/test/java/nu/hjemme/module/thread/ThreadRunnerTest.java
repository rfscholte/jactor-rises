package nu.hjemme.module.thread;

import junit.framework.TestCase;
import nu.hjemme.client.thread.Runner;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test of the thread runner.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class ThreadRunnerTest extends TestCase {

    private Mockery mockery = new JUnit4Mockery();
    private Runner runnerMock = null;
    private ThreadRunner threadRunnerToTest = null;

    @Before
    public void doBefore() {
        runnerMock = mockery.mock(Runner.class);
        init();
    }

    @Test(expected = IllegalStateException.class)
    public void checkIllegalStateException() {
        ThreadRunner threadRunner = new ThreadRunner(null, 10);
        threadRunner.run();
    }

    @Test
    public void testStopThread() {
        mockery.checking(new Expectations() {
            {
                exactly(3).of(runnerMock).isInvokable();
                will(returnValue(true));
                atLeast(1).of(runnerMock).isInvokable();
                will(returnValue(false));
                atLeast(1).of(runnerMock).invoke();
            }
        });

        threadRunnerToTest.run();
        assertTrue("The thread is stopped!", threadRunnerToTest.isStopped());
    }

    @Test
    public void checkRunningThread() throws InterruptedException {
        mockery.checking(new Expectations() {
            {
                atLeast(1).of(runnerMock).getRunnerName();
                will(returnValue("RunnerMock"));
                atLeast(1).of(runnerMock).isInvokable();
                will(returnValue(true));
                atLeast(1).of(runnerMock).invoke();
            }
        });

        threadRunnerToTest = new ThreadRunner(runnerMock, 1);
        threadRunnerToTest.start();
        Thread.sleep(10);
        assertFalse("The thread is not stopped!", threadRunnerToTest.isStopped());
        assertNull("There was no throwable!", threadRunnerToTest.getThrowable());
    }

    @Test
    public void testRuntimeException() {
        init();

        mockery.checking(new Expectations() {
            {
                one(runnerMock).isInvokable();
                will(returnValue(true));
                one(runnerMock).invoke();
                will(throwException(new IllegalStateException("Test Exception!")));
            }
        });

        try {
            threadRunnerToTest.run();
            fail("An unknown exception is expected!");
        } catch (IllegalStateException ise) {}

        assertNotNull("There is an IllegalStateException!", threadRunnerToTest.getThrowable());
        assertTrue("The thread is stopped!", threadRunnerToTest.isStopped());
    }

    private void init() {
        mockery.checking(new Expectations() {
            {
                one(runnerMock).getRunnerName();
                will(returnValue("RunnerMock"));
            }
        });

        threadRunnerToTest = new ThreadRunner(runnerMock, 1);
        mockery.assertIsSatisfied();
    }
}
