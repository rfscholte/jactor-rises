package nu.hjemme.api.i18n;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

/**
 * The i18n content for a menu mapped by key->value.
 * @author Tor Egil Jacobsen
 * @param <Content> The type of content.
 */
public interface I18nContent {

    /**
     * @param key the key to the i18n value.
     * @return <code>true</code> if the key is among the content.
     */
    boolean containsKey(String key);

    /**
     * @param value the i18n value
     * @return <code>true</code> if the value is among the content.
     */
    boolean containsValue(String value);

    /**
     * @return <code>true</code> if no content.
     */
    boolean isEmpty();

    /**
     * @return an enumeration of the keys in this content
     */
    Enumeration <String> getKeys();

    /**
     * @return the size of this content.
     */
    int size();

    /**
     * @param key the key to the i18n value.
     * @return the i18n value for the key
     */
    String get(String key);

    /**
     * @param locale the locale for the content.
     */
    void setLocale(Locale locale);

    /**
     * @return the locale for the content.
     */
    Locale getLocale();

    /**
     * @return the keys which are dynamically altered and not loaded from the I18nReader.
     */
    Set <String> getDynamicallyAlteredKeys();

    /**
     * Clears the dynamically altered keys.
     */
    void clearDynamicallyAlteredKeys();

    /**
     * @param key the key to the content.
     * @param value the value of the content.
     * @return any old content for this key.
     */
    String put(String key, String value);

    /**
     * Puts all from a map of content to the i18n content.
     * @param addition the new content to put to the immutable content.
     */
    void putAll(Map < ? extends String, String> addition);

    /**
     * @return Last modified time stamp, <code>null</code> if no new content.
     */
    DateTime getLastModified();
}
