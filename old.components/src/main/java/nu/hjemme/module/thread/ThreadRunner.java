package nu.hjemme.module.thread;

import nu.hjemme.client.thread.Runner;

/**
 * The thread runner. The implementation which invokes instances of runner.
 * @author Tor Egil Jacobsen
 */
public class ThreadRunner extends Thread {

    /**
     * Creates the thread runner giving it the runner to run and the time it should sleep (in milliseconds)
     * @param runner the runner the thread runner will run
     * @param sleepTime the time the runner will sleep between invokes (in milliseconds)
     */
    protected ThreadRunner(Runner runner, long sleepTime) {
        setRunner(runner);
        this.sleepTime = sleepTime;
    }

    // instance variables
    private Runner runner = null;
    private Throwable throwable = null;
    private boolean isStopped = false;
    private long sleepTime = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        if (runner == null) {
            throw new IllegalStateException("There is no runner to run!");
        }

        try {
            runWhileInvokableAndRunnable();
        } catch (Throwable t) {
            handleThrowable(t);
        }

        isStopped = true;
    }

    /**
     * Handles a <code>java.lang.Throwable</code>.
     * @param t
     */
    private void handleThrowable(Throwable throwable) {
        this.throwable = throwable;
        isStopped = true;

        if (throwable instanceof Error) {
            throw (Error) throwable;
        }

        throw new IllegalStateException(throwable);
    }

    /**
     * Invokes the runner and sleeps as long as the runner is invokable or the RunnableImp is able to run. It will loop as long
     * as the runner says it can be invoked and it will do just that. After one invocation is done, the thread will go to sleep
     * for the given time to sleep.
     */
    private void runWhileInvokableAndRunnable() {
        while (runner.isInvokable()) {
            synchronized (runner) {
                runner.invoke();
            }

            try {
                sleep(sleepTime);
            } catch (InterruptedException ie) {
                throw new IllegalStateException("The thread is interrupted! " + ie);
            }
        }
    }

    // Getters

    /**
     * @return the runner to get.
     */
    public Runner getRunner() {
        return runner;
    }

    /**
     * @return <code>true</code> if stopped.
     */
    public boolean isStopped() {
        return isStopped;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    // Setters

    /**
     * @param runner the runner to set.
     */
    public void setRunner(Runner runner) {
        this.runner = runner;

        if (runner != null) {
            setName(runner.getRunnerName());
        }
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
}
