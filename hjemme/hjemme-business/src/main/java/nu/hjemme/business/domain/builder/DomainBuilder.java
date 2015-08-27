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

    public static GuestBookEntryDomainBuilder aGuestBookEntry() {
        return new GuestBookEntryDomainBuilder();
    }

    public static GuestBookDomainBuilder aGuestBook() {
        return new GuestBookDomainBuilder();
    }

    public static UserDomainBuilder aUser() {
        return new UserDomainBuilder();
    }

    public static PersonDomainBuilder aPerson() {
        return new PersonDomainBuilder();
    }

    public static AddressDomainBuilder anAddress() {
        return new AddressDomainBuilder();
    }

    public static BlogDomainBuilder aBlog() {
        return new BlogDomainBuilder();
    }

    public static BlogEntryDomainBuilder aBlogEntry() {
        return new BlogEntryDomainBuilder();
    }

    static {
        new BuildValidator();
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
