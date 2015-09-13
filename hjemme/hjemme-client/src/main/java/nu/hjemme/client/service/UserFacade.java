package nu.hjemme.client.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;

public interface UserFacade {

    User findUsing(UserName userName);
}
