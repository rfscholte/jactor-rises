package nu.hjemme.module.support;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import nu.hjemme.client.support.PasswordRequest;
import nu.hjemme.client.support.PasswordResponse;

import org.junit.Before;
import org.junit.Test;

/**
 * Test of the password enumeration.
 * @author Tor Egil Jacobsen
 */
public class PasswordValidatorTest {

    private static final String CHARACTERS = "characters";
    private static final String NUMERALS = "numerals";
    private static final String PASSWORD_ERROR = "password.error.";
    private static final String PASSWORD_ERROR_LENGTH = PASSWORD_ERROR + "length";
    private static final String PATTERN_CHARACTER = "[a-zA-Z]";
    private static final String PATTERN_NUMERAL = "[0-9]";

    private PasswordValidator passwordToTest;

    @Before
    public void setUp() {
        passwordToTest = new PasswordValidator();
    }

    @Test(expected = IllegalStateException.class)
    public void willNotInitWhenMandatoryPatternsAreNull() {
        passwordToTest.init();
    }

    @Test(expected = IllegalStateException.class)
    public void willNotInitWhenMandatoryPatternsAreEmpty() {
        passwordToTest.setMandatoryPatterns(new HashMap <String, String>());
        passwordToTest.init();
    }

    @Test()
    public void willInit() {
        passwordToTest.setMandatoryPatterns(mandatoryPatters() //
            .put(NUMERALS, PATTERN_NUMERAL) //
            .map());
        passwordToTest.init();
    }

    private MandatoryPatters mandatoryPatters() {
        return new MandatoryPatters();
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowIllegalArgumentExceptionIfPasswordRequestIsNull() {
        passwordToTest.validate(null);
    }

    @Test
    public void willNotValidatePasswordRequestWhenThePasswordIsNull() {
        passwordToTest.setErrorPrefix(PASSWORD_ERROR);
        PasswordResponse response = passwordToTest.validate(new PasswordRequest(null));

        assertThat("A password with the value null should not be valid!", response.isValid(), is(equalTo(false)));
        String failMessage = response.getErrorMessages() + " should contain an error message for the lenth!";
        assertThat(failMessage, response.getErrorMessages().contains(PASSWORD_ERROR_LENGTH), is(equalTo(true)));
    }

    @Test
    public void willNotValidatePasswordRequestWhenThePasswordIsNotTheRequiredLength() {
        passwordToTest.setRequiredLength(8);
        passwordToTest.setErrorPrefix(PASSWORD_ERROR);
        PasswordResponse response = passwordToTest.validate(new PasswordRequest("invalid"));

        assertThat("A password with the value null should not be valid!", response.isValid(), is(equalTo(false)));
        String failMessage = response.getErrorMessages() + " should contain an error message for the lenth!";
        assertThat(failMessage, response.getErrorMessages().contains(PASSWORD_ERROR_LENGTH), is(equalTo(true)));
    }

    @Test
    public void willNotValidatePasswordWithoutNumerals() {
        passwordToTest.setRequiredLength(6);
        passwordToTest.setErrorPrefix(PASSWORD_ERROR);
        passwordToTest.setMandatoryPatterns( //
            mandatoryPatters().put(NUMERALS, PATTERN_NUMERAL) //
                .put(CHARACTERS, PATTERN_CHARACTER) //
                .map() //
            );
        passwordToTest.init();

        PasswordResponse response = passwordToTest.validate(new PasswordRequest("abcdef"));

        String failMessage = response.getErrorMessages() + " should contain an error message for numerals!";
        assertThat(failMessage, response.getErrorMessages().contains(PASSWORD_ERROR + NUMERALS), is(equalTo(true)));
        assertThat("A password without numerals should not be valid!", response.isValid(), is(equalTo(false)));
    }

    @Test
    public void willNotValidatePasswordWithoutCharacters() {
        passwordToTest.setRequiredLength(6);
        passwordToTest.setErrorPrefix(PASSWORD_ERROR);
        passwordToTest.setMandatoryPatterns( //
            mandatoryPatters().put(NUMERALS, PATTERN_NUMERAL) //
                .put(CHARACTERS, PATTERN_CHARACTER) //
                .map() //
            );
        passwordToTest.init();

        PasswordResponse response = passwordToTest.validate(new PasswordRequest("123456"));

        assertThat("A password without numerals should not be valid!", response.isValid(), is(equalTo(false)));
        String failMessage = response.getErrorMessages() + " should contain an error message for numerals!";
        assertThat(failMessage, response.getErrorMessages().contains(PASSWORD_ERROR + CHARACTERS), is(equalTo(true)));
    }

    @Test
    public void willValidatePasswordWithAllPatternClasses() {
        passwordToTest.setRequiredLength(6);
        passwordToTest.setErrorPrefix(PASSWORD_ERROR);
        passwordToTest.setMandatoryPatterns( //
            mandatoryPatters().put(NUMERALS, PATTERN_NUMERAL) //
                .put(CHARACTERS, PATTERN_CHARACTER) //
                .map() //
            );
        passwordToTest.init();

        PasswordResponse response = passwordToTest.validate(new PasswordRequest("abc123"));

        assertThat(response.getErrorMessages() + " should be empty!", response.getErrorMessages().isEmpty(), is(equalTo(true)));
        assertThat("A password without numerals should not be valid!", response.isValid(), is(equalTo(true)));
    }

    private class MandatoryPatters {
        private Map <String, String> patterns = new HashMap <String, String>();

        public Map <String, String> map() {
            return patterns;
        }

        public MandatoryPatters put(String name, String pattern) {
            patterns.put(name, pattern);
            return this;
        }
    }
}
