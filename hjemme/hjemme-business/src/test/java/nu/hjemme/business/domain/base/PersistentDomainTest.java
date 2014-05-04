package nu.hjemme.business.domain.base;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PersistentDomainTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void skalFeileHvisEtDomeneInstansieresMedEntitetSomErNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersistentDomain.ENTITETEN_PÅ_DOMENEOBJEKTET_KAN_IKKE_VAERE_NULL);

        new PersistentDomain<>(null);
    }
}
