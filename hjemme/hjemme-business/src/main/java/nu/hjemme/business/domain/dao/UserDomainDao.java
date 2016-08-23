package nu.hjemme.business.domain.dao;

import nu.hjemme.business.domain.UserDomain;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.client.dao.UserDao;

public class UserDomainDao {
    private final UserDao userDao;

    public UserDomainDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDomain findUsing(UserName userName) {
        if (userName == null) {
            return null;
        }

        UserEntity entity = userDao.findUsing(userName);

        if (entity == null) {
            return null;
        }

        return new UserDomain(entity);
    }

    public void save(UserDomain userDomain) {
        if (userDomain != null) {
            userDao.save(userDomain.getEntity());
        }
    }
}
