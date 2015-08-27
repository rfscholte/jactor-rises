package nu.hjemme.business.rules;

import nu.hjemme.business.domain.builder.AddressDomainBuilder;
import nu.hjemme.business.domain.builder.DomainBuilder;
import nu.hjemme.business.domain.builder.PersonDomainBuilder;
import nu.hjemme.business.domain.builder.UserDomainBuilder;
import org.junit.rules.ExternalResource;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/** A {@link org.junit.rules.MethodRule} which will ensure that all skipped validations are cleared after each test */
public final class BuildValidations extends ExternalResource {
    private final Set<Build> skipValidations = new HashSet<>();

    private BuildValidations(Build[] builds) {
        super();

        if (builds != null) {
            skipValidations.addAll(asList(builds));
        }
    }

    @Override protected void before() throws Throwable {
        new TestBuildValidator(this);
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

        public boolean shouldSkipBuildOn(Class<?> builderClass) {
            return this.builderClass == builderClass;
        }
    }

    private class TestBuildValidator extends DomainBuilder.BuildValidator {

        private BuildValidations buildValidations;

        private TestBuildValidator(BuildValidations buildValidations) {
            this.buildValidations = buildValidations;
        }

        @Override protected void validate(DomainBuilder<?> domainBuilder) {
            if (shouldNotSkipValidationOn(domainBuilder.getClass())) {
                super.validate(domainBuilder);
            }
        }

        private boolean shouldNotSkipValidationOn(Class<? extends DomainBuilder> domainBuilderClass) {
            for (Build build : buildValidations.skipValidations) {
                if (!build.shouldSkipBuildOn(domainBuilderClass)) {
                    return true;
                }
            }

            return false;
        }
    }
}
