package nu.hjemme.client.datatype;

import com.github.jactorrises.matcher.MatchBuilder;
import com.github.jactorrises.matcher.TypeSafeBuildMatcher;
import org.junit.Test;

import static com.github.jactorrises.matcher.EqualsMatcher.hasImplenetedEqualsMethodUsing;
import static com.github.jactorrises.matcher.HashCodeMatcher.hasImplementedHashCodeAccordingTo;
import static com.github.jactorrises.matcher.LabelMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class UserNameTest {

    @Test public void willBeEqualIfOnlyDifferenceIsTheCharacterCase() {
        assertThat(new UserName("jactor"), new TypeSafeBuildMatcher<UserName>("case difference will not cause unequal user names") {
            @Override
            public MatchBuilder matches(UserName jactor, MatchBuilder matchBuilder) {
                UserName jactorUpperCase = new UserName("JACTOR");
                UserName tip = new UserName("tip");

                return matchBuilder
                        .matches(jactor, is(equalTo(jactorUpperCase), "jactor vs. JACTOR"))
                        .matches(jactor, is(not(equalTo(tip)), "jactor vs. tip"));
            }
        });
    }

    @Test public void willProduceEqualHashCodeIfOnlyDifferenceIsTheCharacterCase() {
        assertThat(new UserName("jactor"), new TypeSafeBuildMatcher<UserName>("case difference will not cause unequal hash codes") {
            @Override
            public MatchBuilder matches(UserName jactor, MatchBuilder matchBuilder) {
                UserName jactorUpperCase = new UserName("JACTOR");
                UserName tip = new UserName("tip");

                return matchBuilder
                        .matches(jactor.hashCode(), is(equalTo(jactorUpperCase.hashCode()), "hash code jactor vs. JACTOR"))
                        .matches(jactor.hashCode(), is(not(equalTo(tip.hashCode())), "hash code jactor vs. tip"));
            }
        });
    }

    @Test public void willImplementHashCodeAccordingToTheJavaSpecifications() {
        UserName base = new UserName("someone");
        UserName equal = new UserName("SOMEONE");
        UserName notEqual = new UserName("SOMEONE else");

        assertThat(base, hasImplementedHashCodeAccordingTo(equal, notEqual));
    }

    @Test public void willImplementEqualsAccordingToTheJavaSpecifications() {
        UserName base = new UserName("someone");
        UserName equal = new UserName("SOMEONE");
        UserName notEqual = new UserName("SOMEONE else");

        assertThat(base, hasImplenetedEqualsMethodUsing(equal, notEqual));
    }
}
