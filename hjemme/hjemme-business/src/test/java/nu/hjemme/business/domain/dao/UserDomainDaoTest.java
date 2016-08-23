package nu.hjemme.business.domain.dao;

import nu.hjemme.business.domain.UserDomain;
import nu.hjemme.business.rules.BuildValidations;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.PersistentData;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.client.dao.UserDao;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static nu.hjemme.business.domain.UserDomain.aUser;
import static nu.hjemme.business.rules.BuildValidations.Build.USER;
import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.notNull;
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
        verify(userDaoMock, never()).save(any(UserEntity.class));
    }

    @Test public void willSaveNotNull() {
        userDomainDaoToTest.save(aUser().build());
        verify(userDaoMock, times(1)).save(notNull(UserEntity.class));
    }

    @Test public void willNotFindUserNameWhichIsNull() {
        assertThat(userDomainDaoToTest.findUsing(null), is(nullValue(), "userDomainDao.findUsingUserName"));
        verify(userDaoMock, never()).findUsing(any(UserName.class));
    }

    @Test public void willFindUserDomainBasedOnUserName() {
        userDomainDaoToTest.findUsing(new UserName("someone"));
        verify(userDaoMock, times(1)).findUsing(new UserName("someone"));
    }

    @Test public void willNotReturnDomainWhenEntityIsNotFound() {
        when(userDaoMock.findUsing(new UserName("someone"))).thenReturn(null);
        UserDomain userDomain = userDomainDaoToTest.findUsing(new UserName("someone"));

        assertThat(userDomain, is(nullValue(), "userDomain"));
    }

    @Test public void willReturnDomainWhenEntityIsFound() {
        when(userDaoMock.findUsing(new UserName("someone"))).thenReturn(PersistentData.getInstance().provideInstanceFor(UserEntity.class));
        userDomainDaoToTest.findUsing(new UserName("someone"));
        verify(userDaoMock, times(1)).findUsing(new UserName("someone"));
    }
}
