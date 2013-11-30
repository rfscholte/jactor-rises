package nu.hjemme.api.i18n;

import java.util.Locale;

import nu.hjemme.api.thread.Runner;

/**
 * The reader of i18n content.<br>
 * The reader is a runner for a thread. Each time a reader is invoked, it should see if the content should be read once more.
 * @author Tor Egil Jacobsen
 */
public interface I18nReader extends Runner {

    /**
     * Initialises the reader with the component it is to read for.
     * @param readForComponent The component to read for.
     * @param locale The <code>java.util.Locale</code> to use.
     */
    void initReader(Locale locale, I18nComponent readForComponent);

    /**
     * Reads the content for the <code>java.util.Locale</code> and the component it has been initialised with.
     */
    void readContent();
}
