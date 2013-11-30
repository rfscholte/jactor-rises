package nu.hjemme.api.i18n;

import java.util.Locale;

/**
 * An event for the i18n components.
 * @author Tor Egil Jacobsen
 */
public interface I18nEvent {

    /**
     * @return the locale. If <code>null</code>, all components with the i18n name needs an update.
     */
    Locale getLocale();

    /**
     * @return the i18nName
     */
    String getI18nName();
}
