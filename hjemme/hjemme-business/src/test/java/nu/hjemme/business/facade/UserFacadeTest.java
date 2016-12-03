package nu.hjemme.business.facade;

import nu.hjemme.business.rules.BuildValidations;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.client.dao.UserDao;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static com.github.jactorrises.matcher.LambdaBuildMatcher.verify;
import static nu.hjemme.business.domain.UserDomain.aUser;
import static nu.hjemme.business.rules.BuildValidations.Build.USER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {
    @Rule public BuildValidations buildValidations = BuildValidations.skipValidationOn(USER);

    @InjectMocks
    private UserFacadeImpl testUserFacadeImpl;

    @Mock
    private UserDao userDaoMock;

    @Test
    public void willFindDefauldUser() {
        when(userDaoMock.findUsing(new UserName("jactor"))).thenReturn(Optional.of(aUser().build().getEntity()));
        Optional<User> user = testUserFacadeImpl.findUsing(new UserName("jactor"));
        assertThat(user, verify("jactor", (jactor, matchBuilder) -> matchBuilder.matches(jactor.isPresent(), is(equalTo(true), "isPresent"))));
    }

    @Test
    public void willNotFindUnknownUser() {
        when(userDaoMock.findUsing(new UserName("someone"))).thenReturn(Optional.empty());
        Optional<User> user = testUserFacadeImpl.findUsing(new UserName("someone"));
        assertThat(user, verify("someone", (someone, matchBuilder) -> matchBuilder.matches(someone.isPresent(), is(equalTo(false), "isPresent"))));
    }
}
