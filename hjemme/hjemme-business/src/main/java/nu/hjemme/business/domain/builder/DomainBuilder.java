package nu.hjemme.business.domain.builder;

import nu.hjemme.persistence.PersistentData;

import java.util.HashSet;
import java.util.Set;

/** The base builder from which to get valid domains. */
public abstract class DomainBuilder<Domain> {
    private static final Set BUILDS_NOT_TO_VALIDATE = new HashSet<>();

    abstract protected Domain initDomain();

    abstract protected void validate();

    public Domain get() {
        if (!BUILDS_NOT_TO_VALIDATE.contains(this.getClass())) {
            validate();
        }

        return initDomain();
    }

    protected <T> T newInstance(Class<T> persistentClass) {
        return PersistentData.getInstance().provideFor(persistentClass);
    }

    public static UserDomainBuilder aUser() {
        return new UserDomainBuilder();
    }

    public static ProfileDomainBuilder aProfile() {
        return new ProfileDomainBuilder();
    }

    public static AddressDomainBuilder anAddress() {
        return new AddressDomainBuilder();
    }

    @SuppressWarnings("unchecked") static void addSkippedValidationOn(Build... builds) {
        if (builds != null) {
            for (Build build : builds) {
                BUILDS_NOT_TO_VALIDATE.add(build.builderClass);
            }
        }
    }

    static void clearBuildsNotToValidate() {
        BUILDS_NOT_TO_VALIDATE.clear();
    }

    public enum Build {
        ADDRESS(AddressDomainBuilder.class),
        PROFILE(ProfileDomainBuilder.class);


        final Class builderClass;

        Build(Class builderClass) {
            this.builderClass = builderClass;
        }
    }
}
