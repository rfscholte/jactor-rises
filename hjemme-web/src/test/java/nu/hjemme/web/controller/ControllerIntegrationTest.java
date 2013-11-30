package nu.hjemme.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = SpringCtx.HJEMME_APPLICATION)
public class ControllerIntegrationTest {

    @Resource
    private HomeController homeController;

    @Resource
    private AboutController aboutController;

    @Resource
    private UserController userController;

    @Test
    public void willGetControllers() {
        assertThat("HomeController", homeController, is(notNullValue()));
        assertThat("AboutController", aboutController, is(notNullValue()));
        assertThat("UserController", userController, is(notNullValue()));
    }
}
