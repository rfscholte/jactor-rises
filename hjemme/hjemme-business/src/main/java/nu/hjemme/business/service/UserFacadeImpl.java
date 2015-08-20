package nu.hjemme.business.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.client.service.UserFacade;
import nu.hjemme.persistence.dao.UserDao;

import java.util.Map;

/** @author Tor Egil Jacobsen */
public class UserFacadeImpl implements UserFacade {

    private final UserDao userDao;

    public UserFacadeImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUsing(UserName userName) {
        return userDao.findUsing(userName);
    }
}
