package nu.hjemme.module.domain;

import nu.hjemme.module.domain.base.DomainBuilder;
import nu.hjemme.module.persistence.BlogEntity;
import nu.hjemme.module.persistence.UserEntity;
import nu.hjemme.module.persistence.mutable.MutableBlog;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class BlogBuilder extends DomainBuilder<Blog, MutableBlog> {
    static final String THE_BLOG_MUST_BELONG_TO_A_USER = "The blog must belong to a user";
    static final String THE_BLOG_MUST_HAVE_A_TITLE = "The blog must have a title";

    private MutableBlog mutableBlog = new BlogEntity();

    public BlogBuilder appendTitle(String title) {
        mutableBlog.setTitle(title);
        return this;
    }

    public BlogBuilder appendUser(UserEntity userEntity) {
        mutableBlog.setUserEntity(userEntity);
        return this;
    }

    @Override
    protected Blog buildInstance() {
        return new Blog(mutableBlog);
    }

    @Override
    protected Blog buildInstance(MutableBlog mutableBlog) {
        return new Blog(mutableBlog);
    }

    @Override
    protected void validate(MutableBlog mutableBlog) {
        Validate.notEmpty(mutableBlog.getTitle(), THE_BLOG_MUST_HAVE_A_TITLE);
        Validate.notNull(mutableBlog.getUser(), THE_BLOG_MUST_BELONG_TO_A_USER);
    }

    @Override
    protected void validateMutableData() {
        validate(mutableBlog);
    }

    public static BlogBuilder init() {
        return new BlogBuilder();
    }
}
