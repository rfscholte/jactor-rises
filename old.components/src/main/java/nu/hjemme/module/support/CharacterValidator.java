package nu.hjemme.module.support;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class CharacterValidator {

    protected Map <String, Pattern> compiledPatterns = new HashMap <String, Pattern>();
    protected Map <String, String> mandatoryPatterns;
    private Integer requiredLength;
    private String errorPrefix;

    /**
     * Initializes the bean by compiling the patterns provided...
     */
    public void init() {
        if (mandatoryPatterns == null || mandatoryPatterns.size() == 0) {
            throw new IllegalStateException("No patterns provided!");
        }

        for (String key : mandatoryPatterns.keySet()) {
            compiledPatterns.put(key, Pattern.compile(mandatoryPatterns.get(key)));
        }
    }

    protected boolean isSmallerThanMinimumSize(String toValidate) {
        return toValidate.length() < requiredLength;
    }

    protected String getErrorPrefix() {
        if (errorPrefix.endsWith(".")) {
            return errorPrefix;
        }

        return errorPrefix + ".";
    }

    protected boolean contains(Pattern pattern, String charactersToValidate) {
        for (int i = 0; i < charactersToValidate.length(); i++) {
            Character character = charactersToValidate.charAt(i);

            if (pattern.matcher(character.toString()).matches()) {
                return true;
            }
        }

        return false;
    }

    protected String createPropertyMessage(String key) {
        return getErrorPrefix() + key.toLowerCase();
    }

    public void setMandatoryPatterns(Map <String, String> mandatoryPatterns) {
        this.mandatoryPatterns = mandatoryPatterns;
    }

    public void setErrorPrefix(String errorPrefix) {
        this.errorPrefix = errorPrefix;
    }

    public void setRequiredLength(Integer requiredLength) {
        this.requiredLength = requiredLength;
    }

}
