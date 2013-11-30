package nu.hjemme.module.persistence;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nu.hjemme.client.domain.BlogEntry;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * A blog entry.
 * @author Tor Egil Jacobsen
 */
@Table(name = "BLOG_ENTRY")
public class BlogEntryDto extends DtoPersistent {

    private static final long serialVersionUID = 5282886807884214108L;

    @Column(name = "ENTRY")
    private String entry;

    @Column(name = "FROM")
    private String from;

    @Column(name = "CREATED")
    private DateTime created;

    @ManyToOne
    @Column(name = "BLOG_ID")
    private BlogDto blogDto;

    public BlogEntryDto() {}

    public BlogEntryDto(BlogEntryDto template) {
        super(template);
        entry = template.entry;
        from = template.from;
        created = template.created;
        blogDto = template.blogDto;
    }

    public BlogEntryDto(BlogEntry dto) {
        super(dto);
        entry = dto.getEntry();
        from = dto.getFrom();
        created = dto.getCreated();
        blogDto = new BlogDto(dto.getBlog());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(entry).append(created).append(blogDto).append(from).toHashCode();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof BlogEntryDto )) {
            return false;
        }

        BlogEntryDto blogEntryDomain = (BlogEntryDto) obj;

        return new EqualsBuilder().append(entry, blogEntryDomain.entry).append(created, blogEntryDomain.created).append(blogDto,
            blogEntryDomain.blogDto).append(from, blogEntryDomain.from).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entry", entry).append("created", created).append("blogDto", blogDto).append("from", from)
            .toString();
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public void setBlogDomain(BlogDto blogDomain) {
        this.blogDto = blogDomain;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public BlogEntry getDomain() {
        BlogEntry blogEntry = newInstance(BlogEntry.class);
        blogEntry.setCreated(new DateTime(created));
        blogEntry.setEntry(entry);
        blogEntry.setFrom(from);

        if (blogDto != null) {
            blogEntry.setBlog(blogDto.getDomain());
        }

        return blogEntry;
    }

    public BlogDto getBlogDto() {
        return blogDto;
    }

    public void setBlogDto(BlogDto blogDto) {
        this.blogDto = blogDto;
    }

    public static Builder opprettBuilder() {
        return new Builder();
    }

    public static class Builder {
        private BlogEntryDto blogEntryDto;

        Builder() {
            blogEntryDto = new BlogEntryDto();
        }

        public Builder created(DateTime dateTime) {
            blogEntryDto.created = dateTime;
            return this;
        }

        public Builder from(String from) {
            blogEntryDto.from = from;
            return this;
        }

        public Builder entry(String entry) {
            blogEntryDto.entry = entry;
            return this;
        }

        public Builder blogDto(BlogDto blogDto) {
            blogEntryDto.blogDto = blogDto;
            return this;
        }

        public BlogEntryDto build() {
            return blogEntryDto;
        }
    }
}
