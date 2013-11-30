package nu.hjemme.module.persistence;

import nu.hjemme.client.domain.AbstractPersistentDomain;
import nu.hjemme.client.domain.User;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

/**
 * An implementation of the user facade.
 * @author Tor Egil Jacobsen
 */

public class UserDao extends AbstractDao {

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public <Domain extends AbstractPersistentDomain> Domain create(Domain user) {
        if (user == null) {
            throw new IllegalArgumentException("To create a null user is not possible!");
        }

        if (user.getId() != null) {
            throw new IllegalArgumentException("A user with an id is allready persisted!");
        }

        return (Domain) getSession().save(new UserDto((User) user));
    }

    /** {@inheritDoc} */
    public <Domain extends AbstractPersistentDomain> boolean delete(Domain user) {
        if (user == null || user.getId() == null) {
            return false;
        }

        delete(user.getId());
        return true;
    }

    /** {@inheritDoc} */
    public <Key> boolean delete(Key key) {
        UserDto userDto = null;

        if (key instanceof String) {
            userDto = retrieveUserDtoBy((String) key);
        } else if (key instanceof Long) {
            userDto = retrieveUserDtoBy((Long) key);
        }

        if (userDto == null) {
            return false;
        }

        getSession().delete(userDto);
        return true;
    }

    private UserDto retrieveUserDtoBy(Long id) {
        Criteria criteria = getSession().createCriteria(UserDto.class) //
            .add(Example.create(UserDto.opprettBuilder() //
                .id(id) //
                .build()));

        Object userDto = criteria.uniqueResult();

        return (UserDto) userDto;
    }

    private UserDto retrieveUserDtoBy(String userName) {
        Criteria criteria = getSession().createCriteria(UserDto.class) //
            .add(Example.create(UserDto.opprettBuilder() //
                .userName(userName) //
                .build()));

        Object userDto = criteria.uniqueResult();

        return (UserDto) userDto;
    }

    /** {@inheritDoc} */
    public <Domain extends AbstractPersistentDomain> void merge(Domain user) {
        if (user == null) {
            throw new IllegalArgumentException("To merge a null user is not possible!");
        }

        if (user.getId() == null) {
            throw new IllegalArgumentException("A user without an id are not persisted!");
        }

        getSession().update(new UserDto((User) user));
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public <Domain extends AbstractPersistentDomain, Key> Domain retrieve(Key key) {

        if (key instanceof String) {
            return (Domain) retrieveUserDtoBy((String) key).getDomain();
        } else if (key instanceof Long) {
            return (Domain) retrieveUserDtoBy((Long) key).getDomain();
        }

        throw new IllegalArgumentException("Unknown type of key!");
    }
}
