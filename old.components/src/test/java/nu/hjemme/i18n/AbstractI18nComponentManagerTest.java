package nu.hjemme.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Locale;
import java.util.MissingResourceException;

import nu.hjemme.api.i18n.I18nComponent;
import nu.hjemme.api.i18n.I18nContent;

import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The test of the component manager of international content.
 * @author Tor Egil Jacobsen
 */
@RunWith(JMock.class)
public class AbstractI18nComponentManagerTest {

    private static final Locale DEFAULT = new Locale("en");
    private static final Locale NORWEGIAN = new Locale("no");

    protected transient Logger log = Logger.getLogger(getClass());

    static {
        Locale.setDefault(DEFAULT);
    }

    private AbstractI18nComponentManager <I18nComponent> managerToTest = null;
    private I18nComponent i18nComponentMock = null;
    private I18nContent i18nContentMock = null;
    private Mockery mockery = new JUnit4Mockery();

    @Before
    public void doBefore() {
        i18nComponentMock = mockery.mock(I18nComponent.class);
        i18nContentMock = mockery.mock(I18nContent.class);
        managerToTest = initManagerToTest();
    }

    @Test
    public void testGet() {
        testWithNoComponents();
        testWithIllegalComponents();
        setManagedComponents();
        testWithNoContentManaged();
        mockery.assertIsSatisfied();
        testWithDefaultContentManaged();
    }

    @Test
    public void testPut() {
        setManagedComponents();
        putContentNotManaged();
        mockery.assertIsSatisfied();
        testPutContent();
        mockery.assertIsSatisfied();
        testPutWithDefaultContent();
    }

    @Test
    public void testToInitManager() {
        setManagedComponents();

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).init();
            }
        });

        managerToTest.init();
    }

    private void putContentNotManaged() {
        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).containsInternationalContent(with(NORWEGIAN));
                will(returnValue(false));
            }
        });

        try {
            managerToTest.put(NORWEGIAN, "testComponent", null, null);
        } catch (IllegalArgumentException iae) {
            log.debug(iae.getClass() + ": " + iae.getMessage());
        }
    }

    private void testPutWithDefaultContent() {
        managerToTest = initManagerToTest();

        try {
            managerToTest.put(NORWEGIAN, "testComponent", "testContentKey", "the default test content", "the test content");
        } catch (MissingResourceException mre) {
            log.debug(mre.getClass() + ": " + mre.getMessage());
        }

        setManagedComponents();
        mockery.assertIsSatisfied();

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).getInternationalContent(with(NORWEGIAN));
                will(returnValue(i18nContentMock));
                one(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(i18nContentMock));
                one(i18nComponentMock).containsInternationalContent(with(NORWEGIAN));
                will(returnValue(true));
                one(i18nComponentMock).containsInternationalContent(with(DEFAULT));
                will(returnValue(true));
                one(i18nContentMock).put(with("testContentKey"), with("the test content"));
                one(i18nContentMock).put(with("testContentKey"), with("the default test content"));
            }
        });

        managerToTest.put(NORWEGIAN, "testComponent", "testContentKey", "the default test content", "the test content");
    }

    private void testPutContent() {
        managerToTest = initManagerToTest();

        try {
            managerToTest.put(NORWEGIAN, "testComponent", "testContentKey", "the test content");
        } catch (MissingResourceException mre) {
            log.debug(mre.getClass() + ": " + mre.getMessage());
        }

        setManagedComponents();

        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).containsInternationalContent(with(NORWEGIAN));
                will(returnValue(true));
                one(i18nComponentMock).getInternationalContent(with(NORWEGIAN));
                will(returnValue(i18nContentMock));
                one(i18nContentMock).put(with("testContentKey"), with("the test content"));
            }
        });

        managerToTest.put(NORWEGIAN, "testComponent", "testContentKey", "the test content");
    }

    private void testWithDefaultContentManaged() {
        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).containsInternationalContent(with(NORWEGIAN));
                will(returnValue(true));
                one(i18nComponentMock).getInternationalContent(with(NORWEGIAN));
                will(returnValue(i18nContentMock));
                one(i18nContentMock).containsKey("defaultContent");
                will(returnValue(false));
                one(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(i18nContentMock));
                one(i18nContentMock).get(with("defaultContent"));
                will(returnValue("The default content"));
            }
        });

        assertEquals("Content expected!", "The default content", managerToTest
            .get("testComponent", "defaultContent", NORWEGIAN));
    }

    private void testWithNoContentManaged() {
        mockery.checking(new Expectations() {
            {
                one(i18nComponentMock).containsInternationalContent(with(NORWEGIAN));
                will(returnValue(true));
                one(i18nComponentMock).getInternationalContent(with(NORWEGIAN));
                will(returnValue(i18nContentMock));
                one(i18nContentMock).containsKey("noContent");
                will(returnValue(false));
                one(i18nComponentMock).getInternationalContent(with(DEFAULT));
                will(returnValue(i18nContentMock));
                one(i18nContentMock).get(with("noContent"));
                will(returnValue(null));
            }
        });

        try {
            managerToTest.get("testComponent", "noContent", NORWEGIAN);
        } catch (MissingResourceException mre) {
            log.debug(mre.getClass() + ": " + mre.getMessage());
        }
    }

    private void testWithIllegalComponents() {
        managerToTest.get("testNotThere", "testNoException", false, NORWEGIAN);

        try {
            managerToTest.get("testNotThere", "testException", NORWEGIAN);
            fail("An missing resource is expected!");
        } catch (MissingResourceException mre) {
            log.debug(mre.getClass() + ": " + mre.getMessage());
        }
    }

    private void testWithNoComponents() {
        managerToTest.get("testComponent", "testNoException", false, NORWEGIAN);

        try {
            managerToTest.get("testComponent", "testException", NORWEGIAN);
            fail("An missing resource is expected!");
        } catch (MissingResourceException mre) {
            log.debug(mre.getClass() + ": " + mre.getMessage());
        }
    }

    private void setManagedComponents() {
        mockery.checking(new Expectations() {
            {
                exactly(2).of(i18nComponentMock).getName();
                will(returnValue("testComponent"));
            }
        });

        managerToTest.setComponentToManage(i18nComponentMock);
        managerToTest.setComponentToManage(i18nComponentMock);
        mockery.assertIsSatisfied();
    }

    private AbstractI18nComponentManager <I18nComponent> initManagerToTest() {
        return new AbstractI18nComponentManager <I18nComponent>() {};
    }
}
