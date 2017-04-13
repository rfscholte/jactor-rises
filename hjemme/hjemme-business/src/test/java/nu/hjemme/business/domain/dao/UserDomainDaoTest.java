package nu.hjemme.business.domain.dao;

import nu.hjemme.business.domain.UserDomain;
import nu.hjemme.business.rules.BuildValidations;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.client.dao.UserDao;
import nu.hjemme.persistence.orm.PersistentData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static nu.hjemme.business.domain.UserDomain.aUser;
import static nu.hjemme.business.rules.BuildValidations.Build.USER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDomainDaoTest {
    @InjectMocks private UserDomainDao userDomainDaoToTest;
    @Rule public BuildValidations buildValidations = BuildValidations.skipValidationOn(USER);
    @Mock private UserDao userDaoMock;

    @Test public void willNotSaveNull() {
        userDomainDaoToTest.save(null);
        verify(userDaoMock, never()).save(any());
    }

    @Test public void willSaveNotNull() {
        userDomainDaoToTest.save(aUser().build());
        verify(userDaoMock, times(1)).save(notNull());
    }

    @Test public void willNotFindUserNameWhichIsNull() {
        assertThat(userDomainDaoToTest.findUsing(null), is(equalTo(Optional.empty())));
        verify(userDaoMock, never()).findUsing(any());
    }

    @Test public void willFindUserDomainBasedOnUserName() {
        when(userDaoMock.findUsing(new UserName("someone"))).thenReturn(
                Optional.of(PersistentData.getInstance().provideInstanceFor(UserEntity.class))
        );

        userDomainDaoToTest.findUsing(new UserName("someone"));
        verify(userDaoMock, times(1)).findUsing(new UserName("someone"));
    }

    @Test public void willNotReturnDomainWhenEntityIsNotFound() {
        when(userDaoMock.findUsing(new UserName("someone"))).thenReturn(Optional.empty());
        Optional<UserDomain> userDomain = userDomainDaoToTest.findUsing(new UserName("someone"));

        assertThat("isPresent", userDomain.isPresent(), is(equalTo(false)));
    }

    @Test public void willReturnDomainWhenEntityIsFound() {
        UserEntity userEntity = PersistentData.getInstance().provideInstanceFor(UserEntity.class);
        when(userDaoMock.findUsing(new UserName("someone"))).thenReturn(Optional.of(userEntity));

        Optional<UserDomain> userDomain = userDomainDaoToTest.findUsing(new UserName("someone"));
        assertThat("isPresent", userDomain.isPresent(), is(equalTo(true)));
    }
}
