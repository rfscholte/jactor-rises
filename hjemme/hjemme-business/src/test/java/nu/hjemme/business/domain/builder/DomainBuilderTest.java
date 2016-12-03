package nu.hjemme.business.domain.builder;

import org.junit.Before;
import org.junit.Test;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static com.github.jactorrises.matcher.LambdaBuildMatcher.verify;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DomainBuilderTest {
    private TestDomainBuilder testDomainBuilder;

    @Before public void initForTesting() {
        testDomainBuilder = new TestDomainBuilder();
    }

    @Test public void shouldBuildDomainWhenBuildMethodIsInvoked() {
        assertThat("Should build domain", testDomainBuilder.build(), is(notNullValue()));
    }

    @Test public void shouldValidateDomainWhenBuildMethodIsInvokedg() {
        assertThat(testDomainBuilder, verify("validate domain when building it", (builder, buildMatcher) -> {
                    buildMatcher.matches(builder.validated, is(equalTo(false)), "before build");
                    builder.build();
                    return buildMatcher.matches(builder.validated, is(equalTo(true), "after build"));
                }
        ));
    }

    private class TestDomainBuilder extends DomainBuilder<DomainBuilderTest> {
        boolean validated;

        @Override protected DomainBuilderTest initDomain() {
            return new DomainBuilderTest();
        }

        @Override protected void validate() {
            validated = true;
        }
    }
}
