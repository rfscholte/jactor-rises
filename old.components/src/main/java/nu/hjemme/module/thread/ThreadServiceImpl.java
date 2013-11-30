package nu.hjemme.module.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import nu.hjemme.client.thread.Runner;
import nu.hjemme.client.thread.ThreadService;

/**
 * The implementation of the thread service.
 * @author Tor Egil Jacobsen
 */
public class ThreadServiceImpl implements ThreadService {

    // The thread runners by name
    protected Map <String, ThreadRunner> threadRunners = null;

    // The name of the implants by creator
    protected Map <Class < ? >, SortedSet <String>> namesByCreator = null;

    public ThreadServiceImpl() {
        threadRunners = new HashMap <String, ThreadRunner>();
        namesByCreator = new HashMap <Class < ? >, SortedSet <String>>();
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void createThreads(Map <Object, Map <Runner, Long>> threadRunnersToCreate) {
        createRunners(threadRunnersToCreate);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized SortedSet <String> getNamesByCreator(Class < ? > creator) {
        return namesByCreator.get(creator);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized SortedSet <String> getRunningThreads() {
        SortedSet <String> runningThreads = getThreadNames(false);

        if (runningThreads.size() == 0) {
            return null;
        }

        return runningThreads;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized Throwable getThrowable(String name) {
        ThreadRunner runner = threadRunners.get(name);
        Throwable throwable = null;

        if (runner != null) {
            throwable = runner.getThrowable();
        }

        return throwable;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized SortedSet <String> getStoppedThreads() {
        SortedSet <String> stoppedThreads = getThreadNames(true);

        if (stoppedThreads.size() == 0) {
            return null;
        }

        return stoppedThreads;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized boolean isKnown(String name) {
        return threadRunners.get(name) != null;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized boolean isStopped(String name) {
        if (threadRunners.get(name) == null) {
            return false;
        }

        return threadRunners.get(name).isStopped();
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void stopThread(String name) {
        ThreadRunner threadRunner = threadRunners.get(name);

        if (threadRunner != null) {
            threadRunner.getRunner().setInvokable(false);
            threadRunner.interrupt();
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void stopThreadsByCreator(Class < ? > creator) {
        SortedSet <String> names = namesByCreator.get(creator);

        if (names == null) {
            return;
        }

        for (String name : names) {
            stopThread(name);
        }
    }

    // --- protected methods ---

    /**
     * When the instance is destroyed by the garbage collector...
     */
    protected void finalize() throws Throwable {
        interruptThreads();
    };

    /**
     * Creates and starts a thread runner.
     * @param runner the runner.
     * @return the runner initialised.
     */
    protected ThreadRunner createAndStart(Runner runner, long sleep) {
        ThreadRunner threadRunner = ThreadFactory.createThread(runner, sleep);
        threadRunner.start();

        return threadRunner;
    }

    // --- private methods ---

    private void createRunners(Map <Object, Map <Runner, Long>> threadRunnersToCreate) {
        validateNotNullOrEmtpy(threadRunnersToCreate);

        Set <Object> creators = threadRunnersToCreate.keySet();

        for (Object creator : creators) {
            Map <Runner, Long> runnersAndSleepTime = threadRunnersToCreate.get(creator);
            Set <Runner> runners = runnersAndSleepTime.keySet();

            for (Runner runner : runners) {
                createThread(runner, runnersAndSleepTime.get(runner), creator.getClass());
            }
        }
    }

    private void validateNotNullOrEmtpy(Map <Object, Map <Runner, Long>> threadRunnersToCreate) {
        if (threadRunnersToCreate == null) {
            throw new IllegalArgumentException("The thread runners can not be null!");
        }

        if (threadRunnersToCreate.isEmpty()) {
            throw new IllegalArgumentException("The thread runners can not be empty!");
        }
    }

    private void createThread(Runner runner, long sleep, Class < ? > createdBy) {
        String runnerName = runner.getRunnerName();
        ThreadRunner threadRunner = createAndStart(runner, sleep);
        stopOldRunner(threadRunners.put(runnerName, threadRunner));
        insertIntoNamesByCreator(createdBy, runnerName);
    }

    /**
     * Insert the name into the set of the thread names by creator.
     * @param creator The creator of the thread.
     * @param runnerName The name of the runner.
     */
    private void insertIntoNamesByCreator(Class < ? > creator, String runnerName) {
        SortedSet <String> names = namesByCreator.get(creator);

        if (names == null) {
            names = new TreeSet <String>();
            namesByCreator.put(creator, names);
        }

        names.add(runnerName);
    }

    /**
     * Stop the old instance of Runner.
     * @param oldRunner
     */
    private void stopOldRunner(ThreadRunner oldRunner) {
        if (oldRunner != null) {
            oldRunner.getRunner().setInvokable(false);
        }
    }

    /**
     * @param isStopped <code>true</code> if the threads to get should be stopped, <code>false</code> if not.
     * @return A set of thread names.
     */
    private SortedSet <String> getThreadNames(boolean isStopped) {
        SortedSet <String> sortedNames = new TreeSet <String>();

        for (ThreadRunner threadRunner : threadRunners.values()) {
            if (isStopped == threadRunner.isStopped()) {
                Runner runner = threadRunner.getRunner();
                sortedNames.add(runner.getRunnerName());
            }
        }

        return sortedNames;
    }

    /**
     * Interrupts all sleeping threads.
     */
    private void interruptThreads() {
        for (ThreadRunner runner : threadRunners.values()) {
            runner.interrupt();
        }
    }
}
