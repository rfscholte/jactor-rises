package nu.hjemme.facade.factory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class UserFacadeFactoryTest {

    private UserFacadeFactory testUserFacadeFactory;

    @Before
    public void initForTesting() {
        testUserFacadeFactory = new UserFacadeFactory();
    }

    @Test
    public void willCreateAnInstance() {
        assertThat("UserFacadeFactory", testUserFacadeFactory.getFacade(), is(notNullValue()));
    }

}
