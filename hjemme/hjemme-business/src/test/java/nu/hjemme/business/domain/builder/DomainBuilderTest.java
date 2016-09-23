package nu.hjemme.business.domain.builder;

import com.github.jactorrises.matcher.MatchBuilder;
import com.github.jactorrises.matcher.TypeSafeBuildMatcher;
import org.junit.Before;
import org.junit.Test;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DomainBuilderTest {
    private TestDomainBuilder testDomainBuilder;

    @Before public void initForTesting() {
        testDomainBuilder = new TestDomainBuilder();
    }

    @Test public void skalByggeEtDomeneNarBuildMetodeKalles() {
        assertThat("skal bygge domene", testDomainBuilder.build(), is(notNullValue()));
    }

    @Test public void skalValidereDomeneVedByging() {
        assertThat(testDomainBuilder, new TypeSafeBuildMatcher<TestDomainBuilder>("Validate domain when building it") {
            @Override
            public MatchBuilder matches(TestDomainBuilder typeToTest, MatchBuilder matchBuilder) {
                matchBuilder.matches(typeToTest.validated, is(equalTo(false), "before build"));

                typeToTest.build();

                return matchBuilder.matches(typeToTest.validated, is(equalTo(true), "after build"));
            }
        });
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
