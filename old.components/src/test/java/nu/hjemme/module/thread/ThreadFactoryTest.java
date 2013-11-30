package nu.hjemme.module.thread;

import static org.junit.Assert.assertNotNull;
import nu.hjemme.client.thread.Runner;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class ThreadFactoryTest {

    private Mockery mockery = new JUnit4Mockery();
    private ThreadFactory threadFactoryMock = null;

    @Before
    public void before() {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
        threadFactoryMock = mockery.mock(ThreadFactory.class);
    }

    @Test
    public void instanceThreadFactory() {
        ThreadFactory.instance = null;
        assertNotNull("The factory should be instansiated!", ThreadFactory.getInstance());
    }

    @Test
    public void createThread() {
        mockery.checking(new Expectations() {
            {
                one(threadFactoryMock).create(with(aNull(Runner.class)), with(1L));
                will(returnValue(new ThreadRunner(null, 1)));
            }
        });

        ThreadFactory.instance = threadFactoryMock;
        assertNotNull("The thread runner is created!", ThreadFactory.createThread(null, 1));
    }
}
