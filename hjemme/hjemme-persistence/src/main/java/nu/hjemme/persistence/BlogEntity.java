package nu.hjemme.persistence;

import nu.hjemme.client.domain.Blog;

/**
 * @author Tor Egil Jacobsen
 */
public interface BlogEntity extends Blog {

    void setTitle(String title);

    void setUserEntity(UserEntity userEntity);

    @Override
    UserEntity getUser();
}
