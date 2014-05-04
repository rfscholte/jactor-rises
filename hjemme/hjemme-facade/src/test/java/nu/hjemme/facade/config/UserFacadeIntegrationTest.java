package nu.hjemme.facade.config;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.service.UserFacade;
import nu.hjemme.facade.factory.config.HjemmeAppContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HjemmeAppContext.class, loader = AnnotationConfigContextLoader.class)
public class UserFacadeIntegrationTest {

    @Resource
    UserFacade testUserFacade;

    @Test
    public void willRetrieveStandardUser() {
        assertThat("User", testUserFacade.retrieveBy(new UserName("tip")), is(notNullValue()));
    }
}
