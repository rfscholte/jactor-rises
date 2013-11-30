package nu.hjemme.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import nu.hjemme.api.i18n.event.I18nEvent;
import nu.hjemme.api.i18n.event.I18nEventListener;
import nu.hjemme.api.i18n.event.I18nEventManager;

/**
 * The factory class which will notify all registered listeners of events that happened.
 * @author Tor Egil Jacobsen
 */
public class I18nEventManagerImpl implements I18nEventManager {

    /**
     * To construct this bean is not public access, but limited to this package or any sub.
     */
    public I18nEventManagerImpl() {
        listenersByName = new HashMap <String, Map <Locale, I18nEventListener>>();
    }

    // All the listeners
    private Map <String, Map <Locale, I18nEventListener>> listenersByName = null;

    /**
     *{@inheritDoc}
     */
    public void register(String name, Locale locale, I18nEventListener listener) {
        Map <Locale, I18nEventListener> listenersByLocale = getInitialisedListenersByLocale(name);
        listenersByLocale.put(locale, listener);
    }

    /**
     * {@inheritDoc}
     */
    public void eventHappened(I18nEvent event) {
        Map <Locale, I18nEventListener> listeners = listenersByName.get(event.getI18nName());

        if (listeners == null) {
            throw new IllegalStateException("No listeners registered for events with name: " + event.getI18nName());
        }

        if (!listeners.containsKey(event.getLocale())) {
            throw new IllegalStateException("No listener registered on event: " + event);
        }

        listeners.get(event.getLocale()).i18nEventOccured(event);
    }

    private Map <Locale, I18nEventListener> getInitialisedListenersByLocale(String name) {
        if (listenersByName == null) {
            listenersByName = new HashMap <String, Map <Locale, I18nEventListener>>();
        }

        if (!listenersByName.containsKey(name)) {
            listenersByName.put(name, new HashMap <Locale, I18nEventListener>());
        }

        return listenersByName.get(name);
    }
}
