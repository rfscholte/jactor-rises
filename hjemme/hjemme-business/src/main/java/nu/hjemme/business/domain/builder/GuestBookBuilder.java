package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.GuestBook;
import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.domain.persistence.GuestBookEntity;
import nu.hjemme.business.domain.persistence.UserEntity;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class GuestBookBuilder extends DomainBuilder<GuestBook> {
    static final String THE_GUEST_BOOK_MUST_BELONG_TO_A_USER = "The guest book must belong to a user";
    static final String THE_TITLE_CANNOT_BE_EMPTY = "The title cannot be empty";

    private GuestBookEntity guestBookEntity = new GuestBookEntity();

    public GuestBookBuilder appendTitle(String title) {
        guestBookEntity.setTitle(title);
        return this;
    }

    public GuestBookBuilder appendUser(UserEntity userEntity) {
        guestBookEntity.setUser(userEntity);
        return this;
    }

    @Override
    protected GuestBook buildInstance() {
        return new GuestBook(guestBookEntity);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(guestBookEntity.getTitle(), THE_TITLE_CANNOT_BE_EMPTY);
        Validate.notNull(guestBookEntity.getUser(), THE_GUEST_BOOK_MUST_BELONG_TO_A_USER);
    }

    public static GuestBookBuilder init() {
        return new GuestBookBuilder();
    }
}
