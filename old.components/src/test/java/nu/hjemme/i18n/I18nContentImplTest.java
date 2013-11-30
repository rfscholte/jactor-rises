package nu.hjemme.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;

import nu.hjemme.api.i18n.LanguageBundle;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * A test of the international content.
 * @author Tor Egil Jacobsen
 */
public class I18nContentImplTest {

    protected transient Logger log = Logger.getLogger(getClass());

    private LanguageBundle contentToTest = null;

    @Before
    public void doBefore() {
        contentToTest = new LanguageBundle();
        Map <String, String> noDynamicContent = new HashMap <String, String>(2);
        noDynamicContent.put("NOT.DYNAMIC.ONE", "some original value");
        noDynamicContent.put("NOT.DYNAMIC.TWO", "some other original value");
        contentToTest.putAll(noDynamicContent);
    }

    @Test
    public void validateDynamicKeys() {
        String dynamicKey = "DYNAMIC.CONTENT";
        assertNull("The content has not been modified!", contentToTest.getLastModified());
        contentToTest.put(dynamicKey, "dynamic content!");
        assertNotNull("The content has just been modified!", contentToTest.getLastModified());
        Set <String> dynamicAlteredKeys = contentToTest.getDynamicallyAlteredKeys();
        assertFalse("The key is not dynamically altered!", dynamicAlteredKeys.contains("NOT.DYNAMIC.TWO"));
        contentToTest.clearDynamicallyAlteredKeys();
        dynamicAlteredKeys = contentToTest.getDynamicallyAlteredKeys();
        assertTrue("No dynamic content!", contentToTest.getDynamicallyAlteredKeys().isEmpty());
    }

    @Test
    public void validateContent() {
        assertEquals("The value for the key is expected!", "some original value", contentToTest.get("NOT.DYNAMIC.ONE"));
        assertFalse("The content is not empty!", contentToTest.isEmpty());
        assertEquals("The content is equal!", "some other original value", contentToTest.get("NOT.DYNAMIC.TWO"));
    }

    @Test
    public void validateKeys() {
        Enumeration <String> keyEnum = contentToTest.getKeys();

        while (keyEnum.hasMoreElements()) {
            String key = keyEnum.nextElement();
            assertTrue("The '" + key + "' is expected!", contentToTest.containsKey(key));
            assertFalse("The '" + key.substring(5) + "' is not expected!", contentToTest.containsKey(key.substring(5)));
        }
    }

    @Test
    public void validateResourceBundleImplementation() {
        assertTrue("The key is present in the content!", contentToTest.containsKey("NOT.DYNAMIC.TWO"));
        assertTrue("The value is present in the content!", contentToTest.containsValue("some original value"));
        assertFalse("The key is not present in the content!", contentToTest.containsKey("DYNAMIC.TWO"));
        assertFalse("The value is not present in the content!", contentToTest.containsValue("some test value"));

        try {
            contentToTest.getObject("I18N");
        } catch (MissingResourceException mre) {
            log.debug(mre.getMessage());
        }

        assertEquals("The not dynamic international content exists!", "some original value", contentToTest
            .handleGetObject("NOT.DYNAMIC.ONE"));
    }

    @Test
    public void addCoverage() {
        log.debug(contentToTest.toString());
        contentToTest = new LanguageBundle(10);
        contentToTest.setLocale(new Locale("no"));
        assertNotNull("The locale was just set!", contentToTest.getLocale());
    }
}
