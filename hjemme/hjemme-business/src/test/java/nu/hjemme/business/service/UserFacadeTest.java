package nu.hjemme.business.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/** @author Tor Egil Jacobsen */
public class UserFacadeTest {
    private UserFacadeImpl testUserFacadeImpl = new UserFacadeImpl();

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
