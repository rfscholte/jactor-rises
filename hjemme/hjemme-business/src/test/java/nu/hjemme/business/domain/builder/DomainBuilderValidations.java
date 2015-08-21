package nu.hjemme.business.domain.builder;

import org.junit.rules.ExternalResource;

/** A {@link org.junit.rules.MethodRule} which will ensure that all skipped validations are cleared after each test */
public class DomainBuilderValidations extends ExternalResource {
    @Override protected void after() {
        DomainBuilder.clearBuildsNotToValidate();
    }

    public DomainBuilderValidations skipValidationOn(DomainBuilder.Build... builds) {
        DomainBuilder.addSkippedValidationOn(builds);
        return this;
    }

    public static DomainBuilderValidations init() {
        return new DomainBuilderValidations();
    }
}
