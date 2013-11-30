package nu.hjemme.module.exception;

import nu.hjemme.module.util.StackUtil;

/**
 * The abstract class which creates the stack trace of the non referenced cause and/or keep the class name of the non referenced
 * cause.
 * @author Tor Egil Jacobsen
 */
public abstract class NonReferencedException extends RuntimeException {
    private static final long serialVersionUID = -2714739500133619437L;


    // Constants
    private static final String SPACE = " ";
    private static final String CAUSED_BY = " is caused by ";
    private static final String COLON_SPACE = ":" + SPACE;

    private String error;
    private String rootCauseName;

    /**
     * The exception created when it is caused by an exception which we do not want the reference of.
     * @param nonReferenced The <code>java.lang.Throwable</code> cause which we do not keep as a memory reference.
     * @param errorMessage The error message which is causing the exception.
     */
    protected NonReferencedException(final Throwable nonReferenced, final String errorMessage) {
        super(errorMessage);

        if (nonReferenced == null) {
            throw new IllegalArgumentException("The cause of a non referenced exception can not be null!");
        }

        setStackTrace(nonReferenced.getStackTrace());
        setError(nonReferenced);
        setRootCauseName(nonReferenced);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getName() + CAUSED_BY + error;
    }

    /**
     * @return The cause class name which is source of the exception.
     */
    public String getRootCauseName() {
        return rootCauseName;
    }

    public String getError() {
        return error;
    }

    public final void setError(final Throwable error) {
        this.error = getThrowableMessage(error);
    }

    public final void setRootCauseName(final Throwable nonReferenced) {
        final Throwable cause = ExceptionUtil.getRootCause(nonReferenced);

        if (cause != null) {
            rootCauseName = cause.getClass().getName();
        } else {
            rootCauseName = getClass().getName();
        }
    }

    private String getThrowableMessage(final Throwable throwable) {
        StringBuilder message = new StringBuilder(StackUtil.getStackInfo(StackUtil
            .getValidStackTraceElement(ExceptionUtil.class.getName())));

        message.append(COLON_SPACE) //
            .append(throwable.getClass()) //
            .append(COLON_SPACE) //
            .append(throwable.getMessage()) //
            .append(COLON_SPACE) //
            .append(throwable.getStackTrace()[0]);

        return appendCauses(throwable, message).toString();
    }

    private StringBuilder appendCauses(final Throwable throwable, final StringBuilder message) {
        Throwable cause = throwable.getCause();

        while (cause != null) {
            message.append(SPACE) //
                .append("caused by ") //
                .append(cause.getStackTrace()[0].toString());
            cause = cause.getCause();
        }

        return message;
    }
}
