package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookDomain;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.UserEntity;
import org.apache.commons.lang.Validate;

public class GuestBookDomainBuilder extends DomainBuilder<GuestBookDomain> {
    static final String THE_GUEST_BOOK_MUST_BELONG_TO_A_USER = "The guest book must belong to a user";
    static final String THE_TITLE_CANNOT_BE_EMPTY = "The title cannot be empty";

    private GuestBookEntity guestBookEntity = newInstance(GuestBookEntity.class);

    public GuestBookDomainBuilder withTitleAs(String title) {
        guestBookEntity.setTitle(title);
        return this;
    }

    public GuestBookDomainBuilder with(UserEntity userEntity) {
        guestBookEntity.setUser(userEntity);
        return this;
    }

    @Override protected GuestBookDomain initDomain() {
        return new GuestBookDomain(guestBookEntity);
    }

    @Override protected void validate() {
        Validate.notEmpty(guestBookEntity.getTitle(), THE_TITLE_CANNOT_BE_EMPTY);
        Validate.notNull(guestBookEntity.getUser(), THE_GUEST_BOOK_MUST_BELONG_TO_A_USER);
    }

    static GuestBookDomainBuilder init() {
        return new GuestBookDomainBuilder();
    }
}
