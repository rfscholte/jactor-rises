package nu.hjemme.api.i18n;

import java.util.Locale;
import java.util.Set;

/**
 * The signature component which can be localised. The international content contains of string values.
 * @author Tor Egil Jacobsen
 */
public interface I18nComponent {

    /**
     * @return The file name of the international content without its suffix
     * @param locale the <code>java.util.Locale</code> resource which is wanted.
     */
    String getI18nFileNameWithoutSuffix(Locale locale);

    /**
     * @return The file name of the international content
     * @param locale the <code>java.util.Locale</code> resource which is wanted.
     */
    String getI18nFileName(Locale locale);

    /**
     * @return The international content for this this component.
     * @param locale the <code>java.util.Locale</code> resource which is wanted.
     * @param throwExceptionIfMissing <code>true</code> will cause the component to throw a missing resource exception if the
     *        content is missing.
     */
    I18nContent getInternationalContent(Locale locale);

    /**
     * @return <code>true</code> if the component has the international content.
     * @param locale the <code>java.util.Locale</code> which determines the content.
     */
    boolean containsInternationalContent(Locale locale);

    /**
     * @return the name of the component.
     */
    String getName();

    /**
     * @return the i18n file name suffix.
     */
    String getI18nFileNameSuffix();

    /**
     * @return the reader for this component.
     * @param locale the <code>java.util.Locale</code> resource which is wanted.
     */
    I18nReader getReader(Locale locale);

    /**
     * @return the writer for this component.
     * @param locale the <code>java.util.Locale</code> resource which is wanted.
     */
    I18nWriter getWriter(Locale locale);

    /**
     * @return a set containing the <code>java.util.Locale</code>s which are used by the component.
     */
    Set <Locale> getSupportedLocales();
}
