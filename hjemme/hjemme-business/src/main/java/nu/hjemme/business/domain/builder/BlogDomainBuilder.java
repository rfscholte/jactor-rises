package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.BlogDomain;
import nu.hjemme.persistence.client.BlogEntity;
import nu.hjemme.persistence.client.UserEntity;
import org.apache.commons.lang.Validate;

public class BlogDomainBuilder extends DomainBuilder<BlogDomain> {
    static final String THE_BLOG_MUST_BELONG_TO_A_USER = "The blog must belong to a user";
    static final String THE_BLOG_MUST_HAVE_A_TITLE = "The blog must have a title";

    private BlogEntity blogEntity = newInstanceOf(BlogEntity.class);

    public BlogDomainBuilder withTitleAs(String title) {
        blogEntity.setTitle(title);
        return this;
    }

    public BlogDomainBuilder with(UserEntity userEntity) {
        blogEntity.setUserEntity(userEntity);
        return this;
    }

    @Override protected BlogDomain initDomain() {
        return new BlogDomain(blogEntity);
    }

    @Override protected void validate() {
        Validate.notEmpty(blogEntity.getTitle(), THE_BLOG_MUST_HAVE_A_TITLE);
        Validate.notNull(blogEntity.getUser(), THE_BLOG_MUST_BELONG_TO_A_USER);
    }
}
