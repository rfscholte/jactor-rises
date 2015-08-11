package nu.hjemme.business.domain.base;

import nu.hjemme.persistence.PersistentEntity;
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

        new TestPersistentDomain();
    }

    private class TestPersistentDomain extends PersistentDomain<TestPersistentEntity> {
        private TestPersistentDomain() {
            super(null);
        }
    }

    private class TestPersistentEntity extends PersistentEntity {
    }
}
