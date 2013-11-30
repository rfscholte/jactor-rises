package nu.hjemme.client.support;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A response after validation of a password.
 * @author Tor Egil Jacobsen
 */
public class PasswordResponse {

    private Set <String> errorMessages = new HashSet <String>();
    private PasswordRequest request;
    private Boolean valid;

    /**
     * Creates a response with a request
     * @param request
     */
    public PasswordResponse(PasswordRequest request) {
        this.request = request;
    }

    /**
     * Appends a {@link PasswordResponse} to this response.
     * @param response
     */
    public void append(PasswordResponse response) {
        request = response.request;
        errorMessages.addAll(response.errorMessages);
        append(response.valid);
    }

    /**
     * Appends the <code>valid</code> value to the response. The <code>valid</code> value will only be appended if it is Already
     * <code>true</code> or not set.
     * @param valid
     */
    public void append(boolean valid) {
        if (this.valid == null || this.valid) {
            this.valid = valid;
        }
    }

    public String getPassword() {
        return request.getPassword();
    }

    public boolean isValid() {
        return valid != null && valid;
    }

    public void addErrorMessage(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    public Set <String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this) //
            .append("password", request != null ? request.getPassword() : null) //
            .append("valid", valid) //
            .append("errorMessages", errorMessages) //
            .toString();
    }
}
