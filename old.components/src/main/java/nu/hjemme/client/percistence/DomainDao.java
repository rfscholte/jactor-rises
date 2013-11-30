package nu.hjemme.client.percistence;

import nu.hjemme.client.domain.AbstractPersistentDomain;

/**
 * All data access objects will implement this interface.
 * @author Tor Egil Jacobsen
 */
public interface DomainDao {

    /**
     * @return the domain created
     * @param <Domain> the persistent domain
     * @param create the domain to create
     */
    <Domain extends AbstractPersistentDomain> Domain create(Domain create);

    /**
     * @return <code>true</code> if the domain given is deleted from its persistent state.
     * @param <Domain> the persistent domain
     * @param domain
     */
    <Domain extends AbstractPersistentDomain> boolean delete(Domain domain);

    /**
     * @return <code>true</code> if the domain given is deleted from its persistent state.
     * @param <Key> the unique key (or id) for this domain.
     * @param key the unique key (or id) for this domain.
     */
    <Key> boolean delete(Key key);

    /**
     * @param <Domain> the persistent domain
     * @param domain
     */
    <Domain extends AbstractPersistentDomain> void merge(Domain domain);

    /**
     * @param <Domain> the persistent domain
     * @param <Key> the unique key (or id) for this domain.
     * @param key
     * @return the domain by key/id.
     */
    <Domain extends AbstractPersistentDomain, Key> Domain retrieve(Key key);
}
