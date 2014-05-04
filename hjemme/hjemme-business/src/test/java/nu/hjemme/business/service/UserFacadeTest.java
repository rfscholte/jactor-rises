package nu.hjemme.business.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/** @author Tor Egil Jacobsen */
public class UserFacadeTest {
    private UserFacadeImpl testUserFacadeImpl;

    @Before
    public void initForTesting() {
        Map<UserName, User> defaultUser = new HashMap<>();
        defaultUser.put(new UserName("jactor"), mock(User.class));

        testUserFacadeImpl = new UserFacadeImpl(defaultUser);
    }

    @Test
    public void willGetStandardUser() {
        User user = testUserFacadeImpl.retrieveBy(new UserName("jactor"));
        assertThat("Standard user", user, is(notNullValue()));
    }

    @Test
    public void willNotGetUnknownUser() {
        User user = testUserFacadeImpl.retrieveBy(new UserName("someone"));
        assertThat("UserImpl", user, is(nullValue()));
    }
}
