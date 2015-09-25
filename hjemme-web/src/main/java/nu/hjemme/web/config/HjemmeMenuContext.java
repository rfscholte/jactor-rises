package nu.hjemme.web.config;

import nu.hjemme.client.domain.menu.dto.MenuDto;
import nu.hjemme.client.domain.menu.dto.MenuItemDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused") // used by spring
public class HjemmeMenuContext {
    @Bean @SuppressWarnings("unused") // used by spring TODO: real menus for the users...
    public MenuDto createUserMenu() {
        return new MenuDto("userMenu")
                .add(new MenuItemDto("testUser", "bullseye")
                                .leggTilBarn(new MenuItemDto("testChild", "bullseye?some=where"))
                );

    }

    @Bean @SuppressWarnings("unused") // used by spring TODO: real menus for hjemme...
    public MenuDto createHjemmeMenu() {
        return new MenuDto("hjemmeMenu")
                .add(new MenuItemDto("testHjemme", "bullseye")
                                .leggTilBarn(new MenuItemDto("testChild", "bullseye?some=where"))
                );

    }
}
