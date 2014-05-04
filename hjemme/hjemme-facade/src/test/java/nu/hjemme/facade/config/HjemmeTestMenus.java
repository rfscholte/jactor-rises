package nu.hjemme.facade.config;

import nu.hjemme.client.dto.MenuDto;
import nu.hjemme.client.dto.MenuItemDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tor Egil Jacobsen
 */
@Configuration
public class HjemmeTestMenus {
    @Bean
    @SuppressWarnings("unused") // brukes av spring
    public MenuDto createTestMenu() {
        return new MenuDto("testMenu")
                .leggTil(new MenuItemDto("testParent", "bullseye")
                                .leggTilBarn(new MenuItemDto("testChild", "bullseye?some=where"))
                );

    }
}
