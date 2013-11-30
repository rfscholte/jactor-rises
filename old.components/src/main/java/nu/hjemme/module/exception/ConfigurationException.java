package nu.hjemme.module.exception;

/**
 * An exception to be thrown if the exception can be avoided due to configuration.
 * @author Tor Egil Jacobsen
 */
public class ConfigurationException extends CommonsException {
    private static final long serialVersionUID = 3153371174294512519L;

    /**
     * Creates the configuration exception.
     */
    public ConfigurationException() {
        super();
    }

    /**
     * Creates the configuration exception.
     * @param message The message for this exception
     */
    public ConfigurationException(final String message) {
        super(message);
    }
}
