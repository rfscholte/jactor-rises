package nu.hjemme.module;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;

import nu.hjemme.client.support.PasswordRequest;
import nu.hjemme.client.support.PasswordResponse;
import nu.hjemme.module.support.PasswordValidator;

import org.junit.Test;

/**
 * A test of the environment class.
 * @author Tor Egil Jacobsen
 */
public class EnvironmentTest {

    private Environment environmentToTest = new Environment();

    @Test(expected = IllegalStateException.class)
    public void willNotInitializeEnvironemntBecauseTimeZoneIsNotSet() {
        environmentToTest.setDefaultLocale(new Locale("en_gb"));
        environmentToTest.setTimeZone(null);
        environmentToTest.init();
    }

    @Test(expected = IllegalStateException.class)
    public void willNotInitializeEnvironemntBecauseSpesifiedTimeZoneCanNotBeFound() {
        environmentToTest.setDefaultLocale(new Locale("en_gb"));
        environmentToTest.setTimeZone("Oslo");
        environmentToTest.init();
    }

    @Test(expected = IllegalStateException.class)
    public void willNotInitializeEnvironemntBecauseDefaultLocaleIsNotSet() {
        environmentToTest.setDefaultLocale(null);
        environmentToTest.setTimeZone("Europe/Oslo");
        environmentToTest.init();
    }

    @Test
    public void willInitializeEnvironemnt() {
        environmentToTest.setDefaultLocale(new Locale("en_gb"));
        environmentToTest.setTimeZone("Europe/Oslo");
        environmentToTest.init();
    }

    @Test
    public void willValidatePasswords() {
        PasswordValidator passwordValidatorMock = mock(PasswordValidator.class);
        environmentToTest.setPasswordValidator(passwordValidatorMock);

        PasswordRequest request = new PasswordRequest("password");
        when(passwordValidatorMock.validate(request)).thenReturn(new PasswordResponse(request));

        assertThat("Response på validering skal være sendt!", environmentToTest.validate(request), is(notNullValue()));
    }
}
