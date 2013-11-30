package nu.hjemme.module.domain;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.module.domain.base.DomainBuilder;
import nu.hjemme.module.persistence.BlogEntity;
import nu.hjemme.module.persistence.BlogEntryEntity;
import nu.hjemme.module.persistence.PersonEntity;
import nu.hjemme.module.persistence.mutable.MutableBlogEntry;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class BlogEntryBuilder extends DomainBuilder<BlogEntry, MutableBlogEntry> {
    static final String THE_ENTRY_MUST_BELONG_TO_A_BLOG = "The blog entry must belong to a blog";
    static final String THE_ENTRY_CANNOT_BE_EMPTY = "The entry field cannot be empty";
    static final String THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE = "The entry must be created by someone";

    private MutableBlogEntry mutableBlogEntry = new BlogEntryEntity();

    public BlogEntryBuilder appendCreatorName(String creatorName) {
        mutableBlogEntry.setCreatorName(new Name(creatorName));
        return this;
    }

    public BlogEntryBuilder appendCreator(PersonEntity creator) {
        mutableBlogEntry.setCreator(creator);
        mutableBlogEntry.setCreatorName(creator.getFirstName());
        return this;
    }

    public BlogEntryBuilder appendEntry(String entry) {
        mutableBlogEntry.setEntry(entry);
        return this;
    }

    public BlogEntryBuilder appendBlog(BlogEntity blogEntity) {
        mutableBlogEntry.setBlogEntity(blogEntity);
        return this;
    }

    @Override
    protected void validateMutableData() {
        validate(mutableBlogEntry);
    }

    @Override
    protected BlogEntry buildInstance() {
        return new BlogEntry(mutableBlogEntry);
    }

    @Override
    protected BlogEntry buildInstance(MutableBlogEntry mutableBlogEntry) {
        return new BlogEntry(mutableBlogEntry);
    }

    @Override
    protected void validate(MutableBlogEntry mutableBlogEntry) {
        Validate.notEmpty(mutableBlogEntry.getEntry(), THE_ENTRY_CANNOT_BE_EMPTY);
        Validate.notNull(mutableBlogEntry.getCreatorName(), THE_ENTRY_MUST_BE_CREATED_BY_SOMEONE);
        Validate.notNull(mutableBlogEntry.getBlog(), THE_ENTRY_MUST_BELONG_TO_A_BLOG);
    }

    public static BlogEntryBuilder init() {
        return new BlogEntryBuilder();
    }
}
