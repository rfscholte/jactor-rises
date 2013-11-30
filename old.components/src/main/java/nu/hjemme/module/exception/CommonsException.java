package nu.hjemme.module.exception;

/**
 * An abstract instance of a java.lang.RuntimeException which are used to throw exceptions which occurs during runtime and which
 * needs to be an instance of RuntimeException. It will be the parent class for all exception handling to be done.
 * @author Tor Egil Jacobsen
 */
public abstract class CommonsException extends RuntimeException {
    private static final long serialVersionUID = -5905452303785986124L;

    /**
     * No arguments. Just initialises a new exception.
     */
    public CommonsException() {
        super();
    }

    /**
     * Initialises a new exception with a message.
     * @param message The message for the exception.
     */
    public CommonsException(final String message) {
        super(message);
    }

    /**
     * Initialises a new exception with the cause of another exception.
     * @param cause The original cause of the original exception.
     */
    public CommonsException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }

    /**
     * Initialises a new exception with a message and a cause for the exception.
     * @param message The message.
     * @param cause The original cause.
     */
    public CommonsException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
