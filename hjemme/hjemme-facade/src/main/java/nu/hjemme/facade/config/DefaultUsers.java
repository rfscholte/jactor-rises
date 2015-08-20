package nu.hjemme.facade.config;

import nu.hjemme.business.domain.AddressDomain;
import nu.hjemme.client.domain.User;

import static nu.hjemme.business.domain.builder.DomainBuilder.aProfile;
import static nu.hjemme.business.domain.builder.DomainBuilder.aUser;
import static nu.hjemme.business.domain.builder.DomainBuilder.anAddress;

/**
 * @author Tor Egil Jacobsen
 */
public class DefaultUsers {
    private static final AddressDomain HOME;
    private static final String JACOBSEN = "Jacobsen";
    private static final User JACTOR;
    private static final User TIP;

    static {
        HOME = anAddress()
                .withAddressLine1As("Haganjordet 1")
                .withCityAs("Rud")
                .withZipCodeAs(1351)
                .withCountryAs("NO", "no")
                .get();

        JACTOR = aUser()
                .withUserNameAs("jactor")
                .withPasswordAs("demo")
                .with(
                        aProfile()
                                .withDescriptionAs("jactor.desc")
                                .withLastNameAs(JACOBSEN)
                                .withFirstNameAs("Tor Egil")
                                .with(HOME)
                                .get()
                ).get();

        TIP = aUser()
                .withUserNameAs("tip")
                .withPasswordAs("demo")
                .with(
                        aProfile()
                                .withDescriptionAs("tip.desc")
                                .withLastNameAs(JACOBSEN)
                                .withFirstNameAs("Tor Egil")
                                .with(HOME)
                                .get()
                ).get();
    }

    public static User getJactor() {
        return JACTOR;
    }

    public static User getTip() {
        return TIP;
    }
}
