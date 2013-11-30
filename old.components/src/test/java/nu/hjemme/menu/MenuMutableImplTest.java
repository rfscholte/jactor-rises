package nu.hjemme.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import nu.hjemme.api.i18n.I18nContent;
import nu.hjemme.api.i18n.I18nReader;
import nu.hjemme.i18n.menu.MenuItemMutable;
import nu.hjemme.i18n.menu.MenuMutableImpl;

import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The test of the mutable menu.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class MenuMutableImplTest {

    protected transient Logger log = Logger.getLogger(getClass());

    private I18nContent i18nContentMock = null;
    private I18nReader readerMock = null;
    private MenuItemMutable mutableMenuItemMock = null;
    private Mockery mockery = new JUnit4Mockery();
    private MenuMutableImpl mutableMenuImplToTest = null;

    static {
        Locale.setDefault(new Locale("en"));
    }

    @Before
    public void doBefore() {
        i18nContentMock = mockery.mock(I18nContent.class);
        mutableMenuItemMock = mockery.mock(MenuItemMutable.class);
        mutableMenuImplToTest = new MenuMutableTestExtension();
        mutableMenuImplToTest.setName("menuTest");
        readerMock = mockery.mock(I18nReader.class);
        addItem();
    }

    @Test
    public void test() {
        mutableMenuImplToTest.getItems();
        mutableMenuImplToTest.getLastModified();
        mutableMenuImplToTest.getName();
        mutableMenuImplToTest.setFileName(null);
        mutableMenuImplToTest.setLastModified(null);
    }

    @Test
    public void toClone() {
        log.debug("toClone");

        mockery.checking(new Expectations() {
            {
                one(mutableMenuItemMock).clone();
                will(returnValue(mutableMenuItemMock));
            }
        });

        mutableMenuImplToTest = mutableMenuImplToTest.clone();
        assertNotNull("A clone should be returned!", mutableMenuImplToTest);
    }

    @Test
    public void internationalise() {
        log.debug("internationalise");

        mockery.checking(new Expectations() {
            {
                one(mutableMenuItemMock).internationalise(with(aNonNull(I18nContent.class)), with("menuTest"));
                one(readerMock).initReader(with(new Locale("en")), with(aNonNull(MenuMutableImpl.class)));
            }
        });

        Map <Locale, I18nReader> readersByLocale = new HashMap <Locale, I18nReader>();
        readersByLocale.put(new Locale("en"), readerMock);
        mutableMenuImplToTest.setReadersByLocale(readersByLocale);
        mutableMenuImplToTest.init();
        mutableMenuImplToTest.internationalise(new Locale("en"));
    }

    @Test
    public void testFileToMenuFolder() {
        mutableMenuImplToTest.setFileName("test_menu.xml");
        mutableMenuImplToTest.setMenuFolder("test/folder");
        assertEquals("The folder and file name should be joined!", "test/folder/test_menu.xml", mutableMenuImplToTest
            .getFilepath());
        mutableMenuImplToTest.setMenuFolder("test/folder/");
        assertEquals("A file seperator at the end of the folder path should not generate an extra seperator!",
            "test/folder/test_menu.xml", mutableMenuImplToTest.getFilepath());
    }

    private void addItem() {
        List <MenuItemMutable> item = new ArrayList <MenuItemMutable>(1);
        item.add(mutableMenuItemMock);
        mutableMenuImplToTest.setItems(item);
    }

    private class MenuMutableTestExtension extends MenuMutableImpl {

        @Override
        public I18nContent getInternationalContent(Locale locale) {
            log.debug("Getting content for " + locale);
            return i18nContentMock;
        }
    }
}
