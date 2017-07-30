package nu.hjemme.facade;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.facade.UserFacade;
import nu.hjemme.facade.config.HjemmeBeanContext;
import nu.hjemme.facade.config.HjemmeDbContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HjemmeBeanContext.class, HjemmeDbContext.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
@Ignore("OutOfMemoryError ???")
public class UserFacadeIntegrationTest {

    @Resource(name = "hjemme.userFacade")
    private UserFacade testUserFacade;

    @Test public void willFetchStandardUser() {
        assertThat(testUserFacade.findUsing(new UserName("tip"))).isNotNull();
    }
}
