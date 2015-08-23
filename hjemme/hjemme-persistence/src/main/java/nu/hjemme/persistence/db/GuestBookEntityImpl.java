package nu.hjemme.persistence.db;

import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.base.PersistentEntityImpl;
import nu.hjemme.persistence.meta.GuestBookMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class GuestBookEntityImpl extends PersistentEntityImpl implements GuestBookEntity {

    @Column(name = GuestBookMetadata.TITLE) private String title;
    @OneToMany(mappedBy = GuestBookMetadata.USER) private UserEntity user;

    public GuestBookEntityImpl() { }

    /** @param guestbook will be used to create the instance... */
    public GuestBookEntityImpl(GuestBook guestbook) {
        title = guestbook.getTitle();
        user = guestbook.getUser() != null ? new UserEntityImpl(guestbook.getUser()) : null;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GuestBookEntityImpl that = (GuestBookEntityImpl) o;

        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getUser(), that.getUser());
    }

    @Override public int hashCode() {
        return hash(getTitle(), getUser());
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getTitle())
                .append(getUser())
                .toString();
    }

    @Override public String getTitle() {
        return title;
    }

    @Override public User getUser() {
        return user;
    }

    @Override public void setTitle(String title) {
        this.title = title;
    }

    @Override public void setUser(UserEntity user) {
        this.user = user;
    }
}
