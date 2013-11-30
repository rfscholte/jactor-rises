package nu.hjemme.i18n;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.api.i18n.I18nContentFileNames;
import nu.hjemme.api.i18n.I18nReader;
import nu.hjemme.api.i18n.I18nWriter;

import org.apache.log4j.Logger;

/**
 * The i18n component.
 * @author Tor Egil Jacobsen
 */
public class I18nComponentImpl implements I18nComponent {

    // Log
    protected transient Logger log = Logger.getLogger(getClass());

    private File readerFolder = null;
    private I18nContentFileNames i18nContentFileNames = null;
    private Map <Locale, I18nContent> contentByLocale = null;
    private Map <Locale, I18nReader> readersByLocale = null;
    private Map <Locale, I18nWriter> writersByLocale = null;
    private String name = null;

    /**
     * {@inheritDoc}
     */
    public void init() {
        initContents();
        initReaders();

        if (writersByLocale != null) {
            initWriters();
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsInternationalContent(Locale locale) {
        return false;
    }

    public String getI18nFileNameSuffix() {
        return i18nContentFileNames.getFileNameSuffix();
    }

    /**
     * {@inheritDoc}
     */
    public String getI18nFileName(Locale locale) {
        return i18nContentFileNames.getI18nFileName(locale);
    }

    /**
     * {@inheritDoc}
     */
    public String getI18nFileNameWithoutSuffix(Locale locale) {
        return i18nContentFileNames.getI18nFileNameWithoutSuffix(locale);
    }

    /**
     * {@inheritDoc}
     */
    public I18nContent getInternationalContent(Locale locale) {
        return contentByLocale.get(locale);
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public I18nReader getReader(Locale locale) {
        return readersByLocale.get(locale);
    }

    /**
     * {@inheritDoc}
     */
    public I18nWriter getWriter(Locale locale) {
        return writersByLocale.get(locale);
    }

    private void initContents() {
        contentByLocale = new HashMap <Locale, I18nContent>(readersByLocale.size());
        Set <Locale> contentToInit = readersByLocale.keySet();

        if (log.isDebugEnabled()) {
            log.debug("Initing bean (hashcode " + hashCode() + ") with content by: " + contentToInit);
        }

        for (Locale locale : contentToInit) {
            I18nContent content = new I18nContentInitiableImpl();
            content.setLocale(locale);
            contentByLocale.put(locale, content);
        }
    }

    private void initWriters() {
        Set <Locale> locales = writersByLocale.keySet();

        for (Locale locale : locales) {
            I18nWriter writer = writersByLocale.get(locale);
            writer.initWriter(locale, this);
        }
    }

    private void initReaders() {
        Set <Locale> lacales = readersByLocale.keySet();

        for (Locale locale : lacales) {
            I18nReader reader = readersByLocale.get(locale);
            reader.initReader(locale, this);
        }
    }

    public File getReaderFolder() {
        return readerFolder;
    }

    public Set <Locale> getSupportedLocales() {
        return readersByLocale.keySet();
    }

    public void setReadersByLocale(Map <Locale, I18nReader> readersByLocale) {
        this.readersByLocale = readersByLocale;
    }

    public void setWritersByLocale(Map <Locale, I18nWriter> writersByLocale) {
        this.writersByLocale = writersByLocale;
    }

    public void setI18nContentFileNames(I18nContentFileNames i18nContentFileNames) {
        this.i18nContentFileNames = i18nContentFileNames;
    }

    public void setReaderFolder(File readerFolder) {
        this.readerFolder = readerFolder;
    }
}
