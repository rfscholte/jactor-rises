package nu.hjemme.business.domain;

import nu.hjemme.client.domain.Persistent;
import org.apache.commons.lang.Validate;

public abstract class PersistentDomain<Entity extends Persistent<Id>, Id> implements Persistent<Id> {
    static final String THE_ENTITY_ON_THE_DOMAIN_CANNOT_BE_NULL = "The Entity on the domain cannot be null!";

    private final Entity entity;

    protected PersistentDomain(Entity entity) {
        Validate.notNull(entity, THE_ENTITY_ON_THE_DOMAIN_CANNOT_BE_NULL);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override public Id getId() {
        return entity.getId();
    }
}
