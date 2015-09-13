package nu.hjemme.web.html;

import org.apache.commons.lang.Validate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebParameters {
    private static final String LOCALE = "locale";
    private static final String SUBMIT_X = "x";
    private static final String SUBMIT_Y = "y";

    private Map<String, String> parameterMap = new HashMap<>();

    public WebParameters(HttpServletRequest request) {
        Validate.notNull(request, "The request cannot be null!");

        Map parameters = request.getParameterMap();

        for (Object parameterName : request.getParameterMap().keySet()) {
            if (!isLanguageParameter(parameterName) && !isFormSubmit(parameterName)) {
                parameterMap.put(parameterName.toString(), ((String[]) parameters.get(parameterName))[0]);
            }
        }
    }

    private boolean isFormSubmit(Object parameterName) {
        return SUBMIT_X.equals(parameterName) || SUBMIT_Y.equals(parameterName);
    }

    private boolean isLanguageParameter(Object parameterName) {
        return LOCALE.equals(parameterName);
    }

    public List<WebParameter> retrieveWebParameters() {
        List<WebParameter> webParameters = new ArrayList<>(parameterMap.size());

        for (String parameterName : parameterMap.keySet()) {
            webParameters.add(new WebParameter(parameterName, parameterMap.get(parameterName)));
        }

        return webParameters;
    }
}
