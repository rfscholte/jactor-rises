package nu.hjemme.i18n;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import nu.hjemme.api.exception.ExceptionUtil;
import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.api.i18n.I18nWriter;
import nu.hjemme.api.util.DatePattern;
import nu.hjemme.api.util.DateUtil;

import org.apache.log4j.Logger;

/**
 * Writes content to a file.
 * @author Tor Egil Jacobsen
 */
public class I18nWriterImpl implements I18nWriter {

    // Constants
    private static final String EQUAL = "=";
    private static final String DOT = ".";
    private static final String LINEFEED = "\n";

    // The international component to write content for.
    private I18nComponent writeableComponent = null;

    // Variables
    private File writerFolder = null;
    private Date lastWritten = null;
    private Locale locale = null;
    private long sleepTime = 0;
    private boolean invokable = true;

    // Log
    protected final transient Logger log = Logger.getLogger(getClass());

    /**
     * {@inheritDoc}
     */
    public void invoke() {
        if (invokable) {
            writeContent();
        } else if (log.isDebugEnabled()) {
            log.debug("Writer is not inbokable!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInvokable() {
        return invokable;
    }

    /**
     * {@inheritDoc}<br>
     * If invokable is <code>false</code>, the content will be written to ensure the latest additions.
     */
    public void setInvokable(boolean invokable) {
        this.invokable = invokable;

        if (!invokable) {
            writeContent();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getRunnerName() {
        return writeableComponent != null ? writeableComponent.getName() + "(" + locale + ")" : "I18nWriter(" + locale + ")";
    }

    /**
     * {@inheritDoc}
     */
    public void initWriter(Locale locale, I18nComponent internationalComponent) {
        this.locale = locale;
        writeableComponent = internationalComponent;
        validate();
    }

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
    public void writeContent() {
        if (writeableComponent == null) {
            throw new IllegalStateException("The writer is not initialised!");
        }

        I18nContent content = writeableComponent.getInternationalContent(locale);

        if (content == null) {
            throw new IllegalStateException("content can not be null for " + locale);
        }

        if (log.isDebugEnabled()) {
            log.debug("content: " + content + ", locale: " + locale);
        }

        // Ensures that no new content is added while writing...
        synchronized (content) {
            if (lastWritten == null || lastWritten.before(content.getLastModified())) {
                writeContent(content);
                lastWritten = DateUtil.getSystemDate();
                content.clearDynamicallyAlteredKeys();
            }
        }
    }

    protected void write(FileWriter writer, I18nContent content) {
        Set <String> dynamicKeys = content.getDynamicallyAlteredKeys();

        try {
            if (dynamicKeys != null && !dynamicKeys.isEmpty()) {
                Set <String> dynamics = new HashSet <String>(dynamicKeys);

                for (String key : dynamics) {
                    StringBuilder builder = new StringBuilder(key);
                    builder.append(EQUAL);
                    builder.append(content.get(key));
                    builder.append(LINEFEED);
                    writer.append(builder.toString());

                    if (log.isDebugEnabled()) {
                        log.debug("Writing to file: " + builder.toString().trim());
                    }

                    content.clearDynamicallyAlteredKeys();
                }
            }

            writer.close();

        } catch (IOException ioe) {
            throw ExceptionUtil.logErrorMessage(ioe);
        }
    }

    /**
     * @return the folder where i18n content should be written.
     */
    protected File getWriterFolder() {
        return writerFolder;
    }

    /**
     * @return the time the reader shall sleep between readings.
     */
    protected long getSleepTime() {
        return sleepTime;
    }

    private void writeContent(I18nContent content) {
        if (!content.isEmpty()) {
            write(createNewFileWriter(), content);
        }
    }

    private void validate() {
        if (locale == null) {
            throw new IllegalArgumentException("The writer must be given a java.util.Locale!");
        }

        if (writeableComponent == null) {
            throw new IllegalArgumentException("The international component must be instanciated!");
        }
    }

    private FileWriter createNewFileWriter() {
        String timestamp = DatePattern.FILE_TIMESTAMP.format(DateUtil.getSystemDate());
        String suffix = writeableComponent.getI18nFileNameSuffix();
        String fileNameWithoutSuffix = writeableComponent.getI18nFileNameWithoutSuffix(locale);
        File file = new File(getWriterFolder(), fileNameWithoutSuffix + DOT + timestamp + DOT + suffix);

        if (log.isDebugEnabled()) {
            log.debug("Writing locale content to: " + file);
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
        } catch (IOException ioe) {
            throw ExceptionUtil.logErrorMessage(ioe);
        }

        return fileWriter;
    }

    public void setWriterFolder(File writerFolder) {
        this.writerFolder = writerFolder;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
}
