package nu.hjemme.module.persistence;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nu.hjemme.client.domain.Blog;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * A users blog.
 * @author Tor Egil Jacobsen
 */
@Table(name = "BLOG")
public class BlogDto extends DtoPersistent {

    private static final long serialVersionUID = 8643754347318688875L;

    @Column(name = "CREATED")
    private DateTime created;

    @Column(name = "HEADER")
    private String header;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @Column(name = "USER_ID")
    private UserDto userDto;

    public BlogDto() {}

    public BlogDto(BlogDto template) {
        super(template);
        created = template.created;
        header = template.header;
        title = template.title;
        userDto = template.userDto;
    }

    public BlogDto(Blog domain) {
        super(domain);
        created = domain.getCreated().toDateTime();
        header = domain.getHeader();
        title = domain.getTitle();
        userDto = new UserDto(domain.getUser());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(created).append(header).append(title).append(userDto).toHashCode();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof BlogDto )) {
            return false;
        }

        BlogDto blogDto = (BlogDto) obj;

        return new EqualsBuilder().append(header, blogDto.header).append(created, blogDto.created).append(title, blogDto.title).append(
            userDto, blogDto.userDto).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("created", created).append("header", header).append("title", title).append("userDto",
            userDto).toString();
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime dateTime) {
        this.created = dateTime;
    }

    @Override
    public Blog getDomain() {
        Blog blog = newInstance(Blog.class);
        blog.setCreated(new DateTime(created));
        blog.setHeader(header);
        blog.setTitle(title);

        if (userDto != null) {
            blog.setUser(userDto.getDomain());
        }

        return blog;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Builder opprettBuilder() {
        return new Builder();
    }

    public static class Builder {
        private BlogDto blogDto;

        Builder() {
            blogDto = new BlogDto();
        }

        public Builder created(DateTime created) {
            blogDto.created = created;
            return this;
        }

        public Builder header(String header) {
            blogDto.header = header;
            return this;
        }

        public Builder user(UserDto userDto) {
            blogDto.userDto = userDto;
            return this;
        }

        public Builder title(String title) {
            blogDto.title = title;
            return this;
        }

        public BlogDto build() {
            return blogDto;
        }
    }
}
