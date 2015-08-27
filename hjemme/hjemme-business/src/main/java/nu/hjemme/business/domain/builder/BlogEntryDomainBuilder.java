package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogEntryDomain;
import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.BlogEntryEntity;
import org.apache.commons.lang.Validate;

public class BlogEntryDomainBuilder extends DomainBuilder<BlogEntryDomain> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_BLOG = "The blog entry must belong to a blog";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry field cannot be empty";
    static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private BlogEntryEntity blogEntryEntity = newInstanceOf(BlogEntryEntity.class);

    public BlogEntryDomainBuilder withEntryAs(String entry, String creator) {
        blogEntryEntity.setEntry(entry);
        blogEntryEntity.setCreatorName(creator);
        return this;
    }

    public BlogEntryDomainBuilder with(BlogEntity blogEntity) {
        blogEntryEntity.setBlog(blogEntity);
        return this;
    }

    public BlogEntryDomainBuilder with(BlogDomainBuilder blog) {
        with(blog.get().getEntity());
        return this;
    }

    @Override protected BlogEntryDomain initDomain() {
        return new BlogEntryDomain(blogEntryEntity);
    }

    @Override protected void validate() {
        Validate.notEmpty(blogEntryEntity.getEntry(), THE_ENTRY_CANNOT_BE_EMPTY);
        Validate.notNull(blogEntryEntity.getCreatorName(), THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);
        Validate.notNull(blogEntryEntity.getBlog(), THE_ENTRY_MUST_BELONG_TO_A_BLOG);
    }
}
