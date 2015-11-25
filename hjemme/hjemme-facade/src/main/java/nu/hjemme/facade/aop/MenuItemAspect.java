package nu.hjemme.facade.aop;

import nu.hjemme.business.domain.menu.MenuItemCache;
import nu.hjemme.client.datatype.MenuTarget;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/** The aspect of caching chosen menu items */
@Aspect
public class MenuItemAspect {
    private static MenuItemCache menuItemCache;

    public MenuItemAspect() {
        menuItemCache = new MenuItemCache();
    }

    @Pointcut("execution(* nu.hjemme.client.facade.MenuFacade.fetchMenuItemBy(..))") @SuppressWarnings("unused") // Brukes av spring og aspectj
    public void menuCaching() {
    }

    @Around("menuCaching()") @SuppressWarnings("unchecked")
    public Object cacheMenuItemsByTarget(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MenuTarget menuTarget = (MenuTarget) proceedingJoinPoint.getArgs()[0];
        Object listWithMenuItems;

        if (menuItemCache.isCached(menuTarget)) {
            listWithMenuItems = menuItemCache.fetchBy(menuTarget);
        } else {
            listWithMenuItems = proceedingJoinPoint.proceed();
            menuItemCache.cache(menuTarget, (java.util.List<nu.hjemme.client.domain.menu.MenuItem>) listWithMenuItems);
        }

        return listWithMenuItems;
    }

    static void cacheMed(MenuItemCache menuItemCacheMock) {
        menuItemCache = menuItemCacheMock;
    }
}
