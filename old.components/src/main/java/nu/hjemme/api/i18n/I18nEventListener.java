package nu.hjemme.api.i18n;

import java.util.EventListener;

/**
 * A listener for events regarding i18n components will implement this method.
 * @author Tor Egil Jacobsen
 */
public interface I18nEventListener extends EventListener {

    /**
     * Something the I18n component has happened.
     * @param event The event object.
     */
    void i18nEventOccured(I18nEvent event);
}
