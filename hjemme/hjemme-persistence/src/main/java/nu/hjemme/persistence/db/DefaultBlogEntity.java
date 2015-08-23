package nu.hjemme.persistence.db;

import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.base.DefaultPersistentEntity;
import nu.hjemme.persistence.meta.BlogMetadata;
import nu.hjemme.persistence.time.Now;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class DefaultBlogEntity extends DefaultPersistentEntity implements BlogEntity {

    @Column(name = BlogMetadata.CREATED) private LocalDateTime created;
    @Column(name = BlogMetadata.TITLE) private String title;
    @ManyToOne @Column(name = BlogMetadata.USER) private UserEntity userEntity;

    public DefaultBlogEntity() {
        created = Now.asDateTime();
    }

    public DefaultBlogEntity(DefaultBlogEntity blogEntity) {
        created = blogEntity.getCreated();
        title = blogEntity.getTitle();
        userEntity = blogEntity.getUser();
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(getTitle(), ((BlogEntity) o).getTitle()) &&
                Objects.equals(getUser(), ((BlogEntity) o).getUser());
    }

    @Override public int hashCode() {
        return hash(getTitle(), getUser());
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getCreated())
                .append(getTitle())
                .append(getUser())
                .toString();
    }

    @Override public LocalDateTime getCreated() {
        return created;
    }

    @Override public String getTitle() {
        return title;
    }

    @Override public UserEntity getUser() {
        return userEntity;
    }

    @Override public void setTitle(String title) {
        this.title = title;
    }

    @Override public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
