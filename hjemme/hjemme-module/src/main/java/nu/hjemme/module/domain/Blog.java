package nu.hjemme.module.domain;

import nu.hjemme.client.domain.User;
import nu.hjemme.module.domain.base.PersistentDomainBean;
import nu.hjemme.module.persistence.mutable.MutableBlog;
import org.joda.time.LocalDate;

/** @author Tor Egil Jacobsen */
public class Blog extends PersistentDomainBean<MutableBlog> implements nu.hjemme.client.domain.Blog {

    private User user;

    public Blog(MutableBlog mutableBlog) {
        super(mutableBlog);
        user = new nu.hjemme.module.domain.User(mutableBlog.getMutableUser());
    }

    @Override
    public String getTitle() {
        return getMutable().getTitle();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public LocalDate getCreated() {
        return getMutable().getCreated();
    }
}
