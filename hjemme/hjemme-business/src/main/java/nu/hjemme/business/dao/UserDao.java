package nu.hjemme.business.dao;

import nu.hjemme.business.domain.ProfileBuilder;
import nu.hjemme.business.domain.UserBuilder;
import nu.hjemme.business.persistence.AddressEntity;
import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.domain.User;

/** @author Tor Egil Jacobsen */
public final class UserDao {
    private static final AddressEntity HOME;

    static {
        HOME = new AddressEntity();
        HOME.setAddressLine1("Haganjordet 1");
        HOME.setCity("Rud");
        HOME.setZipCode(1351);
        HOME.setCountry(new Country("NO", "no"));
    }

    private static final String DEMONSTRATION_USER = "only a demonstration user";
    private static final String JACOBSEN = "Jacobsen";

    private UserDao() {
    }

    public static User initJactor() {
        return UserBuilder.init()
                .appendUserName("jactor")
                .appendPassword(DEMONSTRATION_USER)
                .appendProfile(ProfileBuilder.init()
                        .appendDescription("jactor.desc")
                        .appendLastName(JACOBSEN)
                        .appendFirstName("Tor Egil")
                        .appendAddress(HOME)
                        .retrieveMutableProfile())
                .build();
    }

    public static User initTip() {
        return UserBuilder.init()
                .appendUserName("tip")
                .appendPassword(DEMONSTRATION_USER)
                .appendProfile(ProfileBuilder.init()
                        .appendDescription("tip.desc")
                        .appendLastName(JACOBSEN)
                        .appendFirstName("Tor Egil")
                        .appendAddress(HOME)
                        .retrieveMutableProfile())
                .build();
    }
}
