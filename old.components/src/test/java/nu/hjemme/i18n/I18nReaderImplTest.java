package nu.hjemme.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Locale;

import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.LanguageBundle;

import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test of the reader of international content.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class I18nReaderImplTest {

    protected transient Logger log = Logger.getLogger(getClass());

    private static final Locale DEFAULT = new Locale("en");
    private static final File READER_FOLDER = new File("/resources/svn/jactor-hjemme/hjemme.resources/src/main/resources");

    static {
        Locale.setDefault(DEFAULT);
    }

    private I18nComponent i18nComponentMock = null;
    private LanguageBundle i18nContentInitiableImpl = null;
    private I18nTestReader readerToTest = null;
    private Mockery mockery = new JUnit4Mockery();

    @Before
    public void doBefore() {
        i18nComponentMock = mockery.mock(I18nComponent.class);
        i18nContentInitiableImpl = new LanguageBundle();
        readerToTest = new I18nTestReader();
        readerToTest.setReaderFolder(READER_FOLDER);
    }

    @Test
    public void initAndDestroyReader() {
        log.debug("> initAndDestroyReader");
        readerToTest.initReader(DEFAULT, i18nComponentMock);
        readerToTest.destroy();
    }

    @Test
    public void invokeReader() {
        log.debug("> invokeReader");
        readerToTest.initReader(DEFAULT, i18nComponentMock);

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getI18nFileName(DEFAULT);
                will(returnValue("main_menu.properties"));
                one(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(i18nContentInitiableImpl));
            }
        });

        readerToTest.invoke();
        assertTrue("An international file is read!", !i18nContentInitiableImpl.isEmpty());
        assertTrue("Det skal v¾re lest et internasjonalt innhold for main.beauty.desc!", i18nContentInitiableImpl
            .containsKey("menu.main.beauty.desc"));
    }

    @Test
    public void invokeReaderTwice() {
        log.debug("> invokeReaderTwice");
        readerToTest.initReader(DEFAULT, i18nComponentMock);

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getI18nFileName(DEFAULT);
                will(returnValue("main_menu.properties"));
                one(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(i18nContentInitiableImpl));
            }
        });

        readerToTest.invoke();
        int firstInvoke = i18nContentInitiableImpl.size();
        readerToTest.invoke();
        int secondInvoke = i18nContentInitiableImpl.size();
        assertEquals("The content is not supposed to expand!", firstInvoke, secondInvoke);
        assertEquals("The content is supposed to be read once!", 1, readerToTest.numberOfPropertyLoads);
    }

    @Test
    public void readTwoOfThreeInvoking() {
        log.debug("> readEachInvoking");
        readerToTest.initReader(DEFAULT, i18nComponentMock);

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getI18nFileName(DEFAULT);
                will(returnValue("main_menu.properties"));
                exactly(2).of(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(i18nContentInitiableImpl));
            }
        });

        readerToTest.invoke();
        readerToTest.getFileToRead().setLastModified(readerToTest.getFileToRead().lastModified() + 100000);
        readerToTest.invoke();
        readerToTest.invoke();
        assertEquals("The content is supposed to be read twice!", 2, readerToTest.numberOfPropertyLoads);
        i18nContentInitiableImpl.getKeys();
    }

    @Test(expected = IllegalStateException.class)
    public void illegalState() {
        log.debug("> setNotInvokable");
        readerToTest.initReader(DEFAULT, i18nComponentMock);

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getInternationalContent(DEFAULT);
                will(returnValue(null));
                one(i18nComponentMock).getI18nFileName(DEFAULT);
                will(returnValue("main_menu.properties"));
            }
        });

        readerToTest.invoke();
    }

    @Test
    public void setNotInvokable() {
        log.debug("> setNotInvokable");
        readerToTest.initReader(DEFAULT, i18nComponentMock);

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getName();
                will(returnValue("main"));
            }
        });

        readerToTest.setInvokable(false);
        readerToTest.invoke();
        assertEquals("No reading is supposed to be done!", 0, readerToTest.numberOfPropertyLoads);
    }

    private class I18nTestReader extends I18nReaderImpl {
        protected int numberOfPropertyLoads = 0;

        @Override
        protected long getSleepTime() {
            return 0;
        }

        @Override
        protected void loadProperties() {
            numberOfPropertyLoads++;
            super.loadProperties();
        }
    }
}
