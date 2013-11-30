package nu.hjemme.facade.factory;

import nu.hjemme.client.UserFacade;
import nu.hjemme.module.UserFacadeImpl;

/**
 * A factory bean to initiate the {@link nu.hjemme.client.UserFacade} as a singleton using the springframework.
 * @author Tor Egil Jacobsen
 */
public class UserFacadeFactory extends AbstractFacadeFactory<UserFacade> {
    @Override
    UserFacade getFacade() {
        return new UserFacadeImpl();
    }

    @Override
    Class<UserFacade> getFacadeClass() {
        return UserFacade.class;
    }
}
