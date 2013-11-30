package nu.hjemme.facade;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import nu.hjemme.client.HjemmeFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    SpringCtx.HJEMME_MODULES, SpringCtx.HJEMME_ENVIRONEMNT, SpringCtx.HJEMME_MENUS
})
public class HjemmeFacadeIntegrationTest {

    @Autowired
    private HjemmeFacade hjemmeFacadeToTest;

    @Test
    public void willInstaniateTheFacadeOfHjemme() {
        assertThat("The hjemme facade should not be null!", hjemmeFacadeToTest, is(notNullValue()));
    }
}
