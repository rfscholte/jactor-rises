package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.Blog;
import nu.hjemme.business.domain.base.DomainBuilder;
import nu.hjemme.business.domain.persistence.BlogEntity;
import nu.hjemme.business.domain.persistence.UserEntity;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class BlogBuilder extends DomainBuilder<Blog> {
    static final String THE_BLOG_MUST_BELONG_TO_A_USER = "The blog must belong to a user";
    static final String THE_BLOG_MUST_HAVE_A_TITLE = "The blog must have a title";

    private BlogEntity blogEntity = new BlogEntity();

    public BlogBuilder appendTitle(String title) {
        blogEntity.setTitle(title);
        return this;
    }

    public BlogBuilder appendUser(UserEntity userEntity) {
        blogEntity.setUserEntity(userEntity);
        return this;
    }

    @Override
    protected Blog buildInstance() {
        return new Blog(blogEntity);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(blogEntity.getTitle(), THE_BLOG_MUST_HAVE_A_TITLE);
        Validate.notNull(blogEntity.getUser(), THE_BLOG_MUST_BELONG_TO_A_USER);
    }

    public static BlogBuilder init() {
        return new BlogBuilder();
    }
}
