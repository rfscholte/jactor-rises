package nu.hjemme.api.i18n;

import java.util.Locale;

/**
 * The i18n content manager.
 * @author Tor Egil Jacobsen
 * @param <ComponentToManage> The i18n component being managed.
 */
public interface I18nComponentManager <ComponentToManage extends I18nComponent> {

    /**
     * @return the international content for Œ <code>java.util.Locale</code>.
     * @param locale <code>java.util.Locale</code> for the content.
     * @param name The name of the component where to get the content.
     */
    I18nContent getI18nContent(Locale locale, String name);

    /**
     * Get the i18n managed component content by its locale, name and key. If no component is found, the component for the
     * default <code>java.util.Locale</code> will be retrieved. If no content is found here either, an
     * <code>nu.hjemme.commons.exception.CommonsException</code> will be thrown if told to to throw exceptions.
     * @param name The name of the i18n component.
     * @param key The key to the content of the i18n component.
     * @param throwException <code>true</code> if an exception is to be thrown if the component is not found
     * @param locale <code>java.util.Locale</code> for the content.
     * @return The value of the internationalised content.
     */
    String get(String name, String key, boolean throwException, Locale locale);

    /**
     * Get the i18n managed component content by its <code>java.util.Locale</code>, name and key. If no component is found, the
     * component for the default <code>java.util.Locale</code> will be retrieved. If no content is found here either, an
     * <code>nu.hjemme.commons.exception.CommonsException</code> will be thrown.
     * @param name The name of the i18n component.
     * @param key The key to the content of the i18n component.
     * @param locale <code>java.util.Locale</code> for the content.
     * @return The value of the internationalised content.
     */
    String get(String name, String key, Locale locale);

    /**
     * Put an international components content to the manager.
     * @param locale The <code>java.util.Locale</code> which determines the i18n value.
     * @param name The name of the i18n component.
     * @param key The key for the managed content.
     * @param defaultValue The i18n value for the default <code>java.util.Locale</code>.
     * @param value The i18n value.
     */
    void put(Locale locale, String name, String key, String defaultValue, String value);

    /**
     * Put the managed content of an i18n component to the manager.
     * @param locale The <code>java.util.Locale</code> which determines the i18n value.
     * @param name The name of the i18n component.
     * @param key The key for the managed content.
     * @param internationalValue The i18n value.
     * @return the old i18n value, <code>null</code> if none.
     */
    String put(Locale locale, String name, String key, String internationalValue);

    /**
     * Initialise the manager with the component managers given.
     */
    void init();
}
