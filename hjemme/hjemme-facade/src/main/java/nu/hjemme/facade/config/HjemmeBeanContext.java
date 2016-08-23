package nu.hjemme.facade.config;

import nu.hjemme.business.domain.UserDomain;
import nu.hjemme.business.domain.dao.UserDomainDao;
import nu.hjemme.business.facade.UserFacadeImpl;
import nu.hjemme.client.facade.UserFacade;
import nu.hjemme.persistence.PersistentData;
import nu.hjemme.persistence.client.dao.UserDao;
import nu.hjemme.persistence.dao.DefaultUserDao;
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
        return new UserFacadeImpl(PersistentData.getInstance().provideInstanceFor(UserDao.class, sessionFactory));
    }

    @Bean(name = "hjemme.userDao") public UserDao userDao(SessionFactory sessionFactory) {
        return new DefaultUserDao(sessionFactory);
    }

    @Bean(name = "hjemme.domains.aware.db") public UserDomainDao domainConfig(UserDao userDao) {
        UserDomainDao userDomainDao = new UserDomainDao(userDao);
        UserDomain.setUserDomainDao(userDomainDao);

        return userDomainDao;
    }
}
