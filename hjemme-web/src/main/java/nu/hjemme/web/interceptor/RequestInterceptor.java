package nu.hjemme.web.interceptor;

import nu.hjemme.web.html.WebParameters;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_ACTION;
import static nu.hjemme.web.interceptor.InterceptorValues.ATTRIBUTE_PARAMETERS;

/**
 * A {@link HandlerInterceptorAdapter} that will give the action attribute to the the requested view and enable any of its views to be able to send an html form action to the same
 * request. It will also put the requested parameters on the {@link ModelAndView}.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) {
        modelAndView.addObject(ATTRIBUTE_ACTION, request.getRequestURI());
        modelAndView.addObject(ATTRIBUTE_PARAMETERS, new WebParameters(request).retrieveWebParameters());
    }
}
