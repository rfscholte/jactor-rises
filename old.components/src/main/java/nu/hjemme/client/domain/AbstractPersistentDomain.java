package nu.hjemme.client.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * All persistent domains will have this as a super domain.
 * @author Tor Egil Jacobsen
 */
public abstract class AbstractPersistentDomain extends AbstractDomain implements Serializable {

    private static final long serialVersionUID = -1338095172892650529L;

    private Long id;

    protected AbstractPersistentDomain() {}

    protected AbstractPersistentDomain(AbstractPersistentDomain template) {
        id = template.id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected <Domain extends AbstractPersistentDomain> boolean isEqualTo(Domain domainPersistent) {
        return new EqualsBuilder().append(id, domainPersistent.id).isEquals();
    }

    protected int toHashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    protected String idToString() {
        return "id=" + id;
    }

    public boolean isPersisted() {
        return id != null;
    }
}
