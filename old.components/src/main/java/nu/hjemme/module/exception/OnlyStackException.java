package nu.hjemme.module.exception;

/**
 * An exception which will only keep the stack trace and not the cause of the exception.
 * @author Tor Egil Jacobsen
 */
public class OnlyStackException extends NonReferencedException {
    private static final long serialVersionUID = -1046666765561068352L;

    /**
     * Initialises an exception.
     * @param nonReferanced The cause which is not referenced.
     * @param errorMessage The error message for the exception.
     */
    public OnlyStackException(final Throwable nonReferanced, final String errorMessage) {
        super(nonReferanced, errorMessage);
    }
}
