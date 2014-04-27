package nu.hjemme.business.persistence;

import nu.hjemme.business.persistence.mutable.MutableBlog;
import nu.hjemme.business.persistence.mutable.MutableUser;
import nu.hjemme.client.domain.Blog;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;

import static java.util.Objects.hash;
import static nu.hjemme.business.persistence.meta.BlogMetadata.BLOG_ID;
import static nu.hjemme.business.persistence.meta.BlogMetadata.CREATED;
import static nu.hjemme.business.persistence.meta.BlogMetadata.TITLE;
import static nu.hjemme.business.persistence.meta.BlogMetadata.USER;

/** @author Tor Egil Jacobsen */
public class BlogEntity extends PersistentBean implements MutableBlog {

    @Id
    @Column(name = BLOG_ID)
    // brukes av hibernate
    @SuppressWarnings("unused")
    void setBlogId(Long blogId) {
        setId(blogId);
    }

    // Add persistence type
    @Column(name = CREATED)
    private LocalDate created;

    @Column(name = TITLE)
    private String title;

    @OneToMany(mappedBy = USER)
    private UserEntity userEntity;

    public BlogEntity() {
        created = new LocalDate();
    }

    /** @param blog will be used to create the instance... */
    public BlogEntity(Blog blog) {
        created = blog.getCreated();
        title = blog.getTitle();
        userEntity = blog.getUser() != null ? new UserEntity(blog.getUser()) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlogEntity that = (BlogEntity) o;

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

    @Override
    public LocalDate getCreated() {
        return created;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public UserEntity getUser() {
        return userEntity;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public MutableUser getMutableUser() {
        return userEntity;
    }
}
