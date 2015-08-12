package nu.hjemme.business.domain;

import nu.hjemme.client.domain.Persistent;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public abstract class PersistentDomain<Entity extends Persistent<T>, T> implements Persistent<T> {
    static final String ENTITETEN_PÅ_DOMENEOBJEKTET_KAN_IKKE_VAERE_NULL = "Entiteten på domeneobjektet kan ikke være null!";

    private final Entity entity;

    protected PersistentDomain(Entity entity) {
        Validate.notNull(entity, ENTITETEN_PÅ_DOMENEOBJEKTET_KAN_IKKE_VAERE_NULL);
        this.entity = entity;
    }

    protected Entity getEntity() {
        return entity;
    }

    @Override
    public T getId() {
        return entity.getId();
    }
}
