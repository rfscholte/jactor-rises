package nu.hjemme.i18n;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.util.DateUtil;
import nu.hjemme.util.EnumerationImpl;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

/**
 * The i18n content which will allow change the immutable content.
 * @author Tor Egil Jacobsen
 */
public class LanguageBundle extends ResourceBundle implements I18nContent {

    // Log
    protected final transient Logger logger = Logger.getLogger(getClass());

    // The dynamic content
    private Map <String, String> content;

    // The international value...
    private Locale locale;

    // Dynamically added keys
    private Set <String> dynamicallyAddedKeys;

    // Last dynamic modification
    private DateTime lastModified;

    /**
     * Constructs the content.
     */
    public LanguageBundle() {
        init(null);
    }

    /**
     * Constructs the content.
     */
    public LanguageBundle(Integer startSize) {
        init(startSize);
    }

    /**
     * {@inheritDoc}
     */
    public String put(String key, String value) {
        dynamicallyAddedKeys.add(key);
        lastModified = DateUtil.getSystemDate();

        return content.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    public void putAll(Map < ? extends String, String> addition) {
        content.putAll(addition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(String key) {
        return content.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsValue(String value) {
        return content.containsValue(value);
    }

    /**
     * {@inheritDoc}
     */
    public String get(String key) {
        return content.get(key);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return content.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return content.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return content.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enumeration <String> getKeys() {
        return new EnumerationImpl <String>(content.keySet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object handleGetObject(String key) {
        if (logger.isDebugEnabled()) {
            logger.debug("Key: " + key + ", exists: " + containsKey(key));
        }

        return get(key);
    }

    /**
     * {@inheritDoc}
     */
    public void clearDynamicallyAlteredKeys() {
        for (String dynamicKey : dynamicallyAddedKeys) {
            content.remove(dynamicKey);
        }

        dynamicallyAddedKeys.clear();
    }

    /**
     * {@inheritDoc}
     */
    public Set <String> getDynamicallyAlteredKeys() {
        return dynamicallyAddedKeys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        return locale;
    }

    /**
     * {@inheritDoc}
     */
    public DateTime getLastModified() {
        return lastModified;
    }

    /**
     * {@inheritDoc}
     */
    public void setContent(Map <String, String> content) {
        this.content = content;
    }

    private void init(Integer startSize) {
        if (startSize == null) {
            content = new HashMap <String, String>();
        } else {
            content = new HashMap <String, String>(startSize);
        }

        dynamicallyAddedKeys = new HashSet <String>();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
