package com.github.jactorrises.model.internal;

import com.github.jactorrises.client.facade.UserFacade;
import com.github.jactorrises.model.internal.facade.UserFacadeImpl;
import com.github.jactorrises.model.internal.persistence.client.dao.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import static java.util.Arrays.sort;

@SpringBootApplication
public class JactorModule {

    private static final Logger LOGGER = Logger.getLogger(JactorModule.class);

    @Autowired
    @Bean public UserFacade userFacade(UserDao userDao) {
        return new UserFacadeImpl(userDao);
    }

    public static void main(String... args) {
        display(SpringApplication.run(JactorModule.class, args));
    }

    private static void display(ApplicationContext ctx) {
        LOGGER.debug("Available beans:");

        String[] beanDefinitions = ctx.getBeanDefinitionNames();
        sort(beanDefinitions);

        LOGGER.debug(namesOf(beanDefinitions));
    }

    private static StringBuilder namesOf(String[] beanNames) {
        int count = 0;
        StringBuilder names = new StringBuilder();

        for (String name : beanNames) {
            if (count != 0) {
                names.append(", ");
            }

            count++;

            if (count == 5 || name.contains(".")) {
                names.append("\n-> ");
                count = 0;
            }

            names.append(name);
        }

        return names;
    }
}