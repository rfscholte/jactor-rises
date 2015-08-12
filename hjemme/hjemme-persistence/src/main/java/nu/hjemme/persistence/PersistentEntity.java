package nu.hjemme.persistence;

import nu.hjemme.client.domain.Persistent;

/** @author Tor Egil Jacobsen */
public abstract class PersistentEntity<T> implements Persistent<T> {
    private T id;

    public boolean erIdIkkeNullSamtLikIdPaa(PersistentEntity other) {
        return id != null && id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "(" + getClass().getSimpleName() + "/" + id + ")";
    }

    @Override
    public T getId() {
        return id;
    }

    protected void setId(T id) {
        this.id = id;
    }
}
