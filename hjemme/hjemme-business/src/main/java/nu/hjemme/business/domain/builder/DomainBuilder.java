package nu.hjemme.business.domain.builder;

import nu.hjemme.persistence.PersistentData;

/** The base builder from which to get valid domains. */
public abstract class DomainBuilder<Domain> {
    static BuildValidator buildValidator;

    abstract protected Domain initDomain();

    abstract protected void validate();

    public Domain get() {
        buildValidator.validate(this);

        return initDomain();
    }

    protected <T> T newInstanceOf(Class<T> persistentClass) {
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
