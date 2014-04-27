package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.persistence.GuestBookEntity;
import nu.hjemme.business.persistence.UserEntity;
import nu.hjemme.business.persistence.mutable.MutableGuestBook;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class GuestBookBuilder extends DomainBuilder<GuestBook> {
    static final String THE_GUEST_BOOK_MUST_BELONG_TO_A_USER = "The guest book must belong to a user";
    static final String THE_TITLE_CANNOT_BE_EMPTY = "The title cannot be empty";

    private MutableGuestBook mutableGuestBook = new GuestBookEntity();

    public GuestBookBuilder appendTitle(String title) {
        mutableGuestBook.setTitle(title);
        return this;
    }

    public GuestBookBuilder appendUser(UserEntity userEntity) {
        mutableGuestBook.setUser(userEntity);
        return this;
    }

    @Override
    protected GuestBook buildInstance() {
        return new GuestBook(mutableGuestBook);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(mutableGuestBook.getTitle(), THE_TITLE_CANNOT_BE_EMPTY);
        Validate.notNull(mutableGuestBook.getUser(), THE_GUEST_BOOK_MUST_BELONG_TO_A_USER);
    }

    public static GuestBookBuilder init() {
        return new GuestBookBuilder();
    }
}
