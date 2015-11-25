package nu.hjemme.client.facade;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;

public interface UserFacade {

    User findUsing(UserName userName);
}
