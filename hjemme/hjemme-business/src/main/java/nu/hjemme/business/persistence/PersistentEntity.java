package nu.hjemme.business.persistence;

import nu.hjemme.client.domain.base.Persistent;

/** @author Tor Egil Jacobsen */
public class PersistentEntity implements Persistent {
    private Long id;

    public boolean erIdIkkeNullSamtLikIdPaa(PersistentEntity other) {
        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "(" + getClass().getSimpleName() + "/" + id + ")";
    }

    @Override
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }
}
