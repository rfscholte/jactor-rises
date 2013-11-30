package nu.hjemme.facade.factory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class MenuFacadeFactoryTest {

    private MenuFacadeFactory testMenuFacadeFactory;

    @Before
    public void initForTesting() {
        testMenuFacadeFactory = new MenuFacadeFactory();
    }

    @Test
    public void willCreateAnInstance() {
        testMenuFacadeFactory.setMenus(
                new TestMenuDtos()
                        .menuName("menuName")
                        .addItem("itemName", "target?param=value")
                        .retrieveDtoAsList()
        );

        assertThat("MenuFacadeFactory", testMenuFacadeFactory.getFacade(), is(notNullValue()));
    }
}
