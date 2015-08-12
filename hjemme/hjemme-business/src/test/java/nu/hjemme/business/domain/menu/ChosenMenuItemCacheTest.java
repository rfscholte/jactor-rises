package nu.hjemme.business.domain.menu;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.menu.ChosenMenuItem;
import nu.hjemme.test.matcher.MatchBuilder;
import nu.hjemme.test.matcher.TypeSafeBuildMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static nu.hjemme.test.matcher.DescriptionMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
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
        assertThat(testChosenMenuItemCache.isCached(etStedPaHovedmenyen), is(equalTo(false), "chosen item cached"));
    }

    @Test
    public void skalIkkeHaMenuTargetCacheNarEtAnnetMenuTargetErCachet() {
        testChosenMenuItemCache.cache(etStedPaHovedmenyen, new ArrayList<>());
        assertThat(testChosenMenuItemCache.isCached(etAnnetStedPaHovedmenyen), is(equalTo(false), "not chosen menu cached"));
    }

    @Test
    public void skalHaMenuTargetCachetNarNarMenuTargetSomBesOmErLiktCache() {
        testChosenMenuItemCache.cache(etStedPaHovedmenyen, new ArrayList<>());
        assertThat(testChosenMenuItemCache.isCached(etStedPaHovedmenyen), is(equalTo(true), "chosen from menu"));
    }

    @Test
    public void skalCacheListeAvChosenMenuItemsBasertPaMenuTarget() {
        assertThat(testChosenMenuItemCache, new TypeSafeBuildMatcher<ChosenMenuItemCache>("Caching basert pa menu item target") {
            @Override
            public MatchBuilder matches(ChosenMenuItemCache chosenMenuItemCache, MatchBuilder matchBuilder) {
                @SuppressWarnings("unchecked") List<ChosenMenuItem> eiListeAvChosenMenuItems = mock(List.class);
                @SuppressWarnings("unchecked") List<ChosenMenuItem> eiAnnenListeAvChosenMenuItems = mock(List.class);

                testChosenMenuItemCache.cache(etStedPaHovedmenyen, eiListeAvChosenMenuItems);
                testChosenMenuItemCache.cache(etAnnetStedPaHovedmenyen, eiAnnenListeAvChosenMenuItems);

                return matchBuilder
                        .matches(chosenMenuItemCache.retrieveBy(etStedPaHovedmenyen), is(equalTo(eiListeAvChosenMenuItems), "cache av " + etStedPaHovedmenyen))
                        .matches(chosenMenuItemCache.retrieveBy(etAnnetStedPaHovedmenyen), is(equalTo(eiAnnenListeAvChosenMenuItems), "cache av " + etAnnetStedPaHovedmenyen));

            }
        });
    }
}
