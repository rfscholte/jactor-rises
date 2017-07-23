package nu.hjemme.business.domain.builder;

import nu.hjemme.business.domain.PersonDomain;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nu.hjemme.business.domain.AddressDomain.anAddress;
import static nu.hjemme.business.domain.PersonDomain.aPerson;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("The Person Domain Builder")
class PersonDomainBuilderTest {

    @Test void willNotBuildPersonDomainWithoutAnAddress() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> aPerson().build());
        Assertions.assertThat(illegalArgumentException.getMessage()).isEqualTo(PersonDomainBuilder.AN_ADDRESS_MUST_BE_PRESENT);
    }

    @DisplayName("should build a person when all requered fields are set")
    @Disabled() // TODO: #113 fix when all builders are validation builders
    @Test void willBuildPersonDomainWhenAllRequiredFieldsAreSet() throws Exception {
        PersonDomain personDomain = aPerson().with(anAddress()).withDescriptionAs("description field will not be validated").build();

        assertThat(personDomain, notNullValue());
    }
}
