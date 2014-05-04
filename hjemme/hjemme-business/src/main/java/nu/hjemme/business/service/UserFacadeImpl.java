package nu.hjemme.business.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.client.service.UserFacade;

import java.util.HashMap;
import java.util.Map;

import static nu.hjemme.business.dao.UserDao.initJactor;
import static nu.hjemme.business.dao.UserDao.initTip;

/** @author Tor Egil Jacobsen */
public class UserFacadeImpl implements UserFacade {
    private static final Map<UserName, User> STANDARD_USERS = new HashMap<>(2);

    public UserFacadeImpl() {
        initStandardUsers();
    }

    @Override
    public User retrieveBy(UserName userName) {
        if (!STANDARD_USERS.containsKey(userName)) {
            return null;
        }

        return STANDARD_USERS.get(userName);
    }

    private void initStandardUsers() {
        STANDARD_USERS.put(new UserName("jactor"), initJactor());
        STANDARD_USERS.put(new UserName("tip"), initTip());
    }
}
