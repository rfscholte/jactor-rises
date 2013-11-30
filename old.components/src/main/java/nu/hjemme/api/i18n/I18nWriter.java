package nu.hjemme.api.i18n;

import java.util.Locale;

import nu.hjemme.api.thread.Runner;

/**
 * The writer of content.<br>
 * The writer is a runner for a thread. Each time a writer is invoked, it should see if there is new content that could be
 * written to file.
 * @author Tor Egil Jacobsen
 */
public interface I18nWriter extends Runner {

    /**
     * Initialises the writer with the component to write for.<br>
     * The writer will only write content that is added after it is initialised!
     * @param component The component to write for.
     * @param locale The <code>java.util.Locale</code> to determine the written file name.
     */
    void initWriter(Locale locale, I18nComponent component);

    /**
     * Writes the content for the <code>java.util.Locale</code> and the component it has been initialised with.
     */
    void writeContent();
}
