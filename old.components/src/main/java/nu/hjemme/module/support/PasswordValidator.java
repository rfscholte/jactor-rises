package nu.hjemme.module.support;

import nu.hjemme.client.support.PasswordRequest;
import nu.hjemme.client.support.PasswordResponse;

import org.apache.commons.lang.Validate;

/**
 * Representation of the password requirements of www.hjemme.nu.
 * @author Tor Egil Jacobsen
 */
public class PasswordValidator extends CharacterValidator {

    private static final String LENGTH = "length";

    /**
     * @param request
     * @return {@link PasswordValidationResult} with the result of the validation against the current requirement.
     */
    public PasswordResponse validate(PasswordRequest request) {
        Validate.notNull(request, "The PasswordRequest can not be null!");
        PasswordResponse response = new PasswordResponse(request);
        boolean isValidSize = validateSize(response);

        if (isValidSize) {
            validatePatternClasses(response);
        }

        return response;
    }

    private boolean validateSize(PasswordResponse response) {
        boolean isInvalid = response.getPassword() == null || isSmallerThanMinimumSize(response.getPassword());

        if (isInvalid) {
            response.addErrorMessage(getErrorPrefix() + LENGTH);
            response.append(false);
        } else {
            response.append(true);
        }

        return !isInvalid;
    }

    private void validatePatternClasses(PasswordResponse response) {
        for (String key : mandatoryPatterns.keySet()) {
            validate(key, response);
        }
    }

    private void validate(String key, PasswordResponse response) {
        boolean containsPattern = contains(compiledPatterns.get(key), response.getPassword());

        if (!containsPattern) {
            response.addErrorMessage(createPropertyMessage(key));
            response.append(false);
        } else {
            response.append(true);
        }
    }
}
