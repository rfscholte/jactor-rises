package com.github.jactor.rises.facade.spring;

import com.github.jactor.rises.facade.rest.DefaultBlogRestService;
import com.github.jactor.rises.facade.rest.DefaultGuestBookRestService;
import com.github.jactor.rises.facade.rest.DefaultUserRestService;
import com.github.jactor.rises.model.service.BlogRestService;
import com.github.jactor.rises.model.service.GuestBookRestService;
import com.github.jactor.rises.model.service.UserRestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JactorRest {

    private static final String HTTP_LOCALHOST = "http://localhost:1099/jactor-persistence-orm";

    @Bean public BlogRestService blogRestService() {
        return new DefaultBlogRestService(new RestTemplate(), HTTP_LOCALHOST);
    }

    @Bean public GuestBookRestService guestBookRestService() {
        return new DefaultGuestBookRestService(new RestTemplate(), HTTP_LOCALHOST);
    }

    @Bean public UserRestService userRestService() {
        return new DefaultUserRestService(new RestTemplate(), HTTP_LOCALHOST);
    }
}
