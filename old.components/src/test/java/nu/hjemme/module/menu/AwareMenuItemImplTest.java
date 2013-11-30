package nu.hjemme.module.menu;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nu.hjemme.client.menu.MenuItem;
import nu.hjemme.client.menu.MenuItemTarget;

import org.junit.Test;

public class AwareMenuItemImplTest {

    private AwareMenuItemImpl testAwareMenuItemImpl;

    @Test
    public void willNotGetChildrenWhenNotActive() {
        testAwareMenuItemImpl = new AwareMenuItemImpl( //
            MenuItem.get() //
                .addChild(MenuItem.get().instance()) //
                .targetAsString("anywhere") //
                .itemChoiceKey("right") //
                .instance(), //
            new MenuItemTarget("wrong", "anywhere"));

        assertThat("The item should not be active!", testAwareMenuItemImpl.isActive(), is(equalTo(false)));
        assertThat("The aware item should not get its children!", testAwareMenuItemImpl.getChildren().isEmpty(),
            is(equalTo(true)));
    }

    @Test
    public void willGetChildrenWhenActive() {
        testAwareMenuItemImpl = new AwareMenuItemImpl( //
            MenuItem.get() //
                .addChild(MenuItem.get().instance()) //
                .targetAsString("anywhere") //
                .itemChoiceKey("right") //
                .instance(), //
            new MenuItemTarget("right", "anywhere"));

        assertThat("The item should be active!", testAwareMenuItemImpl.isActive(), is(equalTo(true)));
        assertThat("The aware item should get its children!", testAwareMenuItemImpl.getChildren().size(), is(equalTo(1)));
    }
}
