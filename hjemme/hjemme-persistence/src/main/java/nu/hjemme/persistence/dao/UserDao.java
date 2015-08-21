package nu.hjemme.persistence.dao;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.persistence.UserEntity;

public interface UserDao {

    UserEntity findUsing(UserName userName);

    void save(UserEntity userEntity);
}
