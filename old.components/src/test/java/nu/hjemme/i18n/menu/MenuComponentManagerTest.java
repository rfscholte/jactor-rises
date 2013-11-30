package nu.hjemme.i18n.menu;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test that will check the behaviour of an sub to the abstract component manager...
 * @author Tor Egil Jacobsen
 */
public class MenuComponentManagerTest {

    protected transient Logger log = Logger.getLogger(getClass());

    private MenuComponentManager menuComponentManagerToTest = null;

    @Before
    public void doBefore() {
        menuComponentManagerToTest = new MenuComponentManager();
    }

    @Test
    public void invalid() {
        try {
            menuComponentManagerToTest.setComponentsToManage(null);
        } catch (IllegalArgumentException iae) {
            log.debug(iae.getClass() + ": " + iae.getMessage());
        }

        try {
            menuComponentManagerToTest.setComponentsToManage(new ArrayList <MenuMutable>());
        } catch (IllegalArgumentException iae) {
            log.debug(iae.getClass() + ": " + iae.getMessage());
        }
    }

    @Test
    public void test() {
        List <MenuMutable> componentsToManage = new ArrayList <MenuMutable>();
        componentsToManage.add(new MenuMutableImpl());
        menuComponentManagerToTest.setComponentsToManage(componentsToManage);
    }
}
