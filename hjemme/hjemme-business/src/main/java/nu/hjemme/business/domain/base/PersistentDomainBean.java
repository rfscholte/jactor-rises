package nu.hjemme.business.domain.base;

/** @author Tor Egil Jacobsen */
public class PersistentDomainBean<Entity> {

    private final Entity entity;

    protected PersistentDomainBean(Entity entity) {
        this.entity = entity;
    }

    protected Entity getEntity() {
        return entity;
    }
}
