package nu.hjemme.facade.config;

import nu.hjemme.business.domain.builder.ProfileBuilder;
import nu.hjemme.business.domain.builder.UserBuilder;
import nu.hjemme.business.domain.persistence.AddressEntity;
import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.domain.User;

/**
 * @author Tor Egil Jacobsen
 */
public class DefaultUsers {
    private static final AddressEntity HOME;
    private static final String JACOBSEN = "Jacobsen";
    private static final User JACTOR;
    private static final User TIP;

    static {
        HOME = new AddressEntity();
        HOME.setAddressLine1("Haganjordet 1");
        HOME.setCity("Rud");
        HOME.setZipCode(1351);
        HOME.setCountry(new Country("NO", "no"));

        JACTOR = UserBuilder.init()
                .appendUserName("jactor")
                .appendPassword("demo")
                .appendProfile(ProfileBuilder.init()
                        .appendDescription("jactor.desc")
                        .appendLastName(JACOBSEN)
                        .appendFirstName("Tor Egil")
                        .appendAddress(HOME)
                        .getValidatedProfileEntity())
                .build();

        TIP = UserBuilder.init()
                .appendUserName("tip")
                .appendPassword("demo")
                .appendProfile(ProfileBuilder.init()
                        .appendDescription("tip.desc")
                        .appendLastName(JACOBSEN)
                        .appendFirstName("Tor Egil")
                        .appendAddress(HOME)
                        .getValidatedProfileEntity())
                .build();
    }

    public static User getJactor() {
        return JACTOR;
    }

    public static User getTip() {
        return TIP;
    }
}
