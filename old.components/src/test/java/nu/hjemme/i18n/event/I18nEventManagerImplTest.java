package nu.hjemme.i18n.event;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.Locale;

import nu.hjemme.api.i18n.event.I18nEvent;
import nu.hjemme.api.i18n.event.I18nEventListener;
import nu.hjemme.i18n.I18nEventManagerImpl;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class I18nEventManagerImplTest {

    protected transient Logger log = Logger.getLogger(getClass());

    private I18nEvent event = null;
    private I18nEventManagerImpl eventManagerToTest = null;
    private Listener listener = null;

    @Before
    public void doBefore() {
        event = new I18nEvent("TestEvent", new Locale("no"));
        eventManagerToTest = new I18nEventManagerImpl();
        listener = new Listener();
    }

    @Test
    public void testEventManager() {
        eventManagerToTest.register(event.getI18nName(), event.getLocale(), listener);
        eventManagerToTest.eventHappened(event);
        assertSame("This is the event that occured!", event, listener.eventOccured);
    }

    @Test
    public void validateNotManagedEvent() {
        try {
            eventManagerToTest.eventHappened(event);
            fail("No listeners are registered!");
        } catch (IllegalStateException ise) {
            log.debug(ise.getClass() + ": " + ise.getMessage());
        }

        eventManagerToTest.register(event.getI18nName(), new Locale("en"), listener);

        try {
            eventManagerToTest.eventHappened(event);
            fail("Listeners on the wrong locale is registered!");
        } catch (IllegalStateException ise) {
            log.debug(ise.getClass() + ": " + ise.getMessage());
        }
    }

    /**
     * The i18n event listener.
     * @author Tor Egil Jacobsen
     */
    private class Listener implements I18nEventListener {
        private I18nEvent eventOccured = null;

        public void i18nEventOccured(I18nEvent event) {
            eventOccured = event;
        }
    }
}
