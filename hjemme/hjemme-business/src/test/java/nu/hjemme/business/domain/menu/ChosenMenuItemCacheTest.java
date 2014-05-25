package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.ChosenMenuItem;
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
    private MenuTarget etStedPaHovedmenyen;
    private MenuTarget etAnnetStedPaHovedmenyen;

    @Before
    public void initForTesting() {
        testChosenMenuItemCache = new ChosenMenuItemCache();
    }

    @Before
    public void initEtStedPaHovedmenyen() {
        etStedPaHovedmenyen = new MenuTarget(new MenuItemTarget("somewhere"), new Name("main.menu"));
    }

    @Before
    public void initEtAnnetStedPaHovedmenyen() {
        etAnnetStedPaHovedmenyen = new MenuTarget(new MenuItemTarget("somewhere else"), new Name("main.menu"));
    }


    @Test
    public void skalIkkeHaMenuTargetCachetPaInstansSomIkkeInneholderCache() {
        assertThat("Ingen chosen menu item skal være cachet", testChosenMenuItemCache.harCacheAv(etStedPaHovedmenyen), is(equalTo(false)));
    }

    @Test
    public void skalIkkeHaMenuTargetCacheNarEtAnnetMenuTargetErCachet() {
        testChosenMenuItemCache.cache(etStedPaHovedmenyen, new ArrayList<>());
        assertThat("Ingen chosen menu item skal være cachet", testChosenMenuItemCache.harCacheAv(etAnnetStedPaHovedmenyen), is(equalTo(false)));
    }

    @Test
    public void skalHaMenuTargetCachetNarNarMenuTargetSomBesOmErLiktCache() {
        testChosenMenuItemCache.cache(etStedPaHovedmenyen, new ArrayList<>());
        assertThat("Ingen chosen menu item skal være cachet", testChosenMenuItemCache.harCacheAv(etStedPaHovedmenyen), is(equalTo(true)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void skalCacheListeAvChosenMenuItemsBasertPaMenuTarget() {
        assertThat(testChosenMenuItemCache, new NotNullBuildMatching<ChosenMenuItemCache>("Caching basert pa menu item target") {
            @Override
            public MatchBuilder matches(ChosenMenuItemCache chosenMenuItemCache, MatchBuilder matchBuilder) {
                List<ChosenMenuItem> eiListeAvChosenMenuItems = mock(List.class);
                List<ChosenMenuItem> eiAnnenListeAvChosenMenuItems = mock(List.class);

                testChosenMenuItemCache.cache(etStedPaHovedmenyen, eiListeAvChosenMenuItems);
                testChosenMenuItemCache.cache(etAnnetStedPaHovedmenyen, eiAnnenListeAvChosenMenuItems);

                return matchBuilder
                        .matches(chosenMenuItemCache.hentFor(etStedPaHovedmenyen), is(equalTo(eiListeAvChosenMenuItems)), "cache av " + etStedPaHovedmenyen)
                        .matches(chosenMenuItemCache.hentFor(etAnnetStedPaHovedmenyen), is(equalTo(eiAnnenListeAvChosenMenuItems)),
                                "cache av " + etAnnetStedPaHovedmenyen);
            }
        });
    }
}
