package nu.hjemme.facade.service;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.service.UserFacade;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, HjemmeDbContext.class, MenuFacadeIntegrationTest.HjemmeTestMenus.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserFacadeIntegrationTest {

    @Resource @SuppressWarnings("unused") // initialized by spring
    private UserFacade testUserFacade;

    @Test public void willfetchStandardUser() {
        assertThat(testUserFacade.findUsing(new UserName("tip")), is(notNullValue(), "User by UserName"));
    }
}
