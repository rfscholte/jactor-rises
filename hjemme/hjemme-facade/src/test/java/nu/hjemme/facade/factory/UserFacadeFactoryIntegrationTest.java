package nu.hjemme.facade.factory;

import nu.hjemme.client.datatype.UserName;
import nu.hjemme.client.service.UserFacade;
import nu.hjemme.facade.SpringCtx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {SpringCtx.HJEMME_FACADE_TEST_BEANS})
public class UserFacadeFactoryIntegrationTest {

    @Resource
    UserFacade testUserFacade;

    @Test
    public void willRetrieveStandardUser() {
        assertThat("User", testUserFacade.retrieveBy(new UserName("tip")), is(notNullValue()));
    }
}
