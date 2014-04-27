package nu.hjemme.business.persistence.mutable;

import nu.hjemme.business.persistence.UserEntity;
import nu.hjemme.client.domain.Blog;

/** @author Tor Egil Jacobsen */
public interface MutableBlog extends Blog {
    void setTitle(String title);

    void setUserEntity(UserEntity userEntity);

    MutableUser getMutableUser();
}
