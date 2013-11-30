package nu.hjemme.module.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import nu.hjemme.client.domain.AbstractPersistentDomain;

/**
 * All persistent beans with a generated id will inherent this object.
 * @author Tor Egil Jacobsen
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DtoPersistent implements Serializable {

    private static final long serialVersionUID = 689426410675606496L;

    @Id
    @Column(name = "ID", precision = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public DtoPersistent() {}

    public DtoPersistent(DtoPersistent template) {
        id = template.id;
    }

    public DtoPersistent(AbstractPersistentDomain template) {
        id = template.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected <Dto extends AbstractPersistentDomain> Dto newInstance(Class <Dto> domainClass) {
        Dto dto = null;

        try {
            dto = domainClass.newInstance();
            dto.setId(id);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return dto;
    }

    /**
     * {@inheritDoc}
     * @return every objects must implement the objects hash code
     */
    @Override
    public abstract int hashCode();

    /**
     * {@inheritDoc}
     * @return every objects must implement the objects equals method
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * {@inheritDoc}
     * @return every objects must implement the objects to string method
     */
    @Override
    public abstract String toString();

    /**
     * @return A domain of this implementation.
     */
    public abstract AbstractPersistentDomain getDomain();
}
