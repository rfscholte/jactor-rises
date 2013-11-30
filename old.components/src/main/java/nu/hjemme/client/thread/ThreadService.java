package nu.hjemme.client.thread;

import java.util.Map;
import java.util.SortedSet;

/**
 * A thread service used to manage threads (instances of Runner).
 * @author Tor Egil Jacobsen
 */
public interface ThreadService {

    /**
     * Creates new threads when invoked.
     * @param threadRunnersToCreate A map of runners and their sleep time by a map of beans who wants the runners.
     */
    void createThreads(Map <Object, Map <Runner, Long>> threadRunnersToCreate);

    /**
     * @return a <code>java.lang.Throwable</code> in a thread, <code>null</code>. if none.
     * @param name The name of the thread.
     */
    Throwable getThrowable(String name);

    /**
     * @param name The name of the thread to stop.
     */
    void stopThread(String name);

    /**
     * @return <code>true</code> if the thread is stopped. <code>false</code> if the thread does not exist or is stopped.
     * @param name The name of the thread..
     */
    boolean isStopped(String name);

    /**
     * @return the names of the threads which are running.
     */
    SortedSet <String> getRunningThreads();

    /**
     * @return The names of the threads which are stopped.
     */
    SortedSet <String> getStoppedThreads();

    /**
     * @return <code>true</code> if the thread is known.
     * @param name The name of a thread.
     */
    boolean isKnown(String name);

    /**
     * Stops the threads which is created by a particular class.
     * @param creator The name of the creator.
     */
    void stopThreadsByCreator(Class < ? > creator);

    /**
     * @return the names of threads by a particular class.
     * @param creator The class which created the thread.
     */
    SortedSet <String> getNamesByCreator(Class < ? > creator);
}
