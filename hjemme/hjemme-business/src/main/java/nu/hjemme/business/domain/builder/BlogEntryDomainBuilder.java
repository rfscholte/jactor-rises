package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogEntryDomain;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.BlogEntryEntity;
import nu.hjemme.persistence.PersonEntity;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class BlogEntryDomainBuilder extends DomainBuilder<BlogEntryDomain> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_BLOG = "The blog entry must belong to a blog";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry field cannot be empty";
    static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private BlogEntryEntity blogEntryEntity = new BlogEntryEntity();

    public BlogEntryDomainBuilder appendCreatorName(String creatorName) {
        blogEntryEntity.setCreatorName(new Name(creatorName));
        return this;
    }

    public BlogEntryDomainBuilder appendCreator(PersonEntity creator) {
        blogEntryEntity.setCreator(creator);
        blogEntryEntity.setCreatorName(creator.getFirstName());
        return this;
    }

    public BlogEntryDomainBuilder appendEntry(String entry) {
        blogEntryEntity.setEntry(entry);
        return this;
    }

    public BlogEntryDomainBuilder appendBlog(BlogEntity blogEntity) {
        blogEntryEntity.setBlogEntity(blogEntity);
        return this;
    }

    @Override
    protected BlogEntryDomain buildInstance() {
        return new BlogEntryDomain(blogEntryEntity);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(blogEntryEntity.getEntry(), THE_ENTRY_CANNOT_BE_EMPTY);
        Validate.notNull(blogEntryEntity.getCreatorName(), THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);
        Validate.notNull(blogEntryEntity.getBlog(), THE_ENTRY_MUST_BELONG_TO_A_BLOG);
    }

    public static BlogEntryDomainBuilder init() {
        return new BlogEntryDomainBuilder();
    }
}
