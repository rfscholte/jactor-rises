package nu.hjemme.persistence.domain;

import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.UserEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.hash;
import static nu.hjemme.persistence.meta.BlogMetadata.BLOG_TABLE;
import static nu.hjemme.persistence.meta.BlogMetadata.CREATED;
import static nu.hjemme.persistence.meta.BlogMetadata.TITLE;
import static nu.hjemme.persistence.meta.BlogMetadata.USER;

@Entity
@Table(name = BLOG_TABLE)
public class DefaultBlogEntity extends DefaultPersistentEntity implements BlogEntity {

    @Column(name = CREATED) private String created;
    @Column(name = TITLE) private String title;
    @JoinColumn(name = USER) @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY) private DefaultUserEntity userEntity;

    public DefaultBlogEntity() {
        created = convertFrom(LocalDate.now(), LocalDate.class);
    }

    public DefaultBlogEntity(BlogEntity blogEntity) {
        if (blogEntity != null) {
            created = convertFrom(blogEntity.getCreated(), LocalDate.class);
            title = blogEntity.getTitle();
            userEntity = initializeCopyWith(blogEntity.getUser(), DefaultUserEntity.class);
        }
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(getId(), ((DefaultBlogEntity) o).getId()) &&
                Objects.equals(title, ((DefaultBlogEntity) o).title) &&
                Objects.equals(userEntity, ((DefaultBlogEntity) o).userEntity);
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

    @Override public String getTitle() {
        return title;
    }

    @Override public UserEntity getUser() {
        return userEntity;
    }

    @Override public LocalDate getCreated() {
        return convertTo(created, LocalDate.class);
    }

    @Override public void setTitle(String title) {
        this.title = title;
    }

    @Override public void setUserEntity(UserEntity userEntity) {
        this.userEntity = castOrInitializeCopyWith(userEntity, DefaultUserEntity.class);
    }
}
