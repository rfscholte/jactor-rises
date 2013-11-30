package nu.hjemme.module.domain.base;

/** The base builder from which to build valid domains. */
public abstract class DomainBuilder<Domain, Mutable> {

    abstract protected Domain buildInstance();

    abstract protected Domain buildInstance(Mutable mutable);

    abstract protected void validate(Mutable mutable);

    abstract protected void validateMutableData();

    public Domain build(Mutable... everyMutable) {

        if (everyMutable == null || everyMutable.length == 0) {
            validateMutableData();

            return buildInstance();
        } else if (everyMutable.length > 1) {
            throw new IllegalArgumentException("Only one mutable may be provided!");
        } else {
            Mutable mutable = everyMutable[0];
            validate(mutable);

            return buildInstance(mutable);
        }
    }
}
