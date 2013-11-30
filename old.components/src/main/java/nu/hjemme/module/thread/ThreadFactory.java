package nu.hjemme.module.thread;

import nu.hjemme.client.thread.Runner;

public class ThreadFactory {

    protected static volatile ThreadFactory instance = null;

    public static ThreadRunner createThread(Runner runner, long sleepTime) {
        return getInstance().create(runner, sleepTime);
    }

    protected static ThreadFactory getInstance() {
        if (instance == null) {
            synchronized (ThreadFactory.class) {
                if (instance == null) {
                    instance = new ThreadFactory();
                }
            }
        }

        return instance;
    }

    protected ThreadFactory() {}

    protected ThreadRunner create(Runner runner, long sleepTime) {
        return new ThreadRunner(runner, sleepTime);
    }
}
