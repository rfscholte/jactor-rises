package nu.hjemme.business.rules;

import nu.hjemme.business.domain.builder.AddressDomainBuilder;
import nu.hjemme.business.domain.builder.DomainBuilder;
import nu.hjemme.business.domain.builder.PersonDomainBuilder;
import nu.hjemme.business.domain.builder.UserDomainBuilder;
import org.junit.rules.ExternalResource;

import java.util.HashSet;
import java.util.Set;

/**
 * A {@link org.junit.rules.MethodRule} which will ensure that all skipped validations are cleared after each test
 * TODO: #113, remove when builders are validation builder...
 */
public final class BuildValidations extends ExternalResource {
    private final Set<Class> skipValidations = new HashSet<>();

    private BuildValidations(Build[] builds) {
        super();

        if (builds != null) {
            for (Build build : builds) {
                skipValidations.add(build.builderClass);
            }
        }
    }

    @Override protected void before() throws Throwable {
        new TestBuildValidator();
    }

    @Override protected void after() {
        new DomainBuilder.BuildValidator();
    }

    public static BuildValidations skipValidationOn(Build... builds) {
        return new BuildValidations(builds);
    }

    public enum Build {
        ADDRESS(AddressDomainBuilder.class),
        PERSON(PersonDomainBuilder.class),
        USER(UserDomainBuilder.class);

        private final Class<?> builderClass;

        Build(Class builderClass) {
            this.builderClass = builderClass;
        }
    }

    private class TestBuildValidator extends DomainBuilder.BuildValidator {

        @Override protected void validate(DomainBuilder<?> domainBuilder) {
            if (shouldValidate(domainBuilder.getClass())) {
                super.validate(domainBuilder);
            }
        }

        private boolean shouldValidate(Class<? extends DomainBuilder> domainBuilderClass) {
            return !skipValidations.contains(domainBuilderClass);
        }
    }
}
