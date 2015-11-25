package nu.hjemme.facade.config;

import nu.hjemme.business.domain.UserDomain;
import nu.hjemme.business.domain.dao.UserDomainDao;
import nu.hjemme.business.service.DefaultMenuFacade;
import nu.hjemme.business.service.UserFacadeImpl;
import nu.hjemme.client.domain.menu.Menu;
import nu.hjemme.client.service.MenuFacade;
import nu.hjemme.client.service.UserFacade;
import nu.hjemme.facade.aop.MenuItemAspect;
import nu.hjemme.persistence.PersistentData;
import nu.hjemme.persistence.dao.DefaultUserDao;
import nu.hjemme.persistence.dao.UserDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Locale;

@Configuration
@EnableAspectJAutoProxy
public class HjemmeBeanContext {

    public HjemmeBeanContext() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Bean(name = "hjemme.menuFacade") public MenuFacade menuFacade(Menu... menus) {
        return new DefaultMenuFacade(menus);
    }

    @Bean(name = "hjemme.userFacade") public UserFacade userFacade(SessionFactory sessionFactory) {
        return new UserFacadeImpl(PersistentData.getInstance().provideInstanceFor(UserDao.class, sessionFactory));
    }

    @Bean(name = "hjemme.userDao") public UserDao userDao(SessionFactory sessionFactory) {
        return new DefaultUserDao(sessionFactory);
    }

    @Bean(name = "hjemme.aop.menuItems") public MenuItemAspect menuItemAspect() {
        return new MenuItemAspect();
    }

    @Bean(name = "hjemme.domains.aware.db") public UserDomainDao domainConfig(UserDao userDao) {
        UserDomainDao userDomainDao = new UserDomainDao(userDao);
        UserDomain.setUserDomainDao(userDomainDao);

        return userDomainDao;
    }
}
