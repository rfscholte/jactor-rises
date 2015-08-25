package nu.hjemme.web.interceptor;

import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.service.MenuFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_MAIN_ITEMS;
import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_PERSON_ITEMS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** @author Tor Egil Jacobsen */
@RunWith(MockitoJUnitRunner.class)
public class MenuInterceptorTest {

    @Mock
    MenuFacade mockedMenuFacade;

    private MenuInterceptor testMenuInterceptor;

    @Before
    public void setUpTests() {
        testMenuInterceptor = new MenuInterceptor();
        testMenuInterceptor.setMenuFacade(mockedMenuFacade);
    }

    @Test
    public void whenHandlingHttpRequestTheAwareMenuItemsAreGathered() throws Exception {
        HttpServletRequest mockedHttpServletRequest = mock(HttpServletRequest.class);

        when(mockedHttpServletRequest.getRequestURI()).thenReturn("uri");

        ModelAndView modelAndView = new ModelAndView();

        testMenuInterceptor.postHandle(mockedHttpServletRequest, null, null, modelAndView);

        verify(mockedMenuFacade, times(2)).retrieveChosenMenuItemBy(any(MenuTarget.class));

        assertThat("The aware main items should be present", modelAndView.getModel().get(ATTRIBUTE_MAIN_ITEMS), is(notNullValue()));
        assertThat("The aware persons items should be present", modelAndView.getModel().get(ATTRIBUTE_PERSON_ITEMS), is(notNullValue()));
    }
}
