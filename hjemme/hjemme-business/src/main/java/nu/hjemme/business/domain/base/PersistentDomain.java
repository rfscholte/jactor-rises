package nu.hjemme.business.domain.base;

import nu.hjemme.client.domain.base.Persistent;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class PersistentDomain<Entity extends Persistent> implements Persistent {
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
    public Long getId() {
        return entity.getId();
    }
}
