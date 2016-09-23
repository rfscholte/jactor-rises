package nu.hjemme.web.config;

import com.github.jactorrises.matcher.MatchBuilder;
import com.github.jactorrises.matcher.TypeSafeBuildMatcher;
import nu.hjemme.web.controller.AboutController;
import nu.hjemme.web.controller.HomeController;
import nu.hjemme.web.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HjemmeWebApp.class)
public class HjemmeWebAppIntegrationTest {

    @Resource private HomeController homeController;

    @Resource private AboutController aboutController;

    @Resource private UserController userController;

    @Test
    public void willGetControllers() {
        assertThat(this, new TypeSafeBuildMatcher<HjemmeWebAppIntegrationTest>("Controllers for hjemme-web") {
            @Override public MatchBuilder matches(HjemmeWebAppIntegrationTest typeToTest, MatchBuilder matchBuilder) {
                return matchBuilder
                        .matches(typeToTest.homeController, is(notNullValue(), "HomeController"))
                        .matches(typeToTest.aboutController, is(notNullValue(), "AboutController"))
                        .matches(typeToTest.userController, is(notNullValue(), "UserController"));
            }
        });
    }
}
