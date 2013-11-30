package nu.hjemme.web.interceptor;

import nu.hjemme.web.html.WebParameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_ACTION;
import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_PARAMETERS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
/** @author Tor Egil Jacobsen */
public class RequestInterceptorTest {
    private RequestInterceptor testRequestInterceptor = new RequestInterceptor();

    @Mock
    private HttpServletRequest mockedRequest;

    @Test
    public void willSetTheActionAttributeOnTheModel() throws Exception {
        when(mockedRequest.getRequestURI()).thenReturn("home.do");

        ModelAndView modelAndView = new ModelAndView();
        testRequestInterceptor.postHandle(mockedRequest, null, null, modelAndView);

        assertThat("Action", modelAndView.getModel().get(ATTRIBUTE_ACTION), is(equalTo((Object) "home.do")));
    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void willSetTheParametersAttributeOnTheModel() throws Exception {
        Map<Object, Object> parameterMap = new HashMap<Object, Object>();
        parameterMap.put("some", new String[]{"parameter"});

        when(mockedRequest.getParameterMap()).thenReturn(parameterMap);

        ModelAndView modelAndView = new ModelAndView();
        testRequestInterceptor.postHandle(mockedRequest, null, null, modelAndView);

        List<WebParameter> webParams = (List<WebParameter>) modelAndView.getModel().get(ATTRIBUTE_PARAMETERS);

        assertThat("WebParams", webParams.size(), is(equalTo(1)));
        assertThat("WebParameter.name", webParams.get(0).getName(), is(equalTo("some")));
        assertThat("WebParameter.value", webParams.get(0).getValue(), is(equalTo("parameter")));
    }
}
