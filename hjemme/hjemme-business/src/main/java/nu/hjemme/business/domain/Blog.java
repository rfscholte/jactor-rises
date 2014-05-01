package nu.hjemme.business.domain;

import nu.hjemme.business.domain.base.PersistentBean;
import nu.hjemme.business.persistence.BlogEntity;
import nu.hjemme.client.domain.User;
import org.joda.time.LocalDate;

/** @author Tor Egil Jacobsen */
public class Blog extends PersistentBean<BlogEntity> implements nu.hjemme.client.domain.Blog {

    private User user;

    public Blog(BlogEntity blogEntity) {
        super(blogEntity);
        user = new nu.hjemme.business.domain.User(blogEntity.getUser());
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
    public LocalDate getCreated() {
        return getEntity().getCreated();
    }
}
