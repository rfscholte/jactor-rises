package nu.hjemme.facade.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.service.UserFacade;
import nu.hjemme.facade.config.HjemmeBeanContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore // todo: wait for profile as an entity
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, MenuFacadeIntegrationTest.HjemmeTestMenus.class}, loader = AnnotationConfigContextLoader.class)
public class UserFacadeIntegrationTest {

    @Resource @SuppressWarnings("unused") // initialized by spring
    private UserFacade testUserFacade;

    @Test public void willRetrieveStandardUser() {
        assertThat(testUserFacade.retrieveBy(new UserName("tip")), is(notNullValue(), "User by UserName"));
    }
}
