package nu.hjemme.business.facade;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.client.facade.UserFacade;
import nu.hjemme.persistence.client.dao.UserDao;

public class UserFacadeImpl implements UserFacade {

    private final UserDao userDao;

    public UserFacadeImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override public User findUsing(UserName userName) {
        return userDao.findUsing(userName);
    }
}
