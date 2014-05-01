package nu.hjemme.business.persistence;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PersistentEntityTest {
    private PersistentEntity testPersistentEntity;

    @Before
    public void initForTesting() {
        testPersistentEntity = new PersistentEntity();
    }

    @Test
    public void skalSkriveUtKlassenavnSamtIdFraToStringMetoden() {
        testPersistentEntity.setId(101L);
        String string = testPersistentEntity.toString();

        assertThat(string, allOf(containsString("PersistentEntity"), containsString("101")));
    }

    @Test
    public void skalReturnereTrueNarDatabaseIdErSattPaaBeggeInstanserSamtOmDeErLike() {
        testPersistentEntity.setId(101L);
        PersistentEntity other = new PersistentEntity();
        other.setId(101L);

        assertThat("Persistente bønner skal ha lik dbid: " + testPersistentEntity.getId(),
                testPersistentEntity.erIdIkkeNullSamtLikIdPaa(other), is(equalTo(true))
        );
    }
}
