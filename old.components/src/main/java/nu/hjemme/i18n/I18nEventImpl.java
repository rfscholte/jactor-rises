package nu.hjemme.i18n;

import java.util.Locale;

import nu.hjemme.api.i18n.I18nEvent;

/**
 * An event for the i18n components.
 * @author Tor Egil Jacobsen
 */
public class I18nEventImpl implements I18nEvent {

    private static final String EMPTY = "";
    private static final String SLASH = "/";

    // Variables
    private Locale locale = null;
    private String i18nName = null;

    /**
     * Constructs an event with the name of the component which needs an update, and the locale which is used.
     * @param i18nName The component which needs update.
     * @param locale The locale.
     */
    public I18nEventImpl(String i18nName, Locale locale) {
        this.i18nName = i18nName;
        this.locale = locale;
    }

    /**
     * Constructs an event with the name of the component which needs an update.
     * @param i18nName The component which needs update.
     */
    public I18nEventImpl(String i18nName) {
        this(i18nName, null);
    }

    /* (non-Javadoc)
     * @see nu.hjemme.api.i18n.event.I18nEvent#getLocale()
     */
    public Locale getLocale() {
        return locale;
    }

    /* (non-Javadoc)
     * @see nu.hjemme.api.i18n.event.I18nEvent#getI18nName()
     */
    public String getI18nName() {
        return i18nName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return i18nName + (locale != null ? SLASH + locale : EMPTY );
    }
}
