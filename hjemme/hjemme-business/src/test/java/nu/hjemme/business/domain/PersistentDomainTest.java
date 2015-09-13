package nu.hjemme.business.domain;

import nu.hjemme.persistence.db.DefaultPersistentEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PersistentDomainTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test public void skalFeileHvisEtDomeneInstansieresMedEntitetSomErNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(PersistentDomain.THE_ENTITY_ON_THE_DOMAIN_CANNOT_BE_NULL);

        new TestPersistentDomain();
    }

    private class TestPersistentDomain extends PersistentDomain<DefaultPersistentEntity, Long> {
        private TestPersistentDomain() {
            super(null);
        }
    }
}
