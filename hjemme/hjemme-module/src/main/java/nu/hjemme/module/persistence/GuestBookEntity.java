package nu.hjemme.module.persistence;

import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.module.persistence.base.PersistentBean;
import nu.hjemme.module.persistence.mutable.MutableGuestBook;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static nu.hjemme.module.persistence.meta.GuestBookMetadata.GUEST_BOOK_ID;
import static nu.hjemme.module.persistence.meta.GuestBookMetadata.TITLE;
import static nu.hjemme.module.persistence.meta.GuestBookMetadata.USER;

/** @author Tor Egil Jacobsen */
public class GuestBookEntity extends PersistentBean implements MutableGuestBook {

    @Id
    @Column(name = GUEST_BOOK_ID)
    void setGuestBookId(Long guestBookId) {
        setId(guestBookId);
    }

    @Column(name = TITLE)
    private String title;

    @OneToOne(mappedBy = USER)
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

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getTitle(), that.getTitle())
                .append(getUser(), that.getUser())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getTitle())
                .append(getUser())
                .toHashCode();
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
