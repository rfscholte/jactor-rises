package nu.hjemme.persistence.client.dao;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.client.UserEntity;

public interface UserDao {

    UserEntity findUsing(UserName userName);

    void save(UserEntity userEntity);
}
