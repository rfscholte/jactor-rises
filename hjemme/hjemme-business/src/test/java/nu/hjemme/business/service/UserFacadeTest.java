package nu.hjemme.business.service;

import nu.hjemme.business.rules.BuildValidations;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.dao.UserDao;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static nu.hjemme.business.domain.UserDomain.aUser;
import static nu.hjemme.business.rules.BuildValidations.Build.USER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {
    @Rule public BuildValidations buildValidations = BuildValidations.skipValidationOn(USER);

    @InjectMocks
    private UserFacadeImpl testUserFacadeImpl;

    @Mock
    private UserDao userDaoMock;

    @Before
    public void mockDefaultUser() {
        when(userDaoMock.findUsing(new UserName("jactor"))).thenReturn(aUser().get().getEntity());
    }

    @Test
    public void willFindDefauldUser() {
        User user = testUserFacadeImpl.findUsing(new UserName("jactor"));
        assertThat("Standard user", user, is(notNullValue()));
    }

    @Test
    public void willNotFindUnknownUser() {
        User user = testUserFacadeImpl.findUsing(new UserName("someone"));
        assertThat("UserImpl", user, is(nullValue()));
    }
}
