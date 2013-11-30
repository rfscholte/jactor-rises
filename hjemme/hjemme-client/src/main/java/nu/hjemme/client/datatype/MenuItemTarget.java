package nu.hjemme.client.datatype;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * A bean representing the target of a menu item
 * @author Tor Egil Jacobsen
 */
public class MenuItemTarget {

    private static final String COMMA = ",";
    private static final String QUESTION_MARK = "?";

    static final String THE_TARGET_CANNOT_BE_EMPTY = "The target cannot be empty";

    private final Set<Parameter> parameters = new HashSet<Parameter>();
    private final String target;

    public MenuItemTarget(String target) {
        Validate.notEmpty(target, THE_TARGET_CANNOT_BE_EMPTY);
        this.target = removeParametersFrom(target);
        parameters.addAll(findParametersWithValuesFrom(target));
    }

    private String removeParametersFrom(String target) {
        int indexQuestion = target.indexOf(QUESTION_MARK);

        if (indexQuestion == -1) {
            return target;
        }

        return target.substring(0, indexQuestion);
    }

    private List<Parameter> findParametersWithValuesFrom(String target) {
        int indexQuestion = target.indexOf(QUESTION_MARK);

        if (indexQuestion == -1) {
            return Collections.emptyList();
        }

        return findParametersFromString(target.substring(indexQuestion + 1));
    }

    private List<Parameter> findParametersFromString(String parametersAsString) {
        int indexComma = parametersAsString.indexOf(',');

        if (indexComma == -1) {
            return asList(new Parameter(parametersAsString));
        }

        return findSeveralParametersFrom(parametersAsString);
    }

    private List<Parameter> findSeveralParametersFrom(String parametersAsString) {
        String[] severalParameters = parametersAsString.split(COMMA);
        List<Parameter> parameterList = new ArrayList<Parameter>(severalParameters.length);

        for (String parameterAndValue : severalParameters) {
            parameterList.add(new Parameter(parameterAndValue));
        }

        return parameterList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MenuItemTarget menuItemTarget = (MenuItemTarget) obj;

        return new EqualsBuilder()
                .append(target, menuItemTarget.target)
                .append(parameters, menuItemTarget.parameters)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(target).append(parameters).toHashCode();
    }

    @Override
    public String toString() {
        return target + parametersAsString();
    }

    private String parametersAsString() {
        StringBuilder params = new StringBuilder(parameters.isEmpty() ? "" : QUESTION_MARK);

        for (Parameter parameter : parameters) {
            params.append(parameter.toString());
        }

        return params.toString();
    }

    public String getTarget() {
        return target;
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }
}
