package nu.hjemme.web.config;

import nu.hjemme.client.domain.menu.dto.MenuDto;
import nu.hjemme.client.domain.menu.dto.MenuItemDto;
import org.springframework.context.annotation.Bean;

public class HjemmeWebContext {

    @Bean(name = "hjemme.mainMenu") @SuppressWarnings("unused") // used by spring
    public MenuDto createMainMenu() {
        return new MenuDto("main")
                .add(homeItem())
                .add(jactorItem())
                .add(tipItem());
    }

    @Bean(name = "hjemme.personMenu") @SuppressWarnings("unused") // used by spring
    public MenuDto createProfilMenu() {
        return new MenuDto("person")
                .add(aboutItem());
    }

    @Bean(name = "hjemme.homeItem")
    public MenuItemDto homeItem() {
        return new MenuItemDto("menu.main.home", "home.do")
                .withDescriptionAs("menu.main.home.desc");
    }

    @Bean(name = "hjemme.jactorItem")
    public MenuItemDto jactorItem() {
        return new MenuItemDto("menu.main.jactor", "user.do?choose=jactor")
                .withDescriptionAs("menu.main.jactor.desc");
    }

    @Bean(name = "hjemme.tipItem")
    public MenuItemDto tipItem() {
        return new MenuItemDto("menu.main.tip", "user.do?choose=tip")
                .withDescriptionAs("menu.main.tip.desc");
    }

    @Bean(name = "hjemme.aboutItem")
    public MenuItemDto aboutItem() {
        return new MenuItemDto("menu.person.about", "about.do")
                .withDescriptionAs("menu.person.about.desc");
    }
}
