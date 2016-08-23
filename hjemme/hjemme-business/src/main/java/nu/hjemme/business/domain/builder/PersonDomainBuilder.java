package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.AddressDomain;
import nu.hjemme.business.domain.PersonDomain;
import nu.hjemme.persistence.PersistentData;
import nu.hjemme.persistence.client.PersonEntity;
import org.apache.commons.lang.Validate;

public class PersonDomainBuilder extends DomainBuilder<PersonDomain> {
    static final String AN_ADDRESS_MUST_BE_PRESENT = "An address must be present";

    private PersonEntity personEntity = PersistentData.getInstance().provideInstanceFor(PersonEntity.class);

    @Override protected PersonDomain initDomain() {
        return new PersonDomain(personEntity);
    }

    @Override protected void validate() {
        Validate.notNull(personEntity.getAddress(), AN_ADDRESS_MUST_BE_PRESENT);
    }

    public PersonDomainBuilder with(AddressDomain address) {
        personEntity.setAddressEntity(address.getEntity());
        return this;
    }

    public PersonDomainBuilder with(AddressDomainBuilder address) {
        return with(address.build());
    }

    public PersonDomainBuilder withDescriptionAs(String description) {
        personEntity.setDescription(description);
        return this;
    }
}
