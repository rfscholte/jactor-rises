package nu.hjemme.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import nu.hjemme.api.exception.ExceptionUtil;
import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.api.i18n.I18nReader;
import nu.hjemme.api.util.DatePattern;

import org.apache.log4j.Logger;

/**
 * The abstract reader of i18n content.
 * @author Tor Egil Jacobsen
 */
public class I18nReaderImpl implements I18nReader {

    // Log
    protected transient Logger logger = Logger.getLogger(getClass());

    // Variables
    private File fileToRead = null;
    private File readerFolder = null;
    private I18nComponent component = null;
    private Locale locale = null;
    private Long lastModified = null;
    private boolean invokable = true;
    private long sleepTime = 0;

    /**
     * {@inheritDoc}
     */
    public void initReader(Locale locale, I18nComponent component) {
        this.locale = locale;
        this.component = component;
        validate();
    }

    private void validate() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getRunnerName();
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void destroy() {
        setInvokable(false);
        component = null;
    }

    // the runner

    /**
     * {@inheritDoc}
     */
    public String getRunnerName() {
        return "Reader:" + component.getName() + "/" + locale;
    }

    /**
     * {@inheritDoc}
     */
    public void invoke() {
        if (invokable) {
            readContent();
        } else {
            logger.warn(getRunnerName() + " is not invokable!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInvokable() {
        return invokable;
    }

    /**
     * {@inheritDoc}
     */
    public void setInvokable(boolean invokable) {
        this.invokable = invokable;
    }

    /**
     * @return the time the reader shall sleep between readings.
     */
    protected long getSleepTime() {
        return sleepTime;
    }

    protected void loadProperties() {
        FileInputStream inStream = null;
        Properties properties = null;

        I18nContent content = component.getInternationalContent(locale);

        if (content == null) {
            throw new IllegalStateException("The component must have a content for " + locale);
        }

        try {
            inStream = new FileInputStream(fileToRead);
            properties = new Properties();
            properties.load(inStream);
        } catch (Exception e) {
            throw ExceptionUtil.logErrorMessage(e);
        } finally {
            try {
                if (properties != null) {
                    content.putAll(transform(properties));
                }

                if (inStream != null) {
                    inStream.close();
                }
            } catch (Exception e) {
                throw ExceptionUtil.logErrorMessage(e);
            }
        }

        if (logger.isDebugEnabled()) {
            int size = content.size();
            logger.debug(size + " international values read for java.util.Locale: " + locale);
        }
    }

    public void readContent() {
        initFileToRead();

        // It is only read if it is not read before or the content has been changed.
        if (lastModified == null || fileToRead.lastModified() != lastModified) {
            if (logger.isDebugEnabled()) {
                if (lastModified == null) {
                    logger.debug("The file has not been read earlier.");
                } else {
                    logger.debug("File was last read: " + DatePattern.DATE_HOUR_SECONDS.format(lastModified));
                }

                logger.debug("Read file: " + fileToRead);
            }

            loadProperties();
            lastModified = fileToRead.lastModified();
        }
    }

    private void initFileToRead() {
        if (fileToRead == null) {
            fileToRead = new File(readerFolder, component.getI18nFileName(locale));
        }
    }

    private Map <String, String> transform(Properties properties) {
        Map <String, String> content = new HashMap <String, String>(properties.size());

        for (Object key : properties.keySet()) {
            content.put(key.toString(), properties.getProperty(key.toString()));
        }

        return content;
    }

    protected File getFileToRead() {
        initFileToRead();
        return fileToRead;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void setReaderFolder(File readerFolder) {
        this.readerFolder = readerFolder;
    }
}
