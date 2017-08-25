package nu.hjemme.facade.config;

import nu.hjemme.business.facade.UserFacadeImpl;
import nu.hjemme.client.facade.UserFacade;
import nu.hjemme.persistence.client.dao.UserDao;
import nu.hjemme.persistence.facade.PersistentDataService;
import nu.hjemme.persistence.orm.dao.DefaultUserDao;
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

    @Bean(name = "hjemme.userFacade") public UserFacade userFacade(SessionFactory sessionFactory) {
        return new UserFacadeImpl(PersistentDataService.getInstance().provideInstanceFor(UserDao.class, sessionFactory));
    }

    @Bean(name = "hjemme.userDao") public UserDao userDao(SessionFactory sessionFactory) {
        return new DefaultUserDao(sessionFactory);
    }
}
