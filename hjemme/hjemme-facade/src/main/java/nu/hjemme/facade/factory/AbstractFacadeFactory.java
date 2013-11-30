package nu.hjemme.facade.factory;

import org.springframework.beans.factory.FactoryBean;

/** @author Tor Egil Jacobsen */
public abstract class AbstractFacadeFactory<T> implements FactoryBean<T> {

    /** @return the facade being created */
    abstract T getFacade();

    /** @return the object type of the facade */
    abstract Class<T> getFacadeClass();

    @Override
    public T getObject() {
        return getFacade();
    }

    @Override
    public Class<?> getObjectType() {
        return getFacadeClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
