package nu.hjemme.business.domain.builder;

import nu.hjemme.persistence.PersistentData;

/** The base builder from which to build valid domains. */
public abstract class DomainBuilder<Domain> {

    abstract protected Domain buildInstance();

    abstract protected void validate();

    public Domain build() {
        validate();

        return buildInstance();
    }

    protected <T> T newInstance(Class<T> persistentClass) {
        return PersistentData.getInstance().provideFor(persistentClass);
    }
}
