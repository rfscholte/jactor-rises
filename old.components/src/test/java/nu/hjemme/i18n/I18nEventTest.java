package nu.hjemme.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Locale;


import nu.hjemme.api.i18n.I18nEventImpl;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Test of the i18n event.
 * @author Tor Egil Jacobsen
 */
public class I18nEventTest {

    protected transient Logger log = Logger.getLogger(getClass());

    @Test
    public void initializeAndToString() {
        I18nEventImpl i18nEvent = new I18nEventImpl("TestI18nEvent");
        assertEquals("The name without the locale!", "TestI18nEvent", i18nEvent.toString());
        assertNull("No locale!", i18nEvent.getLocale());
        i18nEvent = new I18nEventImpl("TestI18nEvent", new Locale("no"));
        assertEquals("The name with the locale!", "TestI18nEvent/no", i18nEvent.toString());
        assertEquals("Norwegian locale!", new Locale("no"), i18nEvent.getLocale());
        log.debug("Tested " + i18nEvent.getI18nName());
    }
}
