package nu.hjemme.persistence.db;

import nu.hjemme.client.domain.GuestBook;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.GuestBookEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.base.DefaultPersistentEntity;
import nu.hjemme.persistence.meta.GuestBookMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Objects;

import static java.util.Objects.hash;

public class DefaultGuestBookEntity extends DefaultPersistentEntity implements GuestBookEntity {

    @Column(name = GuestBookMetadata.TITLE) private String title;
    @OneToMany(mappedBy = GuestBookMetadata.USER) private UserEntity user;

    public DefaultGuestBookEntity() { }

    /** @param guestbook will be used to create the instance... */
    public DefaultGuestBookEntity(GuestBook guestbook) {
        title = guestbook.getTitle();
        user = guestbook.getUser() != null ? new DefaultUserEntity(guestbook.getUser()) : null;
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() != o.getClass() &&
                Objects.equals(title, ((DefaultGuestBookEntity) o).title) && Objects.equals(user, ((DefaultGuestBookEntity) o).user);
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
