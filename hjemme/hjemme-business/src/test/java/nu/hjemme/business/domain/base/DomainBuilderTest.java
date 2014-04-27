package nu.hjemme.business.domain.base;

import nu.hjemme.test.MatchBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** @author Tor Egil Jacobsen */
public class DomainBuilderTest {
    private TestDomainBuilder testDomainBuilder;

    @Before
    public void initForTesting() {
        testDomainBuilder = new TestDomainBuilder();
    }

    @Test
    public void skalByggeEtDomeneNarBuildMetodeKalles() {
        assertThat("skal bygge domene", testDomainBuilder.build(), is(notNullValue()));
    }

    @Test
    public void skalValidereDomeneVedByging() {
        MatchBuilder matchBuilder = new MatchBuilder("Validering av domene ved bygging")
                .matches(testDomainBuilder.validated, is(equalTo(false)), "before build");

        testDomainBuilder.build();

        matchBuilder.matches(testDomainBuilder.validated, is(equalTo(true)), "after build");
        assertTrue(matchBuilder.isMatch());
    }

    private static class TestDomainBuilder extends DomainBuilder<DomainBuilderTest> {
        boolean validated;

        @Override
        protected DomainBuilderTest buildInstance() {
            return new DomainBuilderTest();
        }

        @Override
        protected void validate() {
            validated = true;
        }
    }
}
