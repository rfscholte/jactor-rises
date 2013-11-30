package nu.hjemme.menu;

import java.io.File;
import java.util.ArrayList;

import nu.hjemme.api.exception.UnknownException;
import nu.hjemme.i18n.menu.MenuMutable;
import nu.hjemme.i18n.menu.MenuPopulator;
import nu.hjemme.i18n.menu.MenuPopulatorImplXml;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test of the menu reader.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class MenuPopulatorImplXmlTest {

    private MenuPopulator menuPopulatorToTest = null;
    private Mockery mockery = new JUnit4Mockery();
    private MenuMutable mutableMenuMock = null;

    @Before
    public void doBefore() {
        menuPopulatorToTest = new MenuPopulatorImplXml();
    }

    @Test(expected = IllegalStateException.class)
    public void readNullMenu() {
        menuPopulatorToTest.populateMenu();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void readMenu() {
        setMockedMenu();

        mockery.checking(new Expectations() {
            {
                one(mutableMenuMock).getFilepath();
                will(returnValue(FilePath.VALID.getFilePath()));
                one(mutableMenuMock).setLastModified(with(aNonNull(Long.class)));
                one(mutableMenuMock).setItems(with(aNonNull(ArrayList.class)));
                one(mutableMenuMock).setName(with("menu.main"));
            }
        });

        menuPopulatorToTest.populateMenu();
    }

    @Test(expected = IllegalStateException.class)
    public void readInvalidXML() {
        setMockedMenu();

        mockery.checking(new Expectations() {
            {
                one(mutableMenuMock).getFilepath();
                will(returnValue(FilePath.INVALID_XML.getFilePath()));
                one(mutableMenuMock).setLastModified(with(aNonNull(Long.class)));
            }
        });

        menuPopulatorToTest.populateMenu();
    }

    @Test(expected = UnknownException.class)
    public void readInvalidFile() {
        setMockedMenu();

        mockery.checking(new Expectations() {
            {
                one(mutableMenuMock).getFilepath();
                will(returnValue(FilePath.INVALID_FILE.getFilePath()));
                one(mutableMenuMock).setLastModified(with(aNonNull(Long.class)));
            }
        });

        menuPopulatorToTest.populateMenu();
    }

    @Test(expected = UnknownException.class)
    public void readNoNamedMenu() {
        setMockedMenu();

        mockery.checking(new Expectations() {
            {
                one(mutableMenuMock).getFilepath();
                will(returnValue(FilePath.VALID_NO_NAME.getTestFilePath()));
                one(mutableMenuMock).setLastModified(with(aNonNull(Long.class)));
            }
        });

        menuPopulatorToTest.populateMenu();
    }

    private void setMockedMenu() {
        mutableMenuMock = mockery.mock(MenuMutable.class);
        menuPopulatorToTest.setMenu(mutableMenuMock);
    }

    private enum FilePath {
        INVALID_FILE(null), INVALID_XML("menu.xsd"), VALID("main_menu.xml"), VALID_NO_NAME("test.menu.xml");

        private static final String MAC_PATH = "/resources/svn/jactor-hjemme/hjemme.resources/src/main/resources/";
        private static final String TEST_MAC_PATH = "/resources/svn/jactor-hjemme/hjemme.data.business/src/test/resources/";
        private static final String WINDOWS_ROOT = "/C:/";

        private String name = null;

        private FilePath(String name) {
            this.name = name;
        }

        public String getFilePath() {
            if (new File(WINDOWS_ROOT).exists()) {
                return null;
            }

            return MAC_PATH + name;
        }

        public String getTestFilePath() {
            if (new File(WINDOWS_ROOT).exists()) {
                return null;
            }

            return TEST_MAC_PATH + name;
        }
    }
}
