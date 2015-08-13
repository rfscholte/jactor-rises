package nu.hjemme.business.domain;

import nu.hjemme.client.domain.Blog;
import nu.hjemme.client.domain.User;
import nu.hjemme.persistence.BlogEntity;

import java.time.LocalDateTime;

/** @author Tor Egil Jacobsen */
public class BlogDomain extends PersistentDomain<BlogEntity, Long> implements Blog {

    private User user;

    public BlogDomain(BlogEntity blogEntity) {
        super(blogEntity);
        user = new UserDomain(blogEntity.getUser());
    }

    @Override
    public String getTitle() {
        return getEntity().getTitle();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public LocalDateTime getCreated() {
        return getEntity().getCreated();
    }
}
