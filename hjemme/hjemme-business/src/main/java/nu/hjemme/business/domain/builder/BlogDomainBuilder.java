package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogDomain;
import nu.hjemme.persistence.BlogEntity;
import nu.hjemme.persistence.UserEntity;
import org.apache.commons.lang.Validate;

/** @author Tor Egil Jacobsen */
public class BlogDomainBuilder extends DomainBuilder<BlogDomain> {
    static final String THE_BLOG_MUST_BELONG_TO_A_USER = "The blog must belong to a user";
    static final String THE_BLOG_MUST_HAVE_A_TITLE = "The blog must have a title";

    private BlogEntity blogEntity = newInstance(BlogEntity.class);

    public BlogDomainBuilder appendTitle(String title) {
        blogEntity.setTitle(title);
        return this;
    }

    public BlogDomainBuilder appendUser(UserEntity userEntity) {
        blogEntity.setUserEntity(userEntity);
        return this;
    }

    @Override
    protected BlogDomain buildInstance() {
        return new BlogDomain(blogEntity);
    }

    @Override
    protected void validate() {
        Validate.notEmpty(blogEntity.getTitle(), THE_BLOG_MUST_HAVE_A_TITLE);
        Validate.notNull(blogEntity.getUser(), THE_BLOG_MUST_BELONG_TO_A_USER);
    }

    public static BlogDomainBuilder init() {
        return new BlogDomainBuilder();
    }
}
