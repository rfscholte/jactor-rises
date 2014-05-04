package nu.hjemme.business.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.client.service.UserFacade;

import java.util.Map;

/** @author Tor Egil Jacobsen */
public class UserFacadeImpl implements UserFacade {
    private final Map<UserName, User> defaultUsers;

    public UserFacadeImpl(Map<UserName, User> defaultUsers) {
        this.defaultUsers = defaultUsers;
    }

    @Override
    public User retrieveBy(UserName userName) {
        if (defaultUsers == null || !defaultUsers.containsKey(userName)) {
            return null;
        }

        return defaultUsers.get(userName);
    }
}
