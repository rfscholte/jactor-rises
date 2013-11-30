package nu.hjemme.module.domain.base;

/** @author Tor Egil Jacobsen */
public class PersistentDomainBean<Mutable> {

    private final Mutable mutable;

    protected PersistentDomainBean(Mutable mutable) {
        this.mutable = mutable;
    }

    protected Mutable getMutable() {
        return mutable;
    }
}
