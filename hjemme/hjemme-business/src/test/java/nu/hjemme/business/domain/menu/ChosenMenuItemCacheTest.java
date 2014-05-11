package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.test.MatchBuilder;
import nu.hjemme.test.NotNullBuildMatching;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ChosenMenuItemCacheTest {
    private ChosenMenuItemCache testChosenMenuItemCache;

    @Before
    public void initForTesting() {
        testChosenMenuItemCache = new ChosenMenuItemCache();
    }

    @Test
    public void skalIkkeHaMenuItemTargetCachetPaInstansSomIkkeInneholderCache() {
        assertThat("Ingen chosen menu item skal være cachet", testChosenMenuItemCache.harCacheAv(new MenuItemTarget("somewhere")), is(equalTo(false)));
    }

    @Test
    public void skalIkkeHaMenuItemTargetCacheNarEtAnnetMenuItemTargetErCachet() {
        testChosenMenuItemCache.cache(new MenuItemTarget("somewhere"), new ArrayList<>());
        assertThat("Ingen chosen menu item skal være cachet", testChosenMenuItemCache.harCacheAv(new MenuItemTarget("somewhere else")), is(equalTo(false)));
    }

    @Test
    public void skalHaMenuItemTargetCachetNarNarMenuItemTargetSomBesOmErLiktCache() {
        testChosenMenuItemCache.cache(new MenuItemTarget("somewhere"), new ArrayList<>());
        assertThat("Ingen chosen menu item skal være cachet", testChosenMenuItemCache.harCacheAv(new MenuItemTarget("somewhere")), is(equalTo(true)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void skalCacheListeAvChosenMenuItemsBasertPaMenuItemTarget() {
        assertThat(testChosenMenuItemCache, new NotNullBuildMatching<ChosenMenuItemCache>("Caching basert pa menu item target") {
            @Override
            public MatchBuilder matches(ChosenMenuItemCache typeToTest, MatchBuilder matchBuilder) {
                List<ChosenMenuItem> eiListeAvChosenMenuItems = mock(List.class);
                List<ChosenMenuItem> eiAnnenListeAvChosenMenuItems = mock(List.class);
                MenuItemTarget malForEiListe = new MenuItemTarget("malForEiListe");
                MenuItemTarget malForEiAnnenListe = new MenuItemTarget("malForEiAnnenListe");

                testChosenMenuItemCache.cache(malForEiListe, eiListeAvChosenMenuItems);
                testChosenMenuItemCache.cache(malForEiAnnenListe, eiAnnenListeAvChosenMenuItems);

                return matchBuilder
                        .matches(typeToTest.hentFor(malForEiListe), is(equalTo(eiListeAvChosenMenuItems)), "cache av " + malForEiListe)
                        .matches(typeToTest.hentFor(malForEiAnnenListe), is(equalTo(eiAnnenListeAvChosenMenuItems)), "cache av " + malForEiAnnenListe);
            }
        });

    }
}
