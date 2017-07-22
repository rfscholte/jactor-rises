package nu.hjemme.business.domain.builder;

import nu.hjemme.persistence.orm.PersistentData;

/**
 * The base builder from which to build valid domains.
 */
public abstract class DomainBuilder<T> {
    private static BuildValidator buildValidator;

    abstract T initDomain();

    abstract void validate();

    public T build() {
        buildValidator.validate(this);

        return initDomain();
    }

    <T> T newInstanceOf(Class<T> persistentClass) {
        return PersistentData.getInstance().provideInstanceFor(persistentClass);
    }

    static {
        new BuildValidator(); // constructor will set the instance on the static BuildValidator field in the domain builder
    }

    public static class BuildValidator {
        public BuildValidator() {
            DomainBuilder.buildValidator = this;
        }

        protected void validate(DomainBuilder<?> domainBuilder) {
            domainBuilder.validate();
        }
    }
}
