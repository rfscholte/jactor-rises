package nu.hjemme.persistence.db;

import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.UserEntity;
import nu.hjemme.persistence.meta.BlogMetadata;
import nu.hjemme.persistence.time.Now;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class BlogEntityImpl extends PersistentEntity<Long> implements BlogEntity {

    @Id
    @Column(name = BlogMetadata.BLOG_ID)
    // brukes av hibernate
    @SuppressWarnings("unused")
    void setBlogId(Long blogId) {
        setId(blogId);
    }

    // Add persistence type
    @Column(name = BlogMetadata.CREATED)
    private LocalDateTime created;

    @Column(name = BlogMetadata.TITLE)
    private String title;

    @OneToMany(mappedBy = BlogMetadata.USER)
    private UserEntity userEntity;

    public BlogEntityImpl() {
        created = Now.asDateTime();
    }

    public BlogEntityImpl(BlogEntityImpl blogEntity) {
        created = blogEntity.getCreated();
        title = blogEntity.getTitle();
        userEntity = blogEntity.getUser();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlogEntityImpl that = (BlogEntityImpl) o;

        return Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return hash(getTitle(), getUser());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getCreated())
                .append(getTitle())
                .append(getUser())
                .toString();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getTitle() {
        return title;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
