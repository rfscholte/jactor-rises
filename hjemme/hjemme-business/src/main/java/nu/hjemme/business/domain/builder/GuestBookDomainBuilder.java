package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBookDomain;
import nu.hjemme.persistence.client.GuestBookEntity;
import nu.hjemme.persistence.client.UserEntity;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class GuestBookDomainBuilder extends DomainBuilder<GuestBookDomain> {
    static final String THE_GUEST_BOOK_MUST_BELONG_TO_A_USER = "The guest book must belong to a user";
    static final String THE_TITLE_CANNOT_BE_EMPTY = "The title cannot be empty";

    private GuestBookEntity guestBookEntity = newInstance(GuestBookEntity.class);

    public GuestBookDomainBuilder appendTitle(String title) {
        guestBookEntity.setTitle(title);
        return this;
    }

    public GuestBookDomainBuilder appendUser(UserEntity userEntity) {
        guestBookEntity.setUser(userEntity);
        return this;
    }

    @Override
    protected GuestBookDomain buildInstance() {
        return new GuestBookDomain(guestBookEntity);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(guestBookEntity.getTitle(), THE_TITLE_CANNOT_BE_EMPTY);
        Validate.notNull(guestBookEntity.getUser(), THE_GUEST_BOOK_MUST_BELONG_TO_A_USER);
    }

    public static GuestBookDomainBuilder init() {
        return new GuestBookDomainBuilder();
    }
}
