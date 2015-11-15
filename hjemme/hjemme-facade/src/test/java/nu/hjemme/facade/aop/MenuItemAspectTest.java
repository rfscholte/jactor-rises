package nu.hjemme.facade.aop;

import nu.hjemme.client.datatype.MenuItemTarget;
import nu.hjemme.client.datatype.MenuTarget;
import nu.hjemme.client.datatype.Name;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuItemAspectTest {
    private MenuItemAspect testMenuItemAspect;
    private MenuTarget etStedPaHovedmenyen;
    private MenuTarget etAnnetStedPaHovedmenyen;

    @Mock
    private ProceedingJoinPoint mockedProceedingJoinPoint;

    @Before
    public void initForTesting() {
        testMenuItemAspect = new MenuItemAspect();
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
    public void skalHenteListeFraUnderliggendeTjenesteNarIngenErCachet() throws Throwable {
        when(mockedProceedingJoinPoint.getArgs())
                .thenReturn(somObjectArray(etStedPaHovedmenyen))
                .thenReturn(somObjectArray(etAnnetStedPaHovedmenyen));

        testMenuItemAspect.cacheMenuItemsByTarget(mockedProceedingJoinPoint);
        testMenuItemAspect.cacheMenuItemsByTarget(mockedProceedingJoinPoint);

        verify(mockedProceedingJoinPoint, times(2)).proceed();
    }

    private Object[] somObjectArray(MenuTarget menuTarget) {
        return new Object[]{menuTarget};
    }

    @Test
    public void skalReturnereCacheNarSammeArgumentBlirBrukt() throws Throwable {
        when(mockedProceedingJoinPoint.getArgs())
                .thenReturn(somObjectArray(etStedPaHovedmenyen));

        testMenuItemAspect.cacheMenuItemsByTarget(mockedProceedingJoinPoint);
        testMenuItemAspect.cacheMenuItemsByTarget(mockedProceedingJoinPoint);
        testMenuItemAspect.cacheMenuItemsByTarget(mockedProceedingJoinPoint);

        verify(mockedProceedingJoinPoint, times(1)).proceed();
    }
}
