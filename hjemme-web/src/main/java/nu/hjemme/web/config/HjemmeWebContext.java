package nu.hjemme.web.config;

import nu.hjemme.client.domain.menu.dto.MenuDto;
import nu.hjemme.client.domain.menu.dto.MenuItemDto;
import org.springframework.context.annotation.Bean;

/**
 * @author Tor Egil Jacobsen
 */
@SuppressWarnings("unused") // brukes av spring
public class HjemmeWebContext {

    @Bean(name = "hjemme.mainMenu")
    public MenuDto createMainMenu() {
        return new MenuDto("main")
                .leggTil(createHomeItem())
                .leggTil(createJactorItem())
                .leggTil(createTipItem());
    }

    @Bean(name = "hjemme.profileMenu")
    public MenuDto createProfilMenu() {
        return new MenuDto("profile")
                .leggTil(createAboutItem());
    }

    @Bean(name = "hjemme.homeItem")
    public MenuItemDto createHomeItem() {
        return new MenuItemDto("menu.main.home", "home.do")
                .medBeskrivelseSom("menu.main.home.desc");
    }

    @Bean(name = "hjemme.jactorItem")
    public MenuItemDto createJactorItem() {
        return new MenuItemDto("menu.main.jactor", "user.do?choose=jactor")
                .medBeskrivelseSom("menu.main.jactor.desc");
    }

    @Bean(name = "hjemme.tipItem")
    public MenuItemDto createTipItem() {
        return new MenuItemDto("menu.main.tip", "user.do?choose=tip")
                .medBeskrivelseSom("menu.main.tip.desc");
    }

    @Bean(name = "hjemme.aboutItem")
    public MenuItemDto createAboutItem() {
        return new MenuItemDto("menu.profile.about", "about.do")
                .medBeskrivelseSom("menu.profile.about.desc");
    }
}
