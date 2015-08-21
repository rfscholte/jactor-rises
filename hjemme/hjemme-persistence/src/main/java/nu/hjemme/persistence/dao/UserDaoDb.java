package nu.hjemme.persistence.dao;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.db.UserEntityImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Restrictions.eq;

public class UserDaoDb implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoDb(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override public UserEntity findUsing(UserName userName) {
        return (UserEntity) session().createCriteria(UserEntityImpl.class).add(eq("userName", userName.getName())).uniqueResult();
    }

    @Override public void save(UserEntity userEntity) {
        session().save(userEntity);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
