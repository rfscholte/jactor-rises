package nu.hjemme.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.I18nContentFileNames;
import nu.hjemme.api.i18n.I18nReader;
import nu.hjemme.api.i18n.I18nWriter;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class I18nComponentImplTest {

    private static final Locale DEFAULT = new Locale("en");
    private static final Locale NORWEGIAN = new Locale("no");

    private I18nContentFileNames i18nContentFileNamesMock = null;
    private I18nComponent componentToTest = null;
    private I18nReader readerMock = null;
    private I18nWriter writerMock = null;
    private Mockery mockery = new JUnit4Mockery();

    static {
        Locale.setDefault(DEFAULT);
    }

    @Before
    public void doBefore() {
        initMocks();
        Map <Locale, I18nReader> readersByLocale = initReadersByLocale();
        Map <Locale, I18nWriter> writersByLocale = initWritersByLocale();
        componentToTest = new I18nComponent();
        componentToTest.setI18nContentFileNames(i18nContentFileNamesMock);
        componentToTest.setReadersByLocale(readersByLocale);
        componentToTest.setWritersByLocale(writersByLocale);
    }

    @Test
    public void initComponent() {
        mockery.checking(new Expectations() {
            {
                one(readerMock).initReader(with(NORWEGIAN), with(aNonNull(I18nComponent.class)));
                one(readerMock).initReader(with(DEFAULT), with(aNonNull(I18nComponent.class)));
                one(writerMock).initWriter(with(NORWEGIAN), with(aNonNull(I18nComponent.class)));
                one(writerMock).initWriter(with(DEFAULT), with(aNonNull(I18nComponent.class)));
            }
        });

        componentToTest.init();
        Set <Locale> supportedLocales = componentToTest.getSupportedLocales();
        assertTrue("The locale is supported!", supportedLocales.contains(new Locale("en")));
        assertTrue("The locale is supported!", supportedLocales.contains(new Locale("no")));
    }

    @Test
    public void testComponent() {
        mockery.checking(new Expectations() {
            {
                one(i18nContentFileNamesMock).getI18nFileName(with(NORWEGIAN));
                will(returnValue("test.properties"));
                one(i18nContentFileNamesMock).getI18nFileNameWithoutSuffix(with(NORWEGIAN));
                will(returnValue("test"));
            }
        });

        assertEquals("The file name!", "test.properties", componentToTest.getI18nFileName(new Locale("no")));
        assertEquals("The file name without suffix!", "test", componentToTest.getI18nFileNameWithoutSuffix(new Locale("no")));
        assertSame("The same memory reference!", readerMock, componentToTest.getReader(new Locale("en")));
        assertSame("The same memory reference!", writerMock, componentToTest.getWriter(new Locale("en")));
    }

    private void initMocks() {
        i18nContentFileNamesMock = mockery.mock(I18nContentFileNames.class);
        writerMock = mockery.mock(I18nWriter.class);
        readerMock = mockery.mock(I18nReader.class);
    }

    private Map <Locale, I18nWriter> initWritersByLocale() {
        Map <Locale, I18nWriter> writersByLocale = new HashMap <Locale, I18nWriter>();
        writersByLocale.put(DEFAULT, writerMock);
        writersByLocale.put(NORWEGIAN, writerMock);
        return writersByLocale;
    }

    private Map <Locale, I18nReader> initReadersByLocale() {
        Map <Locale, I18nReader> readersByLocale = new HashMap <Locale, I18nReader>();
        readersByLocale.put(DEFAULT, readerMock);
        readersByLocale.put(NORWEGIAN, readerMock);
        return readersByLocale;
    }
}
