package nu.hjemme.i18n;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.api.util.DateUtil;
import nu.hjemme.api.util.EnumerationImpl;

import org.apache.log4j.Logger;

/**
 * The i18n content which will allow to change the immutable content (as protected access).
 * @author Tor Egil Jacobsen
 */
public class I18nContentInitiableImpl extends ResourceBundle implements I18nContent {

    // Log
    protected final transient Logger logger = Logger.getLogger(getClass());

    // The dynamic content
    private Map <String, String> content = null;

    // The international value...
    private Locale locale = null;

    // Dynamically added keys
    private Set <String> dynamicallyAddedKeys = null;

    // Last dynamic modification
    private Date lastModified = null;

    /**
     * Constructs the content.
     */
    public I18nContentInitiableImpl() {
        init(null);
    }

    /**
     * Constructs the content.
     */
    public I18nContentInitiableImpl(Integer startSize) {
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
    // This method is not an override on java from apple mac os x
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
    public Date getLastModified() {
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
