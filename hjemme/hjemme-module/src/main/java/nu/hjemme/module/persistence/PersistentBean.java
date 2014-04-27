package nu.hjemme.module.persistence;

/** @author Tor Egil Jacobsen */
public class PersistentBean {
    private Long id;

    @Override
    public String toString() {
        return "(" + getClass().getSimpleName() + "/" + id + ")";
    }

    protected Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public boolean erIdIkkeNullSamtLikIdPaa(PersistentBean other) {
        return id != null && id.equals(other.getId());
    }
}
