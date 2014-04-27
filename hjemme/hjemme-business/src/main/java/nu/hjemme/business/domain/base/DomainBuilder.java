package nu.hjemme.business.domain.base;

/** The base builder from which to build valid domains. */
public abstract class DomainBuilder<Domain> {

    abstract protected Domain buildInstance();

    abstract protected void validate();

    public Domain build() {
        validate();

        return buildInstance();
    }
}
