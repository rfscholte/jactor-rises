package nu.hjemme.web.interceptor;

import nu.hjemme.web.menu.MenuFacade;
import nu.hjemme.web.menu.MenuTargetRequest;
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

@RunWith(MockitoJUnitRunner.class)
public class MenuInterceptorTest {

    @Mock private MenuFacade menuFacadeMock;

    private MenuInterceptor menuInterceptorToTest;

    @Before public void initMenuInterceptorForTesting() {
        menuInterceptorToTest = new MenuInterceptor();
        menuInterceptorToTest.setMenuFacade(menuFacadeMock);
    }

    @Test public void whenHandlingHttpRequestTheAwareMenuItemsAreGathered() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        HttpServletRequest mockedHttpServletRequest = mock(HttpServletRequest.class);

        when(mockedHttpServletRequest.getRequestURI()).thenReturn("uri");
        menuInterceptorToTest.postHandle(mockedHttpServletRequest, null, null, modelAndView);

        verify(menuFacadeMock, times(2)).fetchMenuItemBy(any(MenuTargetRequest.class));

        assertThat("The aware main items should be present", modelAndView.getModel().get(ATTRIBUTE_MAIN_ITEMS), is(notNullValue()));
        assertThat("The aware persons items should be present", modelAndView.getModel().get(ATTRIBUTE_PERSON_ITEMS), is(notNullValue()));
    }
}
