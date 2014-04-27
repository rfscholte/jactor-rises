package nu.hjemme.business.persistence;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PersistentBeanTest {
    private PersistentBean testPersistentBean;

    @Before
    public void initForTesting() {
        testPersistentBean = new PersistentBean();
    }

    @Test
    public void skalSkriveUtKlassenavnSamtIdFraToStringMetoden() {
        testPersistentBean.setId(101L);
        String string = testPersistentBean.toString();

        assertThat(string, allOf(containsString("PersistentBean"), containsString("101")));
    }

    @Test
    public void skalReturnereTrueNarDatabaseIdErSattPaaBeggeInstanserSamtOmDeErLike() {
        testPersistentBean.setId(101L);
        PersistentBean other = new PersistentBean();
        other.setId(101L);

        assertThat("Persistente bønner skal ha lik dbid (101)", testPersistentBean.erIdIkkeNullSamtLikIdPaa(other), is(equalTo(true)));
    }
}
