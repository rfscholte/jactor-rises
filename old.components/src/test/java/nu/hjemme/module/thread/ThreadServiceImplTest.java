package nu.hjemme.module.thread;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nu.hjemme.client.thread.Runner;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test of the ThreadService implementation.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class ThreadServiceImplTest {

    private Mockery mockery = new JUnit4Mockery();
    private Runner runnerMock = null;
    private ThreadFactory threadFactoryMock = null;
    private ThreadRunner threadRunnerMock = null;
    private ThreadServiceImpl threadServiceImplToTest = null;
    protected int invoked = 0;

    @Before
    public void doBefore() {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
        threadFactoryMock = mockery.mock(ThreadFactory.class);
        threadRunnerMock = mockery.mock(ThreadRunner.class);
        threadServiceImplToTest = new ThreadServiceImpl();
        runnerMock = mockery.mock(Runner.class);
        ThreadFactory.instance = threadFactoryMock;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWhenNullParameter() {
        threadServiceImplToTest.createThreads(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWhenEmptyMap() {
        threadServiceImplToTest.createThreads(new HashMap <Object, Map <Runner, Long>>());
    }

    @Test
    public void createThread() {
        createThreadForTest(1000 * 60 * 60);

        mockery.checking(new Expectations() {
            {
                one(threadRunnerMock).getRunner();
                will(returnValue(runnerMock));
                one(runnerMock).setInvokable(with(false));
            }
        });

        createThreadForTest(100);
    }

    @Test
    public void getThreadNames() {
        createThreadForTest(10000 * 60);
        mockery.assertIsSatisfied();
        testRunningThreadNames();
        mockery.assertIsSatisfied();
        testStoppedThreadNames();
    }

    @Test
    public void testThreadStates() {
        assertFalse("The thread is not known!", threadServiceImplToTest.isKnown("RunnerMock"));
        assertNull("No threads are created!", threadServiceImplToTest.getNamesByCreator(getClass()));
        createThreadForTest(1);
        assertTrue("Tråden er kjent!", threadServiceImplToTest.isKnown("RunnerMock"));
        assertNotNull("A thread is created!", threadServiceImplToTest.getNamesByCreator(getClass()));
    }

    @Test
    public void testStates() throws Throwable {
        assertFalse("The thread is not stopped as no threads are created!", threadServiceImplToTest.isStopped("RunnerMock"));
        threadServiceImplToTest.stopThreadsByCreator(getClass());
        createThreadForTest(1000);

        mockery.checking(new Expectations() {
            {
                one(threadRunnerMock).getThrowable();
                will(returnValue(new RuntimeException("Test")));
                one(threadRunnerMock).isStopped();
                will(returnValue(false));
                one(threadRunnerMock).interrupt();
            }
        });

        assertFalse("The thread is not stopped!", threadServiceImplToTest.isStopped("RunnerMock"));
        assertEquals("RintimeException!", "Test", threadServiceImplToTest.getThrowable("RunnerMock").getMessage());
        threadServiceImplToTest.finalize();
    }

    @Test
    public void stopThreads() {
        createThread();

        mockery.checking(new Expectations() {
            {
                one(threadRunnerMock).getRunner();
                will(returnValue(runnerMock));
                one(runnerMock).setInvokable(with(false));
                one(threadRunnerMock).interrupt();
            }
        });

        threadServiceImplToTest.stopThreadsByCreator(getClass());
    }

    private void testRunningThreadNames() {
        mockery.checking(new Expectations() {
            {
                exactly(2).of(threadRunnerMock).isStopped();
                will(returnValue(false));
                one(threadRunnerMock).getRunner();
                will(returnValue(runnerMock));
                one(runnerMock).getRunnerName();
                will(returnValue("RunnerMock"));
            }
        });

        assertNull("No stopped threads!", threadServiceImplToTest.getStoppedThreads());
        Set <String> threads = threadServiceImplToTest.getRunningThreads();
        assertEquals("The thread is runnable!", "RunnerMock", threads.iterator().next());
    }

    private void testStoppedThreadNames() {
        mockery.checking(new Expectations() {
            {
                exactly(2).of(threadRunnerMock).isStopped();
                will(returnValue(true));
                one(threadRunnerMock).getRunner();
                will(returnValue(runnerMock));
                one(runnerMock).getRunnerName();
                will(returnValue("RunnerMock"));
            }
        });

        assertNull("No running threads!", threadServiceImplToTest.getRunningThreads());
        Set <String> threads = threadServiceImplToTest.getStoppedThreads();
        assertEquals("The thread is stopped!", "RunnerMock", threads.iterator().next());
    }

    private void createThreadForTest(final long sleepTime) {
        mockery.checking(new Expectations() {
            {
                one(threadFactoryMock).create(with(aNonNull(Runner.class)), with(sleepTime));
                will(returnValue(threadRunnerMock));
                one(threadRunnerMock).start();
                one(runnerMock).getRunnerName();
                will(returnValue("RunnerMock"));
            }
        });

        Map <Runner, Long> runnerAndSleepTime = new HashMap <Runner, Long>();
        runnerAndSleepTime.put(runnerMock, sleepTime);
        Map <Object, Map <Runner, Long>> threadsToCreate = new HashMap <Object, Map <Runner, Long>>();
        threadsToCreate.put(this, runnerAndSleepTime);
        threadServiceImplToTest.createThreads(threadsToCreate);
        Set <String> threads = threadServiceImplToTest.getNamesByCreator(getClass());
        assertNotNull("A thread is created!", threads);
        assertFalse("One thread is created!", threads.isEmpty());
        assertEquals("The thread is created!", "RunnerMock", threads.iterator().next());
    }
}
