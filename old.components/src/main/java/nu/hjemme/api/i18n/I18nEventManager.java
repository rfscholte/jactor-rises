package nu.hjemme.api.i18n;

import java.util.Locale;

/**
 * The manager of i18n events (monitor for i18n content).
 * @author Tor Egil Jacobsen
 */
public interface I18nEventManager {

    /**
     * Register a listener for i18n events.
     * @param listener The listener to register.
     * @param name The name of the i18n component.
     * @param locale The java.util.Locale of the i18n component.
     */
    void register(String name, Locale locale, I18nEventListener listener);

    /**
     * Notify the manager about an event that happened.
     * @param event The event happened.
     */
    void eventHappened(I18nEvent event);
}
