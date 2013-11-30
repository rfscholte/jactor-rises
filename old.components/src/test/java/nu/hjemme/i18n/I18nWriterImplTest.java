package nu.hjemme.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import nu.hjemme.api.exception.UnknownException;
import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.api.i18n.LanguageBundle;
import nu.hjemme.api.util.DatePattern;

import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A test of the abstract writer.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class I18nWriterImplTest {

    protected transient Logger log = Logger.getLogger(getClass());

    private static final Locale DEFAULT = new Locale("en");
    private static final Locale NORWEGIAN = new Locale("no");
    private static final File WRITER_FOLDER = new File("/resources/svn/jactor-hjemme/hjemme.business/target");

    static {
        Locale.setDefault(DEFAULT);
    }

    private I18nComponent i18nComponentMock = null;
    private LanguageBundle content = null;
    private I18nTestWriter writerToTest = null;
    private Mockery mockery = new JUnit4Mockery();
    private boolean debugPartOfFileName = false;
    private int noFilesValidated = 0;

    @Before
    public void doBefore() {
        i18nComponentMock = mockery.mock(I18nComponent.class);
        writerToTest = new I18nTestWriter();
        content = new LanguageBundle();
        noFilesValidated = 0;
    }

    @Test
    public void invalidInit() {
        log.debug("> invalidInit");

        try {
            writerToTest.initWriter(null, null);
            fail("Null value is not allowed!");
        } catch (IllegalArgumentException iae) {
            log.debug(iae.getClass() + ": " + iae.getMessage());
        }

        try {
            writerToTest.initWriter(DEFAULT, null);
            fail("Null value is not allowed!");
        } catch (IllegalArgumentException iae) {
            log.debug(iae.getClass() + ": " + iae.getMessage());
        }
    }

    @Test(expected = UnknownException.class)
    public void initWriterWithIllegalFilePath() {
        log.debug("> initWriterWithIllegalFilePath");
        writerToTest.initWriter(NORWEGIAN, i18nComponentMock);
        writerToTest.setWriterFolder(new File("/path/does/not/exists"));

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getI18nFileNameSuffix();
                will(returnValue("properties"));
                one(i18nComponentMock).getI18nFileNameWithoutSuffix(NORWEGIAN);
                will(returnValue("test"));
                one(i18nComponentMock).getInternationalContent(with(NORWEGIAN));
                will(returnValue(content));
            }
        });

        content.put("TO", "WRITE");
        writerToTest.invoke();
    }

    @Test(expected = IllegalStateException.class)
    public void illegalContent() {
        log.debug("> illegalContent");
        writerToTest.initWriter(NORWEGIAN, i18nComponentMock);

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getInternationalContent(with(NORWEGIAN));
                will(returnValue(null));
            }
        });

        writerToTest.writeContent();
    }

    @Test(expected = IllegalStateException.class)
    public void illegalStateWhenNotInitialised() {
        try {
            writerToTest.invoke();
            fail("The writer is not initialised!");
        } catch (IllegalStateException ise) {
            log.debug(ise.getClass() + " " + ise.getMessage());
        }

        writerToTest.initWriter(DEFAULT, i18nComponentMock);

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(null));
            }
        });

        writerToTest.invoke();
    }

    @Test
    public void stopWriter() {
        log.debug("> stopWriter");
        writerToTest.initWriter(DEFAULT, i18nComponentMock);
        writerToTest.setWriterFolder(WRITER_FOLDER);

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getI18nFileNameSuffix();
                will(returnValue("properties"));
                one(i18nComponentMock).getI18nFileNameWithoutSuffix(DEFAULT);
                will(returnValue("test"));
                one(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(content));
            }
        });

        content.put("STOP.VALUE", "verdi som skal skrives f¿r stopp!");
        writerToTest.setInvokable(false);
        assertEquals("Writing is done once!", 1, writerToTest.writeContentNo);
    }

    @Test
    public void writeProps() throws IOException, InterruptedException {
        log.debug("> writeProps");
        writerToTest.initWriter(DEFAULT, i18nComponentMock);
        mockery.assertIsSatisfied();

        mockery.checking(new Expectations() {
            {
                exactly(2).of(i18nComponentMock).getI18nFileNameSuffix();
                will(returnValue("properties"));
                exactly(2).of(i18nComponentMock).getI18nFileNameWithoutSuffix(DEFAULT);
                will(returnValue("test"));
                exactly(2).of(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(content));
            }
        });

        writerToTest.setWriterFolder(WRITER_FOLDER);
        Map <String, String> notWriteable = new HashMap <String, String>();
        notWriteable.put("NO.WRITE", "Denne verdien skal ikke skrives til fil...");
        content.putAll(notWriteable);
        content.put("SOME.VALUE.1", "f¿rste test verdi");
        content.put("SOME.VALUE.2", "andre test verdi");
        writerToTest.invoke();
        Thread.sleep(500); // To allow the writing done in a separate thread to finish
        assertEquals("Writing is done once!", 1, writerToTest.writeContentNo);
        content.put("SOME.VALUE.3", "tredje test verdi");
        writerToTest.invoke();
        assertEquals("Writing is done twice!", 2, writerToTest.writeContentNo);
        validateNotWritten(notWriteable.keySet());
    }

    private void validateNotWritten(Set <String> notWrittenKeys) throws IOException {
        File filePath = WRITER_FOLDER;
        String[] fileNames = filePath.list();
        debugPartOfFileName = true;

        for (String fileName : fileNames) {
            validate(fileName, notWrittenKeys);
        }

        assertTrue("There is more than one file deleted!", 1 < noFilesValidated);
    }

    private void validate(String fileName, Set <String> notWrittenKeys) throws IOException {
        String partOfFileName = DatePattern.FILE_DATE.format(new Date());
        String suffix = "properties";

        if (debugPartOfFileName) {
            log.debug("Validating files containing of '" + partOfFileName + "' and with suffix: " + suffix);
            debugPartOfFileName = false;
        }

        if (fileName.contains(partOfFileName) && fileName.contains(suffix)) {
            log.debug("Checking file: " + fileName);
            File file = new File(WRITER_FOLDER, fileName);
            validate(file, notWrittenKeys);
            noFilesValidated++;
            assertTrue("The validated file is deleted!", file.delete());
        } else {
            log.debug("Do not check file: " + fileName);
        }
    }

    private void validate(File file, Set <String> notWrittenKeys) throws IOException {
        Set <Object> writtenKeys = loadProperties(file);

        for (String notWritten : notWrittenKeys) {
            if (writtenKeys.contains(notWritten)) {
                fail("The key is not supposed to be written!");
            }
        }
    }

    private Set <Object> loadProperties(File file) throws IOException {
        FileInputStream inStream = null;
        Properties properties = new Properties();

        try {
            inStream = new FileInputStream(file);
            properties.load(inStream);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }

        return properties.keySet();
    }

    private class I18nTestWriter extends I18nWriterImpl {
        private int writeContentNo = 0;

        @Override
        protected void write(FileWriter writer, I18nContent content) {
            writeContentNo++;
            super.write(writer, content);
        }
    }
}
