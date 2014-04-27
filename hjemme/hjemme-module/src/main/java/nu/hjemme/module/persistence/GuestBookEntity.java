package nu.hjemme.module.persistence;

import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.module.persistence.mutable.MutableGuestBook;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;

import static java.util.Objects.hash;
import static nu.hjemme.module.persistence.meta.GuestBookMetadata.GUEST_BOOK_ID;
import static nu.hjemme.module.persistence.meta.GuestBookMetadata.TITLE;
import static nu.hjemme.module.persistence.meta.GuestBookMetadata.USER;

/** @author Tor Egil Jacobsen */
public class GuestBookEntity extends PersistentBean implements MutableGuestBook {

    @Id
    @Column(name = GUEST_BOOK_ID)
    // brukes av hibernate
    @SuppressWarnings("unused")
    void setGuestBookId(Long guestBookId) {
        setId(guestBookId);
    }

    @Column(name = TITLE)
    private String title;

    @OneToMany(mappedBy = USER)
    private UserEntity user;

    public GuestBookEntity() {
    }

    /** @param guestbook will be used to create the instance... */
    public GuestBookEntity(GuestBook guestbook) {
        title = guestbook.getTitle();
        user = guestbook.getUser() != null ? new UserEntity(guestbook.getUser()) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GuestBookEntity that = (GuestBookEntity) o;

        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return hash(getTitle(), getUser());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getTitle())
                .append(getUser())
                .toString();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public UserEntity getUser() {
        return user;
    }

    @Override
   public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setUser(UserEntity user) {
        this.user = user;
    }
}
