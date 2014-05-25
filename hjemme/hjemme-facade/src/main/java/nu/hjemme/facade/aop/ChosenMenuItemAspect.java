package nu.hjemme.facade.aop;

import nu.hjemme.business.domain.menu.ChosenMenuItemCache;
import nu.hjemme.client.datatype.MenuTarget;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * The aspect of caching chosen menu items
 * @author Tor Egil Jacobsen
 */
@Aspect
public class ChosenMenuItemAspect {
    private static ChosenMenuItemCache chosenMenuItemCache;

    public ChosenMenuItemAspect() {
        chosenMenuItemCache = new ChosenMenuItemCache();
    }

    @Pointcut("execution(* nu.hjemme.client.service.MenuFacade.*(..))")
    @SuppressWarnings("unused") // Brukes av spring og aspect j
    public void menuCaching() {
    }

    @Around("menuCaching()")
    @SuppressWarnings("unchecked")
    public Object cacheChosenMenuItemsByTarget(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MenuTarget menuTarget = (MenuTarget) proceedingJoinPoint.getArgs()[0];
        Object listeMedChosenMenuItems;

        if (chosenMenuItemCache.harCacheAv(menuTarget)) {
            listeMedChosenMenuItems = chosenMenuItemCache.hentFor(menuTarget);
        } else {
            listeMedChosenMenuItems = proceedingJoinPoint.proceed();
            chosenMenuItemCache.cache(menuTarget, (java.util.List<nu.hjemme.client.domain.menu.ChosenMenuItem>) listeMedChosenMenuItems);
        }

        return listeMedChosenMenuItems;
    }

    static void cacheMed(ChosenMenuItemCache mockedChosenMenuItemCache) {
        chosenMenuItemCache = mockedChosenMenuItemCache;
    }
}
