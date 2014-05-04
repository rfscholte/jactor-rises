package nu.hjemme.facade.factory.config;

import nu.hjemme.business.service.UserFacadeImpl;
import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.domain.User;
import nu.hjemme.client.service.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tor Egil Jacobsen
 */
@Configuration
public class HjemmeAppContext {

/*
    @Bean(name = "hjemme.menuFacade")
    @SuppressWarnings("unused") // brukes av spring
    public MenuFacade menuFacade(List<MenuDto> menus) {
        return new MenuFacadeImpl(menus);
    }
*/

    @Bean(name = "hjemme.userFacade")
    @SuppressWarnings("unused") // brukes av spring
    public UserFacade userFacade() {
        Map<UserName, User> defaultUsers = new HashMap<>();
        defaultUsers.put(DefaultUsers.getJactor().getUserName(), DefaultUsers.getJactor());
        defaultUsers.put(DefaultUsers.getTip().getUserName(), DefaultUsers.getTip());

        return new UserFacadeImpl(defaultUsers);
    }
}
