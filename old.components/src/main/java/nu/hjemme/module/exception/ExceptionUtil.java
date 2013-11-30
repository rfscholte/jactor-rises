package nu.hjemme.module.exception;

/**
 * A class which produces information which is typically needed in logging or exception handling.
 * @author Tor Egil Jacobsen
 */
public class ExceptionUtil {

    // The singleton instance
    protected static volatile ExceptionUtil instance = null;

    /**
     * @return the instance using double checked locking (which is not broken in java 1.5.0)
     */
    private static ExceptionUtil getInstance() {
        if (instance == null) {
            synchronized (ExceptionUtil.class) {
                if (instance == null) {
                    instance = new ExceptionUtil();
                }
            }
        }

        return instance;
    }

    /**
     * @return Returns <code>true</code> if this is the expected cause is the same as the class name of the
     *         NonReferancedException or if it is assignable from ?
     * @param nre The exception which has the cause class name.
     * @param causeClass The cause class name.
     * @param <T> The type of causeClass (needs to extend java.lang.Exception).
     */
    public static <T extends Throwable> boolean isExpectedCause(final NonReferencedException nre, final Class <T> causeClass) {
        return getInstance().isExpected(nre, causeClass);
    }

    /**
     * Runs through the causes of a <code>java.lang.Throwable</code> and checks if it is caused by the expected cause.
     * @param throwable The <code>java.lang.Throwable</code> which may have the expected cause.
     * @param causeClass The class which is expected.
     * @param <T> The type of causeClass (needs to extend java.lang.Exception).
     * @return <code>true</code> if the <code>java.lang.Throwable</code> has the expected cause, <code>false</code> if not.
     */
    public static <T extends Throwable> boolean isExpectedCause(final Throwable throwable, final Class <T> causeClass) {
        return getInstance().isExpectedCause(causeClass, throwable);
    }

    /**
     * Retrieves the root cause of a <code>java.lang.Throwable</code>.
     * @param throwable The <code>java.lang.Throwable</code> from where to return the root cause.
     * @return The root cause or <code>null</code> if there is no cause.
     */
    public static Throwable getRootCause(final Throwable throwable) {
        return getInstance().theRootCause(throwable);
    }

    public static void throwIllegalState(Exception exception) {
        getInstance().illegalSate(exception);
    }

    // --- the bean ---

    protected ExceptionUtil() {
        // To avoid being instantiated.
    }

    protected <T extends Throwable> boolean isExpectedCause(final Class <T> causeClass, final Throwable throwable) {
        if (throwable instanceof NonReferencedException) {
            return isExpectedCause((NonReferencedException) throwable, causeClass);
        }

        if (causeClass == null || throwable == null) {
            return false;
        }

        Throwable cause = theRootCause(throwable);

        if (cause != null && causeClass.isInstance(cause)) {
            return true;
        }

        return false;
    }

    protected Throwable theRootCause(final Throwable throwable) {
        Throwable cause = throwable.getCause();

        while (cause != null && cause.getCause() != null) {
            cause = cause.getCause();
        }

        return cause;
    }

    protected <T extends Throwable> boolean isExpected(final NonReferencedException nre, final Class <T> causeClass) {
        if (causeClass == null || nre == null) {
            return false;
        }

        try {
            final Class < ? > exception = Class.forName(nre.getRootCauseName());

            if (causeClass.isAssignableFrom(exception)) {
                return true;
            }
        } catch (ClassNotFoundException cnfe) {
            return false;
        }

        return false;
    }

    protected RuntimeException transformThrowable(final Throwable throwable) {
        if (throwable instanceof Error) {
            throw (Error) throwable;
        } else if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        }

        return new UnknownException(throwable);
    }

    protected void illegalSate(Exception exception) {
        throw new IllegalStateException(metMessage(exception));
    }

    private String metMessage(Exception exception) {
        return exception.getClass().getName() + ": " + exception.getMessage() + " at " + exception.getStackTrace()[0];
    }
}
