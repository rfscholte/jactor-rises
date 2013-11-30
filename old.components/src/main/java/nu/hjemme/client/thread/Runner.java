package nu.hjemme.client.thread;

/**
 * The interface to indicate that the implemented class can be managed from a thread runner.
 * @author Tor Egil Jacobsen
 */
public interface Runner {

    /**
     * The method to be invoked the thread is run. This is one execution, and then the thread will sleep and do another
     * invocation, and so on...
     */
    void invoke();

    /**
     * @param invokable set the invokable status on the runner.
     */
    void setInvokable(boolean invokable);

    /**
     * @return the name of the runner.
     */
    String getRunnerName();

    /**
     * @return if this thread is invokable.
     */
    boolean isInvokable();

    /**
     * Thirty seconds stated in milliseconds.
     */
    long THIRTY_SECONDS = 1000 * 30;

    /**
     * Twenty four hours stated in milliseconds.
     */
    long TWENTYFOUR_HOURS = 1000 * 60 * 60 * 24;

    /**
     * Six hours stated in milliseconds.
     */
    long SIX_HOURS = 1000 * 60 * 60 * 6;
}
