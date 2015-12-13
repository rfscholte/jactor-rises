package nu.hjemme.web.config;

import nu.hjemme.web.menu.DefaultMenuFacade;
import nu.hjemme.web.menu.Menu;
import nu.hjemme.web.menu.MenuFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static nu.hjemme.web.menu.Menu.aMenu;
import static nu.hjemme.web.menu.MenuItem.aMenuItem;

@Configuration
public class HjemmeWebContext {

    @Bean(name = "hjemme.web.menuFacade") public MenuFacade menuFacade() {
        return new DefaultMenuFacade(mainMenu(), personMenu());
    }

    private Menu mainMenu() {
        return aMenu()
                .withName("main")
                .add(
                        aMenuItem().withName("menu.main.home").withDescription("menu.main.home.desc").withTarget("home.do")
                                .add(aMenuItem().withName("menu.main.jactor").withDescription("menu.main.jactor.desc").withTarget("home.do?choose=jactor"))
                                .add(aMenuItem().withName("menu.main.tip").withDescription("menu.main.tip.desc").withTarget("home.do?chooose=tip"))
                ).build();
    }

    private Menu personMenu() {
        return aMenu()
                .withName("person")
                .add(aMenuItem().withName("menu.person.about").withDescription("menu.person.about.desc").withTarget("about.do"))
                .build();
    }
}
