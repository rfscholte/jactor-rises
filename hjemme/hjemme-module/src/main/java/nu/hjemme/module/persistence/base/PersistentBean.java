package nu.hjemme.module.persistence.base;

import org.apache.commons.lang.builder.HashCodeBuilder;

/** @author Tor Egil Jacobsen */
public abstract class PersistentBean {
    private Object id;

    @Override
    public String toString() {
        return "(" + getClass().getSimpleName() + "/" + id + ")";
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    protected Object getId() {
        return id;
    }

    protected void setId(Object id) {
        this.id = id;
    }
}
