package nu.hjemme.persistence.dao;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.domain.DefaultUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Restrictions.eq;

public class DefaultUserDao implements UserDao {

    private final SessionFactory sessionFactory;

    public DefaultUserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override public UserEntity findUsing(UserName userName) {
        return (UserEntity) session().createCriteria(DefaultUserEntity.class).add(eq("userName", userName.getName())).uniqueResult();
    }

    @Override public void save(UserEntity userEntity) {
        session().save(userEntity);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
