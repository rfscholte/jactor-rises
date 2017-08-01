package nu.hjemme.web.interceptor;

import nu.hjemme.web.menu.MenuFacade;
import nu.hjemme.web.menu.MenuTargetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_MAIN_ITEMS;
import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_PERSON_ITEMS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("A MenuInterceptor")
public class MenuInterceptorTest {

    @Mock private MenuFacade menuFacadeMock;

    private MenuInterceptor menuInterceptorToTest;

    @BeforeEach void initMenuInterceptorForTesting() {
        MockitoAnnotations.initMocks(this);
        menuInterceptorToTest = new MenuInterceptor();
        menuInterceptorToTest.setMenuFacade(menuFacadeMock);
    }

    @DisplayName("should intercept http requests and use the menu facade according to request")
    @Test void whenHandlingHttpRequestTheAwareMenuItemsAreGathered() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        HttpServletRequest mockedHttpServletRequest = mock(HttpServletRequest.class);

        when(mockedHttpServletRequest.getRequestURI()).thenReturn("uri");
        menuInterceptorToTest.postHandle(mockedHttpServletRequest, null, null, modelAndView);

        verify(menuFacadeMock, times(2)).fetchMenuItemBy(any(MenuTargetRequest.class));

        assertThat(modelAndView.getModel().get(ATTRIBUTE_MAIN_ITEMS)).as("The aware main items should be present").isNotNull();
        assertThat(modelAndView.getModel().get(ATTRIBUTE_PERSON_ITEMS)).as("The aware persons items should be present").isNotNull();
    }
}
