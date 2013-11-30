package nu.hjemme.module.persistence.mutable;

import nu.hjemme.client.domain.Blog;
import nu.hjemme.module.persistence.UserEntity;

/** @author Tor Egil Jacobsen */
public interface MutableBlog extends Blog {
    void setTitle(String title);

    void setUserEntity(UserEntity userEntity);

    MutableUser getMutableUser();
}
