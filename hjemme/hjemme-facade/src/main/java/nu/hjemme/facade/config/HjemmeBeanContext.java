package nu.hjemme.facade.config;

import nu.hjemme.business.service.MenuFacadeImpl;
import nu.hjemme.business.service.UserFacadeImpl;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.service.MenuFacade;
import nu.hjemme.client.service.UserFacade;
import nu.hjemme.facade.aop.ChosenMenuItemAspect;
import nu.hjemme.persistence.PersistentData;
import nu.hjemme.persistence.dao.UserDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;
import java.util.Locale;

@Configuration
@EnableAspectJAutoProxy
public class HjemmeBeanContext {

    public HjemmeBeanContext() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Bean(name = "hjemme.menuFacade") @SuppressWarnings("unused") // brukes av spring
    public MenuFacade menuFacade(List<Menu> menus) {
        return new MenuFacadeImpl(menus);
    }

    @Bean(name = "hjemme.userFacade") @SuppressWarnings("unused") // brukes av spring
    public UserFacade userFacade(SessionFactory sessionFactory) {
        return new UserFacadeImpl(PersistentData.getInstance().provideDaoFor(UserDao.class, sessionFactory));
    }

    @Bean(name = "hjemme.aop.chosenMenuItems") @SuppressWarnings("unused") // brukes av spring
    public ChosenMenuItemAspect chosenMenuItemAspect() {
        return new ChosenMenuItemAspect();
    }
}
