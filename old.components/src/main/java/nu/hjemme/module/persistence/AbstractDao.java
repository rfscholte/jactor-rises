package nu.hjemme.module.persistence;

import nu.hjemme.client.percistence.DomainDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * A mother class for all data objects
 * @author Tor Egil Jacobsen
 */
public abstract class AbstractDao implements DomainDao {

    private SessionFactory sessionFactory = null;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
