package nu.hjemme.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.I18nComponentManager;
import nu.hjemme.api.i18n.I18nContent;

import org.apache.log4j.Logger;

/**
 * A class which use an instance of <code>java.util.Locale</code> and the name of an international component to manage it for an
 * application.
 * @author Tor Egil Jacobsen
 * @param <Managed> The i18n component being managed.
 */
public abstract class AbstractI18nComponentManager <Managed extends I18nComponent> implements I18nComponentManager <Managed> {

    // Log
    protected transient final Logger logger = Logger.getLogger(getClass());

    public AbstractI18nComponentManager() {
        componentsByName = new HashMap <String, Managed>();
    }

    // Variables
    private Map <String, Managed> componentsByName = null;

    // public bean methods

    /**
     * {@inheritDoc}
     */
    public void init() {
        intManager();
    }

    /**
     * {@inheritDoc}
     */
    public void setComponentToManage(Managed component) {
        setComponent(component);
    }

    /**
     * {@inheritDoc}
     */
    public I18nContent getI18nContent(Locale locale, String name) {
        return getContent(name, locale);
    }

    /**
     * {@inheritDoc}
     */
    public String get(String name, String key, boolean throwException, Locale locale) {
        return getManagedContent(name, key, throwException, locale);
    }

    /**
     * {@inheritDoc}
     */
    public String get(String name, String key, Locale locale) {
        return get(name, key, true, locale);
    }

    /**
     * {@inheritDoc}
     */
    public final void put(Locale locale, String name, String key, String defaultValue, String value) {
        put(locale, name, key, value);
        put(Locale.getDefault(), name, key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    public String put(Locale locale, String name, String key, String internationalValue) {
        return putContent(locale, name, key, internationalValue);
    }

    // protected

    protected Managed getManagedComponent(String name) {
        return componentsByName.get(name);
    }

    // privates

    private void intManager() {
        for (Managed component : componentsByName.values()) {
            component.init();
        }
    }

    private void setComponent(Managed component) {
        componentsByName.put(component.getName(), component);
    }

    /**
     * Throws the key was not found exception as an invalid state.
     * @param key the key.
     * @param original the <code>java.util.Locale</code> that was asked for originally.
     * @param locale the <code>java.util.Locale</code>.
     */
    private void throwKeyNotFound(String key, Locale locale) {
        StringBuilder keyNotFound = new StringBuilder("The resource (java.util.Locale(");
        keyNotFound.append(locale) //
            .append(")/") //
            .append(key) //
            .append(") was not found! ") //
            .append("Default java.util.Locale: ") //
            .append(Locale.getDefault());

        throw new MissingResourceException(keyNotFound.toString(), getClass().getName(), key);
    }

    /**
     * @return The international content by name and <code>java.util.Locale</code>
     * @param locale The international <code>java.util.Locale</code>
     * @param name The name
     */
    private I18nContent getContent(String name, Locale locale) {
        Managed component = componentsByName.get(name);

        if (component == null) {
            return null;
        }

        I18nContent content = component.getInternationalContent(locale);

        if (content == null && !locale.equals(Locale.getDefault())) {
            return component.getInternationalContent(Locale.getDefault());
        }

        return content;
    }

    /**
     * @param name The name of the component with the content.
     * @param key The key for the content.
     * @param throwException <code>false</code> if not to throw an exception if no content is found.
     * @param locale <code>java.util.Locale</code> of the i18n component to get the content from.
     * @return The managed content.
     */
    private String getManagedContent(String name, String key, boolean throwException, Locale locale) {
        Managed component = componentsByName.get(name);

        if (component == null && throwException) {
            throw new MissingResourceException(componentNotFoundMsg(name), getClass().getName(), key);
        }

        I18nContent content = null;

        if (component != null) {
            content = getContent(key, throwException, locale, component);
            return getManagedContent(key, throwException, locale, content);
        }

        logger.warn(contentNotFoundMsg(name, locale));
        return null;
    }

    private String getManagedContent(String key, boolean throwException, Locale locale, I18nContent content) {
        String managedContent = null;

        if (content != null) {
            managedContent = content.get(key);

            if (managedContent == null && throwException) {
                throwKeyNotFound(key, locale);
            }
        }

        return managedContent;
    }

    private I18nContent getContent(String key, boolean throwException, Locale locale, Managed component) {
        I18nContent content;

        if (component.containsInternationalContent(locale) && component.getInternationalContent(locale).containsKey(key)) {
            content = component.getInternationalContent(locale);
        } else {
            content = component.getInternationalContent(Locale.getDefault());

            if (content == null && throwException) {
                throwKeyNotFound(key, locale);
            }
        }

        return content;
    }

    private Object contentNotFoundMsg(String name, Locale locale) {
        return null;
    }

    private String componentNotFoundMsg(String name) {
        StringBuilder componentNotFound = new StringBuilder("The component named '");
        componentNotFound.append(name) //
            .append("' was not found!");

        return componentNotFound.toString();
    }

    private String putContent(Locale locale, String name, String key, String internationalValue) {
        Managed component = componentsByName.get(name);
        validateComponent(component, name, locale);
        I18nContent content = component.getInternationalContent(locale);

        return content.put(key, internationalValue);
    }

    private void validateComponent(Managed component, String name, Locale locale) {
        if (component == null) {
            String notFound = componentNotFoundMsg(name);

            throw new MissingResourceException(notFound, getClass().getName(), name);
        }

        if (!component.containsInternationalContent(locale)) {
            throw new IllegalArgumentException("The component named " + name
                + " does not manage i18n resources with java.util.Locale: " + locale);
        }
    }
}
