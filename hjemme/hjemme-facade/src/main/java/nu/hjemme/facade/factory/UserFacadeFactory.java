package nu.hjemme.facade.factory;

import nu.hjemme.business.facade.UserFacadeImpl;
import nu.hjemme.client.facade.UserFacade;

/**
 * A factory bean to initiate the {@link nu.hjemme.client.facade.UserFacade} as a singleton using the springframework.
 * @author Tor Egil Jacobsen
 */
public class UserFacadeFactory extends AbstractFacadeFactory<UserFacade> {
    private volatile UserFacade userFacade;

    private UserFacade initFacade() {
        if (userFacade == null) {
            userFacade = new UserFacadeImpl();
        }

        return userFacade;
    }

    @Override
    UserFacade getFacade() {
        return initFacade();
    }

    @Override
    Class<UserFacade> getFacadeClass() {
        return UserFacade.class;
    }
}
